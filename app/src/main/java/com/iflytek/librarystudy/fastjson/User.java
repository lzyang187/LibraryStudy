package com.iflytek.librarystudy.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: cyli8
 * @date: 2018/2/10 10:47
 */

public class User implements Serializable {
    @JSONField(ordinal = 2)
    public int id;
    @JSONField(ordinal = 1)
    public String name;
    private String address = "anhui";
//    @JSONField(serializeUsing = MoneySerializer.class)
    public int money;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
