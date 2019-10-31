package com.central.user.api.hystrix;

import com.central.common.model.SysUser;
import com.central.user.api.ISysUserServiceApi;
import org.springframework.stereotype.Component;

/**
 * <p>Title: </p>
 * <p>Description: 回调类 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author lbb
 * @version 1.0
 * 修改记录：
 * 修改序号，修改日期，修改人，修改内容
 */
@Component
public class SysUserServiceApiHystrix implements ISysUserServiceApi{


    @Override
    public SysUser selectByUsername(String username) {
        return null;
    }
}
