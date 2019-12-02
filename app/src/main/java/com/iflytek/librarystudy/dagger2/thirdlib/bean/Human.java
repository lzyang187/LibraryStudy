package com.iflytek.librarystudy.dagger2.thirdlib.bean;

/**
 * @author: cyli8
 * @date: 2019-10-21 11:30
 */
//假设Human是第三方库中的类，没有使用@Inject注解
public class Human {
    public String name;

    public Human() {
        name = "human";
    }
}
