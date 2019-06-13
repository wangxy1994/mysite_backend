package com.wangxy.site.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.JwtUtil;

@SpringBootApplication(scanBasePackages = {"com.wangxy.site"})
@MapperScan("com.wangxy.site.*.mapper*")
public class MysiteManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysiteManagerApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }



}
