package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ParamConfigDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ParamConfigRow): Future[Int] = db.run {
    ParamConfig += resource
  }

  def del(id: Long): Future[Int] = db.run {
    ParamConfig.filter(_.id === id).delete
  }

  def update(resource: ParamConfigRow): Future[Int] = db.run {
    ParamConfig.filter(_.id === resource.id).update(resource)
  }

  def list(dto: ParamConfigDTO): Future[Seq[ParamConfigRow]] = db.run {
    ParamConfig.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ParamConfigDTO)(x: ParamConfig): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ParamConfigDTO)(x: ParamConfig): Rep[Option[Boolean]] = {
     List(
       dto.paramType.map(x.paramType === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

