package com.mlxg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.mlxg.mapper")
@ComponentScan(basePackages = {"com.mlxg","com.n3r.idworker"})
public class BirdApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BirdApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BirdApplication.class, args);
    }

}
