package com.banksteel.bean;

/**
 * 雇员
 * @author wukaifeng
 *
 */
public class Employee {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 薪水
	 */
	private Double salary;
	
	public Employee() {
		super();
	}
	public Employee(String name) {
		super();
		this.name = name;
	}
	public Employee(String name, Integer age, Double salary) {
	    super();
	    this.name = name;
	    this.age = age;
	    this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public String test(int x, int y) {
		System.out.println("x:" + x + "_y: " + y);
		return "";
	}
	public String test2(int x) {
		System.out.println("x:" + x );
		
		return "x:" + x ;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
}
