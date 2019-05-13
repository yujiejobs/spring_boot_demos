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


【The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone】  
解决方案(数据库执行)：  
`show variables like '%time_zone%';`  
`set global time_zone='+8:00';`