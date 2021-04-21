package org.github.feather.queryServer
package query.executor

import model.DataType
import query.builder.MysqlQueryBuilder

import com.zaxxer.hikari.HikariDataSource
import io.circe.syntax._
import proto._

import java.sql.{Connection, ResultSet, Statement}

class MysqlQueryExecutor(queryDto: QueryDto) extends QueryExecutor {

  private val dataSourceDto: DataSource = queryDto.dataSource.get
  private val columns: Seq[Column] = queryDto.column

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
    var resList = List[Map[String, Any]]()

    while (rs.next()) {

      resList = resList :+
        columns
          .groupBy(col => col.name)
          .map(v => (v._1, v._2.head))
          .map(v => (v._1, matchType(rs, v._2)))

    }

    rs.close()
    connection.close()

    import model.CustomEncoder.decodeAny
    resList.asJson.noSpaces
  }

  def close() = {
    dataSource.close()
  }


  def runQuery(): String = {

    pre()
    val str: String = query()
    post()
    close()

    str
  }

  def geneJdbcUrl(dataSource: DataSource): String = {

    s"jdbc:mysql://${dataSource.host}:${dataSource.port}/${dataSource.database}?${dataSource.param}"

  }

  def geneDriverClass(dataSource: DataSource): String = {

    "com.mysql.cj.jdbc.Driver"

  }

  def matchType(rs: ResultSet, column: Column): Any = {
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
