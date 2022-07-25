package github.stone98.example.java.netty4.futureAndPromise;

import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * {@link io.netty.util.concurrent.Future} and {@link io.netty.util.concurrent.Promise} Test
 *
 * @author stone
 * @date 2021/10/26
 */
@Slf4j
public class FutureAndPromiseTest {

    private final static EventExecutor EXECUTOR = new SingleThreadEventExecutor(null, Executors.defaultThreadFactory(), true) {
        @Override
        protected void run() {
            for (; ; ) {
                Runnable task = takeTask();
                if (task != null) {
                    task.run();
                    updateLastExecutionTime();
                }

                if (confirmShutdown()) {
                    break;
                }
            }
        }
    };

    public CountDownLatch getLatch(int count) {
        return new CountDownLatch(count);
    }

    @Test
    public void testListenerNotifyLater() throws Exception {
        CountDownLatch latch = getLatch(2);
        FutureListener listener = future -> latch.countDown();
        Promise<Boolean> promise = new DefaultPromise(EXECUTOR);
        promise.addListener(listener);
        promise.setSuccess(Boolean.TRUE);
        EXECUTOR.execute(() -> log.info("task1 start execute."));
//        EXECUTOR.execute(() -> log.info("task2 start execute."));
        latch.await();// Wait listener execute finish.
        log.info("task execute result:[{}]", promise.get());
    }
}
