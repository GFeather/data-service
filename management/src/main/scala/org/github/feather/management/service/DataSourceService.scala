package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.DataSourceDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class DataSourceService @Inject()(DataSourceDao: DataSourceDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: DataSourceRow): Future[Int] = {
    val row: DataSourceRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    DataSourceDao.save(row)
  }

  def del(code: String): Future[Int] = {
    DataSourceDao.del(code)
  }

  def update(resource: DataSourceRow): Future[Int] = {
    val row: DataSourceRow = resource.copy(updateTime = Some(Instant.now()))
    DataSourceDao.update(row);
  }

  def list(dto: DataSourceDTO): Future[Seq[DataSourceRow]] = {
    DataSourceDao.list(dto)
  }
}

