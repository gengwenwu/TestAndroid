package gww.testapp.ui.rxjava2.model;

/**
 * desc: 汽车 <br/>
 * time: 2018/5/5 下午7:12 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class Car {
    private String name;
    private String brand;

    public Car() {
    }

    public Car(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
