package patterns;

public class InvertedTraingle {

	public static void main(String[] args) {
		int rows = 20, i, j, sp;

		int k = 0;

		for (;;) {
			for (i = 1; i <= rows; i++) {
				for (sp = 0; sp < rows - i; sp++)
					System.out.print("  ");

				for (j = i; j < 2*i; j++)
					System.out.print("* ");

				for (j = 0; j < i; j++)
					System.out.print("* ");



				System.out.print("\n");
			}
			for (i = rows; i >= 1; i--) {
				for (sp = 0; sp < rows - i; sp++)
					System.out.print("  ");

				for (j = i; j < 2*i; j++)
					System.out.print("* ");

				for (j = 0; j < i; j++)
					System.out.print("* ");

				System.out.print("\n");
			}

			

//			k++;
//			if (k == 1)
//				break;
		}

	}
}
