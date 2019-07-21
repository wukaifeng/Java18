package com.banksteel.bean;

import com.banksteel.test.TestCollector.CaloricLevel;

public class Dish {
	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;

	}

	public Type getType() {
		return type;
	}
	
	public  static CaloricLevel getCaloricLevel(Dish dish){
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {
		MEAT, FISH, OTHER
	}
}
