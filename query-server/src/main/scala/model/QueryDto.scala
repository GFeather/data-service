package org.github.feather.sqlServer
package model

case class QueryDto(dataSource: DataSource, param: List[Param], column: List[Column], group: List[ParamGroup])

case class DataSource(dbType: Int, host: String, port: Int, database: String, user: String, password: String, param: String, tableName: String)

case class Param(group: Int, logicType: Int, name: String, dataType: Int, length: Int, operator: Int, column: String, value: String)

case class ParamGroup(id: Int, logicType: Int)

case class Column(name: String, dataType: Int, length: Int)

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