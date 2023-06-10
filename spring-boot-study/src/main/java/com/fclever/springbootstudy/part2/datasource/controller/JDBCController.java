package com.fclever.springbootstudy.part2.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Description
 * Copyright ©fclever 版权所有
 *
 * @packageName: com.fclever.springbootstudy.part2.datasource.controller
 * @className: JDBCController
 * @description:
 * @author: Fclever
 * @date 2023/6/4 23:17
 **/
@RestController
public class JDBCController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/emplist")
	public List<Map<String,Object>> empList(){
		String sql = "select * from emp";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
		return maps;
	}

	@GetMapping("/addEmp")
	public String addUser(){
		String sql = "insert into emp(empno,ename) values(1111,'zhangsan')";
		jdbcTemplate.update(sql);
		return "success";
	}

	@GetMapping("/updateEmp/{id}")
	public String updateEmp(@PathVariable("id") Integer id){
		String sql = "update emp set ename=? where empno = "+id;
		String name = "list";
		jdbcTemplate.update(sql,name);
		return "update success";
	}

	@GetMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable("id")Integer id){
		String sql = "delete from emp where empno = "+id;
		jdbcTemplate.update(sql);
		return "delete success";
	}
}
