package com.example.redis.lock;

import com.example.redis.lock.RedisApplication;
import com.example.redis.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisApplicationTests {

    @Autowired
    private RedisLock redisLock;

    private int count = 0;


    @Test
    public void test() throws InterruptedException {
        int clientCount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {

                // 通过Snowflake算法获取唯一的ID字符串

                String id = getId();
                try {
                    redisLock.lock(id);
                    count++;
                } finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);
    }

    private String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
