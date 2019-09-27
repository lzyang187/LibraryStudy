package com.iflytek.librarystudy.dagger2;

/**
 * @author: cyli8
 * @date: 2019-05-29 15:26
 */
public class Bus {
    /**
     * 驾驶员
     */
    private String driver;

    /**
     * 座位数
     */
    private int seats;

    public Bus() {

    }

    public Bus(String driver, int seats) {
        this.driver = driver;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "驾驶员：" + driver + " 座位数：" + seats;
    }
}
