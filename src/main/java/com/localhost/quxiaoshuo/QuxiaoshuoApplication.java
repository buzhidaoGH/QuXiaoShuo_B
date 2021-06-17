package com.localhost.quxiaoshuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//暂时排除mybatis数据库exclude = DataSourceAutoConfiguration.class
@SpringBootApplication
//扫描Mybatis的Mapper,注解配置
@MapperScan(basePackages = "com.localhost.quxiaoshuo.dao")
public class QuxiaoshuoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuxiaoshuoApplication.class, args);
	}

}
