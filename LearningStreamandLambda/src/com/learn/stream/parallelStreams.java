package com.learn.stream;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class Student{
	String name;
	int score;
	
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
		
}
public class parallelStreams {

	public static void main(String[] args) {
		
		List<Student> sl = Arrays.asList(
							new Student("shree",95),
							new Student("ajay",90),
							new Student("vijay",85),
							new Student("deepya",98),
							new Student("pavya",91),
							new Student("vishal",95),
							new Student("venky",98),
							new Student("ravi",100),
							new Student("amar",100),
							new Student("santhosh",35),
							new Student("sapna",99) 
							);
		
	    int dt = LocalDateTime.now().getSecond();
	    System.out.println("a "+dt);
	    
		sl.stream().filter(i->i.getScore()>=90).limit(3).forEach(i-> System.out.print(i.getName()+" "));
		System.out.println("ab "+(dt-LocalDateTime.now().getSecond()));
		
		
		dt = LocalDateTime.now().getSecond();
		System.out.println("b "+dt);
		sl.parallelStream().filter(i->i.getScore()>=90).limit(3).forEach(i-> System.out.print(i.getName()+" "));
		System.out.println("b "+(dt-LocalDateTime.now().getSecond()));
	}
}
