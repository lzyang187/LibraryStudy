package com.iflytek.librarystudy.flexbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.flexbox.*
import com.iflytek.librarystudy.R
import com.iflytek.librarystudy.databinding.ActivityFlexboxLayoutBinding
import com.iflytek.librarystudy.smartrefresh.MyAdapter

class FlexboxLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFlexboxLayoutBinding>(this,
                R.layout.activity_flexbox_layout)
        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START
        binding.recyclerview.layoutManager = flexboxLayoutManager
        val list = ArrayList<String>()
        list.add("过火")
        list.add("三国演义")
        list.add("中华人民共和国")
        list.add("安徽省亳州市蒙城县三义镇徐圩村李桥庄")
        list.add("青花瓷")
        list.add("过火")
        val adapter = MyAdapter(this, list)
        binding.recyclerview.adapter = adapter
    }
}