package practice;

public class SumToSIngleDigit {

	
	public static void main(String[] args) {
		
		
		int n =1234567891;
		int sum=0;
		
		sum = sum(n);
		do {
			sum = sum(sum);
		}while(sum>=10);
		
		System.out.println(sum);
		
		System.out.println("using rec : "+sum_rec(n));
	}
	
	public static int sum(int n) {
		int sum=0;
		int last=0;
		while(n != 0 ) {
			last = n%10;
			sum +=last;
			n=n/10;
			
		}
		
		return sum;
	}
	
	public static int sum_rec(int n) {
		int sum=0;
		int last=0;
		int out=0;
		while(n != 0 ) {
			last = n%10;
			sum +=last;
			n=n/10;
			
		}
		if(sum>=10)
			out = sum_rec(sum);
		else
			out= sum;
		
		return out;	
	}

}
