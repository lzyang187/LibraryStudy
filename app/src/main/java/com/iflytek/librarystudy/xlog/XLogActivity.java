package com.iflytek.librarystudy.xlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.formatter.border.DefaultBorderFormatter;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.DefaultStackTraceFormatter;
import com.elvishew.xlog.formatter.thread.DefaultThreadFormatter;
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.iflytek.librarystudy.BuildConfig;
import com.iflytek.librarystudy.R;

public class XLogActivity extends AppCompatActivity {
    private static final String TAG = "XLogActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_log);

        //初始化
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                        : LogLevel.NONE)
                .tag("MY_TAG")                                         // 指定 TAG，默认为 "X-LOG"
//                .t()                                                   // 允许打印线程信息，默认禁止
                .st(1)                                                 // 允许打印深度为2的调用栈信息，默认禁止
//                .b()                                                   // 允许打印日志边框，默认禁止
                .jsonFormatter(new DefaultJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
                .xmlFormatter(new DefaultXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
                .throwableFormatter(new DefaultThrowableFormatter())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
                .threadFormatter(new DefaultThreadFormatter())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
                .stackTraceFormatter(new DefaultStackTraceFormatter())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
                .borderFormatter(new DefaultBorderFormatter())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//                .addObjectFormatter(AnyClass.class,                    // 为指定类添加格式化器
//                        new AnyClassObjectFormatter())                     // 默认使用 Object.toString()
//                .addInterceptor(new BlacklistTagsFilterInterceptor(    // 添加黑名单 TAG 过滤器
//                        "blacklist1", "blacklist2", "blacklist3"))
//                .addInterceptor(new MyInterceptor())                   // 添加一个日志拦截器
                .build();

        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
//        Printer consolePrinter = new ConsolePrinter();             // 通过 System.out 打印日志到控制台的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder("/sdcard/xlog/")                              // 指定保存日志文件的路径
                .fileNameGenerator(new MyFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
//                .backupStrategy(new NeverBackupStrategy()              // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
//                        .cleanStrategy(new FileLastModifiedCleanStrategy(MAX_TIME))     // 指定日志文件清除策略，默认为 NeverCleanStrategy()
//                        .flattener(new MyFlattener())                          // 指定日志平铺器，默认为 DefaultFlattener
                .build();

        XLog.init(                                                 // 初始化 XLog
                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
//                consolePrinter,
                filePrinter);

        //调用
        XLog.tag(TAG).e("simple message");
        XLog.tag(TAG).json("{\"a\":\"123\",\"b\":{\"c\":123}}");
        XLog.tag(TAG).xml("<team><member name=\"Elvis\"/><member name=\"Leon\"/></team>");
        XLog.tag(TAG).i("i level message");
    }

}