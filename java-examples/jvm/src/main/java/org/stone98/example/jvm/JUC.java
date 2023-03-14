package org.stone98.example.jvm;

public class JUC {
    
    public static void main(String[] args) {
        ThreadLocal t = new ThreadLocal();
        t.set(1);
        System.out.println(t.get());
        ThreadLocal b =new ThreadLocal();
        b.set(2);
        System.out.println(b.get());
    }
    
}
