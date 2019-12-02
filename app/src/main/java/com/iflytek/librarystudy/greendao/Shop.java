package com.iflytek.librarystudy.greendao;


import androidx.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * @author: cyli8
 * @date: 2019-09-26 17:28
 */

// 告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
@Entity
public class Shop implements Serializable {

    private static final long serialVersionUID = 0L;

    /**
     * 对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
     */
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;
    /**
     * 可以自定义字段名，注意外键不能使用该属性
     */
    @Property(nameInDb = "price")
    private String price;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_BOOK = 2;
    @NotNull
    private int type;
    /**
     * 使用该注释的属性不会被存入数据库的字段中
     */
    @Transient
    private String mylove;
    private long createTime;
    private boolean isFree;



    @Keep
    public Shop(String name, String price, int type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.createTime = System.currentTimeMillis();
    }

    @NonNull
    @Override
    public String toString() {
        return id + " " + name + " " + price + " " + type + " " + createTime + " " + isFree;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean getIsFree() {
        return this.isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    @Generated(hash = 985467959)
    public Shop(Long id, String name, String price, int type, long createTime,
            boolean isFree) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.createTime = createTime;
        this.isFree = isFree;
    }

    @Generated(hash = 633476670)
    public Shop() {
    }


}
