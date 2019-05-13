## spring_boot_init
####springboot脚手架
Spring Boot的基础结构共三个文件
* src/main/java  程序开发以及主程序入口
* src/main/resources 配置文件
* src/test/java  测试程序

1、Application.java 建议放到跟目录下面,主要用于做一些框架配置  
2、domain目录主要用于实体（Entity）与数据访问层（Repository）  
3、service 层主要是业务类代码  
4、controller 负责页面访问控制
---
#### Thymeleaf 模板
Spring Boot 推荐使用 Thymeleaf 来代替 Jsp  
* Thymeleaf 是一款用于渲染 XML/XHTML/HTML5 内容的模板引擎。类似 JSP，Velocity，FreeMaker 等，
它也可以轻易的与 Spring MVC 等 Web 框架进行集成作为 Web 应用的模板引擎。与其它模板引擎相比
Thymeleaf 最大的特点是能够直接在浏览器中打开并正确显示模板页面，而不需要启动整个 Web 应用  
* 注意，由于 Thymeleaf 使用了 XML DOM 解析器，因此它并不适合于处理大规模的 XML 文件
* URL 在 Web 应用模板中占据着十分重要的地位，需要特别注意的是 Thymeleaf 对于 URL 的处理是通过语法 @{...} 来处理的。  
Thymeleaf 支持绝对路径 URL  
`<a th:href="@{http://www.thymeleaf.org}">Thymeleaf</a>`

---
### Redis 下载地址   
https://github.com/MSOpenTech/redis/releases
* Redis 是目前业界使用最广泛的内存数据存储。相比 Memcached，Redis 支持更丰富的数据结构，  
例如 hashes, lists, sets 等，同时支持数据持久化。除此之外，Redis 还提供一些类数据库的特性，  
比如事务，HA，主从库。可以说 Redis 兼具了缓存系统和数据库的一些特性，因此有着丰富的应用场景  

* Spring Boot 提供了对 Redis 集成的组件包：spring-boot-starter-data-redis  
spring-boot-starter-data-redis依赖于spring-data-redis 和 lettuce 。  
Spring Boot 1.0 默认使用的是 Jedis 客户端，2.0 替换成 Lettuce，但如果你从 Spring Boot 1.5.X 切换过来，  
几乎感受不大差异，这是因为 spring-boot-starter-data-redis 为我们隔离了其中的差异性。

* Lettuce 是一个可伸缩线程安全的 Redis 客户端，多个线程可以共享同一个 RedisConnection，  
它利用优秀 netty NIO 框架来高效地管理多个连接

---
Redis 共享 Session  
Spring Session 提供了一套创建和管理 Servlet HttpSession 的方案。Spring Session 提供了集群 Session（Clustered Sessions）功能，默认采用外置的 Redis 来存储 Session 数据，以此来解决 Session 共享的问题。

---

【The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone】  
解决方案(数据库执行)：  
`show variables like '%time_zone%';`  
`set global time_zone='+8:00';`