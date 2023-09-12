package patterns;

public class butterflyPattern {

	public static void main(String[] args) {
		
		
		int len=5;
		int sp = 2 * len - 2;
		for(int i=0;i<len;i++) {
			
			for(int j=0;j<=i;j++) {
				System.out.print("* ");
			}
			for(int j=0;j<sp;j++) {
				System.out.print("  ");
			}
			for(int j=sp+1;j<len*2-i;j++) {
				System.out.print("* ");
			}
			System.out.print("\n");
			sp -=2;
			
		}
		sp = 0;
		for(int i=0;i<len;i++) {
			
			for(int j=0;j<len-i;j++) {
				System.out.print("* ");
			}
			for(int j=len-i;j<len+sp;j++) {
				System.out.print("  ");
			}
			for(int j=len;j<len*2-i;j++) {
				System.out.print("* ");
			}
			System.out.print("\n");
			sp +=1;
			
		}
		
	}
}
