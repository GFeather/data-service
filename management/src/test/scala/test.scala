package org.github.feather

object test extends App {

  val a = "a"
  val b = "b"

  private val str: String = new StringContext("{name:", ",id:", "}").s(a, b)
  println(str)

}
