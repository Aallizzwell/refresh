package com.proxydemo.administrator.pullrefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListview;
    private   String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };//定义一个String数组用来显示ListView的内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = findViewById(R.id.lv);
        mListview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));

}
}
