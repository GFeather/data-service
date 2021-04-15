package org.github.feather.management
package repository

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class DataSourceDao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
                             (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def save(resource: DataSourceRow): Future[Int] = db.run {
    DataSource += resource
  }

  def del(code: String): Future[Int] = db.run {
    DataSource.filter(_.code === code).delete
  }

  def update(resource: DataSourceRow): Future[Int] = db.run {
    DataSource.filter(_.code === resource.code).update(resource)
  }

  def list(dto: DataSourceDTO): Future[Seq[DataSourceRow]] = db.run {
    DataSource.filter(filters(dto)).filter(filterOption(dto)).result
  }

  def filters(dto: DataSourceDTO)(x: DataSource): Rep[Boolean] = {
     List(
      dto.id.map(x.id === _),
      dto.code.map(x.code === _),

    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
  }

  def filterOption(dto: DataSourceDTO)(x: DataSource): Rep[Option[Boolean]] = {
     List(
       dto.name.map(x.name like _),
       dto.dbType.map(x.dbType === _),
    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
  }
}

