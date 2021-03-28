package util;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-11 5:18 下午
 */
@Slf4j
public class TestBiased {

    public static void main(String[] args) throws InterruptedException {
        Dog d = new Dog();
        String s = ClassLayout.parseInstance(d).toPrintable();
        log.info(s);
        int i = d.hashCode();
        s = ClassLayout.parseInstance(d).toPrintable();
        log.info(s);

    }
}

class Dog {

}
