package pers.study.juc;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinDemoTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1,10);
        Future<Integer> future = forkJoinPool.submit(forkJoinDemo);
        Integer sum = future.get();
        System.out.println(sum);
    }

}
