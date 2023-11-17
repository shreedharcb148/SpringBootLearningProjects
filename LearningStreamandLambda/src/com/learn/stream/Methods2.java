package com.learn.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Methods2 {

	public static void main(String[] args) {


		List<Integer> list = Arrays.asList(1,0,2,9,5,1,6,12,13,10,15,10);

		list.stream().sorted().forEach(i-> System.out.print(i+" "));
		System.out.println();
		list.stream().sorted(Comparator.reverseOrder()).forEach(i-> System.out.print(i+" "));
		
		
		System.out.println(list.stream().allMatch(i->{return (i>0);}));
		System.out.println(list.stream().anyMatch(i->{return (i>0);}));
		System.out.println(list.stream().noneMatch(i->{return (i>0);}));
		
		
	}
}
