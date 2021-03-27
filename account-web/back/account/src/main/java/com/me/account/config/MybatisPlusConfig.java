package com.me.account.config;

import java.io.IOException;

import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisSessionFactoryBuilder;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MybatisPlusConfig {
    public static final Logger log= LoggerFactory.getLogger(DataSourceConfiguration.class);
    @Autowired
    private DataSource dataSource;
    @Value("${mybatis-plus.global-config.id-type}")
    private Integer idType;
    @Value("${mybatis-plus.global-config.field-strategy}")
    private int fieldStrategy;
    @Value("${mybatis-plus.global-config.db-column-underline}")
    private boolean dbColumnUnderline;
    @Value("${mybatis-plus.global-config.capital-mode}")
    private boolean capitalMode;
    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis-plus.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis-plus.global-config.refresh-mapper}")
    private boolean refreshMapper;


    //mybatis plus 全局配置
    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfiguration(){
        log.info("初始化GlobalConfiguration");
        GlobalConfiguration configuration=new GlobalConfiguration();
        //主键策略
        configuration.setRefresh(refreshMapper);
        configuration.setIdType(idType);
        //字段策略
        configuration.setFieldStrategy(fieldStrategy);
        //数据库大写 下划线转换
        //configuration.setCapitalMode(capitalMode);
        configuration.setDbColumnUnderline(dbColumnUnderline);

        return configuration;
    }
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean(@Qualifier(value = "globalConfig") GlobalConfiguration configuration) throws Exception{
        log.info("初始化SqlSessionFactory");
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Interceptor[] interceptor={new PaginationInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptor);
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        try{
            sqlSessionFactoryBean.setGlobalConfig(configuration);
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(){
        log.info("初始化transactionManager");
        return new DataSourceTransactionManager(dataSource);
    }




}