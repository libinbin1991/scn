package com.central.user.rpc;

import com.central.common.model.SysUser;
import com.central.user.api.ISysUserServiceApi;
import com.central.user.service.ISysUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author lbb
 * @version 1.0
 * 修改记录：
 * 修改序号，修改日期，修改人，修改内容
 */
@RestController
public class SysUserServiceApiClient implements ISysUserServiceApi {

    @Resource
    private ISysUserService iSysUserService;

    @Override
    public SysUser selectByUsername(@RequestBody String username) {
        System.out.println("rpc远程调用开始----------");
        SysUser sysUser = iSysUserService.selectByUsername(username);
        return sysUser;
    }
}
