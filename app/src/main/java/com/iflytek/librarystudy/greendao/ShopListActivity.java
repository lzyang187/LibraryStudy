package com.iflytek.librarystudy.greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;

import java.util.List;

public class ShopListActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mShowTv;
    private int mCurIndex;

    // TODO: 2019-09-27 未处理数据库升级数据恢复的问题
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        findViewById(R.id.insert_btn).setOnClickListener(this);
        findViewById(R.id.query_btn).setOnClickListener(this);
        findViewById(R.id.query_book_btn).setOnClickListener(this);
        mShowTv = findViewById(R.id.show_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert_btn:
                int type = Shop.TYPE_BOOK;
                if (mCurIndex % 2 == 0) {
                    type = Shop.TYPE_PHONE;
                }
                Shop shop = new Shop("shopName" + mCurIndex, "shopPrice" + mCurIndex, type);

                ShopDaoManager.getInstance().insertOrReplace(shop);
                mCurIndex++;
                break;
            case R.id.query_btn:
                List<Shop> allList = ShopDaoManager.getInstance().loadAll();
                StringBuilder sb = new StringBuilder();
                if (allList != null && allList.size() > 0) {
                    for (Shop s : allList) {
                        sb.append(s.toString()).append("\n");
                    }
                }
                mShowTv.setText(sb.toString());
                break;
            case R.id.query_book_btn:
                List<Shop> bookList = ShopDaoManager.getInstance().queryByType(Shop.TYPE_BOOK);
                StringBuilder bookSb = new StringBuilder();
                if (bookList != null && bookList.size() > 0) {
                    for (Shop s : bookList) {
                        bookSb.append(s.toString()).append("\n");
                    }
                }
                mShowTv.setText(bookSb.toString());
                break;
            default:
                break;
        }
    }
}
