package com.fclever.springbootstudy.part2.datasource.mult;

import java.lang.annotation.*;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.mult
 * @className: DataSource
 * @description:
 * @author: Fclever
 * @date 2023/6/10 19:54
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

	/**
	 * 切换数据源名称
	 */
	DataSourceType value() default DataSourceType.REMOTE;
}
