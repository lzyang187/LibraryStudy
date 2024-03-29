package com.iflytek.librarystudy.xlog;

import com.elvishew.xlog.printer.file.naming.FileNameGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * created by 李朝阳 on 2020/6/9 9:38
 */
class MyFileNameGenerator implements FileNameGenerator {

    ThreadLocal<SimpleDateFormat> mLocalDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA);
        }
    };

    @Override
    public boolean isFileNameChangeable() {
        return true;
    }

    @Override
    public String generateFileName(int logLevel, long timestamp) {
        SimpleDateFormat sdf = mLocalDateFormat.get();
        if (sdf != null) {
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(new Date(timestamp));
        }
        return "";
    }
}
