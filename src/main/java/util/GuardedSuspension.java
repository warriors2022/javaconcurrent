package util;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-13 5:16 下午
 * 同步模式之保护性暂停
 * 即Guarded Suspension，用在一个线程等待另一个线程的执行结果
 * JDK中，join实现、Future的实现，采用的就是此模式
 */
@Slf4j
public class GuardedSuspension {
    public static void main(String[] args) {
        GuardedObject object = new GuardedObject();
        new Thread(() -> {
            Object msg = object.getMsg(5000);
            log.info("获得信息:{}", msg);
        }, "t1").start();

        new Thread(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            object.putMsg(null);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            object.putMsg("aaaa");
        }, "t2").start();
    }
}

@Slf4j
class GuardedObject{
    private Object msg;

    public synchronized Object getMsg(long waitTime) {
        long startTime = System.currentTimeMillis();
        long delay = 0;
        while (Objects.isNull(msg)) {
            if (waitTime == 0) {
                try {
                    log.info("进入等待");
                    this.wait(waitTime);
                    log.info("被唤醒或时间到了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                long remainingTime = waitTime - delay;
                if (remainingTime <= 0) {
                    break;
                }
                try {
                    log.info("进入等待");
                    this.wait(remainingTime);
                    log.info("被唤醒或时间到了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                delay = System.currentTimeMillis() - startTime;
            }
        }
        return msg;
    }

    public synchronized void putMsg(Object msg) {
        this.msg = msg;
        this.notifyAll();
    }
}

