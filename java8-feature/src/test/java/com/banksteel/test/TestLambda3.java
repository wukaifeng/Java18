package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

import org.junit.Test;

import com.banksteel.bean.Employee;

/**
 * Lambda 表达式测试类3
 * 四大函数式接口
 * 
 * @author wukaifeng
 *
 */
public class TestLambda3 {
	
	// 消费性接口
	// Comsumer<T>:   void accept (T t)
	@Test
	public void test1() {
		Consumer<Double> con = (x) -> System.out.println("吃饭花费：" + x + "元");
		eat(120.22, con);
	}
	public void eat (double money, Consumer<Double> con) {
		con.accept(money);
	}
	
	// 供给性接口：
	// Supplier<T> : T get();
	@Test
	public void test2() {
		List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
		numList.forEach(System.out :: println);
	}
	// 产生指定个数的整数
	public List<Integer> getNumList(Integer num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}
		
		return list;
	}	
	
	// 函数性接口
	@Test
	public void test3() {
		String ret = handleStr("   函数性接口测试      ", (x) -> x.trim());
		// ret = handleStr("函数性接口测试", String :: trim);
		System.out.println(ret);
		ret = handleStr("函数性接口测试", (x) -> x.substring(0, 3));
		
		System.out.println(ret);
		
	}
	// 处理字符串
	public String handleStr (String str, Function<String, String> fun) {
		return fun.apply(str);
	}
	
	// 断言型接口
	@Test
	public void test4() {
		List<String> list = Arrays.asList("hello", "test", "ok", "zhangsan");
		//长度大于3
		List<String> filterStrList = filterStr(list, (x) -> x.length() > 3);
		filterStrList.forEach(System.out :: println);
		
	}
	
	// 满足条件的字符串，放入集合
	public List<String> filterStr(List<String> list, Predicate<String> predicate) {
		List<String> retList = new ArrayList<>();
		for (String str : list) {
			if (predicate.test(str)) {
				retList.add(str);
			}
		}
		
		return retList;
	}
	
	@Test
	public void test5() {
		ToIntFunction<Long> intFunction = (x) -> x.intValue() * x.intValue();
		System.out.println(intFunction.applyAsInt(12L));
	}
}
