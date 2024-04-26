package org.codeman.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hdgaadd
 * created on 2021/11/23
 */
public class UseThreadLocal {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            int number = i;
            es.execute(() -> System.out.println(number + ":" + new intUtil().addTen(number)));
        }
    }

    private static class intUtil {

        public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>(); // 使用threadLocal保存线程保存的当前共享变量num

        public static int addTen(int number) {
            threadLocal.set(number);

            try { // 休息1秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return threadLocal.get() + 10;
        }
    }
}


