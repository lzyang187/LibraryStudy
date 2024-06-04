package com.iflytek.librarystudy.mmkv

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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



    }

    private fun logValue(mmkv: MMKV?) {
        val decodeBool = mmkv?.decodeBool("lzy")
        Log.d(TAG, "decodeBool: $decodeBool")
    }

    companion object {
        private const val TAG = "MMKVActivity"
    }
}