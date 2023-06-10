package com.fclever.springbootstudy.part1.controller.viewresolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part1.controller.viewresolver
 * @className: ViewConfig
 * @description:	注册自定义视图解析器为bean，可以被ioc接管
 * @author: Fclever
 * @date 2023/5/13 13:28
 **/
@Configuration
public class ViewConfig {

	@Bean
	public ViewResolver myViewResolver() {
		return new MyViewResolver();
	}
}
