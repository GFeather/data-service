package org.github.feather.management
package rest

import javax.inject.Inject
import play.api.Logging
import play.api.libs.json.JsValue
import play.api.mvc._
import play.libs.Json

import scala.concurrent.{ExecutionContext, Future}

class LoggingAction @Inject()(playBodyParsers: PlayBodyParsers)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[Request, JsValue]
    with Logging {

  override def parser: BodyParser[JsValue] = playBodyParsers.json

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = {
    logger.trace("Building action")
    logger.trace(request.path)
    logger.trace(request.body.toString)
    block(request)
  }

}
