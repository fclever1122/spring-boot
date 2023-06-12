package com.fclever.springbootstudy.part2.datasource.mult;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.mult
 * @className: DynamicDataSourceContextHolder
 * @description:	数据源切换类
 * @author: Fclever
 * @date 2023/6/10 19:46
 **/
public class DynamicDataSourceContextHolder {

	/**
	 * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
	 *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
	 */
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	/**
	 * 设置数据源变量
	 * @param dataSourceType
	 */
	public static void setDataSourceType(String dataSourceType){
		System.out.printf("切换到{%s}数据源", dataSourceType);
		CONTEXT_HOLDER.set(dataSourceType);
	}

	/**
	 * 获取数据源变量
	 * @return
	 */
	public static String getDataSourceType(){
		return CONTEXT_HOLDER.get();
	}

	/**
	 * 清空数据源变量
	 */
	public static void clearDataSourceType(){
		CONTEXT_HOLDER.remove();
	}
}
