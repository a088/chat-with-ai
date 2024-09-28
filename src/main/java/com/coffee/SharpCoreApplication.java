package com.coffee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@MapperScan("com.coffee.mapper")
public class SharpCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharpCoreApplication.class,args);
    }
}
