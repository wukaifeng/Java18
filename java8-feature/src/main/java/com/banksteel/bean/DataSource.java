package com.banksteel.bean;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Student> getStu() {
        List<Student> stu = new ArrayList<>();
        stu.add(new Student(10,"张三",70));
        stu.add(new Student(13,"李四",80));
        stu.add(new Student(11,"王二",60));
        stu.add(new Student(8,"麻子",90));
        stu.add(new Student(7,"还有你",75));

        return stu;
    }
}
