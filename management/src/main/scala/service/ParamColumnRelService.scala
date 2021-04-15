package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ParamColumnRelDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ParamColumnRelService @Inject()(ParamColumnRelDao: ParamColumnRelDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ParamColumnRelRow): Future[Int] = {
    val row: ParamColumnRelRow = resource.copy(createTime = Some(Instant.now()))
    ParamColumnRelDao.save(row)
  }

  def del(id: Long): Future[Int] = {
    ParamColumnRelDao.del(id)
  }

  def update(resource: ParamColumnRelRow): Future[Int] = {
    val row: ParamColumnRelRow = resource.copy(updateTime = Some(Instant.now()))
    ParamColumnRelDao.update(row);
  }

  def list(dto: ParamColumnRelDTO): Future[Seq[ParamColumnRelRow]] = {
    ParamColumnRelDao.list(dto)
  }
}

