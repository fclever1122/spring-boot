package com.fclever.springbootstudy.part2.datasource.mult;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.mult
 * @className: DataSourceAspect
 * @description:
 * @author: Fclever
 * @date 2023/6/10 19:54
 **/
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

	/**
	 * 切入点   使用了@DataSource注解的位置
	 */
	@Pointcut("@annotation(com.fclever.springbootstudy.part2.datasource.mult.DataSource)")
	public void dsPointCut() {

	}

	/**
	 * 环绕通知
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("dsPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		// 获取方法签名
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 获取方法对象
		Method method = signature.getMethod();
		// 获取方法上的DataSource注解
		DataSource dataSource = method.getAnnotation(DataSource.class);
		// 不为空时，设置要使用的数据源
		if (dataSource != null) {
			DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
		}
		try {
			return point.proceed();
		} finally {
			// 销毁数据源 在执行方法之后
			DynamicDataSourceContextHolder.clearDataSourceType();
		}
	}
}
