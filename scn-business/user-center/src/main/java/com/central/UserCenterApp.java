package com.central;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.central.user.mapper*")
public class UserCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApp.class, args);
    }


}
