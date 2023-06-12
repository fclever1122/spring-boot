package com.fclever.springbootstudy;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringBootStudyApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		System.out.println("------------------------");
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);

		// druid测试
		DruidDataSource druidDataSource = (DruidDataSource)dataSource;
		System.out.println(druidDataSource.getMaxActive());
		System.out.println(druidDataSource.getInitialSize());

		connection.close();
		System.out.println("------------------------");
	}

	@Test
	void contextLoads1() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		System.out.println(connection.getMetaData().getURL());

		connection.close();
	}
}
