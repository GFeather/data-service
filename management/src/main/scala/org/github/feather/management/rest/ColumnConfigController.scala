package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.ColumnConfigService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * ColumnConfig
 * @author feather
 */
@Singleton
class ColumnConfigController @Inject()(loggingAction: LoggingAction,
                                    service: ColumnConfigService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[ColumnConfigRow] = Json.format[ColumnConfigRow]
  implicit val dtoFormat: Format[ColumnConfigDTO] = Json.format[ColumnConfigDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ColumnConfigRow] = request.body.validate[ColumnConfigRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[ColumnConfigDTO] = request.body.validate[ColumnConfigDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.id.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ColumnConfigRow] = request.body.validate[ColumnConfigRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[ColumnConfigDTO] = request.body.validate[ColumnConfigDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

