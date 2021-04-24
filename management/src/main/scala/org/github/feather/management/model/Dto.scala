package org.github.feather
package management.model

import java.time.Instant

object Dto {
  case class ApiInfoDTO(id: Option[Long],
                        code: Option[String],
                        name: Option[String],
                        remoteCode: Option[String],
                        path: Option[String],
                        version: Option[Short],
                        createTime: Option[Instant],
                        updateTime: Option[Instant])

  case class ApiParamRelDTO(id: Option[Long],
                            paramId: Option[Long],
                            remoteCode: Option[String],
                            createTime: Option[Instant],
                            updateTime: Option[Instant])

  case class ApiServerDTO(id: Option[Long],
                          code: Option[String],
                          protocol: Option[String],
                          host: Option[String],
                          name: Option[String],
                          createTime: Option[Instant],
                          updateTime: Option[Instant])

  case class ColumnConfigDTO(id: Option[Long],
                             tableCode: Option[String],
                             version: Option[Short],
                             name: Option[String],
                             dataType: Option[Short],
                             length: Option[Short],
                             createTime: Option[Instant],
                             updateTime: Option[Instant])

  case class DataApiInfoDTO(id: Option[Long],
                            code: Option[String],
                            name: Option[String],
                            path: Option[String],
                            tableCode: Option[String],
                            version: Option[Short],
                            createTime: Option[Instant],
                            updateTime: Option[Instant])

  case class DataSourceDTO(id: Option[Long],
                           code: Option[String],
                           dbType: Option[Short],
                           host: Option[String],
                           port: Option[Short],
                           database: Option[String],
                           user: Option[String],
                           password: Option[String],
                           param: Option[String],
                           createTime: Option[Instant],
                           updateTime: Option[Instant],
                           name: Option[String])

  case class GatewayInfoDTO(id: Option[Long],
                            code: Option[String],
                            `type`: Option[Short],
                            path: Option[String],
                            param: Option[String],
                            source: Option[String],
                            createTime: Option[Instant],
                            updateTime: Option[Instant])

  case class ParamColumnRelDTO(id: Option[Long],
                               paramId: Option[Long],
                               columnId: Option[Long],
                               operator: Option[Short],
                               createTime: Option[Instant],
                               updateTime: Option[Instant])

  case class ParamConfigDTO(id: Option[Long],
                            name: Option[String],
                            paramType: Option[Short],
                            dataType: Option[Short],
                            length: Option[Short],
                            required: Option[Boolean],
                            createTime: Option[Instant],
                            updateTime: Option[Int])

  case class RemoteApiInfoDTO(id: Option[Long],
                              code: Option[String],
                              name: Option[String],
                              serverCode: Option[String],
                              path: Option[String],
                              version: Option[Short],
                              createTime: Option[Instant],
                              updateTime: Option[Instant])

  case class TableConfigDTO(id: Option[Long],
                            code: Option[String],
                            sourceCode: Option[String],
                            name: Option[String],
                            version: Option[Short],
                            effective: Option[Boolean],
                            createTime: Option[Instant],
                            updateTime: Option[Instant])


}


