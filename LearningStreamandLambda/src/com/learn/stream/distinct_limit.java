package com.learn.stream;

import java.util.Arrays;
import java.util.List;

public class distinct_limit {

	public static void main(String[] args) {
		
		
		List<Integer> list = Arrays.asList(1,1,3,3,5,5,7,8,9,10);
		
		list.stream().distinct().forEach(i-> System.out.print(i+" "));
		
		list.stream().limit(4).forEach(i-> System.out.print(i+" "));
	}
}
