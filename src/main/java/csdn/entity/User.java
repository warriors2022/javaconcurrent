package csdn.entity;

import lombok.Data;

/**
 * @author yw
 * @version 1.0
 * @date 2021-02-27 2:55 下午
 */
@Data
public class User {
    public User() {
        System.out.println("我是User类");
    }
    private String name;

    private int age;

    private static String id = "USER_ID";
}

