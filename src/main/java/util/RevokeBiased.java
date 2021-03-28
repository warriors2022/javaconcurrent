package util;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-11 8:31 下午
 * 撤销偏向锁
 */
@Slf4j
public class RevokeBiased {

    public static void main(String[] args) {
        Cat cat = new Cat();
        createThread01(cat);
        createThread02(cat);
    }

    public static void createThread01(Cat cat) {
        new Thread(() ->{
            log.info(ClassLayout.parseInstance(cat).toPrintable());
            synchronized (cat) {
                log.info(ClassLayout.parseInstance(cat).toPrintable());
            }
            log.info(ClassLayout.parseInstance(cat).toPrintable());
        }).start();
    }

    public static void createThread02(Cat cat) {
        new Thread(() ->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(ClassLayout.parseInstance(cat).toPrintable());
            synchronized (cat) {
                log.info(ClassLayout.parseInstance(cat).toPrintable());
            }
            log.info(ClassLayout.parseInstance(cat).toPrintable());
        }).start();
    }
}

class Cat {

    private String e;
}
