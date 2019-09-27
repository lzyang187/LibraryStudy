package com.iflytek.librarystudy;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.lzy.greendao.DaoMaster;
import com.lzy.greendao.DaoSession;
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

        setupDatabase();
    }

    private static DaoSession daoSession;

    private void setupDatabase() {
        //创建数据库shop.db"
        //DevOpenHelper 创建SQLite数据库的SQLiteOpenHelper的具体实现
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "libraryStudy.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
