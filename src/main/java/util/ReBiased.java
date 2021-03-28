package util;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-11 10:02 下午
 * 重偏向
 */
@Slf4j
public class ReBiased {

    public static void main(String[] args) {
        List<Door> doors = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            doors.add(new Door());
        }
        Door door = new Door();
        createThread01(doors);
        createThread02(doors);
        createThread03(doors);
        createThread04(door);
//        createThread05(door);
    }

    public static void createThread01(List<Door> doors) {
        new Thread(()->{
            for (Door door : doors) {
                synchronized (door) {
                }

            }
            log.info(ClassLayout.parseClass(Door.class).toPrintable());
        }, "线程1").start();

    }

    public static void createThread02(List<Door> doors) {
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 50 ; i++) {
                Door door = doors.get(i);
                synchronized (door) {
//                    log.info(ClassLayout.parseInstance(door).toPrintable());
                }
            }
        }, "线程2").start();
    }

    public static void createThread03(List<Door> doors) {
        new Thread(()->{
            try {
                Thread.sleep(4000);
                for (int i = 0; i < 50 ; i++) {
                    Door door = doors.get(i);
                    synchronized (door) {
//                        log.info(ClassLayout.parseInstance(door).toPrintable());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程3").start();
    }
    public static void createThread04(Door door) {
        new Thread(() -> {
            synchronized (door) {
                int i = 0;
                while (true) {
                    log.info(ClassLayout.parseInstance(door).toPrintable());
                    if (i > 5) {
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程4").start();
    }

    public static void createThread05(Door door) {
        new Thread(() -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (door) {
                log.info(ClassLayout.parseInstance(door).toPrintable());
            }
        }, "线程5").start();
    }

}
class Door{

}