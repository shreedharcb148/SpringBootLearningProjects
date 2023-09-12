package learningCore;

class parent{
	
	static void display() {
		System.out.println("static parent");
	}
}

class child extends parent{
	
	static void display() {
		System.out.println("hhhiii");
	}
}


//METHOD HIDING
public class learningCoreThings {

	
	public static void main(String[] args) {
		//child.display();
		
		try {
			int a = 10/0;
			
		}catch(ArithmeticException e) {
			System.out.println("arith");
		}catch(Exception e) {
			System.out.println("exception");
		}
		
	}
}


