package org.github.feather.queryServer

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import akka.grpc.scaladsl.{ServerReflection, ServiceHandler}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import org.github.feather.queryServer.service.QueryApiImpl
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import proto._

import scala.concurrent.duration.DurationInt
import scala.util.control.NonFatal

object QueryServer extends App {
  val logger = LoggerFactory.getLogger(getClass)

  val system = ActorSystem(Behaviors.empty, "QueryServer")
  try {
    init(system)
  } catch {
    case NonFatal(e) =>
      logger.error("Terminating due to initialization failure.", e)
      system.terminate()
  }

  def  init(system: ActorSystem[_]) = {

//    AkkaManagement(system).start()

    val grpcInterface =
      system.settings.config.getString("query-server.grpc.interface")
    val grpcPort =
      system.settings.config.getInt("query-server.grpc.port")
    val grpcService = new QueryApiImpl()
    GrpcServer.start(grpcInterface, grpcPort, system, grpcService)
  }

}

object GrpcServer {
  def start(
             interface: String,
             port: Int,
             system: ActorSystem[_],
             queryApi: QueryApi): Unit = {
    implicit val sys: ActorSystem[_] = system
    implicit val ec: ExecutionContext =
      system.executionContext

    val service: HttpRequest => Future[HttpResponse] =
      ServiceHandler.concatOrNotFound(
        QueryApiHandler.partial(queryApi),
        // ServerReflection enabled to support grpcurl without import-path and proto parameters
        ServerReflection.partial(List(QueryApi))
      )

    val bound =
      Http()
        .newServerAt(interface, port)
        .bind(service)
        .map(_.addToCoordinatedShutdown(3.seconds))

    bound.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info(
          "Shopping online at gRPC server {}:{}",
          address.getHostString,
          address.getPort)
      case Failure(ex) =>
        system.log.error("Failed to bind gRPC endpoint, terminating system", ex)
        system.terminate()
    }
  }
}
