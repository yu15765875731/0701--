package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类。
 * 作用：启动整个 Spring Boot 应用。
 * 说明：@SpringBootApplication 是 Spring Boot 的入口注解，
 * 它集成了 @Configuration、@EnableAutoConfiguration、@ComponentScan，
 * 会自动扫描当前包及其子包下的组件，例如 Controller、Service、Repository 等。
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
