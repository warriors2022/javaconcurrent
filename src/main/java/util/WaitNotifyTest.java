package util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-13 4:16 下午
 */
@Slf4j
public class WaitNotifyTest {

    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while (true) {
            Thread.sleep(5000);
            System.out.println(thread.getState());
        }

    }
}
