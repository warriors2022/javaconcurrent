package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-10 10:34 下午
 * 两阶段终止
 */
@Slf4j
public class TwoStageTerm {
    public static void main(String[] args) throws InterruptedException {
        Thread start = start();

        stop(start);

    }

    private static Thread start() {
        Runnable runnable = () -> {
          while (true) {
              if (Thread.currentThread().isInterrupted()) {
                  break;
              }
              try {
                  TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
                  e.printStackTrace();
                  //sleep会把线程的打断标记清除
                  Thread.currentThread().interrupt();
              }
              log.info("开始采样...");
          }
        };
        Thread thread = new Thread(runnable, "采样");
        thread.start();
        return thread;
    }

    public static void stop(Thread t) throws InterruptedException {
        TimeUnit.SECONDS.sleep(12);
        t.interrupt();
    }
}
