package github.stone98.example.java.netty4.serialization.hessian.example;

import github.stone98.example.java.netty4.serialization.hessian.Hessian2Utils;
import org.junit.Test;

/**
 * @author shikui@tidu.com
 * @date 2021/11/13
 */
public class Hessian2Example {
    @Test
    public void serialization() throws Exception {
        byte[] hessian2Data;
        Car deserializeCar;

        Car car = new Car();
        car.setName("布加迪");
        car.setPrice(12000000);
        car.setColor("红色");
        car.setLength(2980);

        hessian2Data = Hessian2Utils.serialize(car);
        System.out.println("Hessian序列化数据：" + hessian2Data);

        deserializeCar = Hessian2Utils.deserialize(hessian2Data);
        System.out.println("Hessian反序列化数据：" + deserializeCar);
    }
}
