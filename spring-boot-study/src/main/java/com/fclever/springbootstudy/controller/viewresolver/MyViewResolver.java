package com.fclever.springbootstudy.controller.viewresolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.controller.viewresolver
 * @className: MyViewResolver
 * @description:	自定义视图解析器
 * @author: Fclever
 * @date 2023/5/13 13:26
 **/
public class MyViewResolver implements ViewResolver {


	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// 在此处实现自定义的视图解析逻辑
		// 返回一个实现了 View 接口的视图对象
		return null;
	}
}
