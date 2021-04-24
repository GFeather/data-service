package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ColumnConfigDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ColumnConfigService @Inject()(ColumnConfigDao: ColumnConfigDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ColumnConfigRow): Future[Int] = {
    val row: ColumnConfigRow = resource.copy(createTime = Some(Instant.now()))
    ColumnConfigDao.save(row)
  }

  def del(id: Long): Future[Int] = {
    ColumnConfigDao.del(id)
  }

  def update(resource: ColumnConfigRow): Future[Int] = {
    val row: ColumnConfigRow = resource.copy(updateTime = Some(Instant.now()))
    ColumnConfigDao.update(row);
  }

  def list(dto: ColumnConfigDTO): Future[Seq[ColumnConfigRow]] = {
    ColumnConfigDao.list(dto)
  }
}

