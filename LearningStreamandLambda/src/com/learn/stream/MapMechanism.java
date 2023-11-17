package com.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.learn.pojo.Employee;

public class MapMechanism {

	public static void main(String[] args) {
		
		List<String> vehicles = Arrays.asList("bus","car","bicycle","flight","train");
		
		//tc1 : converting to upper case
		vehicles.stream().map(i->i.toUpperCase()).forEach(a->System.out.print(a+" "));
		
		System.out.println();
		//tc2 : finding length
		
		List li = vehicles.stream().map(i->i.length()).collect(Collectors.toList());
		
		li.stream().forEach(i->System.out.print(i+" "));
		
		System.out.println();
		
		//tc3 : multiple all element in with 3
		
		List<Integer> intList = Arrays.asList(2,3,4,5,6,7,8,9);
		
		intList.stream().map(i->i*3).forEach(i->System.out.print(i+" "));
		
		
		//tc 4: fetch emp > 20000 and fetch their name list in upper case
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("shreedhar", 1234, 20000));
		empList.add(new Employee("venky", 12378, 22000));
		empList.add(new Employee("vishal", 4567, 23000));
		empList.add(new Employee("sunil", 1234, 1000));
		
		System.out.println();
		
		List<String> out = empList.stream()
							.filter(i->i.getSalary()>=20000)
							.map(i->i.getName().toUpperCase()).toList();
		
		out.stream().forEach(i->System.out.print(i+" "));
							
		System.out.println("sorting....");
		empList.stream().sorted(Comparator.comparingInt(i->i.getSalary())).forEach(i-> System.out.print(i.getName()+" "));
		
		
	}
}
