package com.centertest.user.service;


/**
 * @author lbb
 */

public interface ISysUserService  {


    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    String selectByUsername(String username);
}
