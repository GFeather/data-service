package org.github.feather.queryServer
package model

object JDBCField {
  // 等于
  val USERNAME = "username"
  // 不等于
  val PASSWORD = "password"
  // 大于
  val JDBC_URL = "jdbcUrl"
  // 大于等于
  val DRIVER_CLASS = "driverClassName"
  // not null
  val MIN = "minIdle"
  // between
  val INITIAL = "initialSize"
  // and
  val MAX = "maxActive"
  // and
  val MAX_WAIT = "maxWait"
}

object DruidJDBCField {
  // 等于
  val USERNAME = "username"
  // 不等于
  val PASSWORD = "password"
  // 大于
  val JDBC_URL = "jdbcUrl"
  // 大于等于
  val DRIVER_CLASS = "driverClassName"
  // not null
  val MIN = "minIdle"
  // between
  val INITIAL = "initialSize"
  // and
  val MAX = "maxActive"
  // and
  val MAX_WAIT = "maxWait"
}

object HikariJDBCField {
  // 等于
  val USERNAME = "username"
  // 不等于
  val PASSWORD = "password"
  // 大于
  val JDBC_URL = "jdbcUrl"
  // 大于等于
  val DRIVER_CLASS = "dataSourceClassName"
  // not null
  val MIN = "minimumIdle"
  // between
  val INITIAL = "initialSize"
  // and
  val MAX = "maximumPoolSize"
  // and
  val KEEP_ALIVE_TIME = "keepaliveTime"
}
