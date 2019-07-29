package com.banksteel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import com.banksteel.bean.Employee;

/**
 * Map测试
 * 
 * @author wukaifeng
 *
 */
public class TestMap {
    
    @Test
    public void test1() {
       Map<String, Object> map1 = new HashMap<>();
       map1.put("1", null);
       map1.put("2", "2");
       map1.put("2", "3");
       System.out.println(map1);
    }
    
    List<Employee> emps = new ArrayList<>();
    {
        Employee emp1 = new Employee();
        emp1.setAge(12);
       // emp1.setName("zhangsan");
        Employee emp2 = new Employee();
        emp2.setAge(12);
       // emp2.setName("zhangsan1");
        emps.add(emp1);
        emps.add(emp2);
    }
    
    /**
     * 1、toMap的value不能为null
     * 2、toMap的可以key是不能重复
     */
    @Test
    public void test2() {
        // jdk8 toMap
        Map<Integer, String> map = emps.stream().collect(Collectors.toMap(Employee::getAge, Employee :: getName));
        System.out.println(map);
    }
    
    @Test
    public void test3() {
        Map<Integer, String> memberMap = emps.stream().collect(Collectors.toMap(Employee::getAge, Employee :: getName, (x, y) -> y));
        System.out.println(memberMap);
    }
    
    @Test
    public void test4() {
        Map<Integer, String> memberMap = emps.stream().collect(Collectors.toMap(Employee::getAge, Employee :: getName, (x, y) -> y, HashMap ::new));
        System.out.println(memberMap);
    }
    
    @Test
    public void test5() {
        Map<Integer, String> memberMap = emps.stream().collect(HashMap::new, (m,v)->
        m.put(v.getAge(), v.getName()),HashMap::putAll);
        System.out.println(memberMap);
    }
    
    @Test
    public void test6() {
        Map<Integer, String> memberMap = new HashMap<>();
        emps.forEach((employee) -> memberMap.put(employee.getAge(), employee.getName()));
        System.out.println(memberMap);
    }
    
}
