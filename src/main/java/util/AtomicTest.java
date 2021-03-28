package util;

import java.math.BigDecimal;
import java.util.concurrent.atomic.*;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-19 12:30 下午
 */
public class AtomicTest {

    public static void main(String[] args) {
        //原子整数
        AtomicInteger i = new AtomicInteger(5);
        i.updateAndGet(x -> x * 10);
        i.incrementAndGet();


        //原子引用
        AtomicReference<String> atomicStr = new AtomicReference<>();
        boolean s = atomicStr.compareAndSet(null, "s");
        System.out.println(s);

        //带版本号的原子引用（解决ABA问题）
        AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
        String prev = ref.getReference();
        int stamp = ref.getStamp();

        //带标记的原子引用（版本号使用布尔值进行替换）
        String a = new String("111");
        String b = new String("111");
        AtomicMarkableReference<String> markableReference = new AtomicMarkableReference<>(a, false);

        System.out.println(markableReference.compareAndSet(b, "222", false, true));
        //对数组中的每个元素进行原子操作
        AtomicLongArray atomicLongArray = new AtomicLongArray(10);
        atomicLongArray.getAndIncrement(1);

        //字段更新器
        AtomicReferenceFieldUpdater updater
                = AtomicReferenceFieldUpdater.newUpdater(Hello.class, String.class, "str");
        Hello hello = new Hello();
        updater.compareAndSet(hello, "haha", "xixi");
        System.out.println(hello.getStr());

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        long l = longAdder.longValue();
        System.out.println(l);


    }
}
