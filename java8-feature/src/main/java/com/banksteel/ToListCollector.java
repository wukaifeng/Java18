package com.banksteel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	@Override
	public Supplier<List<T>> supplier() {
		return () -> new ArrayList<T>();
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
            };
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		// TODO Auto-generated method stub
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(EnumSet.of(
				Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

}
