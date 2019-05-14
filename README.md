## spring_boot_init
---
项目配置双数据源，请先创建两个数据库test tese1：
分别创建数据表，并手动插入相关数据  
CREATE TABLE `user` (  
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `email` varchar(255) NOT NULL,  
  `pass_word` varchar(255) NOT NULL,  
  `user_name` varchar(255) NOT NULL,  
  PRIMARY KEY (`id`),  
) 

### springboot脚手架
Spring Boot的基础结构共三个文件
* src/main/java  程序开发以及主程序入口
* src/main/resources 配置文件
* src/test/java  测试程序

1、Application.java 建议放到跟目录下面,主要用于做一些框架配置  
2、domain目录主要用于实体（Entity）与数据访问层（Repository）  
3、service 层主要是业务类代码  
4、controller 负责页面访问控制
---
### Thymeleaf 模板
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
### MongoDB  
* MongoDB 是最早热门非关系数据库的之一，使用也比较普遍，一般会用做离线数据分析来使用，放到内网的居多  
基于分布式文件存储的数据库。由C++语言编写。旨在为 WEB 应用提供可扩展的高性能数据存储解决方案。  
MongoDB 是一个高性能，开源，无模式的文档型数据库，是当前 NoSql 数据库中比较热门的一种  

* MongoDB 是一个介于关系数据库和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的。他支持的数据结构非常松散，是类似 json 的 bjson 格式，因此可以存储比较复杂的数据类型。MongoDB 最大的特点是他支持的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。

* 传统的关系数据库一般由数据库（database）、表（table）、记录（record）三个层次概念组成，MongoDB 是由数据库（database）、集合（collection）、文档对象（document）三个层次组成。MongoDB 对于关系型数据库里的表，但是集合中没有列、行和关系概念，这体现了模式自由的特点。

* MongoDB 中的一条记录就是一个文档，是一个数据结构，由字段和值对组成。MongoDB 文档与 JSON 对象类似。字段的值有可能包括其它文档、数组以及文档数组。MongoDB 支持 OS X、Linux 及 Windows 等操作系统，并提供了 Python，PHP，Ruby，Java及 C++ 语言的驱动程序，社区中也提供了对 Erlang 及 .NET 等平台的驱动程序。

* MongoDB 的适合对大量或者无固定格式的数据进行存储，比如：日志、缓存等。对事物支持较弱，不适用复杂的多文档（多表）的级联查询。文中演示 Mongodb 版本为 3.5。

___

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
### 邮件服务
早期的时候我们会使用 JavaMail 相关 api 来写发送邮件的相关代码，后来 Spring 推出了 JavaMailSender 更加简化了邮件发送的过程，在之后 Spring Boot 对此进 行了封装就有了现在的 spring-boot-starter-mail
注意需要开启邮件服务器的 POP3/SMTP/IMAP 服务


* 发送失败  
因为各种原因，总会有邮件发送失败的情况，比如：邮件发送过于频繁、网络异常等。在出现这种情况的时候，我们一般会考虑重新重试发送邮件，会分为以下几个步骤来实现：

1、接收到发送邮件请求，首先记录请求并且入库。  
2、调用邮件发送接口发送邮件，并且将发送结果记录入库。  
3、启动定时系统扫描时间段内，未发送成功并且重试次数小于3次的邮件，进行再次发送  
* 异步发送
很多时候邮件发送并不是我们主业务必须关注的结果，比如通知类、提醒类的业务可以允许延时或者失败。这个时候可以采用异步的方式来发送邮件，加快主交易执行速度，在实际项目中可以采用MQ发送邮件相关参数，监听到消息队列（RabbitMQ来实现）之后启动发送邮件。


---

【The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone】  
解决方案(数据库执行)：  
`show variables like '%time_zone%';`  
`set global time_zone='+8:00';`