package org.stone98.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存异常
 */
public class HeapOOM {
    static class OOMObject{

    }

    /**
     * VM args：-Xms20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) throws Throwable {
        List<OOMObject> oomObjects = new ArrayList<OOMObject>();
        while (true) {
            Thread.sleep(1);
            oomObjects.add(new OOMObject());
        }
    }
}
