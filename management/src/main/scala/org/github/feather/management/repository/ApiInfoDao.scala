package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiInfoDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ApiInfoRow): Future[Int] = db.run {
    ApiInfo += resource
  }

  def del(code: String): Future[Int] = db.run {
    ApiInfo.filter(_.code === code).delete
  }

  def update(resource: ApiInfoRow): Future[Int] = db.run {
    ApiInfo.filter(_.code === resource.code).update(resource)
  }

  def list(dto: ApiInfoDTO): Future[Seq[ApiInfoRow]] = db.run {
    ApiInfo.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ApiInfoDTO)(x: ApiInfo): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ApiInfoDTO)(x: ApiInfo): Rep[Option[Boolean]] = {
     List(
       dto.name.map(x.name like _),
       dto.remoteCode.map(x.remoteCode === _),
       dto.version.map(x.version === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

