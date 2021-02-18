package com.central.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.central.common.model.SysUser;
import com.central.user.mapper.SysUserMapper;
import com.central.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

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


    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/users/name/{username}")
    public String selectByUsername(@PathVariable String username) {






        return username;
    }


    /**
     * 查询用户实体对象SysUser
     */
    @PostMapping(value = "/api/getByUsername")
    @Cacheable(value = "user", key = "#username")
    public SysUser getByUsername(String username) {
        log.info("-------------RPC调用开始------------------");
        System.out.println("RPC调用开始---------");
        return iSysUserService.selectByUsername(username);
    }




    /**
     * 查询用户实体对象SysUser
     */
    @PostMapping(value = "/api/crud")
    public void getByUsername() {
            SysUser sysUser  = new SysUser();

        sysUser.setUsername("李白");
        sysUser.setNickname("刺客1");
        sysUser.setPassword("123468790");
        sysUser.setId(1L);

     //   int insert = sysUserMapper.insert(sysUser);


    }




    @RequestMapping("/api/updateByWrapperLambda")
    public void updateByWrapperLambda(){
        SysUser sysUser  = new SysUser();




        sysUser.setMobile("000000000000000000000");

        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = Wrappers.<SysUser>lambdaUpdate();
        lambdaUpdateWrapper.eq(SysUser::getUsername,"李白")
                .eq(SysUser::getNickname,"刺客1");


        int rows = sysUserMapper.update(sysUser, lambdaUpdateWrapper);
        System.out.println("影响记录数:"+rows);
    }


   /* public void updateByWrapperLambdaChain(){
        boolean sign = new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName, "李艺伟")
                .eq(User::getAge, 22)
                .set(User::getAge, 30).update();

        System.out.println(sign);
    }*/

}
