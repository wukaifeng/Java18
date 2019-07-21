package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.banksteel.bean.Dish;
import com.banksteel.bean.Trader;
import com.banksteel.bean.Transaction;

/**
 * 收集器测试
 * 
 * @author wukaifeng
 *
 */
public class TestCollector {
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");
	
	List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700), 
			new Transaction(alan, 2012, 950));
	
	List<Dish> menu = new ArrayList<>();
	{
		menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT), 
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER), 
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
	}

	/**
	 * java7交易按照货币分组
	 */
	@Test
	public void test1() {
		Map<Integer, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		for (Transaction transaction : transactions) {
			Integer year = transaction.getYear();
			List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(year);
			if (transactionsForCurrency == null) {
				transactionsForCurrency = new ArrayList<>();
				transactionsByCurrencies.put(year, transactionsForCurrency);
			}
			transactionsForCurrency.add(transaction);
		}
		Set<Entry<Integer, List<Transaction>>> entrySet = transactionsByCurrencies.entrySet();
		for (Entry<Integer, List<Transaction>> entry : entrySet) {
			System.out.println("key:" + entry.getKey());
			System.out.println("value:" + entry.getValue());
		}
	}
	
	@Test
	public void test2() {
		Map<Integer, List<Transaction>> collect = transactions.stream().collect(Collectors.groupingBy(Transaction :: getYear));
		collect.forEach((k, v) -> 
		{
			System.out.println("key:" + k);
			v.stream().forEach(System.out:: println);
			});
	}
	
	@Test
	public void test3() {
		long count = transactions.stream().count();
		System.out.println("count: " + count);
		Long collectCounting = transactions.stream().collect(Collectors.counting());
		System.out.println("collectCounting:" + collectCounting);
	}
	
	@Test
	public void test4() {
		Comparator<Dish> comparator = Comparator.comparingInt(Dish :: getCalories);
		Optional<Dish> collect = menu.stream().collect(Collectors.maxBy(comparator));
		Optional<Dish> collect2 = menu.stream().collect(Collectors.minBy(comparator));
		// Collectors.summingLong
		// Collectors.summingDouble
		int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		//Collectors.averagingInt
		// averagingLong
		// averagingDouble
		double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
		
		IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		/**
		 * IntSummaryStatistics{count=9, sum=4300, min=120,
                         average=477.777778, max=800}
		 */
		// 链接字符串
		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
		String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
	}
	
	@Test
	public void test5() {
		int totalCalories = menu.stream().collect(Collectors.reducing(
                0, Dish::getCalories, (i, j) -> i + j));
		
		Optional<Dish> mostCalorieDish =
			    menu.stream().collect(Collectors.reducing(
			 (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		
		int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
	}
	
	@Test
	public void test6() {

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));
	}
	
	@Test
	 public void test7() {
		 Runnable r2 = () -> System.out.println("Hello");
		 Runnable r3 = System.out::println;
		 r3.run();
	 }
	
	public enum CaloricLevel { DIET, NORMAL, FAT }
}

