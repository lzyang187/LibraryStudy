package com.iflytek.librarystudy.logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        Logger.d("cyli8 %s", "aaaaa");
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        Logger.d(list);
        Map<String, String> map = new HashMap<>();
        map.put("1", "1value");
        map.put("2", "2value");
        Logger.d(map);
        Logger.t("a_tag").e("aaa");
        Logger.json("{\"key\":\"value\",\"list\":[\"a\",\"b\",1]}");
        Logger.xml("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n" +
                "    <color name=\"colorPrimary\">#3F51B5</color>\n" +
                "    <color name=\"colorPrimaryDark\">#303F9F</color>\n" +
                "    <color name=\"colorAccent\">#FF4081</color>\n" +
                "</resources>\n");
    }
}
