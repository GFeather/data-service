package org.github.feather.management
package service

import org.github.feather.common.utils.CodeUtil
import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.repository.RemoteApiInfoDao

import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RemoteApiInfoService @Inject()(RemoteApiInfoDao: RemoteApiInfoDao)
                                (implicit ec: ExecutionContext) {

  def save(resource: RemoteApiInfoRow): Future[Int] = {
    val row: RemoteApiInfoRow = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
    RemoteApiInfoDao.save(row)
  }

  def del(code: String): Future[Int] = {
    RemoteApiInfoDao.del(code)
  }

  def update(resource: RemoteApiInfoRow): Future[Int] = {
    val row: RemoteApiInfoRow = resource.copy(updateTime = Some(Instant.now()))
    RemoteApiInfoDao.update(row);
  }

  def list(dto: RemoteApiInfoDTO): Future[Seq[RemoteApiInfoRow]] = {
    RemoteApiInfoDao.list(dto)
  }
}

