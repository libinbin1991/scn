package com.central.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 描述：如果不使用代码手动初始化DataSource的话，监控界面的SQL监控会没有数据("是spring boot的bug???")
 * @author chhliu
 * 创建时间：2017年2月9日 下午7:33:08
 * @version 1.2.0
 */
@Slf4j
@Configuration
@RefreshScope
@Data
public class DruidConfiguration
{
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // @Value("${spring.datasource.initialSize}")
    // private int initialSize;
    //
    // @Value("${spring.datasource.minIdle}")
    // private int minIdle;
    //
    // @Value("${spring.datasource.maxActive}")
    // private int maxActive;
    //
    // @Value("${spring.datasource.maxWait}")
    // private int maxWait;
    //
    // @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    // private int timeBetweenEvictionRunsMillis;
    //
    // @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    // private int minEvictableIdleTimeMillis;
    //
    // @Value("${spring.datasource.validationQuery}")
    // private String validationQuery;
    //
    // @Value("${spring.datasource.testWhileIdle}")
    // private boolean testWhileIdle;
    //
    // @Value("${spring.datasource.testOnBorrow}")
    // private boolean testOnBorrow;
    //
    // @Value("${spring.datasource.testOnReturn}")
    // private boolean testOnReturn;
    //
    // @Value("${spring.datasource.poolPreparedStatements}")
    // private boolean poolPreparedStatements;
    //
    // @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    // private int maxPoolPreparedStatementPerConnectionSize;
    //
    // @Value("${spring.datasource.filters}")
    // private String filters;
    //
    // @Value("${spring.datasource.connectionProperties}")
    // private String connectionProperties;
    //
    // @Value("${spring.datasource.useGlobalDataSourceStat}")
    // private boolean useGlobalDataSourceStat;

    @Bean
    @RefreshScope
    public DruidDataSource dataSource()
    {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        // datasource.setInitialSize(initialSize);
        // datasource.setMinIdle(minIdle);
        // datasource.setMaxActive(maxActive);
        // datasource.setMaxWait(maxWait);
        // datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // datasource.setValidationQuery(validationQuery);
        // datasource.setTestWhileIdle(testWhileIdle);
        // datasource.setTestOnBorrow(testOnBorrow);
        // datasource.setTestOnReturn(testOnReturn);
        // datasource.setPoolPreparedStatements(poolPreparedStatements);
        // datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        // datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        // try
        // {
        //     datasource.setFilters(filters);
        // }
        // catch (SQLException e)
        // {
        //     log.error("druid configuration initialization filter: " + e);
        // }
        // datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }
}