package util;

import lombok.Data;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-15 2:56 下午
 */
@Data
public class Hello {

    public volatile String str = "haha";

    private volatile int id;

    private volatile String name;


    public static void main(String[] args) {
        long pow = (long) (Math.pow(2, 64) * 8);
        long v = pow / 1024 / 8 / 1024;
        System.out.println(v);
        Hello hello = new Hello();
        hello.setStr("bb");
        hello.print();
        hello = new Hello();
        hello.setStr("cc");
        hello.print();
    }

    public void print() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        };
        thread.start();
    }
}
