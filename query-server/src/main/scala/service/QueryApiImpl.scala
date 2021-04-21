package org.github.feather.queryServer
package service

import query.executor.MysqlQueryExecutor
import proto._

import scala.concurrent.Future


object QueryApiImpl extends QueryApi {
  override def query(in: QueryDto): Future[Data] = {

    if (in.dataSource.isEmpty) {
      Future.failed(new Exception("datasource is null"))
    } else {
      val executor = new MysqlQueryExecutor(in)
      Future.successful(Data(200, "success", executor.runQuery()))
    }

  }

}
