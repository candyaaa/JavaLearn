package github.stone98.example.java.basic.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用：内存不足时会进行清除
 * Xmx20M
 */
public class SoftReferenceCase {
    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        Thread.sleep(5L);
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024 * 1024 * 11];
        System.out.println(softReference.get());
    }
}
