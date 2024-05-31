package com.vishal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Employee> emp = new ArrayList<>();
		
		List<String> l1 = new ArrayList<>();
		l1.add("abc");l1.add("xyz");l1.add("pqr");
		
		List<String> l2 = new ArrayList<>();
		l2.add("XYZ");l2.add("ABC");l2.add("PQR");
		
		emp.add(new Employee(1,"shreedhar",l1));
		emp.add(new Employee(1,"vishal",l2));
		
		
		emp.parallelStream().filter(x->x.getName().equals("shreedhar")).map(Employee::getAddresses).collect(Collectors.toList());
		System.out.println(emp.parallelStream().filter(x->x.getName().equals("vishal")).map(Employee::getAddresses).collect(Collectors.toList()));

	}

}
