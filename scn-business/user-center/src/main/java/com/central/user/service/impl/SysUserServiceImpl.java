package com.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.common.model.SysUser;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.user.mapper.SysUserMapper;
import com.central.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  * @author 作者 lbb E-mail: lbb_it@163.com
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    private SysUser getUser(List<SysUser> users) {
        SysUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }


    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectByUsername(String username) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("username", username)
        );
        return getUser(users);
    }

}