package pers.study.reference;

import java.io.IOException;

/**
 * 强引用:无论如何都不会被回收，除非没有被引用
 */
public class NormalReferenceCase {
    public static void main(String[] args) {
        String s = "发送%s请求到Pod Proxy失败,响应";
        System.out.println(String.format(s,"hh"));
    }
}
