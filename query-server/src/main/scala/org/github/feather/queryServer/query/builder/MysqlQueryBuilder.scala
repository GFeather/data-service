package org.github.feather.queryServer
package query.builder

import model.{DataType, LogicType, Operator}
import proto._

object MysqlQueryBuilder {

  def * (bean: QueryDto): String = {
    val columns: Seq[Column] = bean.column
    val params: Seq[Param] = bean.param
    val groups: Seq[ParamGroup] = bean.group
    val source: DataSource = bean.dataSource.get
    val sql = s"select ${select(columns)} from ${source.tableName} where ${where(params, groups)}"
    sql
  }

  def select(list: Seq[Column]): String = {
    list.map("`" + _.name + "`").mkString(",")
  }

  def where(params: Seq[Param], groups: Seq[ParamGroup]): String = {

    val paramGroupMap: Map[Int, String] = composeParam(params)

    groups
      .sortWith((a, b) => a.id < b.id)
      .map((v) => s" (${paramGroupMap(v.id)}) ${logicWrap(v.logicType)} ")
      .mkString("")

  }

  def composeParam(list: Seq[Param]): Map[Int, String] = {

    list
      .groupBy(_.group)
      .map(v => (v._1, v._2.sortWith((a, b) => a.operator < b.operator && a.logicType > b.logicType)))
      .map(v => (v._1, v._2.map(operateWrap).mkString("")))

  }

  def operateWrap(param: Param): String = {
    param.operator match {
      case Operator.EQ => s" `${param.column}` = ${processValue(param.dataType, param.value)} ${logicWrap(param)}"
      case Operator.NE => s" `${param.column}` <> ${processValue(param.dataType, param.value)} ${logicWrap(param)}"
      case Operator.GT => s" `${param.column}` > ${processValue(param.dataType, param.value)} ${logicWrap(param)}"
      case Operator.GE => s" `${param.column}` >= ${processValue(param.dataType, param.value)} ${logicWrap(param)}"
      case Operator.B => s" `${param.column}` between ${processValue(param.dataType, param.value)} "
      case Operator.A => s" and ${processValue(param.dataType, param.value)} ${logicWrap(param)}"
      case _ => ""
    }
  }

  def logicWrap(param: Param): String = {
    logicWrap(param.logicType)
  }

  def logicWrap(logicType: Int): String = {
    logicType match {
      case LogicType.NONE => s""
      case LogicType.OR => s" OR "
      case LogicType.AND => s" AND "
      case _ => ""
    }
  }

  def processValue(dataType: Int, value: String): String = {
    dataType match {
      case DataType.String => s"'${value}'"
      case DataType.Date=> s"'${value}'"
      case DataType.Time => s"'${value}'"
      case DataType.DateTime => s"'${value}'"
      case _ => s"${value}"
    }
  }

}
