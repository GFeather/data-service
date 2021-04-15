package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GatewayInfoDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: GatewayInfoRow): Future[Int] = db.run {
    GatewayInfo += resource
  }

  def del(code: String): Future[Int] = db.run {
    GatewayInfo.filter(_.code === code).delete
  }

  def update(resource: GatewayInfoRow): Future[Int] = db.run {
    GatewayInfo.filter(_.code === resource.code).update(resource)
  }

  def list(dto: GatewayInfoDTO): Future[Seq[GatewayInfoRow]] = db.run {
    GatewayInfo.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: GatewayInfoDTO)(x: GatewayInfo): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: GatewayInfoDTO)(x: GatewayInfo): Rep[Option[Boolean]] = {
     List(
       dto.`type`.map(x.`type` === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

