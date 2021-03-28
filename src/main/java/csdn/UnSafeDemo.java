package csdn;

import csdn.entity.User;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-27 2:49 下午
 */
public class UnSafeDemo {
    public static void main(String[] args) throws IllegalAccessException
            , NoSuchFieldException, InstantiationException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
        User user = (User) unsafe.allocateInstance(User.class);
    }
}
