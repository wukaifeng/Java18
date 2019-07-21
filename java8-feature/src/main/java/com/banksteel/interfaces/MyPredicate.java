package com.banksteel.interfaces;

@FunctionalInterface
public interface MyPredicate <T>{
	public Boolean test(T t);

	boolean equals(Object obj);
}
