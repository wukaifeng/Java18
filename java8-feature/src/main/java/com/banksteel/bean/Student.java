package com.banksteel.bean;

public class Student {

    private int age;
    private String name;
    private int score;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student(int age, String name, int score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    public Student() {}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("      \"age\":").append(age);
        sb.append(",       \"name\":\"").append(name).append('\"');
        sb.append(",       \"score\":").append(score);
        sb.append('}');
        return sb.toString();
    }

}
