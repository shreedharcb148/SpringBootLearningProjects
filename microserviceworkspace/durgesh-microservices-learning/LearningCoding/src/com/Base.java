package com;

class Resource{
	static int n = 0;
	synchronized public void even() {
	
		while(n<=20 && n%2 == 0) {
			System.out.println("A Thread : "+n);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n++;
			notify();
		}
        	
	}
	synchronized public void odd() {
		while(n<=20 && n%2 == 1) {
			System.out.println("B Thread : "+n);
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n++;
			notify();
		}
	}
}

class A extends Thread {

	Resource res;
	A(Resource res){
		this.res=res;
	}
    @Override
    public void run() {
        this.res.even();
        	
    }

}
class B extends Thread{

	Resource res;
	B(Resource res){
		this.res=res;
	}
    @Override
    public void run() {
        this.res.odd();
        	
    }

}
public class Base {

	public static void main(String[] args) {
		
		Resource res = new Resource();
		new A(res).start();
		new B(res).start();
        
	}

}
