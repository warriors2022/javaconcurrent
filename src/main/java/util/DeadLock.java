package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-14 3:05 下午
 */
@Slf4j
public class DeadLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                log.info("进入A锁");
                LockSupport.parkNanos(1000000);
                synchronized (b) {
                    log.info("结束");
                }
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (b) {
                log.info("进入B锁");
                LockSupport.parkNanos(10000000);
                synchronized (a) {
                    log.info("结束");
                }
            }
        }, "t2").start();
    }
}
