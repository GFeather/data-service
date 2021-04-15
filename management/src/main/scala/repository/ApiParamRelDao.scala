package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ApiParamRelDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ApiParamRelRow): Future[Int] = db.run {
    ApiParamRel += resource
  }

  def del(id: Long): Future[Int] = db.run {
    ApiParamRel.filter(_.id === id).delete
  }

  def update(resource: ApiParamRelRow): Future[Int] = db.run {
    ApiParamRel.filter(_.id === resource.id).update(resource)
  }

  def list(dto: ApiParamRelDTO): Future[Seq[ApiParamRelRow]] = db.run {
    ApiParamRel.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ApiParamRelDTO)(x: ApiParamRel): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ApiParamRelDTO)(x: ApiParamRel): Rep[Option[Boolean]] = {
     List(
       dto.paramId.map(x.paramId === _),
       dto.remoteCode.map(x.remoteCode === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

