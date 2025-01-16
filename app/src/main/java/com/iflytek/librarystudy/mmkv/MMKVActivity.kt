package com.iflytek.librarystudy.mmkv

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.iflytek.librarystudy.R
import com.tencent.mmkv.MMKV

class MMKVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmkvactivity)

        val mmkv = MMKV.defaultMMKV()
        logValue(mmkv)
        // 增
        mmkv?.encode("lzy", true)
        // 查
        logValue(mmkv)
        // 改
        mmkv?.encode("lzy", 2)
        logValue(mmkv)
        val decodeInt = mmkv?.decodeInt("lzy")
        Log.d(TAG, "decodeBool: $decodeInt")
        // 删
        val b1 = mmkv?.contains("lzy")
        mmkv?.remove("lzy")
        val b2 = mmkv?.contains("lzy")
        Log.d(TAG, "contains: $b1  $b2")


        findViewById<Button>(R.id.btn_arouter).setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/test/activity")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .withObject("key4", Object())
                .navigation();
        }
    }

    private fun logValue(mmkv: MMKV?) {
        val decodeBool = mmkv?.decodeBool("lzy")
        Log.d(TAG, "decodeBool: $decodeBool")
    }

    companion object {
        private const val TAG = "MMKVActivity"
    }
}