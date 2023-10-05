package com.husky.hqMovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.husky.hqMovie.mapper")
@SpringBootApplication
public class HqMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(HqMovieApplication.class, args);
    }

}
