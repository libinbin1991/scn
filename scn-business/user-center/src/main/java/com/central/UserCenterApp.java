package com.central;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  * @author 作者 lbb E-mail: lbb_it@163.com
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.central.user.mapper*")
@EnableFeignClients
public class UserCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApp.class, args);
    }


}
