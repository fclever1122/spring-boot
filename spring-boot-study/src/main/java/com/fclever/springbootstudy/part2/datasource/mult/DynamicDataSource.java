package com.fclever.springbootstudy.part2.datasource.mult;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.mult
 * @className: DynamicDataSource
 * @description:
 * @author: Fclever
 * @date 2023/6/10 19:51
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

	public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
		// 设置默认数据源
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		// 存放用来切换的数据源
		super.setTargetDataSources(targetDataSources);
		// afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
		super.afterPropertiesSet();
	}

	/**
	 * 根据Key获取数据源的信息
	 *
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceType();
	}
}
