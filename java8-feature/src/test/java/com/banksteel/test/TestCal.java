package com.banksteel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class TestCal {
    
    List<Map<String, Integer>> mapList = new ArrayList<>();
    
    
    @Test
    public void test1() {
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("num", 11);
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("num", 11);
        mapList.add(map1);
        mapList.add(map2);
        
       double sum = mapList.stream().map(x->x.get("num")).mapToDouble(x-> x.doubleValue()).sum();
        System.out.println("sum:" + sum);
    }
}
