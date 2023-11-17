package com.learning;

public class ArrayManipulation {

	public static void main(String[] args) {

		int[] a = new int[]{ 10, 20, 30, 40, 50 };

	
		System.out.println("----LeftShift----");

		for(int j=0;j<a.length;j++) {
			System.out.print(a[j]+" ");
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			a = leftShiftByOne(a);
			
			for(int j=0;j<a.length;j++) {
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("----LeftShift End----");
		
		a = new int[]{ 10, 20, 30, 40, 50 };
		
		System.out.println("----RightShift----");

		for(int j=0;j<a.length;j++) {
			System.out.print(a[j]+" ");
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			a = rightShiftByOne(a);
			
			for(int j=0;j<a.length;j++) {
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("----RightShift End----");
	}

	
	public static int[] rightShiftByOne(int[] a) {
		
		//store last element
		int last = a[a.length-1];
		
		for(int i=a.length-1;i>0;i--) {
			a[i]=a[i-1];
		}
		a[0] = last;
		
		return a;
	}
	public static int[] leftShiftByOne(int[] a) {
		
		// store 1st element
		int first = a[0];
		for (int i = 1; i < a.length ; i++) {
			a[i - 1] = a[i];
		}
		a[a.length - 1] = first;
		
		return a;
	}
}
