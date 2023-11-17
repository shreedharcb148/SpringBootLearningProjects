package com.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.learn.pojo.Employee;

public class Practice {

	public static void main(String[] args) {
		
		//find common element in 3 lists
		List<Integer> list1 = Arrays.asList(1,2,2,1,2,3,3,4,5,6,7,8);
		List<Integer> list2 = Arrays.asList(1,1,2,2,3,4,5,6,2,3);
		List<Integer> list3 = Arrays.asList(1,2,3);
		
		
//		List arr = list1.stream().filter(list2::contains)
//						.filter(list3::contains)
//						.collect(Collectors.toList());
//		arr.stream().forEach(System.out::println);
//		
//		
//		list1.stream().limit(5).forEach(System.out::println);
//		
//		IntStream.range(0, 10).forEach(System.out::println);
		
		//tc 1 : common elements,distinct and sort
		List out = list1.stream().filter(list2::contains).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList())
					.stream().sorted().collect(Collectors.toList());
		
		out.stream().forEach(System.out::print);
		
		System.out.println();
		
		//tc 2 : sorting with employee list on 
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("shreedhar", 1234, 20000));
		empList.add(new Employee("venky", 12378, 22000));
		empList.add(new Employee("vishal", 4567, 23000));
		empList.add(new Employee("sunil", 1234, 1000));
		
		
		List<Employee> emps = empList.stream().sorted(
			Comparator.comparingInt(i->i.getSalary())).collect(Collectors.toList());//.forEach(j->{System.out.println(j.getName());});
		
		
		emps.stream().forEach(j->{
			System.out.print(j.getName()+" ");
			System.out.print(j.getPhone()+" ");
			System.out.println(j.getSalary());
		});
		
		//tc 3 : filter even numbers and odd numbers
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		List<Integer> out2 = list.stream().filter(i->i%2==0).collect(Collectors.toList());
		out2.stream().forEach(System.out::print);
		
		System.out.println();
		
		out2 = list.stream().filter(i->i%2!=0).collect(Collectors.toList());
		out2.stream().forEach(System.out::print);
		
		System.out.println();
		//tc4 : fetch words length > 5
		
		List<String> strr = Arrays.asList("Shreedhar","Karthik","Suresh","Akash","Ajay");
		
		strr.stream().filter(i->i.length()>5).forEach(j->System.out.println(j));
		
		
		System.out.println();
		
		//tc5 : filter emplyoee whose salary is less thn 1500
		
		empList.stream().filter(i->i.getSalary()<=1000).forEach(j->System.out.println(j.getName()));
		
		//tc6 : min and max in list
		System.out.println("min  "+list.stream().max((a,b)->a-b).get());
		
		
		
		
	}
}
