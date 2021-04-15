package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.GatewayInfoDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GatewayInfoService @Inject()(GatewayInfoDao: GatewayInfoDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: GatewayInfoRow): Future[Int] = {
    val row: GatewayInfoRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    GatewayInfoDao.save(row)
  }

  def del(code: String): Future[Int] = {
    GatewayInfoDao.del(code)
  }

  def update(resource: GatewayInfoRow): Future[Int] = {
    val row: GatewayInfoRow = resource.copy(updateTime = Some(Instant.now()))
    GatewayInfoDao.update(row);
  }

  def list(dto: GatewayInfoDTO): Future[Seq[GatewayInfoRow]] = {
    GatewayInfoDao.list(dto)
  }
}

