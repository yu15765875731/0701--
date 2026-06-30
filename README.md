# 0701框架

这是一个面向“入职前自学”的 Spring Boot 项目骨架，目标不是做一个复杂电商系统，而是把常见的学习点用最小代码串起来。

## 项目定位

- 适合初学者快速理解 Spring Boot 的分层结构
- 适合把 MVC、依赖注入、事务、AOP、拦截器、Redis、RocketMQ 等概念落到代码里
- 重点不是业务完整，而是“能看懂框架如何协作”

## 目录说明

- src/main/java：业务代码
- src/main/resources：配置文件
- src/main/resources/static：静态资源目录
- src/test：测试目录

## 主要接口说明

### 用户相关接口

- POST /users：新增用户
- GET /users/{id}：根据 ID 查询用户
- GET /users：查询所有用户
- DELETE /users/{id}：删除用户

### Redis 相关接口

- POST /redis/cache/{key}：将值写入 Redis 缓存
- GET /redis/cache/{key}：从 Redis 获取缓存值
- POST /redis/lock/{key}：尝试获取分布式锁
- DELETE /redis/lock/{key}/{requestId}：释放分布式锁

### RocketMQ 相关接口

- POST /mq/send：发送 RocketMQ 消息

### 说明

- Controller 负责接收请求并返回响应
- Service 负责处理业务逻辑
- Repository/Mapper 负责数据库访问
- Interceptor 负责统一做登录校验或日志处理
- AOP 负责统一记录方法调用和执行耗时
- Redis 用于演示缓存和分布式锁思路
- RocketMQ 用于演示生产者 / 消费者异步消息流程

## 适合学习的知识点

1. MVC：Controller、Service、Repository 的分层职责
2. 依赖注入：通过构造器注入服务对象
3. 事务：在服务层使用 @Transactional
4. AOP：记录接口请求日志
5. 拦截器：统一处理请求头、认证逻辑
6. Redis：用于缓存和分布式锁的思想
7. RocketMQ：用于异步消息处理的框架思路

## 后续建议

建议阅读顺序为：

1. 先看 Controller 和 Service
2. 再理解 Repository 和数据库表结构
3. 然后看拦截器和 AOP
4. 最后结合 Redis 和消息队列理解系统扩展方式

## 启动与使用说明

- 前提环境（任选其一或按需准备）：
	- Java 17+ 已安装（环境变量配置正确）。
	- 本地或全局 Maven：如果系统未安装 Maven，可使用项目中已经下载到 `E:\apache-maven-3.9.16` 的本地 Maven，命令示例：
		- `E:\apache-maven-3.9.16\bin\mvn.cmd spring-boot:run` （直接运行）
		- 或 `E:\apache-maven-3.9.16\bin\mvn.cmd -DskipTests package` 然后 `java -jar target/0701-framework-1.0.0.jar`
	- MySQL（可选，项目使用 JPA 自动建表）：创建数据库 `springboot_demo` 或在 `src/main/resources/application.yml` 中修改 `spring.datasource.url` 指向你的数据库。
	- Redis（可选，用于缓存与分布式锁）：推荐使用 Docker 启动：`docker run -d -p 6379:6379 redis:7`
	- RocketMQ（可选，用于消息队列演示）：可使用 Docker Compose 或官方镜像快速启动。本示例 `application.yml` 将 NameServer 指向 `localhost:9876`，实际运行需启动 RocketMQ Broker/NameServer。

- 常用配置位置：
	- 数据库 / Redis / RocketMQ 配置：`src/main/resources/application.yml`

- 常用接口测试示例（启动后访问 `http://localhost:8080`）：
	- 新增用户：
		- `curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d "{\"username\":\"zhangsan\",\"email\":\"a@example.com\"}"`
	- 查询用户：`curl http://localhost:8080/users/1`
	- Redis 缓存写入：`curl -X POST http://localhost:8080/redis/cache/key1 -d 'hello' -H "Content-Type: text/plain"`
	- 发送 RocketMQ 消息：`curl -X POST http://localhost:8080/mq/send -H "Content-Type: text/plain" -d 'order:12345'`

- 运行检查与常见问题：
	- 如果 `mvn` 命令不可用，可以使用仓内的本地 Maven：`E:\apache-maven-3.9.16\bin\mvn.cmd`。
	- 如果 Redis 无法连接，确认本机是否有 Redis 服务在 6379 端口运行，或者使用 Docker 启动。
	- 如果 RocketMQ 无法发送/消费，确认 `spring.rocketmq.name-server` 指向的 NameServer 地址可达。

## 项目当前状态小结

- 本地已在 `E:\apache-maven-3.9.16` 下载并可用 Maven，项目可以使用该 Maven 构建。
- `application.yml` 已补充 RocketMQ 的基础配置（请在实际环境中替换 NameServer 地址）。
- 本地 Redis 检测：未发现 6379 端口上的可用 Redis 服务（请启动 Redis 或使用 Docker）。

如需，我可以接着：

1. 为项目生成 Maven Wrapper（mvnw/mvnw.cmd），方便在没有 Maven 的机器上直接构建；
2. 在项目中加入一个 `docs/STARTUP.md`，包含一步步截图与命令；
3. 帮你用 Docker 快速启动 Redis 和 RocketMQ 的示例 Compose 文件。
