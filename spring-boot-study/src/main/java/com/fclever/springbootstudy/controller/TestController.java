package com.fclever.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.controller
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
}
