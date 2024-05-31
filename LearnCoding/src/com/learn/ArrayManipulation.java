package com.learn;

import java.util.Arrays;

public class ArrayManipulation {
	
	static void secHeighestCountWord() {
		
		String[] str = {"ab","ab","abc","bb","bb","bb"};
		//output ab
	}
	
	static void missingElements() {
		 int arr[] = { 6, 7, 10, 11, 13 };
		 int N = arr.length; 
		 
		 int diff = arr[0] - 0; 
		 
		    for(int i = 0; i < N; i++) 
		    { 
		        if (arr[i] - i != diff) 
		        { 
		            while (diff < arr[i] - i) 
		            { 
		                System.out.print((i + diff) + " "); 
		                diff++; 
		            } 
		        } 
		    } 
	}
	
	void separateElements() {
		
		int a[] = {-9,-1,3,-4,-6,-7,0,2,4};
		//output : 2 0 2 4 -1 -4 -6 -7
		
		int i=0,j=a.length-1;
		while(i<j) {
			while(a[i]>=0) {
				i++;
				System.out.print(i+"  >>> ");
				break;
			}
			while(a[j]<=0){
				j--;
				break;
			}
		
//			swap(a[i],a[j]);
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		
		for(int z=0;z<a.length;z++) {
			System.out.print(a[z]+" ");
		}
		
	}

	private void swap(int i, int j) {
		int t = i;
		i = j;
		j = t;
		
	}

	public static void main(String[] args) {
		missingElements();
	}
}
