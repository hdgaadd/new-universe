package org.codeman;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hdgaadd
 * created on 2022/04/16
 */
@Slf4j
public class ThreadCreate {

    public static void main(String[] args) throws InterruptedException {
        new ThreadCreate().main();
    }

    private void main() throws InterruptedException {
        Thread threadOne = new Thread(new RunClass());
        threadOne.start();
        threadOne.join();

        Thread threadTwo = new Thread(this::run);
        threadTwo.start();
        threadOne.join();

        Runnable runnable = () -> {
            log.info("第三种");
        };
        new Thread(runnable).start();
    }

    private void run() {
        log.info("创建Runnable的第二种方法，使用this::run");
    }

    static class RunClass implements java.lang.Runnable {
        @Override
        public void run() {
            log.info("创建Runnable的第一种方法");
        }
    }
}
