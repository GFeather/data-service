package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.TableConfigDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TableConfigService @Inject()(TableConfigDao: TableConfigDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: TableConfigRow): Future[Int] = {
    val row: TableConfigRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    TableConfigDao.save(row)
  }

  def del(code: String): Future[Int] = {
    TableConfigDao.del(code)
  }

  def update(resource: TableConfigRow): Future[Int] = {
    val row: TableConfigRow = resource.copy(updateTime = Some(Instant.now()))
    TableConfigDao.update(row);
  }

  def list(dto: TableConfigDTO): Future[Seq[TableConfigRow]] = {
    TableConfigDao.list(dto)
  }
}

