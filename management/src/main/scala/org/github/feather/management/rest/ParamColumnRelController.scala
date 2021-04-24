package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.ParamColumnRelService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * ParamColumnRel
 * @author feather
 */
@Singleton
class ParamColumnRelController @Inject()(loggingAction: LoggingAction,
                                    service: ParamColumnRelService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[ParamColumnRelRow] = Json.format[ParamColumnRelRow]
  implicit val dtoFormat: Format[ParamColumnRelDTO] = Json.format[ParamColumnRelDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ParamColumnRelRow] = request.body.validate[ParamColumnRelRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ParamColumnRelDTO] = request.body.validate[ParamColumnRelDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.id.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ParamColumnRelRow] = request.body.validate[ParamColumnRelRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ParamColumnRelDTO] = request.body.validate[ParamColumnRelDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

