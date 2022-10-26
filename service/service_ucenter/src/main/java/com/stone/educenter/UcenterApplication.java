package com.stone.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author stonestart
 * @create 2022/9/8 - 19:17
 */
@SpringBootApplication
@ComponentScan({"com.stone"})
@MapperScan("com.stone.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}



