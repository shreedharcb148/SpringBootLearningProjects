package com.learn.pojo;

public class Employee {

	private String name;
	private int phone;
	private int salary;
	
	
	public Employee(String name, int phone, int salary) {
		super();
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}