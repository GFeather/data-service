package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ColumnConfigDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: ColumnConfigRow): Future[Int] = db.run {
    ColumnConfig += resource
  }

  def del(id: Long): Future[Int] = db.run {
    ColumnConfig.filter(_.id === id).delete
  }

  def update(resource: ColumnConfigRow): Future[Int] = db.run {
    ColumnConfig.filter(_.id === resource.id).update(resource)
  }

  def list(dto: ColumnConfigDTO): Future[Seq[ColumnConfigRow]] = db.run {
    ColumnConfig.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: ColumnConfigDTO)(x: ColumnConfig): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: ColumnConfigDTO)(x: ColumnConfig): Rep[Option[Boolean]] = {
     List(
       dto.tableCode.map(x.tableCode === _),
       dto.version.map(x.version === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

