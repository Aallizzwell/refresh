package com.proxydemo.administrator.pullrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.proxydemo.administrator.pullrefresh.wight.CInterceptor;
import com.proxydemo.administrator.pullrefresh.wight.PtrFrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView mListview;
    private   String[] strs = new String[] {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
    };//定义一个String数组用来显示ListView的内容
    private PtrFrameLayout prt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mListview = findViewById(R.id.lv);
//        prt = findViewById(R.id.ptr);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                prt.setLoadingEnd();
//            }
//        });
//
//        mListview.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, strs));

        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new AInterceptor());
        interceptors.add(new BInterceptor());
        interceptors.add(new CInterceptor());
        Request request = new Request();
        request.setRequestTag("Start");
        interceptors.get(0).doRequest(request,interceptors,1);

        Response response = interceptors.get(0).getResponse();


        Log.i(TAG, "onCreate: ");
    }
}
