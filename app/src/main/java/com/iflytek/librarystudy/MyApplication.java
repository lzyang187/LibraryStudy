package com.iflytek.librarystudy;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author: cyli8
 * @date: 2018/2/23 10:14
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 0
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("CustomTag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        FormatStrategy fileformatStrategy = CsvFormatStrategy.newBuilder()
                .tag("filetag")
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(fileformatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

    }

}
