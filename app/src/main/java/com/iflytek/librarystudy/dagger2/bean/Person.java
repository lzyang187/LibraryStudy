package com.iflytek.librarystudy.dagger2.bean;

import javax.inject.Inject;

/**
 * @author: cyli8
 * @date: 2019-10-21 11:20
 */
public class Person {
    public String name;

    @Inject
    public Person() {
        name = "test";
    }
}
