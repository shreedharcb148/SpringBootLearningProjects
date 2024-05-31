package com.learn;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> arr = new CustomArrayListImplementation();
		arr.add(1);arr.add(2);arr.add(1);arr.add(1);arr.add(2);arr.add(1);arr.add(3);arr.add(3);arr.add(1);
		System.out.println(arr);
		
		
		new ArrayManipulation().separateElements();
		new StringManipulation().repeatNonrepeatCharsInStr();
	}
}
