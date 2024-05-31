package com.learn;

import java.util.ArrayList;
import java.util.List;

class test{
	
	
	{
		System.out.println("local block");
	}
	static {
		System.out.println("static block");
	}
	static void abc(){
		System.out.println("abc block");
	}
}
public class Streams {
	
	public static void main(String[] args) {
		
		test t = new test();
		t.abc();
		
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(0, "shree", "bale"));
		list.add(new Employee(0, "deepa", "bhat"));
		list.add(new Employee(0, "mangesh", "Jadhav"));
		list.add(new Employee(0, "vishal", "L"));
		list.add(new Employee(0, "Pavitra", "M"));
		list.add(new Employee(0, "cc", "mp"));
		list.add(new Employee(0, "shrinath", "more"));
		
		//stream to return name list of employee startwith s
		
		List<String> out = list.stream().filter(x->x.getFname().startsWith("s")).map(Employee::getFname).toList();
		System.out.println(out);
		
		List<Employee> list2 = new ArrayList<>();
		list.add(new Employee(0, "shree", "bale"));
		list.add(new Employee(0, "deepa", "bhat"));
		list.add(new Employee(0, "mahesh", "Jadhav"));
		list.add(new Employee(0, "mallesh", "Naik"));
		list.add(new Employee(0, "pradeep", "M"));
		list.add(new Employee(0, "cc", "mp"));
		list.add(new Employee(0, "shrinath", "more"));
		
		out = list.stream().filter(x->list2.contains(x.fname)).map(Employee::getFname).toList();
		System.out.println(out);
		
		
		
	}
}
