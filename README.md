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
### mybatis 
    使用手册： http://www.mybatis.org/mybatis-3/zh/java-api.html
    
    @Select 是查询类的注解，所有的查询均使用这个
    @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
    @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
    @Update 负责修改，也可以直接传入对象
    @delete 负责删除
    
    注意，使用#符号和$符号的不同
    # 预编译（prepared statement），防止SQL注入攻击
    $ 无预编译
    如下：
    预编译：select * from table where id = '1';（将入参转为字符串类型）
    非预编译：select * from table where id = 1;（直接注入）

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

### Redis 共享 Session   
Spring Session 提供了一套创建和管理 Servlet HttpSession 的方案。Spring Session 提供了集群 Session（Clustered Sessions）功能，默认采用外置的 Redis 来存储 Session 数据，以此来解决 Session 共享的问题。

---
### RabbitMQ    高可用性、高性能、灵活性
* 即一个消息队列，主要是用来实现应用程序的异步和解耦，同时也能起到消息缓冲，消息分发的作用  
消息中间件在互联网公司的使用中越来越多，刚才还看到新闻阿里将 RocketMQ 捐献给了 Apache，当然了今天的主角还  
是讲 RabbitMQ。消息中间件最主要的作用是解耦，中间件最标准的用法是生产者生产消息传送到队列，消费者从队列中  
拿取消息并处理，生产者不用关心是谁来消费，消费者不用关心谁在生产消息，从而达到解耦的目的。在分布式的系统中  
，消息队列也会被用在很多其它的方面，比如：分布式事务的支持，RPC 的调用等等  

RabbitMQ 介绍
* RabbitMQ 是实现 AMQP（高级消息队列协议）的消息中间件的一种，最初起源于金融系统，用于在分布式系统中存储转发消息，  
在易用性、扩展性、高可用性等方面表现不俗。 RabbitMQ 主要是为了实现系统之间的双向解耦而实现的。  
当生产者大量产生数据时，消费者无法快速消费，那么需要一个中间层。保存这个数据。

* AMQP，即 Advanced Message Queuing Protocol，高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。   
消息中间件主要用于组件之间的解耦，消息的发送者无需知道消息使用者的存在，反之亦然。AMQP 的主要特征是面向消息、队列、  
路由（包括点对点和发布/订阅）、可靠性、安全。

* RabbitMQ 是一个开源的 AMQP 实现，服务器端用Erlang语言编写，支持多种客户端，如：Python、Ruby、.NET、Java、JMS、C、  
PHP、ActionScript、XMPP、STOMP 等，支持 AJAX。用于在分布式系统中存储转发消息，在易用性、扩展性、高可用性等方面表 现不俗。

* 相关概念
通常我们谈到队列服务, 会有三个概念： 发消息者、队列、收消息者，RabbitMQ 在这个基本概念之上, 多做了一层抽象, 在发消息者和 队列之间, 加入了交换器 (Exchange).   
这样发消息者和队列就没有直接联系, 转而变成发消息者把消息给交换器, 交换器根据调度策略再把消息再给队列。  
一对多发送:一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中  
多对多发送:和一对多一样，接收端仍然会均匀接收到消息

[详情可见]http://www.ityouknow.com/springboot/2016/11/30/spring-boot-rabbitMQ.html

---

【The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone】  
解决方案(数据库执行)：  
`show variables like '%time_zone%';`  
`set global time_zone='+8:00';`