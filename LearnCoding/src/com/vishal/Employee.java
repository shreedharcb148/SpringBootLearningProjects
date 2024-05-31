package com.vishal;

import java.util.List;

public class Employee {

	int id;
	
	String name;
	
	List<String> addresses;

	
	public Employee(int id, String name, List<String> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.addresses = addresses;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public List<String> getAddresses() {
		return addresses;
	}
	
	
}
