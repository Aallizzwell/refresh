package com.proxydemo.administrator.pullrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.proxydemo.administrator.pullrefresh.wight.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    private ListView mListview;
    private   String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };//定义一个String数组用来显示ListView的内容
    private PtrFrameLayout prt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = findViewById(R.id.lv);
        prt = findViewById(R.id.ptr);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prt.setLoadingEnd();
            }
        });

        mListview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));

}
}
