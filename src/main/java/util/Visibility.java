package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-15 3:43 下午
 */
@Slf4j
public class Visibility {
    private static boolean RUN = true;
    private static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                if (!RUN) {
                    break;
                }
                synchronized ("1"){

                }
            }
        });
        t.start();
        Thread.sleep(1000);
        log.info("停止");
        RUN = false;
    }

}
