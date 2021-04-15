package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TableConfigDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: TableConfigRow): Future[Int] = db.run {
    TableConfig += resource
  }

  def del(code: String): Future[Int] = db.run {
    TableConfig.filter(_.code === code).delete
  }

  def update(resource: TableConfigRow): Future[Int] = db.run {
    TableConfig.filter(_.code === resource.code).update(resource)
  }

  def list(dto: TableConfigDTO): Future[Seq[TableConfigRow]] = db.run {
    TableConfig.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: TableConfigDTO)(x: TableConfig): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: TableConfigDTO)(x: TableConfig): Rep[Option[Boolean]] = {
     List(
       dto.name.map(x.name like _),
       dto.sourceCode.map(x.sourceCode === _),
       dto.version.map(x.version === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

