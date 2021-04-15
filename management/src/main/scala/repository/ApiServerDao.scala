package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiServerDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ApiServerRow): Future[Int] = db.run {
    ApiServer += resource
  }

  def del(code: String): Future[Int] = db.run {
    ApiServer.filter(_.code === code).delete
  }

  def update(resource: ApiServerRow): Future[Int] = db.run {
    ApiServer.filter(_.code === resource.code).update(resource)
  }

  def list(dto: ApiServerDTO): Future[Seq[ApiServerRow]] = db.run {
    ApiServer.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ApiServerDTO)(x: ApiServer): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ApiServerDTO)(x: ApiServer): Rep[Option[Boolean]] = {
     List(
       dto.name.map(x.name like _),
       dto.protocol.map(x.protocol === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

