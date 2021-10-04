package github.candy.java.learn.basic.enums;

/**
 * 枚举是一个特殊的class，
 * 相当于被final static修饰，不能被继承
 * 构造方法被强制私有化
 * 所有得枚举都隐式继承Enum类，因此不能再继承其他得类
 *
 * @author candy
 */
public enum ColorEnum {
    // 每个枚举变量都是这个枚举类得实例相当于RED = new ColorEnum(1); 有序列号
    RED, GREEN, BLANK, YELLOW
}
