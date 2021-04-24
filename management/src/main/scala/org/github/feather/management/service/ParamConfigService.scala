package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ParamConfigDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ParamConfigService @Inject()(ParamConfigDao: ParamConfigDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ParamConfigRow): Future[Int] = {
    val row: ParamConfigRow = resource.copy(createTime = Some(Instant.now()))
    ParamConfigDao.save(row)
  }

  def del(id: Long): Future[Int] = {
    ParamConfigDao.del(id)
  }

  def update(resource: ParamConfigRow): Future[Int] = {
    val row: ParamConfigRow = resource.copy(updateTime = Some(Instant.now()))
    ParamConfigDao.update(row);
  }

  def list(dto: ParamConfigDTO): Future[Seq[ParamConfigRow]] = {
    ParamConfigDao.list(dto)
  }
}

