package com.iflytek.librarystudy.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: cyli8
 * @date: 2019-09-26 17:28
 */

/**
 * 告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
 */
@Entity
public class Shop {
    /**
     * 对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
     */
    @Id(autoincrement = true)
    private long id;
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
    private int mylove;
    private long createTime;

    @Generated(hash = 2040886176)
    public Shop(long id, String name, String price, int type, long createTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.createTime = createTime;
    }

    @Generated(hash = 633476670)
    public Shop() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
}
