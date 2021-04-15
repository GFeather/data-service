package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ParamColumnRelDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ParamColumnRelRow): Future[Int] = db.run {
    ParamColumnRel += resource
  }

  def del(id: Long): Future[Int] = db.run {
    ParamColumnRel.filter(_.id === id).delete
  }

  def update(resource: ParamColumnRelRow): Future[Int] = db.run {
    ParamColumnRel.filter(_.id === resource.id).update(resource)
  }

  def list(dto: ParamColumnRelDTO): Future[Seq[ParamColumnRelRow]] = db.run {
    ParamColumnRel.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ParamColumnRelDTO)(x: ParamColumnRel): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ParamColumnRelDTO)(x: ParamColumnRel): Rep[Option[Boolean]] = {
     List(
       dto.operator.map(x.operator === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

