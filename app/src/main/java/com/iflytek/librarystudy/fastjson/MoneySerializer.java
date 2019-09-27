package com.iflytek.librarystudy.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: cyli8
 * @date: 2018/2/10 11:35
 */

public class MoneySerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {

    }
}
