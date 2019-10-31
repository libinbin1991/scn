package com.central.user.api;

import com.central.common.model.SysUser;
import com.central.user.api.hystrix.SysUserServiceApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Title: </p>
 * <p>Description: 远程服务消费者</p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author lbb
 * @version 1.0
 * 修改记录：
 * 修改序号，修改日期，修改人，修改内容
 */
@FeignClient(value = "user-center", fallback = SysUserServiceApiHystrix.class)
public interface ISysUserServiceApi {

    @PostMapping(value = "/api/getByUsername")
    SysUser selectByUsername(@RequestBody String username);

}
