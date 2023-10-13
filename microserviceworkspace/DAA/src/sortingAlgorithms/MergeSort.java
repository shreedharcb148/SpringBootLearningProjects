package sortingAlgorithms;


class merg_sort{
	
	void merge(int arr[], int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		// Create temp arrays
		int L[] = new int[n1];
		int R[] = new int[n2];

		// Copy data to temp arrays
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		// Merge the temp arrays

		// Initial indices of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] if any
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// Copy remaining elements of R[] if any
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	void sort(int arr[], int l, int r)
	{
		if (l < r) {

			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}
	
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

}


class m_sort{
	
	public void mergeSort(int[] arr,int l,int r) {
		
		if(l<r) {
			int mid=l+(r-1)/2;
			mergeSort(arr,l,mid);
			mergeSort(arr,mid+1,r);
			merge(arr,l,mid,r);
		}
		
	}

	private void merge(int[] arr, int l, int mid, int r) {
		
		//find the both array sizes
		int n1 = mid-l+1;
		int n2= r-mid;
		
		//create two temp arr for left and right
		int left[] = new int[n1];
		int right[] = new int[n2];
		
		//copy data to temp arr
		for(int i=0;i<n1;i++) {
			left[i]=arr[l+i];
		}
		
		for(int j=0;j<n2;j++) {
			right[j]=arr[mid+1+j];
		}
		
		//merge temp arrays
		int i=0,j=0,k=1;
		
		while(i<n1 && j<n2) {
			if(left[i]<=right[j]) {
				arr[k]=left[i];
				i++;
			}else {
				arr[k]=right[j];
				j++;
			}
			k++;
		}
		//copy remaining elements to arr
		while(i<n1) {
			arr[k]=left[i];
			i++;k++;
		}
		
		while(j<n2) {
			arr[k]=right[j];
			j++;k++;
		}
		
	}
	
	
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
public class MergeSort {

	public static void main(String[] args) {
		
		int arr[] = { 12, 11, 13, 5, 6, 7 };

		merg_sort ob = new merg_sort();
		ob.sort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array is");
		ob.printArray(arr);
		
//		int[] arr2 = { 12, 11, 13, 5, 6, 7 };
//	
//		m_sort m = new m_sort();
//		m.mergeSort(arr2,0,arr2.length-1);
//
//		m.printArray(arr2);
	}
	

}
