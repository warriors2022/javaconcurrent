package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-10 8:09 下午
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(()->{
            log.info("aaa");
            Thread.sleep(1000);
            return 100;
        });
        Thread thread = new Thread(futureTask, "aaa");
        thread.start();
        Integer integer = futureTask.get();
        log.info("{}", integer);
    }
}
