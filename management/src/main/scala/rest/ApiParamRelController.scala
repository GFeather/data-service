package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.ApiParamRelService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * ApiParamRel
 * @author feather
 */
@Singleton
class ApiParamRelController @Inject()(loggingAction: LoggingAction,
                                    service: ApiParamRelService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[ApiParamRelRow] = Json.format[ApiParamRelRow]
  implicit val dtoFormat: Format[ApiParamRelDTO] = Json.format[ApiParamRelDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ApiParamRelRow] = request.body.validate[ApiParamRelRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ApiParamRelDTO] = request.body.validate[ApiParamRelDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.id.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ApiParamRelRow] = request.body.validate[ApiParamRelRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ApiParamRelDTO] = request.body.validate[ApiParamRelDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

