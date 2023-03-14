package org.stone98.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+PrintGCDetails  打印GC详细日志
 * -XX:+UseSerialGC   标明新生代、新生代使用Serial GC
 * -XX:+UseParNewGC   标明新生代使用ParNew GC
 * -XX:+UseParallelGC 表明老年代使用Parallel Old GC
 * -XX:+UseParallelOldGC  表明老年代使用Parallel Old GC
 * -XX:+UseConcMarkSweepGC 表明老年代使用CMS GC。同时，年轻代会触发对ParNew的使用
 */
public class CGUseTest {
    
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        while (true){
            byte[] bytes = new byte[1024 * 10];
            list.add(bytes);
        }
    }
}
