package com.iflytek.librarystudy;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.lzy.studysource.provider/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            Log.e(TAG, "onCreate: " + author + " " + name + " " + price);

        }
        cursor.close();
    }
}
