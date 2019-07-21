package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.banksteel.bean.Dish;
import com.banksteel.bean.Dish.Type;
import com.banksteel.test.TestCollector.CaloricLevel;

/**
 * 流测试
 * 
 * @author wukaifeng
 *
 */
public class TestStream {
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
	@Test
	public void test2_jdk8() {
		List<String> lowCaloricDishesName = menu.stream()
				.filter(x -> x.getCalories() < 400)
				.sorted(Comparator.comparing(Dish:: getCalories))
				.map(Dish:: getName)
				.collect(Collectors.toList());
//		for (String dishName : lowCaloricDishesName) {
//			System.out.println("dishName:" + dishName);
//		}
		//Consumer con = x -> System.out.println(x);
		lowCaloricDishesName.forEach(System.out :: println);
	}
	
	@Test
	public void test1_jdk7() {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish d : menu) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());

			}
		});

		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish dish : lowCaloricDishes) {
			lowCaloricDishesName.add(dish.getName());
		}
		
		for (String dishName : lowCaloricDishesName) {
			System.out.println("dishName:" + dishName);
		}

	}
	
	@Test
	public void testGroupingBy() {
		Map<Type, List<Dish>> collectMap = menu.stream().collect(Collectors.groupingBy(Dish :: getType));
		collectMap.forEach((k, v) -> {
			System.out.println("key:" + k);
			v.forEach(System.out :: println);
		});
	}
	
	@Test
	public void test2() {
		List<String> names = menu.stream().
				filter(d -> {
					System.out.println("filtering" + d.getName());	
					return d.getCalories() > 300;})
				.map(d -> {
					System.out.println("mapping" + d.getName());
					return d.getName();})
				.limit(3)
				.collect(Collectors.toList());

		System.out.println(names);
	}

	@Test
	public void test3() {
		long count = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.distinct()
				.limit(3)
				.skip(1)
				.count();

		System.out.println("count:" + count);
	}
	
	@Test
	public void testDishNameLengths() {
		List<Integer> collect = menu.stream()
				.map(Dish :: getName)
				.map(String :: length)
				.collect(Collectors.toList());
		
		collect.forEach(System.out::println);
	}
	
	@Test
	public void test4() {
		String[] words = {"Hello","World"};
		Stream<String> wordList = Arrays.stream(words);
		List<String[]> collect = wordList.map(x -> x.split("")).distinct().collect(Collectors.toList());
		collect.forEach(x -> {
			Arrays.asList(x).forEach(System.out :: println);
		});
	}
	@Test
	public void test5() {
		String[] words = {"Hello","World"};
		Stream<String> wordList = Arrays.stream(words);
		Stream<String[]> map = wordList.map(x -> x.split(""));
		Stream<Stream<String>> map2 = map.map(Arrays :: stream);
		map2.distinct().forEach(x -> {
			x.forEach(y -> {
				System.out.println(y);
			});
		});
	}
	
	@Test
	public void test6() {
		String[] words = {"Hello","World"};
		Stream<String> wordList = Arrays.stream(words);
		Stream<String[]> map = wordList.map(x -> x.split(""));
		Stream<String> map2 = map.flatMap(Arrays :: stream);
		map2.distinct().forEach(System.out :: println);
	}
	/*
	 * 给 一个数  表，如何返回一个由每个数的 方构成的 表呢?例如，给 [1, 2, 3, 4,
			5]，应该返回[1, 4, 9, 16, 25]
	 */
	@Test
	public void test7() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers.stream()
				.map(n -> n * n)
				.collect(Collectors.toList());
		squares.forEach(System.out :: println);
	}
	/**
	 * 给 两个数  表，如何返回所有的数对呢?例如，给  表[1, 2, 3]和 表[3, 4]，应 该返回
	 * [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
	 * 为简单起 ，你可以用有两个元素的数组来代 表数对。
	 */
	@Test
	public void test8() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3); 
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream()
		                 .flatMap(i -> numbers2.stream()
		                		 			   //.filter(j -> (i + j) % 3 == 0)
		                                       .map(j -> new int[]{i, j}))
		                 .collect(Collectors.toList());
		pairs.forEach(x -> {
			Arrays.stream(x).forEach(System.out :: println);
		});
	}
	
	@Test
	public void test9() {
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
			menu.stream().collect(Collectors.groupingBy(Dish :: getCaloricLevel));
	}
	
}
