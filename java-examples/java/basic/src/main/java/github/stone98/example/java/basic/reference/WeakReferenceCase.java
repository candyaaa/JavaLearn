package github.stone98.example.java.basic.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用:只要发生gc就会回收
 */
public class WeakReferenceCase {
    public static void main(String[] args) {
        WeakReference<Example> weakReference = new WeakReference<Example>(new Example());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
