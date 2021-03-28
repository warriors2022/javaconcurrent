package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-20 4:54 下午
 */
public class UnsafeTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);

        long idOffset
                = unsafe.objectFieldOffset(Hello.class.getDeclaredField("id"));
        long nameOffset
                = unsafe.objectFieldOffset(Hello.class.getDeclaredField("name"));
        Hello hello = new Hello();
        System.out.println(hello);
        unsafe.compareAndSwapInt(hello, idOffset, 0, 20);
        unsafe.compareAndSwapObject(hello, nameOffset, null, "aaa");
        System.out.println(hello);

    }
}
