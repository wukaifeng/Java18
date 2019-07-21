package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test;

import com.banksteel.bean.Employee;
import com.banksteel.interfaces.MyPredicate;

/**
 * Lambda 表达式测试类
 * 
 * @author wukaifeng
 *
 */
public class TestLambda {

	@Test
	public void test1() {
		// set排序
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			} 
		};
		TreeSet<Integer> treeSet = new TreeSet<>(com);
		treeSet.add(5);
		treeSet.add(3);

		System.out.println("treeSet:" + treeSet);
	}

	@Test
	public void test2() {
		// jdk8 set时写排序、Lambda表达式
		Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
		// Comparator<Integer> com = Integer :: compare;
		TreeSet<Integer> treeSet = new TreeSet<>(com);
		treeSet.add(5);
		treeSet.add(3);
		treeSet.forEach(x -> System.out.println(x));
		//treeSet.forEach(System.out::println);
	}
	
	@Test
	public void test3() {
		// jdk8 set时写排序、Lambda表达式
		TreeSet<Integer> treeSet = new TreeSet<>(Integer :: compare);
		treeSet.add(5);
		treeSet.add(3);
		treeSet.forEach(System.out::println);
	}
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 38, 5555.99),
			new Employee("王五", 50, 6666.66),
			new Employee("赵六", 16, 3333.33),
			new Employee("田七", 8, 7777.77));
	
	@Test
	public void test4() {
		// 年龄大于35
		List<Employee> filterEmployees = filterEmployees(employees);
		
		// 大于5000
		filterEmployees = filterEmployees2(employees);
		
		// 策略模式优化
		filterEmployees = filterEmployee(employees, new MyPredicate<Employee>() {

			@Override
			public Boolean test(Employee t) {
				return t.getName().equals("张三");
			}
		});
		for (Employee employee : filterEmployees) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void test5() {
		List<Employee>  filterEmployees = filterEmployee(employees, new MyPredicate<Employee>() {
			@Override
			public Boolean test(Employee t) {
				return t.getAge() > 35;
			}
		});
		// lambda 写法
		filterEmployees = filterEmployee(employees, x -> x.getAge() > 35);
		filterEmployees.forEach(System.out :: println);
	}
	
	@Test
	public void test6() {
		// Stream API 写法
		employees.stream().filter(x -> x.getAge() > 35)
						  .forEach(System.out :: println);
		// 获取名称
		List<String> names = employees.stream()
									  .filter(x -> x.getSalary() > 5000)
									  .sorted(Comparator.comparing(Employee :: getSalary))
									  .limit(2)
									  .map(Employee :: getName)
									  .collect(Collectors.toList());
		
		names.forEach(System.out :: println);
		
		// 按工资分组
		Map<Integer, List<Employee>> map = employees.stream()
													.filter(x -> x.getAge() > 35)
													.sorted(Comparator.comparing(Employee :: getSalary))
													.skip(1)
													.limit(10)
													.collect(Collectors.groupingBy(Employee :: getAge));
		map.forEach((x, y) -> {
			System.out.println("key:" + x);
			System.out.println("value:" + y);
		});
	}
	
	// 需求：获取当前公司中年龄大于35的员工信息
	public List<Employee> filterEmployees(List<Employee> employees) {
		List<Employee> emps = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getAge() > 35) {
				emps.add(employee);
			}
		}
		return emps;
	}
	// 获取员工公司大于5000
	public List<Employee> filterEmployees2(List<Employee> employees) {
		List<Employee> emps = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getSalary() > 5000) {
				emps.add(employee);
			}
		}
		return emps;
	}
	
	public List<Employee> filterEmployee(List<Employee> employees, MyPredicate<Employee> myPredicate) {
		List<Employee> emps = new ArrayList<>();
		for (Employee employee : employees) {
			if (myPredicate.test(employee)) {
				emps.add(employee);
			}
		}
		return emps;
	}
}
