package org.github.feather
package common.codegen.templates

case class Repository(Package: String, Bean: String) {
  def gen() = {

    val template =
      s"""
         |package ${Package}
         |package repository
         |
         |import ${Package}.domain.Tables._
         |import ${Package}.model.Dto._
         |import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
         |import slick.jdbc.JdbcProfile
         |
         |import javax.inject.Inject
         |import scala.concurrent.{ExecutionContext, Future}
         |
         |class ${Bean}Dao @Inject() (val dbConfigProvider: DatabaseConfigProvider)
         |                             (implicit ec: ExecutionContext)
         |  extends HasDatabaseConfigProvider[JdbcProfile]{
         |  import profile.api._
         |
         |  def save(resource: ${Bean}Row): Future[Int] = db.run {
         |    ${Bean} += resource
         |  }
         |
         |  def del(code: String): Future[Int] = db.run {
         |    ${Bean}.filter(_.code === code).delete
         |  }
         |
         |  def update(resource: ${Bean}Row): Future[Int] = db.run {
         |    ${Bean}.filter(_.code === resource.code).update(resource)
         |  }
         |
         |  def list(dto: ${Bean}DTO): Future[Seq[${Bean}Row]] = db.run {
         |    ${Bean}.filter(filters(dto)).filter(filterOption(dto)).result
         |  }
         |
         |  def filters(dto: ${Bean}DTO)(x: ${Bean}): Rep[Boolean] = {
         |     List(
         |      dto.id.map(x.id === _),
         |      dto.code.map(x.code === _),
         |    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
         |  }
         |
         |  def filterOption(dto: ${Bean}DTO)(x: ${Bean}): Rep[Option[Boolean]] = {
         |     List(
         |       dto.name.map(x.name like _),
         |       dto.protocol.map(x.protocol === _),
         |    ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(LiteralColumn(Option(true)))
         |  }
         |}
         |
         |""".stripMargin
    template
  }

}
