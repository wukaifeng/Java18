package com.banksteel.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;
import com.banksteel.bean.Employee;

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
    
    List<Employee> employees = Arrays.asList(
        new Employee("张三", 18, 9999.99),
        new Employee("李四", 38, 5555.99),
        new Employee("王五", 50, 6666.66),
        new Employee("赵六", 16, 3333.33),
        new Employee("田七", 8, 7777.77));

    @Test
    public void test1() {

        list.replaceAll(x -> x * x);

        list.stream().forEach(System.out::println);

    }
    
    @Test
    public void test2() {
        Comparator<Employee> comparing = Comparator.comparing(Employee :: getAge).thenComparing(Employee :: getSalary);
        Comparator<Employee> comparingDouble = Comparator.comparingDouble(Employee :: getSalary);
        employees.sort(comparing);
    }
}
