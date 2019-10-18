package com.iflytek.librarystudy.dagger2.dependencies;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.dagger2.subcomponent.AFatherComponent;
import com.iflytek.librarystudy.dagger2.subcomponent.DaggerAFatherComponent;

import javax.inject.Inject;

public class ChildActivity extends AppCompatActivity {
    private static final String TAG = "ChildActivity";

    @Inject
    Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        //父类的注入
        FatherComponent component = DaggerFatherComponent.builder().fatherModule(new FatherModule()).build();
        //子类的注入
        DaggerChildComponent.builder().childModule(new ChildModule()).fatherComponent(component).build().inject(this);
        ((TextView) findViewById(R.id.tv)).setText(child.toString());
        Log.i(TAG, "onCreate: " + child.toString());

        //subcomponent
        AFatherComponent fatherComponent = DaggerAFatherComponent.builder().build();
        fatherComponent.inject().build().inject(this);
        ((TextView) findViewById(R.id.tv)).setText(child.toString());
        Log.i(TAG, "onCreate: " + child.toString());

    }

}
