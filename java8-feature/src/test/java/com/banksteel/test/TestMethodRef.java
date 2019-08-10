package com.banksteel.test;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Test;
import com.banksteel.bean.Employee;

/**
 * Lambda 方法引用
 * 
 * @author wukaifeng
 *
 */
public class TestMethodRef {
	
	// 对象实例方法
	@Test
	public void test1() {
		// Consumer<String> consumer = (x) -> System.out.println(x);
		Consumer<String>  consumer = System.out :: println;
		consumer.accept("对象实例方法");
	}
	@Test
	public void test2() {
		Employee emp = new Employee();
		BiConsumer<Integer, Integer> bi = (x, y) -> emp.test(x, y);
		BiConsumer<Integer, Integer> bi2 = emp :: test;
		bi2.accept(4, 3);
	}
	
	@Test
	public void test3() {
		Employee emp = new Employee();
		Function<Integer, String> fun1 = (x) -> "aaaa";
		Function<Integer, String> fun2 = emp :: test2;
		String apply = fun2.apply(12);
		System.out.println(apply);
	}
	
	// 类 :: 静态方法
	@Test
	public void test4() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		com = Integer :: compare;
		int compare = com.compare(12, 10);
		System.out.println("compare:" + compare);
	}
	
	// 类： 实例方法
	@Test
	public void test5() {
		BiPredicate<String, String> bi = (x, y) -> x.equals(y);
		bi = String :: equals;
		boolean test = bi.test("12", "123");
		System.out.println("test:" + test);
	}
	
	// 构造器引用
	@Test
	public void test6() {
		Supplier<Employee> emp = () -> new Employee();
		emp = Employee :: new;
		Employee employee = emp.get();
		System.out.println(employee);
	}
	@Test
	public void test7() {
	    Function<String, Employee> emp = (x) -> new Employee(x);
	    emp = Employee :: new;
	    Employee employee = emp.apply("李四");
	    System.out.println(employee);
	}
	
	// 数组引用
	// Type[] :: new
	@Test
	public void test8() {
		Function<Integer, String[]> fi = (x) -> new String[7];
		fi = String[] :: new;
		String[] apply = fi.apply(10);
		System.out.println(apply.length);
	}
}
