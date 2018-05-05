package gww.testapp.ui.rxjava2.model;

import java.util.List;

/**
 * desc: 用户Bean <br/>
 * time: 2018/5/5 下午12:48 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class User {

    private String name;
    private int age;
    private List<Car> cars;

    public User() {

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, List<Car> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "name:" + name + ", age:" + age;
    }

}
