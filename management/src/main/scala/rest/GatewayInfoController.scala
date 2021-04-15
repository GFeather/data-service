package org.github.feather.management
package rest

import org.github.feather.management.domain.Tables._
import org.github.feather.management.model.Dto._
import org.github.feather.management.service.GatewayInfoService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{Format, JsResult, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * GatewayInfo
 * @author feather
 */
@Singleton
class GatewayInfoController @Inject()(loggingAction: LoggingAction,
                                    service: GatewayInfoService,
                                    cc: ControllerComponents,
                                    implicit val ec: ExecutionContext) extends AbstractController(cc) {

  implicit val format: Format[GatewayInfoRow] = Json.format[GatewayInfoRow]
  implicit val dtoFormat: Format[GatewayInfoDTO] = Json.format[GatewayInfoDTO]

  def save: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[GatewayInfoRow] = request.body.validate[GatewayInfoRow]
    value.fold(
      err => BadRequest("JsonError"),
      row => { service.save(row); Ok("ok")}
    )
  }

  def del: Action[JsValue] = loggingAction { implicit request: Request[JsValue] =>
    val value: JsResult[GatewayInfoDTO] = request.body.validate[GatewayInfoDTO]
    value.fold(
      err => BadRequest("JsonError"),
      dto => dto.code.map(service.del).map(_ => Ok("")).getOrElse(BadRequest)
    )
  }

  def update: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[GatewayInfoRow] = request.body.validate[GatewayInfoRow]
    value.fold(
      err => Future(BadRequest("JsonError")),
      row => {service.update(row).map(_ => Ok("ok"))}
    )
  }

  def list: Action[JsValue] = loggingAction.async { implicit request: Request[JsValue] =>
    val value: JsResult[GatewayInfoDTO] = request.body.validate[GatewayInfoDTO]
    value.fold(
      err => Future(BadRequest("JsonError")),
      dto => service.list(dto).map(api => Ok(Json.toJson(api)))
    )
  }

}

