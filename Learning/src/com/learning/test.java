package com.learning;

public interface test {

	public default void dMethod() {
		System.out.println("default method....");
	}
	
	public static int sMethod() {
		System.out.println("static method....");
		return 12;
	}
	
//	public abstract void m1();
}
