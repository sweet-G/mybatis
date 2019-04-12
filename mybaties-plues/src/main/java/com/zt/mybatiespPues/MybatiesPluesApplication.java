package com.zt.mybatiespPues;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zt.mapper")
public class MybatiesPluesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatiesPluesApplication.class, args);
    }

}
