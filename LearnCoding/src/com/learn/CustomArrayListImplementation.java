package com.learn;

import java.util.ArrayList;

public class CustomArrayListImplementation extends ArrayList<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Integer ele) {
		
		if(!super.contains(ele)) {
			super.add(ele);
			return true;
		}
		return false;
		
	}


}


