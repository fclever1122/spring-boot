package com.fclever.springbootstudy.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.controller.config
 * @className: MyConfig
 * @description:
 * @author: Fclever
 * @date 2023/5/13 13:50
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 这样配置之后，浏览器访问/fc就会找到静态资源helloviewcontroller.html，是不需要去单独写controller来做映射的
		// 如果controller中有相同的映射，那会以controller为主，需要注意
		registry.addViewController("/fc").setViewName("helloviewcontroller");
	}
}
