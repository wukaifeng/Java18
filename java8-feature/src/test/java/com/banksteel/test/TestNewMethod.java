package com.banksteel.test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * 测试jdk8新增方法
 * 
 * @author wukaifeng
 *
 */
public class TestNewMethod {

    /**
     * Map 的新增方法
     * 
     */
    @Test
    public void testMapNewMethod() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", null);
        map.put("2", "2");
        // getOrDefault、forEach
        Object val = map.getOrDefault("3", "123");
        System.out.println("val:" + val);
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
        // compute
        String msg = "测试";
        map.compute("1", (k, v) -> (v == null) ? msg : ((String) v).concat(msg));
        System.out.println("======================");
        System.out.println(map.get("1"));
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });

        // computeIfAbsent
        map.computeIfAbsent("1", k -> {
            return "123456";
        });
        System.out.println("======================");
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });

        // computeIfPresent
        map.computeIfPresent("4", (k, v) -> {
            return "123456";
        });
        System.out.println("======================");
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
        // merge、
        map.merge("7", "ddddd", (k, v) -> "hhhhh");
        System.out.println("======================");
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
        // putIfAbsent、
        Object putIfAbsent = map.putIfAbsent("1", "dddd");
        System.out.println("======================");
        System.out.println(putIfAbsent);
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
        // remove(key,value)
        map.put("11", "123");
        map.remove("11", "123");
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
        // replaceAll、replace
        map.replaceAll((k, v) -> {
            return k + v;
        });

        System.out.println("====================================");
        map.forEach((k, v) -> {
            System.out.println("K：" + k + "_v:" + v);
        });
    }

}
