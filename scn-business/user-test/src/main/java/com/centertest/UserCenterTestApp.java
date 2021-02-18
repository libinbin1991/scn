package com.centertest;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  * @author 作者 lbb E-mail: lbb_it@163.com
 */


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UserCenterTestApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterTestApp.class, args);
    }


}
