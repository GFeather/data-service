package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RemoteApiInfoDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: RemoteApiInfoRow): Future[Int] = db.run {
    RemoteApiInfo += resource
  }

  def del(code: String): Future[Int] = db.run {
    RemoteApiInfo.filter(_.code === code).delete
  }

  def update(resource: RemoteApiInfoRow): Future[Int] = db.run {
    RemoteApiInfo.filter(_.code === resource.code).update(resource)
  }

  def list(dto: RemoteApiInfoDTO): Future[Seq[RemoteApiInfoRow]] = db.run {
    RemoteApiInfo.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: RemoteApiInfoDTO)(x: RemoteApiInfo): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: RemoteApiInfoDTO)(x: RemoteApiInfo): Rep[Option[Boolean]] = {
     List(
       dto.name.map(x.name like _),
       dto.serverCode.map(x.serverCode === _),
       dto.version.map(x.version === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

