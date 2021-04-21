package org.github.feather.queryServer
package service

import query.executor.MysqlQueryExecutor

import akka.grpc.GrpcServiceException
import io.grpc.Status
import proto._

import scala.concurrent.Future


class QueryApiImpl extends QueryApi {
  override def query(in: QueryDto): Future[Data] = {

    if (in.dataSource.isEmpty) {
      Future.failed(new GrpcServiceException(Status.INVALID_ARGUMENT.withDescription("dataSource is empty")))
    } else {
      val executor = new MysqlQueryExecutor(in)
      Future.successful(Data(200, "success", executor.runQuery()))
    }

  }

}
