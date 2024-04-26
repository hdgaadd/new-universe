package org.codeman;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author hdgaadd
 * created on 2022/12/14
 *
 * description: 使用ThreadFactory定义线程的全局异常
 */
@Slf4j
public class CustomGlobalException {
    public static void main(String[] args) {
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
                log.error("there is an error!");
            });
            return thread;
        };
        new ThreadPoolExecutor()
        ExecutorService executor = new ThreadPoolExecutor(6,
                6,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(66),
                factory);

        executor.execute(() -> {
            throw new NullPointerException();
        });
        executor.execute(() -> {
            throw new NullPointerException();
        });
    }
}
