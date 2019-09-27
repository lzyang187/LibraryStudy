package com.iflytek.librarystudy.rxjava.use;

import android.util.Log;

/**
 * 词霸实体类
 *
 * @author: cyli8
 * @date: 2018/3/12 15:18
 */

public class CBTranslation {
    private int status;
    private Content content;

    private static class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    public void showInfo(String tag) {
        Log.e(tag, "status:" + status);
        Log.e(tag, "from:" + content.from);
        Log.e(tag, "to:" + content.to);
        Log.e(tag, "vendor:" + content.vendor);
        Log.e(tag, "out:" + content.out);
        Log.e(tag, "errNo:" + content.errNo);
    }
}
