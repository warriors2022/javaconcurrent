package csdn;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author yw
 * @version 1.0
 * @date 2021-03-16 6:21 下午
 */
public class DelayQueueDemo {
    public static void main(String args[]) {
        try {
            System.out.println("网吧开始营业");
            WangBa siyu = new WangBa();
            Thread shangwang = new Thread(siyu);
            shangwang.start();
            siyu.shangji("路人甲", "123", 1);
            siyu.shangji("路人乙", "234", 2);
            siyu.shangji("路人丙", "345", 3);
        } catch (Exception ex) {
        }
    }
}

class Wangming implements Delayed {
    private String name; // 身份证
    private String id; // 截止时间
    private long endTime;

    public Wangming(String name, String id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    /**
     * 用来判断是否到了截止时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Wangming jia = (Wangming) o;
        return endTime - jia.endTime > 0 ? 1 : 0;
    }
}

class WangBa implements Runnable {
    private DelayQueue<Wangming> queue = new DelayQueue<Wangming>();
    public boolean yinye = true;

    public void shangji(String name, String id, int money) {
        Wangming man = new Wangming(name, id, 100 * 60 * money +
                System.currentTimeMillis());
        System.out.println("网名" + man.getName() + " 身份证" +
                man.getId() + "交钱" + money + "块,开始上机...");
        this.queue.add(man);
    }

    public void xiaji(Wangming man) {
        System.out.println("网名" + man.getName() + " 身份证" +
                man.getId() + "时间到下机...");
    }

    @Override
    public void run() {
        while (yinye) {
            try {
                System.out.println("检查 ing");
                Wangming man = queue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}