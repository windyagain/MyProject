package com.me.account.config;

import java.beans.PropertyVetoException;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置datasource到ioc容器里面
 *
 * @author xiangze
 *
 */
@Configuration
@MapperScan(basePackages = "com.me.account.dao")
public class DataSourceConfiguration {
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;


    /**
     * 生成与spring-dao.xml对应的bean dataSource
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

}