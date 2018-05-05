package gww.testapp.data;

import java.util.Arrays;

import gww.testapp.ui.rxjava2.model.Car;
import gww.testapp.ui.rxjava2.model.User;

/**
 * desc: 简单数据 <br/>
 * time: 2018/5/5 下午5:57 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public interface SimpleData {

    String USER_ZHANG_SAN_JSON = "{\"age\":22,\"name\":\"张三\"}";
    String USER_LI_SI_JSON = "{\"age\":33,\"name\":\"李四\"}";


    Car CAR_AUDI_A3 = new Car("A3", "奥迪");
    Car CAR_AUDI_Q5 = new Car("Q5", "奥迪");
    Car CAR_BMW_X3 = new Car("X3", "宝马");
    Car CAR_BMW_X6 = new Car("X6", "宝马");

    User USER_ZHANG_SAN = new User("张三", 22, Arrays.asList(CAR_AUDI_A3, CAR_AUDI_Q5));
    User USER_LI_SI = new User("李四", 33, Arrays.asList(CAR_BMW_X3, CAR_BMW_X6));


}