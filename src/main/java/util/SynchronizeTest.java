package util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-16 8:33 下午
 */
@Slf4j
public class SynchronizeTest {
    private static Object LOCK_A = new Object();

    private static Object LOCK_B = new Object();


    public static void main(String[] args) throws InterruptedException {
        threadOne();
        Thread.sleep(5000);
        threadTwo();
    }

    public static void threadOne() {
        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (LOCK_A) {
                    synchronized (LOCK_B) {
                        LOCK_B.wait();
                    }
                }
            }
        }.start();
    }

    public static void threadTwo() {
        new Thread() {
            @Override
            public void run() {
                synchronized (LOCK_A) {
                    synchronized (LOCK_B) {
                        log.info("运行。。。");
                    }
                }
            }
        }.start();
    }
}
