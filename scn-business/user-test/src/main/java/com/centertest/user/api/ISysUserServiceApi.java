package com.centertest.user.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
@FeignClient(value ="user-center-cust")
public interface ISysUserServiceApi {

    @GetMapping(value = "/users/name/username")
    public String selectUsername(@RequestParam("key") String key);

}
