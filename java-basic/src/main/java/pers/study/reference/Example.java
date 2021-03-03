package pers.study.reference;

public class Example {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("example被jvm回收");
    }
}
