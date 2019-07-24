package com.banksteel;

import com.banksteel.bean.Employee;
import com.banksteel.interfaces.MyPredicate;

/**
 * 过滤年龄
 * @author 吴凯丰
 *
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

	@Override
	public Boolean test(Employee t) {
		return t.getAge() > 35;
	}

}
