package org.stone98.example.jvm;

/**
 * @Description: VM Args: -Xss128k 测试StackOverflowError
 * @Author: stone-98
 * @createTime: 2023年01月13日 11:49:23
 */
public class JavaVMStackSof01 {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSof01 oom = new JavaVMStackSof01();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw new RuntimeException(e);
        }
    }
}
