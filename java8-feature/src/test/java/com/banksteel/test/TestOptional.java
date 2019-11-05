package com.banksteel.test;

import java.util.Optional;
import org.junit.Test;
import com.banksteel.bean.Car;
import com.banksteel.bean.Insurance;
import com.banksteel.bean.OptionCar;
import com.banksteel.bean.OptionalPerson;
import com.banksteel.bean.Person;

public class TestOptional {
    
    @Test
    public void test1() {
        Person person = new Person();
        // 下面代码会报空指针异常
        String name = person.getCar().getInsurance().getName();
        System.out.println("name:" + name);
    }
    
    public String test2() {
        Person person = new Person();
        if (null != person) {
            Car car = person.getCar();
            if (null != car) {
                Insurance insurance = car.getInsurance();
                if (null != insurance) {
                    return insurance.getName();
                }
            }
        }
        return "UnKnown";
    }
    
    public String test3() {
        Person person = new Person();
        Car car = person.getCar();
        if (car == null) {
            return "unKnown";
        }
        Insurance insurance = car.getInsurance();
        if (null == insurance) {
            return "unKnown";
        }
        
        return insurance.getName();
    }
    
    @Test
    public void test4() {
        OptionalPerson opPerson = new OptionalPerson();
        Optional<OptionCar> optionCar = opPerson.getOptionCar();
        // 创建一个null的car对象
        Optional<Car> optionCar2 = Optional.empty();
        
        Car car = null;
        car = new Car();
        // 创建optional对象的方法1
        Optional<Car> ofCar = Optional.of(car);
        car = null;
        Optional<Car> ofNullable = Optional.ofNullable(car);
        Car car2 = ofNullable.orElseGet(Car :: new);
    }
    
    @Test
    public void test5() {
        OptionCar car = new OptionCar();
        Optional<OptionCar> ofCar = Optional.of(car);
        Optional<Optional<Insurance>> insuranceOptional = ofCar.map(OptionCar :: getInsurance);
        Optional<Insurance> insurance = ofCar.flatMap(OptionCar :: getInsurance);
    }
    
    @Test
    public void test6() {
        OptionalPerson person = new OptionalPerson();
        Optional<OptionalPerson> optionalPerson = Optional.of(person);
        // flatMap 返回值不能为null
        Optional<OptionCar> flatMap = optionalPerson.flatMap(OptionalPerson :: getOptionCar);
        String insuranceName = flatMap.flatMap(OptionCar :: getInsurance).map(Insurance :: getName).orElse("unKnown");
        System.out.println("insuranceName:" + insuranceName);
    }
    
    @Test
    public void test7() {
        OptionalPerson person = null;
        Optional<OptionalPerson> optionalPerson = Optional.ofNullable(person);
        System.out.println("isPresent:" + optionalPerson.isPresent());
        OptionalPerson person2 = new OptionalPerson();
        OptionCar optionCar = new OptionCar();
 
        person2.setOptionCar(Optional.ofNullable(optionCar));
        OptionalPerson orElse = optionalPerson.orElse(person2);
        Optional<OptionCar> optionCar2 = orElse.getOptionCar();
    }

}
