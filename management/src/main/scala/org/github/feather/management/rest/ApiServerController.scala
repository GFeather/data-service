package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.ApiServerService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * ApiServer
 * @author feather
 */
@Singleton
class ApiServerController @Inject()(loggingAction: LoggingAction,
                                    service: ApiServerService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[ApiServerRow] = Json.format[ApiServerRow]
  implicit val dtoFormat: Format[ApiServerDTO] = Json.format[ApiServerDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ApiServerRow] = request.body.validate[ApiServerRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ApiServerDTO] = request.body.validate[ApiServerDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.code.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ApiServerRow] = request.body.validate[ApiServerRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ApiServerDTO] = request.body.validate[ApiServerDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

