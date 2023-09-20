package com.pav2py.service.abstraction;

import com.pav2py.model.abstraction.WorkBookClass;

public abstract class NVMService {

	public String name;

	public String path;

	public WorkBookClass workBook;

	public abstract boolean validate();

	public abstract void createSheets();

}
