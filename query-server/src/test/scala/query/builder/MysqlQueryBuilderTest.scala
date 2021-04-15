package org.github.feather.sqlServer
package query.builder

import model.{Column, DataSource, Param, ParamGroup, QueryDto}
import query.builder.MysqlQueryBuilder.*

import org.scalatest.funsuite.AnyFunSuite

class MysqlQueryBuilderTest extends AnyFunSuite {

  test("SQL Should right") {

    val columns: List[Column] =
      List(
        Column("c1", 0, 12),
        Column("c4", 2, 12),
        Column("c2", 0, 12),
        Column("c3", 4, 12))

    val params: List[Param] =
      List(
        Param(3, 0, "c4", 11, 12, 5, "c4", "2020-01-01 00:00:00"),
        Param(1, 0, "c1", 0, 12, 3, "c1", "100"),
        Param(3, 0, "c4", 11, 12, 6, "c4", "2020-01-02 00:00:00"),
        Param(2, 1, "c2", 0, 12, 3, "c2", "100"),
        Param(2, 0, "c3", 4, 12, 1, "c3", "test"))

    val groups: List[ParamGroup] =
      List(
        ParamGroup(1, 2),
        ParamGroup(3, 0),
        ParamGroup(2, 1))

    val dataSource = DataSource(1, "localhost", 1, "test", "root", "root", "?t=1", "test")

    val bean: QueryDto = QueryDto(dataSource, params, columns, groups)
    println(MysqlQueryBuilder * bean)
    val res = "select `c1`,`c4`,`c2`,`c3` from test where  ( `c1` >= 100 )  AND   ( `c2` >= 100  OR  `c3` <> 'test' )  OR   ( `c4` between '2020-01-01 00:00:00'  and '2020-01-02 00:00:00' )"
    assert(res.strip().equals((MysqlQueryBuilder * bean).strip()))
  }

}
