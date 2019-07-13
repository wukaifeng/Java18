package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.banksteel.bean.Dish;
import com.banksteel.bean.Dish.Type;

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
				.count();

		System.out.println("count:" + count);
	}
	
}
