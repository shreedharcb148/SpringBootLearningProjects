package com.learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Collections implements test{

	public static void main(String[] args) {
		
		String pa= "";
		
		try {
			FileReader fr = new FileReader(new File(pa));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int a =5;
			//int b=a/0;
		}
		finally {
			System.out.println("finally");
			System.out.println(test.sMethod());
		}
		

	}
}
