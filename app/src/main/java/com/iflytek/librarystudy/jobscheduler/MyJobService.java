package com.iflytek.librarystudy.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * @author: cyli8
 * @date: 2019-05-09 15:10
 */
public class MyJobService extends JobService {
    public static final String TAG = "JobScheduler";

    private Messenger mMessenger;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "JobService task running", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyJobService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyJobService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    // 返回true，表示该工作耗时，同时工作处理完成后需要调用onStopJob销毁（jobFinished）
    // 返回false，任务运行不需要很长时间，到return时已完成任务处理
    @Override
    public boolean onStartJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler, 1, params));
        return true;
    }

    // 有且仅有onStartJob返回值为true时，才会调用onStopJob来销毁job
    // 返回false来销毁这个工作
    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeMessages(1);
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MyJobService onDestroy");
    }
}
