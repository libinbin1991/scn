package com.central.log.config;

import com.central.log.properties.TraceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 日志自动配置
 *
 * @author lbb
 * @date 2019/10/31
 */
@EnableConfigurationProperties(TraceProperties.class)
public class LogAutoConfigure {

}
