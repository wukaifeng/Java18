package com.banksteel;

import com.banksteel.bean.Employee;
import com.banksteel.interfaces.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

	@Override
	public Boolean test(Employee t) {
		return t.getAge() > 35;
	}

}
