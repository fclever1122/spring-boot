package com.fclever.springbootstudy.part2.datasource.controller;

import com.fclever.springbootstudy.part2.datasource.mult.DataSource;
import com.fclever.springbootstudy.part2.datasource.mult.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.controller
 * @className: EmpController
 * @description:
 * @author: Fclever
 * @date 2023/6/10 19:55
 **/
@RestController
public class EmpController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/local")
	@DataSource(value = DataSourceType.LOCAL)
	public List<Map<String, Object>> local(){
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from emp");
		return maps;
	}
	@GetMapping("/remote")
	@DataSource(value = DataSourceType.REMOTE)
	public List<Map<String, Object>> remote(){
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from emp");
		return maps;
	}

}
