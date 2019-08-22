package com.banksteel.test;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
interface A {
    int fun(int num, int num1);

    default void fun1(){
        System.out.println("哈哈哈");
    }

    static void fun2(){
        System.out.println("你比");
    }
}
public class Test01 {

    public static void main(String[] args) {
//        A a = (s,s1) -> s + s1;
//
//        System.out.println(a.fun(10,10));
//
//        a.fun1();
//        A.fun2();

        /*
              1.function 功能型接口 有个参数又返回值
              2.consumer 消费型接口   有参数，无返回值
              3.supplier 供给型接口  无参数 有返回值
              4.predicate 断言型接口，  返回boolean结果
         */

        Function<Integer,String> fun  = (s) -> String.valueOf(s);
        String str = fun.apply(5);
        System.out.println(str.length());

        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("钢银");

//        Long l = () -> System::currentTimeMillis;
//        System.out.println(supplier.get());
//
//        Predicate<Integer> predicate = (s) -> s > 5;
//        System.out.println(predicate.test(6));


    }
}
