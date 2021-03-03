package pers.study.reference;

import java.io.IOException;

/**
 * 强引用:无论如何都不会被回收，除非没有被引用
 */
public class NormalReferenceCase {
    public static void main(String[] args) throws IOException {
        Example example = new Example();
        example = null;
        System.gc();
        System.in.read();
    }
}
