package com.iflytek.librarystudy.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.ArrayList;

/**
 * 1、若属性是私有的，必须有set、get方法。否则无法反序列化。
 *
 * @author: cyli8
 * @date: 2018/2/10 10:48
 */

public class Test {
    public static void main(String[] args) {
        User user1 = new User();
        user1.id = 1;
        //在fastjson中，缺省是不输出空值的
//        user1.name = "用户1";

        User user2 = new User();
        user2.id = 2;
        user2.name = "用户2";

        Group group = new Group();
        group.id = 100;
        group.users = new ArrayList<>();
        group.users.add(user1);
        group.users.add(user2);

        //序列化
        String jsonString = JSON.toJSONString(group, SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty);
        System.out.println(jsonString);

        //反序列化
        Group groupCopy = JSON.parseObject(jsonString, Group.class);
        System.out.println(groupCopy.users.get(1).name);

        JSONObject jo = new JSONObject();
        jo.put("id", 1);
        jo.put("name", "用户1");
        jo.put("isMale", false);
        String joStr = jo.toJSONString();
        System.out.println(joStr);

        JSONObject joCopy = JSON.parseObject(joStr);
        boolean isMale = joCopy.getBoolean("isMale");
        int id = joCopy.getIntValue("id");
        System.out.println(isMale + "  " + id);

        //类型不一致时的情况
        String s = "{\"id\":\"3\",\"name\":3}";
        User user3 = JSONObject.parseObject(s, User.class);
        System.out.println(user3.id + "  " + user3.name);

        //根据不同的环境返回定制化属性
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, "id");
        String filterStr = JSON.toJSONString(user1, filter);
        System.out.println(filterStr);

    }
}
