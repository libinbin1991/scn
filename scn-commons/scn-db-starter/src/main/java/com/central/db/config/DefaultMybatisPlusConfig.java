package com.central.db.config;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * mybatis-plus配置
 * @author zlt
 * @date 2018/12/13
 */
@Import(DateMetaObjectHandler.class)
public class DefaultMybatisPlusConfig {
    @Autowired
    private TenantHandler tenantHandler;

    @Autowired
    private ISqlParserFilter sqlParserFilter;

   /* @Autowired
    private TenantProperties tenantProperties;

    *//**
     * 分页插件，自动识别数据库类型
     *//*
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        boolean enableTenant = tenantProperties.getEnable();
        //是否开启多租户隔离
        if (enableTenant) {
            TenantSqlParser tenantSqlParser = new TenantSqlParser()
                    .setTenantHandler(tenantHandler);
            paginationInterceptor.setSqlParserList(CollUtil.toList(tenantSqlParser));
            paginationInterceptor.setSqlParserFilter(sqlParserFilter);
        }
        return paginationInterceptor;
    }*/

    /**
     * 打印 sql，性能分析拦截器，不建议生产使用
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
