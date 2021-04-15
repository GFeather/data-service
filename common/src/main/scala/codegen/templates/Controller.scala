package org.github.feather
package common.codegen.templates

case class Controller(Package: String, Bean: String) {
  def gen() = {

    val template =
      s"""
        |package ${Package}
        |package rest
        |
        |import ${Package}.domain.Tables._
        |import ${Package}.model.Dto._
        |import ${Package}.service.${Bean}Service
        |import play.api.libs.json.Format.GenericFormat
        |import play.api.libs.json.{Format, JsResult, JsValue, Json}
        |import play.api.mvc._
        |
        |import javax.inject._
        |import scala.concurrent.{ExecutionContext, Future}
        |
        |/**
        | * ${Bean}
        | * @author feather
        | */
        |@Singleton
        |class ${Bean}Controller @Inject()(loggingAction: LoggingAction,
        |                                    service: ${Bean}Service,
        |                                    cc: ControllerComponents,
        |                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {
        |
        |  implicit val format: Format[${Bean}Row] = Json.format[${Bean}Row]
        |  implicit val dtoFormat: Format[${Bean}DTO] = Json.format[${Bean}DTO]
        |
        |  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
        |    val value: JsResult[${Bean}Row] = request.body.validate[${Bean}Row]
        |    value.fold(
        |      err => BadRequest("JsonError"),
        |      row => { service.save(row); Ok("ok")}
        |    )
        |  }
        |
        |  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
        |    val value: JsResult[${Bean}DTO] = request.body.validate[${Bean}DTO]
        |    value.fold(
        |      err => BadRequest("JsonError"),
        |      dto => dto.code.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
        |    )
        |  }
        |
        |  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
        |    val value: JsResult[${Bean}Row] = request.body.validate[${Bean}Row]
        |    value.fold(
        |      err => Future(BadRequest("JsonError")),
        |      row => {service.update(row).map(_ => Ok("ok"))}
        |    )
        |  }
        |
        |  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
        |    val value: JsResult[${Bean}DTO] = request.body.validate[${Bean}DTO]
        |    value.fold(
        |      err => Future(BadRequest("JsonError")),
        |      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
        |    )
        |  }
        |
        |}
        |
        |""".stripMargin
    template
  }

}
