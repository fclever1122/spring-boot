package com.fclever.mybatis.part3.mybatis.entity;

public class Emp {

	private Integer empno;

	private String empname;

	public Emp(Integer empno, String empname) {
		this.empno = empno;
		this.empname = empname;
	}

	public Emp() {
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	@Override
	public String toString() {
		return "Emp{" +
				"empno=" + empno +
				", empname='" + empname + '\'' +
				'}';
	}
}
