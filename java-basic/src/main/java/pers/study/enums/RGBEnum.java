package pers.study.enums;


/**
 * @Create: 2020-07-17 17:35
 * @description:
 * @author: candy
 **/
public enum RGBEnum {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法 ,赋值给成员变量
    private RGBEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }
}
