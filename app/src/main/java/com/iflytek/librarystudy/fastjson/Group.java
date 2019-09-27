package com.iflytek.librarystudy.fastjson;

import java.io.Serializable;
import java.util.List;

/**
 * @author: cyli8
 * @date: 2018/2/10 10:47
 */

public class Group implements Serializable {
    public List<User> users;
    public int id;
}
