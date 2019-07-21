package com.banksteel.test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import org.junit.Test;

import com.banksteel.interfaces.MyFun;

/**
 * Lambda 表达式测试类2
 * 
 * @author wukaifeng
 *
 */
public class TestLambda2 {
	
	// 语法格式1： 无参数、无返回值
	// () -> System.out.println("hello lambda!"); 
	@Test
	public void test1() {
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello world!");
			}
		};
		r1.run();
		
		Runnable r2 = () -> System.out.println("hello lambda!");
		r2.run();
	}
	
	// 语法格式2： 有一个参数，无返回值
	// (x) -> System.out.println(x);
	@Test
	public void test2() {
		Consumer<String> consumer = (x) -> System.out.println(x);
		consumer.accept("hello lambda!");
	}
	
	// 语法格式3： 有一个参数，无返回值, 当有一个参数时，小括号可以省略。
	// x -> System.out.println(x);
	@Test
	public void test3() {
		Consumer<String> consumer = x -> System.out.println(x);
		consumer.accept("hello lambda!");
	}
	
	// 语法格式4：两个参数，有返回值
	@Test
	public void test4() {
		Comparator<Integer> comparator = (x, y) -> {
			return Integer.compare(x, y);
		};
		int compare = comparator.compare(3, 2);
		System.out.println(compare);
	}
	
	// 语法格式5：只有一条语句，大括号和return可以省略
	@Test
	public void test5() {
		Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
		int compare = comparator.compare(3, 2);
		System.out.println(compare);
	}
	
	// 语法格式6：类型推断
	@Test
	public void test6() {
		BinaryOperator<Integer> binaryOperator = (Integer x, Integer y) -> {
			System.out.println("测试两个参数、类型推断");
			return x + y;
		};
		Integer value = binaryOperator.apply(12, 13);
		System.out.println("value:" + value);
	}
	
	// 测试Lambda
	@Test
	public void test7() {
		Integer value = operation(2, x -> x * x);
		System.out.println("value:" + value);
		value = operation(6, x -> x - 3);
		System.out.println("value:" + value);
	} 
	
	public Integer operation(Integer operate, MyFun<Integer> myFun) {
		
		return myFun.getValue(operate);
	}
	
	
}
