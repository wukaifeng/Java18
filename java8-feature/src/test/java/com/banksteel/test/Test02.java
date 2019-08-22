package com.banksteel.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import com.banksteel.bean.DataSource;
import com.banksteel.bean.Student;

public class Test02 {

    public static void main(String[] args) {
        // 获取流
        Stream<Student> s = DataSource.getStu().stream();
        Stream.of(12,2,5,6,33);
        Stream.of(DataSource.getStu());
        // 终止操作
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(6);
        Optional<Integer> sum = list.stream().reduce((n,n1) -> n + n1);
        System.out.println(sum.get());

        int num = list.stream().reduce(3, (n,n1) -> n+n1);
        System.out.println(num);

        Optional<Student> stus = s.reduce((s2,s1) -> {
            Student temstu = new Student();
            temstu.setAge(s2.getAge()+s1.getAge());
            return temstu;
        });
        System.out.println(stus.get().getAge());

        /**
         * 方法 描述：
         * 1. allMatch(Predicate p) 传入一个断言型函数，对流中所有的元素进行判断，
         * 如果都满足返回true，否则返回false。
         * 2. anyMatch(Predicate p) 传入一个断言型函数，对流中所有的元素进行判断，只要有一
         * 个满足条件就返回true，都不满足返回false。
         * 3. noneMatch(Predicate p) 所有条件都不满足，返回true，否则返回false。
         * 4. findFirst() 返回流中的第一个元素。
         * 5. findAny() 并行流下返回流中的任意一个元素，串型流下和findFirst()作用一样。
         * 6. count() 返回流中元素的个数。
         * 7. max(Comparator c) 按照给定的排序规则进行排序后，返回流中最大值的元素
         * 8. min(Comparator c) 按照给定的排序规则进行排序后，返回流中最小值的元素
         * 9. forEach(Consumer c) 内部迭代
         */

        // 由于s流对象在上面已经别使用过后，就不能再次被使用，只能使用一次
//        boolean  b = DataSource.getStu().stream().allMatch((s1) -> s1.getAge() > 12);
//        System.out.println(b);
//        b = DataSource.getStu().stream().anyMatch((s1) -> s1.getAge() > 12);
//        System.out.println(b);
//        Optional<Student> student = DataSource.getStu().stream().findFirst();
//        System.out.println(student);
//        student = DataSource.getStu().stream().findAny();
//        System.out.println(student);
//        System.out.println(DataSource.getStu().stream().count());
//
//        System.out.println(DataSource.getStu().stream().max((n1,n2) -> n1.getAge() - n2.getAge()));
//        System.out.println(DataSource.getStu().stream().min((n1,n2) -> n1.getAge() - n2.getAge()));
//
//        DataSource.getStu().forEach((s1) ->{
//            System.out.println(s1);
//        });

        // Stream的终止操作-收集
//        List<Student> studentList = DataSource.getStu().stream().collect(Collectors.toList());
//        System.out.println(studentList);

//        Set<Student> set = DataSource.getStu().stream().collect(Collectors.toSet());
//        System.out.println(set);

//        Map<String,Student> studentMap = DataSource.getStu().stream().collect(Collectors.toMap(s1 -> s1.getName(), s2 -> s2));
//        System.out.println(studentMap);

        /**
         * 方法 描述
         * 1. filter(Predicate d) 接受一个断言型函数，对Stream流中的元素进行处理，过滤掉不满足条件的元素
         * 2. distinct 筛选元素，通过Stream元素中的hasCode和equals方法来去除重复元素
         * 3. limit(long maxSize) 截断流，使元素不超过manSize指定的数量
         * 4. skip(Long n) 跳过元素，返回一个扔掉了前n个元素的流，若流中的元素不足n个，      则会返回一个空流
         */
//        System.out.println(DataSource.getStu().stream().filter((s1) -> s1.getScore() < 80).collect(Collectors.toList()));
//
//        System.out.println(list.stream().distinct().collect(Collectors.toList()));

//        System.out.println(list.stream().limit(2).collect(Collectors.toList()));
//
//        System.out.println(list.stream().skip(2).collect(Collectors.toList()));

//

//         map(Function f) 接受一个函数型接口作为参数，该函数会对流中的每个元素进行处理，返回处理后的流
//        List<String> names = DataSource.getStu().stream().map((s1) -> s1.getName()).collect(Collectors.toList());
//        System.out.println(names);
//


        // flatMap(Function f) 接受一个函数作为参数，将流中的每个值都转换成一个新流，最后再将这些流连接到一起
//        List<String> strs = new ArrayList<>();
//        strs.add("hell");
//        strs.add("jjdd");
//        System.out.println(strs.stream().map((s1) -> s1.split("")).flatMap( str -> Arrays.stream(str)).collect(Collectors.toList()));


        // sorted(Comparator comp) 返回一个新流，并且Comparator指定的排序方式进行排序
//        System.out.println(list.stream().sorted().collect(Collectors.toList()));

//        System.out.println(DataSource.getStu().stream().sorted().collect(Collectors.toList()));
//        System.out.println(DataSource.getStu().stream().sorted((s1 ,s2) -> s1.getScore() - s2.getScore()).collect(Collectors.toList()));

//        DataSource.getStu().parallelStream().sorted((s1 ,s2) -> s1.getScore() - s2.getScore()).collect(Collectors.toList());


        // 并行流
        Long start = System.currentTimeMillis();
        Long nums = LongStream.rangeClosed(1,5000000000L).parallel().reduce(1,(n,n1) -> n+n1);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
