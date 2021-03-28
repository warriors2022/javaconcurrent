package util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-10 5:39 下午
 */
@Slf4j(topic = "my Sync")
public class Sync {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("runing");
        });
        t.setName("11");
        t.start();
        t.interrupt();
        log.info("runing");
    }
}
