package com.learning;

public class Collections {

	public static void main(String[] args) {
		
		
		try {
			int a =5;
			int b=a/0;
		}
		finally {
			System.out.println("finally");
		}
	}
}
