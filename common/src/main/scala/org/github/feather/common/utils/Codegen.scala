package org.github.feather
package common.utils

import common.codegen.templates.Controller

import java.io.BufferedWriter
import java.nio.file.{Files, Path, Paths}

object Codegen extends App {
  //  slick.codegen.SourceCodeGenerator.main(
  //    Array("slick.jdbc.PostgresProfile",
  //      "org.postgresql.Driver",
  //      "jdbc:postgresql://localhost/postgres?characterEncoding=utf8&serverTimezone=UTC",
  //      "./org.github.feather.management/src/main/scala/org.github.feather.management/domain",
  //      "org.github.feather.management.domain",
  //      "postgres",
  //      "123456")
  //  )
  private val list = List("ApiServer", "ColumnConfig", "ApiInfo", "ApiParamRel", "DataApiInfo", "DataSource", "GatewayInfo", "ParamColumnRel", "ParamConfig", "RemoteApiInfo", "TableConfig")
  val root = "D:\\\\Dev\\\\Code\\\\Scala\\\\api-service\\\\management\\\\src\\\\main\\\\scala\\\\management\\"
  for (bean <- list) {
    val path: Path = Paths.get(s"${root}\\rest\\${bean}Controller.scala")
    if (!Files.exists(path)) {
      Files.createFile(path)
      val writer: BufferedWriter = Files.newBufferedWriter(path)
      val str: String = Controller("org.github.feather.management", bean).gen()
      writer.write(str)
      writer.close()
    } else {
      Files.delete(path)
    }
  }
}
