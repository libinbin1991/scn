package com.central.user.service;


import com.central.common.model.LoginAppUser;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lbb
 */

public interface ISysUserService extends ISuperService<SysUser> {


    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);
}
