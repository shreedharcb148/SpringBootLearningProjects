package com.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StringManipulation {

	static void repeatNonrepeatCharsInStr() {
		String str = "xababacde";
		//out1 s1 = "xcde";
		//out2 s2 = "ab";
		
		char[] arr = str.toCharArray();
		
		String s1="",s2="";
		 
		Map<Character,Integer> map = new HashMap();
		
		for(int i=0;i<arr.length;i++) {
			if(!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			}else {
				int v = map.get(arr[i]) + 1;
				map.put(arr[i], v);
			}
		}
	
		for(int i=0;i<str.length();i++) {
			
			char c = str.charAt(i);
			
			str.replace(str.charAt(i)+"", "");
			System.out.println(str);
			
			if(str.contains(c+"")) {
				s2 = s2 + c;
			}else {
				s1 = s1 + c;
			}
			str.replace(str.charAt(i), c);
//			System.out.print(str);
		}
		System.out.print(s1 + "\n" + s2);
	}
	
	public static void main(String[] args) {
		repeatNonrepeatCharsInStr();
	}
}
