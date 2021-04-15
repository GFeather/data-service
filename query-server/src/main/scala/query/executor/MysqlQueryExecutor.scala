package org.github.feather.queryServer
package query.executor

import model._
import query.builder.MysqlQueryBuilder

import com.zaxxer.hikari.HikariDataSource
import io.circe.generic.auto._
import io.circe.syntax._

import java.sql.{Connection, ResultSet, Statement}

class MysqlQueryExecutor(queryDto: QueryDto) extends QueryExecutor {

  private val dataSourceDto: DataSource = queryDto.dataSource
  private val columns: List[Column] = queryDto.column

  private val dataSource = new HikariDataSource();
  dataSource.setUsername(dataSourceDto.user)
  dataSource.setPassword(dataSourceDto.password)
  dataSource.setJdbcUrl(geneJdbcUrl(dataSourceDto))


  def init(): Unit = {

  }

  def pre() = {

  }

  def post() = {

  }

  def query() = {
    val connection: Connection = dataSource.getConnection
    val stmt: Statement = connection.createStatement()
    val rs: ResultSet = stmt.executeQuery(MysqlQueryBuilder * queryDto)
    var resList = List[Map[String, AnyVal]]()

    while (rs.next()) {

      resList = resList :+
        columns
          .groupBy(col => col.name)
          .map(v => (v._1, v._2.head))
          .map(v => (v._1, matchType(rs, v._2)))

    }
    rs.close()
    connection.close()
    Map("res" -> resList).asJson.noSpaces
  }


  def runQuery(): String = {

    pre()
    val str: String = query()
    post()

    str
  }

  def geneJdbcUrl(dataSource: DataSource): String = {

    s"jdbc:mysql://${dataSource.host}:${dataSource.port}/${dataSource.database}?${dataSource.param}"

  }

  def geneDriverClass(dataSource: DataSource): String = {

    "com.mysql.cj.jdbc.Driver"

  }

  def matchType(rs: ResultSet, column: Column): AnyVal = {
    column.dataType match {
      case DataType.Boolean => rs.getBoolean(column.name)
      case DataType.Byte => rs.getByte(column.name)
      case DataType.Short => rs.getShort(column.name)
      case DataType.Int => rs.getInt(column.name)
      case DataType.Long => rs.getLong(column.name)
      case DataType.Float => rs.getFloat(column.name)
      case DataType.Double => rs.getDouble(column.name)
      case DataType.String => rs.getString(column.name)
      case DataType.Date => rs.getDate(column.name).toString
      case DataType.Time => rs.getTime(column.name).toString
      case DataType.DateTime => rs.getTimestamp(column.name).toString
      case DataType.Timestamp => rs.getTimestamp(column.name).toString

      case _ => rs.getString(column.name)
    }
  }
}
