x-Lily6X 分布式系统框架

特点：
1. 可轻松快速进入应用开发  通过简单的配置数据库信息，后台所有代码全部自动生成。
2. 接口说明文档自动生成
3. 扩展性良好    核心内容全部独立，扩展时，只需引入即可使用。
4. 严谨的代码风格  严谨的编码习惯
5. 良好的设计思路
6. 安全的服务机制  只需要暴露一个接口，所有服务皆可通过该接口访问。
7. 支持分布式部署

目录介绍：
1. xLily6X-Api      请求处理模块
2. xLily6X-Common   系统核心公共模块
3. xLily6X-Facade   系统公共模块
4. xLily6X-Service  服务模块
5. xLily6X-Web      视图模块

使用说明：
1. 通过 CodeGeneratorUtil 类的 main 方法 可完成后台代码自动生成工作
2. xLily6X-Api war 包，需要tomcat 进行部署
3. xLily6X-Service jar 包 ，可使用java -jar 命令部署
3. 通过nginx 部署 xLily6X-Web