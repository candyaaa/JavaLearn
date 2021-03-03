package pers.study.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用：通过引用获取不到对应的对象，它的作用就是每次回收都会在QUEUE中增加一条数据
 * 用于DirectByteBuffer。直接内存的管理
 * 如果 a -> DirectByteBuffer -> 直接内存,此时DirectByteBuffer被回收，则在QUEUE中添加一条数据，提示GC进行回收堆外内存
 */
public class PhantomReferenceCase {
    private static final List<Object> LIST = new LinkedList<Object>();
    private static final ReferenceQueue QUEUE = new ReferenceQueue();

    public static void main(String[] args) {
        PhantomReference<Example> phantomReference = new PhantomReference<Example>(new Example(), QUEUE);
        new Thread(() -> {
            for (; ; ) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get());
            }
        }).start();
        new Thread(() -> {
            for (; ; ) {
                Reference poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("虚引用被jvm回收");
                }
            }
        }).start();
    }
}
