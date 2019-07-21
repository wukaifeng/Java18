package com.banksteel.interfaces;

@FunctionalInterface
public interface MyFun<T> {
	
	public T getValue(T t);
}
