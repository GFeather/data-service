package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ApiServerDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiServerService @Inject()(ApiServerDao: ApiServerDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ApiServerRow): Future[Int] = {
    val row: ApiServerRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    ApiServerDao.save(row)
  }

  def del(code: String): Future[Int] = {
    ApiServerDao.del(code)
  }

  def update(resource: ApiServerRow): Future[Int] = {
    val row: ApiServerRow = resource.copy(updateTime = Some(Instant.now()))
    ApiServerDao.update(row);
  }

  def list(dto: ApiServerDTO): Future[Seq[ApiServerRow]] = {
    ApiServerDao.list(dto)
  }
}

