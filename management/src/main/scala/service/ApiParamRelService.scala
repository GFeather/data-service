package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ApiParamRelDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiParamRelService @Inject()(ApiParamRelDao: ApiParamRelDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ApiParamRelRow): Future[Int] = {
    val row: ApiParamRelRow = resource.copy(createTime = Some(Instant.now()))
    ApiParamRelDao.save(row)
  }

  def del(id: Long): Future[Int] = {
    ApiParamRelDao.del(id)
  }

  def update(resource: ApiParamRelRow): Future[Int] = {
    val row: ApiParamRelRow = resource.copy(updateTime = Some(Instant.now()))
    ApiParamRelDao.update(row);
  }

  def list(dto: ApiParamRelDTO): Future[Seq[ApiParamRelRow]] = {
    ApiParamRelDao.list(dto)
  }
}

