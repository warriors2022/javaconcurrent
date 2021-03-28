package util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-11 11:34 上午
 */
@Slf4j
public class ParkTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("停止执行");
            LockSupport.park();
            log.info("{}", Thread.currentThread().isInterrupted());
            log.info("继续执行");
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
