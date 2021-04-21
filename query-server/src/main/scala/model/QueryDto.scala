package org.github.feather.queryServer
package model

case class QueryDto(dataSource: DataSource, param: List[Param], column: List[Column], group: List[ParamGroup])

case class DataSource(dbType: Int, host: String, port: Int, database: String, user: String, password: String, param: String, tableName: String)

case class Param(group: Int, logicType: Int, name: String, dataType: Int, length: Int, operator: Int, column: String, value: String)

case class ParamGroup(id: Int, logicType: Int)

case class Column(name: String, dataType: Int, length: Int)

case class Data(status: Int, msg: String, data: String)

