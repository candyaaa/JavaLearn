package pers.study.juc;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Integer> {

    private static final Integer MAX = 2;

    private int start;

    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (start - end < MAX) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            Integer leftJoin = leftTask.join();
            Integer rightJoin = rightTask.join();
            sum = leftJoin + rightJoin;
        }
        return sum;
    }
}
