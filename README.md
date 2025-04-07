# Fast-ai-test-back
api接口管理平台。基于大模型，RAG技术提供出入参数Mock,接口自动化测试,编排测试流程等功能。提高接口测试，开发效率。
- 后端：https://github.com/hongchaoqun/fast-ai-test-back
- 前端：https://github.com/hongchaoqun/fast-ai-test

## 🐰 基础项目框架：ruoyi-vue-pro

基础项目框架，基于 [ruoyi-vue-pro](https://github.com/YunaiV/ruoyi-vue-pro) 修改，提供了更多功能和更好的代码规范。

| 版本                                                                  | JDK 8 + Spring Boot 2.7                                                   | JDK 17/21 + Spring Boot 3.2                                                           |
|---------------------------------------------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| 【完整版】[ruoyi-vue-pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) | [`master`](https://gitee.com/zhijiantianya/ruoyi-vue-pro/tree/master/) 分支 | [`master-jdk17`](https://gitee.com/zhijiantianya/ruoyi-vue-pro/tree/master-jdk17/) 分支 |

* 【完整版】：包括系统功能、基础设施、会员中心、数据报表、工作流程、商城系统、微信公众号、CRM、ERP 等功能

![架构图](/.image/common/ruoyi-vue-pro-architecture.png)

### 系统功能

|     | 功能    | 描述                              |
|-----|-------|---------------------------------|
|     | 用户管理  | 用户是系统操作者，该功能主要完成系统用户配置          |
| ⭐️  | 在线用户  | 当前系统中活跃用户状态监控，支持手动踢下线           |
|     | 角色管理  | 角色菜单权限分配、设置角色按机构进行数据范围权限划分      |
|     | 菜单管理  | 配置系统菜单、操作权限、按钮权限标识等，本地缓存提供性能    |
|     | 部门管理  | 配置系统组织机构（公司、部门、小组），树结构展现支持数据权限  |
|     | 岗位管理  | 配置系统用户所属担任职务                    |
| 🚀  | 租户管理  | 配置系统租户，支持 SaaS 场景下的多租户功能        |
| 🚀  | 租户套餐  | 配置租户套餐，自定每个租户的菜单、操作、按钮的权限       |
|     | 字典管理  | 对系统中经常使用的一些较为固定的数据进行维护          |
| 🚀  | 短信管理  | 短信渠道、短息模板、短信日志，对接阿里云、腾讯云等主流短信平台 |
| 🚀  | 邮件管理  | 邮箱账号、邮件模版、邮件发送日志，支持所有邮件平台       |
| 🚀  | 站内信   | 系统内的消息通知，提供站内信模版、站内信消息          |
| 🚀  | 操作日志  | 系统正常操作日志记录和查询，集成 Swagger 生成日志内容 |
| ⭐️  | 登录日志  | 系统登录日志记录查询，包含登录异常               |
| 🚀  | 错误码管理 | 系统所有错误码的管理，可在线修改错误提示，无需重启服务     |
|     | 通知公告  | 系统通知公告信息发布维护                    |
| 🚀  | 敏感词   | 配置系统敏感词，支持标签分组                  |
| 🚀  | 应用管理  | 管理 SSO 单点登录的应用，支持多种 OAuth2 授权方式 |
| 🚀  | 地区管理  | 展示省份、城市、区镇等城市信息，支持 IP 对应城市      |

![功能图](/.image/common/system-feature.png)

### 接口管理

|    | 功能     | 描述           |
|----|--------|--------------|
| 🚀 | 接口文档   | 上传，解析        |
| 🚀 | 项目管理   | 接口项目管理       |
| 🚀 | 目录管理   | 目录管理         |
| 🚀 | api详情  | api接口详情信息    |
| 🚀 | 参数Mock | 大模型Mock出请求参数 |
| 🚀 | 自动化测试  | 按分组批量测试      |

## 🐨 技术栈

### 模块

| 项目                        | 说明                 |
|---------------------------|--------------------|
| `yudao-dependencies`      | Maven 依赖版本管理       |
| `yudao-framework`         | Java 框架拓展          |
| `yudao-server`            | 管理后台 + 用户 APP 的服务端 |
| `yudao-module-system`     | 系统功能的 Module 模块    |
| `yudao-module-ai`         | AI 大模型的 Module 模块  |
| `yudao-module-apiManager` | 接口文档管理 模块          |
| `yudao-module-xflow`      | 编排流程，根据流程编排测试   |                 

### 框架

| 框架                                                                                          | 说明               | 版本             | 学习指南                                                           |
|---------------------------------------------------------------------------------------------|------------------|----------------|----------------------------------------------------------------|
| [Spring Boot](https://spring.io/projects/spring-boot)                                       | 应用开发框架           | 3.4.1          | [文档](https://github.com/YunaiV/SpringBoot-Labs)                |
| [MySQL](https://www.mysql.com/cn/)                                                          | 数据库服务器           | 5.7 / 8.0+     |                                                                |
| [Druid](https://github.com/alibaba/druid)                                                   | JDBC 连接池、监控组件    | 1.2.23         | [文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [MyBatis Plus](https://mp.baomidou.com/)                                                    | MyBatis 增强工具包    | 3.5.7          | [文档](http://www.iocoder.cn/Spring-Boot/MyBatis/?yudao)         |
| [Dynamic Datasource](https://dynamic-datasource.com/)                                       | 动态数据源            | 4.3.1          | [文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [Redis](https://redis.io/)                                                                  | key-value 数据库    | 5.0 / 6.0 /7.0 |                                                                |
| [Redisson](https://github.com/redisson/redisson)                                            | Redis 客户端        | 3.32.0         | [文档](http://www.iocoder.cn/Spring-Boot/Redis/?yudao)           |
| [Spring MVC](https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc) | MVC 框架           | 6.1.10         | [文档](http://www.iocoder.cn/SpringMVC/MVC/?yudao)               |
| [Spring Security](https://github.com/spring-projects/spring-security)                       | Spring 安全框架      | 6.3.1          | [文档](http://www.iocoder.cn/Spring-Boot/Spring-Security/?yudao) |
| [Hibernate Validator](https://github.com/hibernate/hibernate-validator)                     | 参数校验组件           | 8.0.1          | [文档](http://www.iocoder.cn/Spring-Boot/Validation/?yudao)      |
| [Flowable](https://github.com/flowable/flowable-engine)                                     | 工作流引擎            | 7.0.0          | [文档](https://doc.iocoder.cn/bpm/)                              |
| [Quartz](https://github.com/quartz-scheduler)                                               | 任务调度组件           | 2.3.2          | [文档](http://www.iocoder.cn/Spring-Boot/Job/?yudao)             |
| [Springdoc](https://springdoc.org/)                                                         | Swagger 文档       | 2.3.0          | [文档](http://www.iocoder.cn/Spring-Boot/Swagger/?yudao)         |
| [SkyWalking](https://skywalking.apache.org/)                                                | 分布式应用追踪系统        | 9.0.0          | [文档](http://www.iocoder.cn/Spring-Boot/SkyWalking/?yudao)      |
| [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin)                       | Spring Boot 监控平台 | 3.3.2          | [文档](http://www.iocoder.cn/Spring-Boot/Admin/?yudao)           |
| [Jackson](https://github.com/FasterXML/jackson)                                             | JSON 工具库         | 2.17.1         |                                                                |
| [MapStruct](https://mapstruct.org/)                                                         | Java Bean 转换     | 1.6.3          | [文档](http://www.iocoder.cn/Spring-Boot/MapStruct/?yudao)       |
| [Lombok](https://projectlombok.org/)                                                        | 消除冗长的 Java 代码    | 1.18.34        | [文档](http://www.iocoder.cn/Spring-Boot/Lombok/?yudao)          |
| [JUnit](https://junit.org/junit5/)                                                          | Java 单元测试框架      | 5.10.1         | -                                                              |
| [Mockito](https://github.com/mockito/mockito)                                               | Java Mock 框架     | 5.7.0          | -   <br/>                                                           |
| [spring-ai](https://spring.io/projects/spring-ai)                                             | spring ai 框架     |           | - 
## 🐷 演示图

![登录](/.image/login.png)
![项目管理](/.image/project.png)
![目录管理](/.image/dir.png)
![api管理](/.image/api.png)
![批量测试](/.image/batch.png)

### 测试流程编排
开发中...

目前已经实现接口文档管理，请求参数Mock, 响应结果Mock,其他功能开发测试中