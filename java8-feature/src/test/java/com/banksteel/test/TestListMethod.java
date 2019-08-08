package com.banksteel.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * test list
 * 
 * @author wukaifeng
 *
 */
public class TestListMethod {

    List<Long> list = new ArrayList<>();
    {
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(5L);
    }

    @Test
    public void test1() {

        list.replaceAll(x -> x * x);

        list.stream().forEach(System.out::println);

    }
}
