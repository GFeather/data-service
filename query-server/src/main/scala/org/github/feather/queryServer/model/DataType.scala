package org.github.feather.queryServer
package model

object DataType {
  val Boolean = 0
  val Byte = 1
  val Short = 2
  val Int = 3
  val Long = 4
  val Float = 5
  val Double = 6
  val String = 7
  val Date = 8
  val Time = 9
  val DateTime = 10
  val Timestamp = 11
}

object LogicType {
  val NONE = 0
  val OR = 1
  val AND = 2
}

object Operator {
  // 等于
  val EQ = 0
  // 不等于
  val NE = 1
  // 大于
  val GT = 2
  // 大于等于
  val GE = 3
  // not null
  val NN = 4
  // between
  val B = 5
  // and
  val A = 6

}
