package util;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-14 11:40 上午
 */
@Slf4j
public class ProductConsumer {
    public static void main(String[] args) {
        MessageQueue queue1 = new MessageQueue(10);
        MessageQueue queue2 = new MessageQueue(10);
        //生产者快于消费者的情况
        //生产者
        for (int i = 0; i < 20; i++) {
            String msg = "消息" + i;
            new Thread(() -> {
                try {
                    new Product().putMsg(queue1, msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "p" + i).start();
        }
        //消费者
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                Consumer consumer = new Consumer();
                try {
                    while (true) {
                        Thread.sleep(1000);
                        consumer.getMsg(queue1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "m" + i).start();
        }
        //消费者快于生产者的情况

    }
}

class Product {
    public void putMsg(MessageQueue queue, String msg) throws InterruptedException {
        queue.putMsg(msg);
    }
}

class Consumer {
    public String getMsg(MessageQueue queue) throws InterruptedException {
        return queue.getMsg();
    }
}

@Slf4j
class MessageQueue {
    private final List<String> mqList = new ArrayList<>();
    private Integer limit;

    public MessageQueue(Integer limit) {
        this.limit = limit;
    }

    public void putMsg(String msg) throws InterruptedException {
        synchronized (mqList) {
            while (mqList.size() >= limit) {
                log.info("已满");
                mqList.wait();
            }
            log.info("需要存入的消息:{}", msg);
            mqList.add(msg);
            mqList.notifyAll();
        }
    }

    public String getMsg() throws InterruptedException {
        synchronized (mqList) {
            while (mqList.size() == 0) {
                mqList.wait();
            }
            String msg = mqList.remove(0);
            log.info("获取的消息:{}", msg);
            mqList.notifyAll();
            return msg;
        }
    }
}

