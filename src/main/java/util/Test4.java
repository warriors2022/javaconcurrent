package util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-10 8:29 下午
 *
 */
@Slf4j
public class Test4 {
    public static void main(String[] args) {
        Runnable t1 = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("t1");
            }
        };
        Runnable t2 = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("t2");
            }
        };
        Thread thread1 = new Thread(t1, "t1");
        Thread thread2 = new Thread(t2, "t2");
        thread1.start();
        thread2.start();
    }
}
