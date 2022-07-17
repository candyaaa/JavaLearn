package github.stone98.example.java.basic.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Create: 2020-07-17 17:31
 * @description:
 * @author: candy
 **/
public enum SeasonEnum {
    //每一个枚举变量都是枚举类SeasonEnum的实例化
    //SeasonEnum.SPRING获得的对象相当于new SeasonEnum("春","春困");获得的对象
    SPRING("春","春困"),SUMMER("夏","夏睡"),AUTUMN("秋","秋乏"),WINTER("冬","冬眠");

    //强制私有化的构造方法，因为枚举类默认继承Enum类，被final static修饰，不能被继承
    //由于被强制private，所以不能使用new去生成枚举对象
    //在SeasonEnum.SPRING获得对象，隐式调用了下面的构造方法
    // 构造方法 ,赋值给成员变量
    private SeasonEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }


    //添加成员变量的原因是，方便够着方法赋值，使用SeasonEnum.SPRING.getName就能获取对应的值
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String value;

}
