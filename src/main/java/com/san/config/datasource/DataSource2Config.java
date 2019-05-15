//package com.san.config.datasource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author yujie
// * @projectName spring_boot_init
// * @packageName com.san.config.datasource
// * @className DataSource2Config
// * @description
// * @date 2019/05/14 1:14
// */
//@Configuration
//@MapperScan(basePackages = "com.*.dao", sqlSessionTemplateRef  = "DB_2_SqlSessionTemplate")
//public class DataSource2Config {
//
//    @Bean(name = "DB_2_DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.db2")
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "DB_2_SqlSessionFactory")
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("DB_2_DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name = "DB_2_TransactionManager")
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("DB_2_DataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "DB_2_SqlSessionTemplate")
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("DB_2_SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}