package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.DataSourceService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * DataSource
 * @author feather
 */
@Singleton
class DataSourceController @Inject()(loggingAction: LoggingAction,
                                    service: DataSourceService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[DataSourceRow] = Json.format[DataSourceRow]
  implicit val dtoFormat: Format[DataSourceDTO] = Json.format[DataSourceDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[DataSourceRow] = request.body.validate[DataSourceRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[DataSourceDTO] = request.body.validate[DataSourceDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.code.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[DataSourceRow] = request.body.validate[DataSourceRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[DataSourceDTO] = request.body.validate[DataSourceDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

