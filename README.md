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

【The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone】  
解决方案(数据库执行)：  
`show variables like '%time_zone%';`  
`set global time_zone='+8:00';`