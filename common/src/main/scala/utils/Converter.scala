package org.github.feather.common
package utils

import shapeless.{Generic, LabelledGeneric}

object Converter {
  case  class A()
  case class Create(userName: String,lastName: String)
  case class Created(userName: String,lastName: String)

  def convert[T, R](a: T) = {
    case class A(a: String, b: String)
    case class B(a: String, b: Option[String])

    val a = A("a", "b")
    val b = B("a", Some("b"))
    implicit class Convert[A,RA](value: A)(implicit ga: Generic.Aux[A,RA]) {
      def convertTo[B,RB](gb: Generic.Aux[B,RB])(implicit ev: RA =:= RB): B =
        gb.from(ga.to(value))
    }
    implicit class op[A](value: A) {
      def convert(): Option[A] = {
        Some(value)
      }
    }
//    implicit class LabelledConvert[A,RA](value: A)(implicit ga: LabelledGeneric.Aux[A,RA]){
//      def convertTo[B, RB](gb: LabelledGeneric.Aux[B,RB])(implicit ev: RA =:= RB): B =
//        gb.from(ga.to(value))
//    }
     val created = Create("foo","bar").convertTo(Generic[Created])
  }


}



