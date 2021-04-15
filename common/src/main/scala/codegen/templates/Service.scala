package org.github.feather
package common.codegen.templates

case class Service(Package: String, Bean: String) {
  def gen() = {

    val template =
      s"""
         |package ${Package}
         |package service
         |
         |import org.github.feather.common.utils.CodeUtil
         |import ${Package}.domain.Tables._
         |import ${Package}.model.Dto._
         |import ${Package}.repository.${Bean}Dao
         |
         |import java.time.Instant
         |import javax.inject.Inject
         |import scala.concurrent.{ExecutionContext, Future}
         |
         |class ${Bean}Service @Inject()(${Bean}Dao: ${Bean}Dao)
         |                                (implicit ec: ExecutionContext) {
         |
         |  def save(resource: ${Bean}Row): Future[Int] = {
         |    val row: ${Bean}Row = resource.copy(code = CodeUtil.simpleUUID(), createTime = Some(Instant.now()))
         |    ${Bean}Dao.save(row)
         |  }
         |
         |  def del(code: String): Future[Int] = {
         |    ${Bean}Dao.del(code)
         |  }
         |
         |  def update(resource: ${Bean}Row): Future[Int] = {
         |    ${Bean}Dao.update(resource);
         |  }
         |
         |  def list(dto: ${Bean}DTO): Future[Seq[${Bean}Row]] = {
         |    ${Bean}Dao.list(dto)
         |  }
         |}
         |
         |""".stripMargin
    template
  }

}
