package divideandconquer;

public class QuickSort {

	public static void main(String[] args) {
		
		int a[]= {10,16,8,12,15,6,3,9,5};
		
		QuickSort(a,0,a.length);
		
		
	}

	private static void QuickSort(int[] a, int low, int high) {
		
		if(low<high) {
			int part_ele = Partition(a,low,high);
			System.out.println("part_ele "+part_ele+" : "+a[part_ele]);
			System.out.println(low +" : "+part_ele+" : "+high);
			QuickSort(a, low, part_ele);
			QuickSort(a, part_ele+1,high);
		}
		
	}

	private static int Partition(int[] a, int i, int j) {
		int pivot;
		
		//10,16,8,12,15,6,3,9,5
		while(i<j) {
			pivot = a[0];
			
			do {
				i++;
			}while(a[i]<=pivot);
			
			do {
				j--;
			}while(a[j]>=pivot);
			
			if(i<j) {
				swap(a,i,j);
			}
			printArray(a);
			System.out.println();
			System.out.println(i+" "+j);
		}
		swap(a,j,0);
		
		return j;
	}

	private static void printArray(int[] a) {
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
		
	}

	private static void swap(int[] a, int i, int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}


}
