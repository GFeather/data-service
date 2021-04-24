package org.github.feather.queryServer.builder

import proto._

import com.zaxxer.hikari.HikariDataSource
import org.scalatest.funsuite.AnyFunSuite

import java.sql.{Date, ResultSet, Statement, Time, Timestamp}
import java.time.{LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class MysqlQueryTest extends AnyFunSuite {

  test("timezone") {
    println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
  }

  test("time test") {

    val source = new HikariDataSource();
    source.setUsername("root")
    source.setPassword("root")
    source.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai")


    val connection = source.getConnection
    val stmt: Statement = connection.createStatement()
    val rs: ResultSet = stmt.executeQuery("select * from test")
    while (rs.next()) {

      val date: Date = rs.getDate("data_time")
      val time: Time = rs.getTime("data_time")
      val timestamp: Timestamp = rs.getTimestamp("data_time")

      val date1: Date = rs.getDate("create_time")
      val time1: Time = rs.getTime("create_time")
      val timestamp1: Timestamp = rs.getTimestamp("create_time")

      println(s"time ${time.toString} ${time.getTime} | ${time1.toString} ${time1.getTime}")
      println(s"date ${date.toString} ${date.getTime} | ${date1.toString} ${date1.getTime}")
      println(s"timestamp ${timestamp.getTime} | ${timestamp1.getTime}")
      println("--------------")
    }
  }
}
