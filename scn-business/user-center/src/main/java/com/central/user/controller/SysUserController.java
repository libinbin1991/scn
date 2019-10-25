package com.central.user.controller;

import com.central.common.model.SysUser;
import com.central.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private ISysUserService iSysUserService;


    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/users/name/{username}")
    @Cacheable(value = "user", key = "#username")
    public SysUser selectByUsername(@PathVariable String username) {
        return iSysUserService.selectByUsername(username);
    }
}
