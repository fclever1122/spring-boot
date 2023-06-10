package com.fclever.springbootstudy.part1.controller;

import com.fclever.springbootstudy.part1.controller.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part1.controller
 * @className: TestController
 * @description:
 * @author: Fclever
 * @date 2023/5/11 21:30
 **/
@Controller
public class TestController {

	@RequestMapping(value = "/fc")
	public String test() {
		return "hellocontroller";
	}

	/**
	 * 测试Thymeleaf
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hello")
	public String hello(Model model) {
		model.addAttribute("msg","Hello Thymeleaf");
		return "hello";
	}

	@RequestMapping("thymeleaf")
	public String thymeleaf(ModelMap map){

		map.put("thText","th:text设置文本内容 <b>加粗</b>");
		map.put("thUText","th:utext 设置文本内容 <b>加粗</b>");
		map.put("thValue","thValue 设置当前元素的value值");
		map.put("thEach","Arrays.asList(\"th:each\", \"遍历列表\")");
		map.put("thIf","msg is not null");
		map.put("thObject",new Person("zhangsan",12,"男"));
		return "thymeleaf";
	}

	@RequestMapping("base")
	public String bae() {
		return "base";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}
}
