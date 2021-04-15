package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.DataApiInfoDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class DataApiInfoService @Inject()(DataApiInfoDao: DataApiInfoDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: DataApiInfoRow): Future[Int] = {
    val row: DataApiInfoRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    DataApiInfoDao.save(row)
  }

  def del(code: String): Future[Int] = {
    DataApiInfoDao.del(code)
  }

  def update(resource: DataApiInfoRow): Future[Int] = {
    val row: DataApiInfoRow = resource.copy(updateTime = Some(Instant.now()))
    DataApiInfoDao.update(row);
  }

  def list(dto: DataApiInfoDTO): Future[Seq[DataApiInfoRow]] = {
    DataApiInfoDao.list(dto)
  }
}

