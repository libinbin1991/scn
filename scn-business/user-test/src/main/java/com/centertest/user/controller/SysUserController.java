package com.centertest.user.controller;


import com.centertest.user.api.ISysUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 作者 lbb E-mail: lbb_it@163.com
 * 用户
 */
@Slf4j
@RestController
public class SysUserController {


    @Resource
    private ISysUserServiceApi serviceApi;

    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/userTest/users/name/{username}")
    public void selectByUsername(@PathVariable String username) {

        System.out.println(username);

        String s = serviceApi.selectUsername(username);


        System.out.println("返回值"+s);
    }


}
