package util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-15 11:45 上午
 * ABC交替打印
 */
@Data
public class ABCAlternately {

    private Thread t1;
    private Thread t2;
    private Thread t3;
    public static void main(String[] args) {
        parkAndUnparkImpl();
    }

    public static void parkAndUnparkImpl() {
        ABCAlternately abcAlternately = new ABCAlternately();
        ParkUnPark parkUnPark = new ParkUnPark( 5);
        Runnable runT1 = () -> parkUnPark.print("a", abcAlternately.getT2());
        Runnable runT2 = () -> parkUnPark.print("b", abcAlternately.getT3());
        Runnable runT3 = () -> parkUnPark.print("c", abcAlternately.getT1());
        abcAlternately.setT1(new Thread(runT1));
        abcAlternately.setT2(new Thread(runT2));
        abcAlternately.setT3(new Thread(runT3));
        abcAlternately.getT1().start();
        abcAlternately.getT2().start();
        abcAlternately.getT3().start();
        LockSupport.unpark(abcAlternately.getT1());
    }

}

@Slf4j
@AllArgsConstructor
@Data
class ParkUnPark {
    private Integer loopNum;
    public void print(String msg, Thread nextThread) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.print(msg);
            LockSupport.unpark(nextThread);
        }
    }
}
