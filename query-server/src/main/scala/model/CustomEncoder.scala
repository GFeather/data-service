package org.github.feather.queryServer
package model

import io.circe.{Decoder, Encoder, HCursor}
import io.circe.syntax._
import scala.{Boolean, Int, _}

object CustomEncoder {
  implicit val decodeAny: Encoder[Any] = Encoder.instance {
    case int: Int => int.asJson
    case boolean:Boolean => boolean.asJson
    case byte:Byte => byte.asJson
    case short:Short => short.asJson
    case long:Long => long.asJson
    case float:Float => float.asJson
    case double:Double => double.asJson
    case string:String => string.asJson
  }

}


