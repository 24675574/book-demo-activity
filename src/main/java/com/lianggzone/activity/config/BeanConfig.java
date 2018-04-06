package com.lianggzone.activity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <h3>概要:</h3><p>BeanConfig</p>
 * <h3>功能:</h3><p>BeanConfig</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:persistence.properties"})
public class BeanConfig {

	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "dataSource", destroyMethod = "close")
	@Qualifier("dataSource")
	public DataSource dataSource() {
	    DruidDataSource dataSource = new DruidDataSource();
	    dataSource.setDriverClassName(env.getProperty("source.driverClassName").trim());
        dataSource.setUrl(env.getProperty("source.url").trim());
        dataSource.setUsername(env.getProperty("source.username").trim());
        dataSource.setPassword(env.getProperty("source.password").trim());
        
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("druid.initialSize").trim()));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("druid.minIdle").trim()));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("druid.maxActive").trim()));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("druid.maxWait").trim()));
        
        dataSource.setRemoveAbandoned(Boolean.valueOf(env.getProperty("druid.removeAbandoned").trim()));//超过时间是否回收
        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(env.getProperty("druid.removeAbandonedTimeout").trim()));//超过时间限制多长
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("druid.timeBetweenEvictionRunsMillis").trim()));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("druid.minEvictableIdleTimeMillis").trim()));
        dataSource.setValidationQuery(env.getProperty("druid.validationQuery").trim());
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("druid.testWhileIdle").trim()));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("druid.testOnBorrow").trim()));
        dataSource.setTestOnReturn(Boolean.valueOf((env.getProperty("druid.testOnReturn").trim())));
        dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("druid.poolPreparedStatements").trim()));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize").trim()));
        
        return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}
