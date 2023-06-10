package com.fclever.springbootstudy.part1.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part1.controller.config
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
		registry.addViewController("/login.html").setViewName("login");
	}

	/**
	 * 国际化配置开始
	 */
	@Bean
	public LocaleResolver localeResolver(){
		return new NativeLocaleResolver();
	}

	protected static class NativeLocaleResolver implements LocaleResolver {

		@Override
		public Locale resolveLocale(HttpServletRequest request) {
			String language = request.getParameter("language");
			Locale locale = Locale.getDefault();
			if (!StringUtils.isEmpty(language)) {
				String[] split = language.split("_");
				locale = new Locale(split[0], split[1]);
			}
			return locale;
		}

		@Override
		public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

		}
	}

	/**
	 * 国际化配置结束
	 */
}
