package util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-15 5:10 下午
 */
public class TwoStageTermVolatile {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(3500);
        twoPhaseTermination.stop();
    }


}

@Slf4j
class TwoPhaseTermination {
    private Thread monitorThread;

    private boolean stop;

    public void start() {
        monitorThread = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.info("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.info("执行监控");
                } catch (InterruptedException e) {

                }
            }
        }, "monitor");
        monitorThread.start();
    }
    public void stop() {
        monitorThread.interrupt();
        stop = true;
    }
}
