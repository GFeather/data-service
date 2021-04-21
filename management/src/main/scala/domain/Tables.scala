package org.github.feather.management
package domain

import java.time.Instant
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
//object Tables extends Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
object Tables {
  val profile: slick.jdbc.JdbcProfile = slick.jdbc.PostgresProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(`ApiInfo`.schema, ApiParamRel.schema, ApiServer.schema, ColumnConfig.schema, DataApiInfo.schema, DataSource.schema, GatewayInfo.schema, ParamColumnRel.schema, ParamConfig.schema, RemoteApiInfo.schema, TableConfig.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table `ApiInfo`
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param remoteCode Database column remote_code SqlType(varchar), Length(16,true), Default(None)
   *  @param path Database column path SqlType(varchar), Length(128,true), Default(None)
   *  @param version Database column version SqlType(int2), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class `ApiInfoRow`(id: Long, code: String, name: Option[String] = None, remoteCode: Option[String] = None, path: Option[String] = None, version: Option[Short] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching `ApiInfoRow` objects using plain SQL queries */
  implicit def `GetResultApiInfoRow`(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Short]], e4: GR[Option[Instant]]): GR[`ApiInfoRow`] = GR{
    prs => import prs._
    `ApiInfoRow`.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[Short], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table api_info . Objects of this class serve as prototypes for rows in queries. */
  class `ApiInfo`(_tableTag: Tag) extends profile.api.Table[`ApiInfoRow`](_tableTag, "api_info ") {
    def * = (id, code, name, remoteCode, path, version, createTime, updateTime) <> (`ApiInfoRow`.tupled, `ApiInfoRow`.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), name, remoteCode, path, version, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> `ApiInfoRow`.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column remote_code SqlType(varchar), Length(16,true), Default(None) */
    val remoteCode: Rep[Option[String]] = column[Option[String]]("remote_code", O.Length(16,varying=true), O.Default(None))
    /** Database column path SqlType(varchar), Length(128,true), Default(None) */
    val path: Rep[Option[String]] = column[Option[String]]("path", O.Length(128,varying=true), O.Default(None))
    /** Database column version SqlType(int2), Default(None) */
    val version: Rep[Option[Short]] = column[Option[Short]]("version", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing RemoteApiInfo(database name api_info _remote_api_info_code_fk) */
    lazy val remoteApiInfoFk = foreignKey("api_info _remote_api_info_code_fk", remoteCode, RemoteApiInfo)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table `ApiInfo` */
  lazy val `ApiInfo` = new TableQuery(tag => new `ApiInfo`(tag))

  /** Entity class storing rows of table ApiParamRel
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param paramId Database column param_id SqlType(int8), Default(None)
   *  @param remoteCode Database column remote_code SqlType(varchar), Length(16,true), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class ApiParamRelRow(id: Long, paramId: Option[Long] = None, remoteCode: Option[String] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching ApiParamRelRow objects using plain SQL queries */
  implicit def GetResultApiParamRelRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[Option[Instant]]): GR[ApiParamRelRow] = GR{
    prs => import prs._
    ApiParamRelRow.tupled((<<[Long], <<?[Long], <<?[String], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table api_param_rel. Objects of this class serve as prototypes for rows in queries. */
  class ApiParamRel(_tableTag: Tag) extends profile.api.Table[ApiParamRelRow](_tableTag, "api_param_rel") {
    def * = (id, paramId, remoteCode, createTime, updateTime) <> (ApiParamRelRow.tupled, ApiParamRelRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), paramId, remoteCode, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> ApiParamRelRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column param_id SqlType(int8), Default(None) */
    val paramId: Rep[Option[Long]] = column[Option[Long]]("param_id", O.Default(None))
    /** Database column remote_code SqlType(varchar), Length(16,true), Default(None) */
    val remoteCode: Rep[Option[String]] = column[Option[String]]("remote_code", O.Length(16,varying=true), O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing ParamConfig (database name api_param_rel_param_config_id_fk) */
    lazy val paramConfigFk = foreignKey("api_param_rel_param_config_id_fk", paramId, ParamConfig)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing RemoteApiInfo(database name api_param_rel_remote_api_info_code_fk) */
    lazy val remoteApiInfoFk = foreignKey("api_param_rel_remote_api_info_code_fk", remoteCode, RemoteApiInfo)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ApiParamRel */
  lazy val ApiParamRel = new TableQuery(tag => new ApiParamRel(tag))

  /** Entity class storing rows of table ApiServer
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param protocol Database column protocol SqlType(varchar), Length(8,true), Default(None)
   *  @param host Database column host SqlType(varchar), Length(644,true), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None)
   *  @param name Database column name SqlType(varchar), Length(128,true), Default(None) */
  case class ApiServerRow(id: Long, code: String, name: Option[String] = None, protocol: Option[String] = None, host: Option[String] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching ApiServerRow objects using plain SQL queries */
  implicit def GetResultApiServerRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Instant]]): GR[ApiServerRow] = GR{
    prs => import prs._
    ApiServerRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table api_server. Objects of this class serve as prototypes for rows in queries. */
  class ApiServer(_tableTag: Tag) extends profile.api.Table[ApiServerRow](_tableTag, "api_server") {
    def * = (id, code, name, protocol, host, createTime, updateTime) <> (ApiServerRow.tupled, ApiServerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), name, protocol, host, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> ApiServerRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column protocol SqlType(varchar), Length(8,true), Default(None) */
    val protocol: Rep[Option[String]] = column[Option[String]]("protocol", O.Length(8,varying=true), O.Default(None))
    /** Database column host SqlType(varchar), Length(644,true), Default(None) */
    val host: Rep[Option[String]] = column[Option[String]]("host", O.Length(644,varying=true), O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))
    /** Database column name SqlType(varchar), Length(128,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(128,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table ApiServer */
  lazy val ApiServer = new TableQuery(tag => new ApiServer(tag))

  /** Entity class storing rows of table ColumnConfig
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param tableCode Database column table_code SqlType(varchar), Length(16,true), Default(None)
   *  @param version Database column version SqlType(int2), Default(None)
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param dataType Database column data_type SqlType(int2), Default(None)
   *  @param length Database column length SqlType(int2), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class ColumnConfigRow(id: Long, tableCode: Option[String] = None, version: Option[Short] = None, name: Option[String] = None, dataType: Option[Short] = None, length: Option[Short] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching ColumnConfigRow objects using plain SQL queries */
  implicit def GetResultColumnConfigRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Short]], e3: GR[Option[Instant]]): GR[ColumnConfigRow] = GR{
    prs => import prs._
    ColumnConfigRow.tupled((<<[Long], <<?[String], <<?[Short], <<?[String], <<?[Short], <<?[Short], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table column_config. Objects of this class serve as prototypes for rows in queries. */
  class ColumnConfig(_tableTag: Tag) extends profile.api.Table[ColumnConfigRow](_tableTag, "column_config") {
    def * = (id, tableCode, version, name, dataType, length, createTime, updateTime) <> (ColumnConfigRow.tupled, ColumnConfigRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), tableCode, version, name, dataType, length, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> ColumnConfigRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column table_code SqlType(varchar), Length(16,true), Default(None) */
    val tableCode: Rep[Option[String]] = column[Option[String]]("table_code", O.Length(16,varying=true), O.Default(None))
    /** Database column version SqlType(int2), Default(None) */
    val version: Rep[Option[Short]] = column[Option[Short]]("version", O.Default(None))
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column data_type SqlType(int2), Default(None) */
    val dataType: Rep[Option[Short]] = column[Option[Short]]("data_type", O.Default(None))
    /** Database column length SqlType(int2), Default(None) */
    val length: Rep[Option[Short]] = column[Option[Short]]("length", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing TableConfig (database name column_config_table_config_code_fk) */
    lazy val tableConfigFk = foreignKey("column_config_table_config_code_fk", tableCode, TableConfig)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ColumnConfig */
  lazy val ColumnConfig = new TableQuery(tag => new ColumnConfig(tag))

  /** Entity class storing rows of table DataApiInfo
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param path Database column path SqlType(varchar), Length(128,true), Default(None)
   *  @param tableCode Database column table_code SqlType(varchar), Length(16,true), Default(None)
   *  @param version Database column version SqlType(int2), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class DataApiInfoRow(id: Long, code: String, name: Option[String] = None, path: Option[String] = None, tableCode: Option[String] = None, version: Option[Short] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching DataApiInfoRow objects using plain SQL queries */
  implicit def GetResultDataApiInfoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Short]], e4: GR[Option[Instant]]): GR[DataApiInfoRow] = GR{
    prs => import prs._
    DataApiInfoRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[Short], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table data_api_info. Objects of this class serve as prototypes for rows in queries. */
  class DataApiInfo(_tableTag: Tag) extends profile.api.Table[DataApiInfoRow](_tableTag, "data_api_info") {
    def * = (id, code, name, path, tableCode, version, createTime, updateTime) <> (DataApiInfoRow.tupled, DataApiInfoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), name, path, tableCode, version, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> DataApiInfoRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column path SqlType(varchar), Length(128,true), Default(None) */
    val path: Rep[Option[String]] = column[Option[String]]("path", O.Length(128,varying=true), O.Default(None))
    /** Database column table_code SqlType(varchar), Length(16,true), Default(None) */
    val tableCode: Rep[Option[String]] = column[Option[String]]("table_code", O.Length(16,varying=true), O.Default(None))
    /** Database column version SqlType(int2), Default(None) */
    val version: Rep[Option[Short]] = column[Option[Short]]("version", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing TableConfig (database name data_api_info_table_config_code_fk) */
    lazy val tableConfigFk = foreignKey("data_api_info_table_config_code_fk", tableCode, TableConfig)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table DataApiInfo*/
  lazy val DataApiInfo= new TableQuery(tag => new DataApiInfo(tag))

  /** Entity class storing rows of table DataSource
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param dbType Database column db_type SqlType(int2), Default(None)
   *  @param host Database column host SqlType(varchar), Length(16,true), Default(None)
   *  @param port Database column port SqlType(int2), Default(None)
   *  @param database Database column database SqlType(varchar), Length(32,true), Default(None)
   *  @param user Database column user SqlType(varchar), Length(32,true), Default(None)
   *  @param password Database column password SqlType(varchar), Length(32,true), Default(None)
   *  @param param Database column param SqlType(varchar), Length(128,true), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None)
   *  @param name Database column name SqlType(varchar), Length(128,true), Default(None) */
  case class DataSourceRow(id: Long, code: String, dbType: Option[Short] = None, host: Option[String] = None, port: Option[Short] = None, database: Option[String] = None, user: Option[String] = None, password: Option[String] = None, param: Option[String] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None, name: Option[String] = None)
  /** GetResult implicit for fetching DataSourceRow objects using plain SQL queries */
  implicit def GetResultDataSourceRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Short]], e3: GR[Option[String]], e4: GR[Option[Instant]]): GR[DataSourceRow] = GR{
    prs => import prs._
    DataSourceRow.tupled((<<[Long], <<[String], <<?[Short], <<?[String], <<?[Short], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Instant], <<?[Instant], <<?[String]))
  }
  /** Table description of table data_source. Objects of this class serve as prototypes for rows in queries. */
  class DataSource(_tableTag: Tag) extends profile.api.Table[DataSourceRow](_tableTag, "data_source") {
    def * = (id, code, dbType, host, port, database, user, password, param, createTime, updateTime, name) <> (DataSourceRow.tupled, DataSourceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), dbType, host, port, database, user, password, param, createTime, updateTime, name)).shaped.<>({r=>import r._; _1.map(_=> DataSourceRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column db_type SqlType(int2), Default(None) */
    val dbType: Rep[Option[Short]] = column[Option[Short]]("db_type", O.Default(None))
    /** Database column host SqlType(varchar), Length(16,true), Default(None) */
    val host: Rep[Option[String]] = column[Option[String]]("host", O.Length(16,varying=true), O.Default(None))
    /** Database column port SqlType(int2), Default(None) */
    val port: Rep[Option[Short]] = column[Option[Short]]("port", O.Default(None))
    /** Database column database SqlType(varchar), Length(32,true), Default(None) */
    val database: Rep[Option[String]] = column[Option[String]]("database", O.Length(32,varying=true), O.Default(None))
    /** Database column user SqlType(varchar), Length(32,true), Default(None) */
    val user: Rep[Option[String]] = column[Option[String]]("user", O.Length(32,varying=true), O.Default(None))
    /** Database column password SqlType(varchar), Length(32,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(32,varying=true), O.Default(None))
    /** Database column param SqlType(varchar), Length(128,true), Default(None) */
    val param: Rep[Option[String]] = column[Option[String]]("param", O.Length(128,varying=true), O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))
    /** Database column name SqlType(varchar), Length(128,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(128,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table DataSource */
  lazy val DataSource = new TableQuery(tag => new DataSource(tag))

  /** Entity class storing rows of table GatewayInfo
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param `type` Database column type SqlType(int2), Default(None)
   *  @param path Database column path SqlType(varchar), Length(128,true), Default(None)
   *  @param param Database column param SqlType(json), Length(2147483647,false), Default(None)
   *  @param source Database column source SqlType(json), Length(2147483647,false), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class GatewayInfoRow(id: Long, code: String, `type`: Option[Short] = None, path: Option[String] = None, param: Option[String] = None, source: Option[String] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching GatewayInfoRow objects using plain SQL queries */
  implicit def GetResultGatewayInfoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Short]], e3: GR[Option[String]], e4: GR[Option[Instant]]): GR[GatewayInfoRow] = GR{
    prs => import prs._
    GatewayInfoRow.tupled((<<[Long], <<[String], <<?[Short], <<?[String], <<?[String], <<?[String], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table gateway_info. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class GatewayInfo(_tableTag: Tag) extends profile.api.Table[GatewayInfoRow](_tableTag, "gateway_info") {
    def * = (id, code, `type`, path, param, source, createTime, updateTime) <> (GatewayInfoRow.tupled, GatewayInfoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), `type`, path, param, source, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> GatewayInfoRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column type SqlType(int2), Default(None)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Option[Short]] = column[Option[Short]]("type", O.Default(None))
    /** Database column path SqlType(varchar), Length(128,true), Default(None) */
    val path: Rep[Option[String]] = column[Option[String]]("path", O.Length(128,varying=true), O.Default(None))
    /** Database column param SqlType(json), Length(2147483647,false), Default(None) */
    val param: Rep[Option[String]] = column[Option[String]]("param", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column source SqlType(json), Length(2147483647,false), Default(None) */
    val source: Rep[Option[String]] = column[Option[String]]("source", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))
  }
  /** Collection-like TableQuery object for table GatewayInfo */
  lazy val GatewayInfo = new TableQuery(tag => new GatewayInfo(tag))

  /** Entity class storing rows of table ParamColumnRel
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param paramId Database column param_id SqlType(int8), Default(None)
   *  @param columnId Database column column_id SqlType(int8), Default(None)
   *  @param operator Database column operator SqlType(int2), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class ParamColumnRelRow(id: Long, paramId: Option[Long] = None, columnId: Option[Long] = None, operator: Option[Short] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching ParamColumnRelRow objects using plain SQL queries */
  implicit def GetResultParamColumnRelRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[Short]], e3: GR[Option[Instant]]): GR[ParamColumnRelRow] = GR{
    prs => import prs._
    ParamColumnRelRow.tupled((<<[Long], <<?[Long], <<?[Long], <<?[Short], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table param_column_rel. Objects of this class serve as prototypes for rows in queries. */
  class ParamColumnRel(_tableTag: Tag) extends profile.api.Table[ParamColumnRelRow](_tableTag, "param_column_rel") {
    def * = (id, paramId, columnId, operator, createTime, updateTime) <> (ParamColumnRelRow.tupled, ParamColumnRelRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), paramId, columnId, operator, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> ParamColumnRelRow.tupled((_1.get, _2, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column param_id SqlType(int8), Default(None) */
    val paramId: Rep[Option[Long]] = column[Option[Long]]("param_id", O.Default(None))
    /** Database column column_id SqlType(int8), Default(None) */
    val columnId: Rep[Option[Long]] = column[Option[Long]]("column_id", O.Default(None))
    /** Database column operator SqlType(int2), Default(None) */
    val operator: Rep[Option[Short]] = column[Option[Short]]("operator", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing ColumnConfig (database name param_column_rel_column_config_id_fk) */
    lazy val columnConfigFk = foreignKey("param_column_rel_column_config_id_fk", columnId, ColumnConfig)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing ParamConfig (database name param_column_rel_param_config_id_fk) */
    lazy val paramConfigFk = foreignKey("param_column_rel_param_config_id_fk", paramId, ParamConfig)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ParamColumnRel */
  lazy val ParamColumnRel = new TableQuery(tag => new ParamColumnRel(tag))

  /** Entity class storing rows of table ParamConfig
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param paramType Database column param_type SqlType(int2), Default(None)
   *  @param dataType Database column data_type SqlType(int2), Default(None)
   *  @param length Database column length SqlType(int2), Default(None)
   *  @param required Database column required SqlType(bool), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(int4), Default(None) */
  case class ParamConfigRow(id: Long, name: Option[String] = None, paramType: Option[Short] = None, dataType: Option[Short] = None, length: Option[Short] = None, required: Option[Boolean] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching ParamConfigRow objects using plain SQL queries */
  implicit def GetResultParamConfigRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Short]], e3: GR[Option[Boolean]], e4: GR[Option[Instant]], e5: GR[Option[Int]]): GR[ParamConfigRow] = GR{
    prs => import prs._
    ParamConfigRow.tupled((<<[Long], <<?[String], <<?[Short], <<?[Short], <<?[Short], <<?[Boolean], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table param_config. Objects of this class serve as prototypes for rows in queries. */
  class ParamConfig(_tableTag: Tag) extends profile.api.Table[ParamConfigRow](_tableTag, "param_config") {
    def * = (id, name, paramType, dataType, length, required, createTime, updateTime) <> (ParamConfigRow.tupled, ParamConfigRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), name, paramType, dataType, length, required, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> ParamConfigRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column param_type SqlType(int2), Default(None) */
    val paramType: Rep[Option[Short]] = column[Option[Short]]("param_type", O.Default(None))
    /** Database column data_type SqlType(int2), Default(None) */
    val dataType: Rep[Option[Short]] = column[Option[Short]]("data_type", O.Default(None))
    /** Database column length SqlType(int2), Default(None) */
    val length: Rep[Option[Short]] = column[Option[Short]]("length", O.Default(None))
    /** Database column required SqlType(bool), Default(None) */
    val required: Rep[Option[Boolean]] = column[Option[Boolean]]("required", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(int4), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))
  }
  /** Collection-like TableQuery object for table ParamConfig */
  lazy val ParamConfig = new TableQuery(tag => new ParamConfig(tag))

  /** Entity class storing rows of table RemoteApiInfo
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param serverCode Database column server_code SqlType(varchar), Length(16,true), Default(None)
   *  @param path Database column path SqlType(varchar), Length(128,true), Default(None)
   *  @param version Database column version SqlType(int2), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class RemoteApiInfoRow(id: Long, code: String, name: Option[String] = None, serverCode: Option[String] = None, path: Option[String] = None, version: Option[Short] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching RemoteApiInfoRow objects using plain SQL queries */
  implicit def GetResultRemoteApiInfoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Short]], e4: GR[Option[Instant]]): GR[RemoteApiInfoRow] = GR{
    prs => import prs._
    RemoteApiInfoRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[Short], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table remote_api_info. Objects of this class serve as prototypes for rows in queries. */
  class RemoteApiInfo(_tableTag: Tag) extends profile.api.Table[RemoteApiInfoRow](_tableTag, "remote_api_info") {
    def * = (id, code, name, serverCode, path, version, createTime, updateTime) <> (RemoteApiInfoRow.tupled, RemoteApiInfoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), name, serverCode, path, version, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> RemoteApiInfoRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column server_code SqlType(varchar), Length(16,true), Default(None) */
    val serverCode: Rep[Option[String]] = column[Option[String]]("server_code", O.Length(16,varying=true), O.Default(None))
    /** Database column path SqlType(varchar), Length(128,true), Default(None) */
    val path: Rep[Option[String]] = column[Option[String]]("path", O.Length(128,varying=true), O.Default(None))
    /** Database column version SqlType(int2), Default(None) */
    val version: Rep[Option[Short]] = column[Option[Short]]("version", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing ApiServer (database name remote_api_info_api_server_code_fk) */
    lazy val apiServerFk = foreignKey("remote_api_info_api_server_code_fk", serverCode, ApiServer)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table RemoteApiInfo*/
  lazy val RemoteApiInfo= new TableQuery(tag => new RemoteApiInfo(tag))

  /** Entity class storing rows of table TableConfig
   *  @param id Database column id SqlType(bigserial), AutoInc
   *  @param code Database column code SqlType(varchar), PrimaryKey, Length(16,true)
   *  @param sourceCode Database column source_code SqlType(varchar), Length(16,true), Default(None)
   *  @param name Database column name SqlType(varchar), Length(32,true), Default(None)
   *  @param version Database column version SqlType(int2), Default(None)
   *  @param effective Database column effective SqlType(bool), Default(None)
   *  @param createTime Database column create_time SqlType(timestamp), Default(None)
   *  @param updateTime Database column update_time SqlType(timestamp), Default(None) */
  case class TableConfigRow(id: Long, code: String, sourceCode: Option[String] = None, name: Option[String] = None, version: Option[Short] = None, effective: Option[Boolean] = None, createTime: Option[Instant] = None, updateTime: Option[Instant] = None)
  /** GetResult implicit for fetching TableConfigRow objects using plain SQL queries */
  implicit def GetResultTableConfigRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Short]], e4: GR[Option[Boolean]], e5: GR[Option[Instant]]): GR[TableConfigRow] = GR{
    prs => import prs._
    TableConfigRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[Short], <<?[Boolean], <<?[Instant], <<?[Instant]))
  }
  /** Table description of table table_config. Objects of this class serve as prototypes for rows in queries. */
  class TableConfig(_tableTag: Tag) extends profile.api.Table[TableConfigRow](_tableTag, "table_config") {
    def * = (id, code, sourceCode, name, version, effective, createTime, updateTime) <> (TableConfigRow.tupled, TableConfigRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), sourceCode, name, version, effective, createTime, updateTime)).shaped.<>({r=>import r._; _1.map(_=> TableConfigRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc */
    val id: Rep[Long] = column[Long]("id", O.AutoInc)
    /** Database column code SqlType(varchar), PrimaryKey, Length(16,true) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(16,varying=true))
    /** Database column source_code SqlType(varchar), Length(16,true), Default(None) */
    val sourceCode: Rep[Option[String]] = column[Option[String]]("source_code", O.Length(16,varying=true), O.Default(None))
    /** Database column name SqlType(varchar), Length(32,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(32,varying=true), O.Default(None))
    /** Database column version SqlType(int2), Default(None) */
    val version: Rep[Option[Short]] = column[Option[Short]]("version", O.Default(None))
    /** Database column effective SqlType(bool), Default(None) */
    val effective: Rep[Option[Boolean]] = column[Option[Boolean]]("effective", O.Default(None))
    /** Database column create_time SqlType(timestamp), Default(None) */
    val createTime: Rep[Option[Instant]] = column[Option[Instant]]("create_time", O.Default(None))
    /** Database column update_time SqlType(timestamp), Default(None) */
    val updateTime: Rep[Option[Instant]] = column[Option[Instant]]("update_time", O.Default(None))

    /** Foreign key referencing DataSource (database name table_config_data_source_code_fk) */
    lazy val dataSourceFk = foreignKey("table_config_data_source_code_fk", sourceCode, DataSource)(r => Rep.Some(r.code), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table TableConfig */
  lazy val TableConfig = new TableQuery(tag => new TableConfig(tag))
}
