package com.fclever.springbootstudy.part2.datasource.config;

import com.fclever.springbootstudy.part2.datasource.mult.DataSourceType;
import com.fclever.springbootstudy.part2.datasource.mult.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.config
 * @className: DataSourceConfig
 * @description:
 * @author: Fclever
 * @date 2023/6/10 19:52
 **/
@Configuration
public class DataSourceConfig {

	/**
	 * 通过配置信息注入Bean  指定对应前缀
	 *  方法名自动作为Bean名称
	 * @return
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.remote")
	public DataSource remoteDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 通过配置信息注入Bean  指定对应前缀
	 * 	方法名自动作为Bean名称
	 *
	 * 当名为 spring.datasource.local.enabled 的属性存在并且值为 true 时，相关的 Bean 才会被装配。
	 * @return
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.local")
	// @ConditionalOnProperty(prefix = "spring.datasource.local", name = "enabled", havingValue = "true")
	public DataSource localDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dynamicDataSource")
	@Primary
	@DependsOn({"remoteDataSource","localDataSource"})
	public DynamicDataSource dataSource(DataSource remoteDataSource, DataSource localDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceType.REMOTE.name(), remoteDataSource);
		targetDataSources.put(DataSourceType.LOCAL.name(), localDataSource);
		return new DynamicDataSource(remoteDataSource, targetDataSources);
	}
}
