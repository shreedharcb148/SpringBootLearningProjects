package com.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.learn.pojo.Employee;

public class FlatMap {

	public static void main(String[] args) {
		
		//map()
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
		
		List<Integer> list2 = list1.stream().map(i->i+10).collect(Collectors.toList());
		
		//System.out.println(list2);
		
		//flatmap () - when u have complex data 
		
		List<Integer> l1 = Arrays.asList(1,2);
		List<Integer> l2 = Arrays.asList(3,4);
		List<Integer> l3 = Arrays.asList(5,6);
		
		List<List<Integer>> finallist = Arrays.asList(l1,l2,l3); 
		
		//it returns collection of stream that means basically it converts complex data into simple data 
		//here it is converting list of list to single list by combining all list values in one list
		
		//TODO : try with only map??? possible ???
		List<Integer> finallist2 = finallist.stream()
				.flatMap(x->
					x.stream().map(n->n+10))
				.collect(Collectors.toList());
		
		System.out.println(finallist2);
		
		
		System.out.println();
		//tc : read all members of the team 
		List<String> ls1 = Arrays.asList("Mallesh","Pradeep","Prashant");
		List<String> ls2 = Arrays.asList("shreedhar","vishal","venky");
		List<String> ls3 = Arrays.asList("soumya","soni","vinni");
		List<String> ls4 = Arrays.asList("udaal","Paapya","Meghi");
		
		
		List<List<String>> listoflist = Arrays.asList(ls1,ls2,ls3,ls4); //Arrays.asList(ls1,ls2,ls3,ls4);
		
		List<String> outt = listoflist.stream()
							.flatMap(x->x.stream())
							.collect(Collectors.toList());
		
		outt.stream().forEach(x->System.out.print(x+" "));
		
		
		System.out.println();
		
		//tc :
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("shreedhar", 1234, 20000));
		empList.add(new Employee("venky", 12378, 22000));
		empList.add(new Employee("vishal", 4567, 23000));
		empList.add(new Employee("sunil", 1234, 1000));
		
		
		List<Employee> empList2 = new ArrayList<>();
		empList.add(new Employee("udaal",56333, 20000));
		empList.add(new Employee("paapya", 4555, 22222));
		empList.add(new Employee("meghi", 64454, 22333));
		empList.add(new Employee("soni", 2222, 24560));
		
		
		List<List<Employee>> clist = Arrays.asList(empList,empList2);
		
		List<String> names = clist.stream().flatMap(x->x.stream().map(i->i.getName())).collect(Collectors.toList());
		
		names.stream().forEach(x->System.out.print(x+" "));
		
		
		
		
		
		
		
		
	}
}
