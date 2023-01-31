package org.stone98.example.jvm;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年01月17日 15:34:33
 */
public class ClassInitTest {

    static {
        i = 20;
    }

    static int i = 10;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
