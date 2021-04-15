package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.ApiInfoDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiInfoService @Inject()(ApiInfoDao: ApiInfoDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: ApiInfoRow): Future[Int] = {
    val row: ApiInfoRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    ApiInfoDao.save(row)
  }

  def del(code: String): Future[Int] = {
    ApiInfoDao.del(code)
  }

  def update(resource: ApiInfoRow): Future[Int] = {
    val row: ApiInfoRow = resource.copy(updateTime = Some(Instant.now()))
    ApiInfoDao.update(row);
  }

  def list(dto: ApiInfoDTO): Future[Seq[ApiInfoRow]] = {
    ApiInfoDao.list(dto)
  }
}

