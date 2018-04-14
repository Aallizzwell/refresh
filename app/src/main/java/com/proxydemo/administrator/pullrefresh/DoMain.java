package com.proxydemo.administrator.pullrefresh;

import android.util.Log;

import com.proxydemo.administrator.pullrefresh.wight.CInterceptor;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class DoMain {

    private static final String TAG = DoMain.class.getSimpleName();

    public static void main(String[] args) {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new AInterceptor());
        interceptors.add(new BInterceptor());
        interceptors.add(new CInterceptor());
        Request request = new Request();
        request.setRequestTag("Start");
        interceptors.get(0).doRequest(request,interceptors,1);

        Response response = interceptors.get(0).getResponse();


        Log.i(TAG, "main: "+response.getResponseTag());
    }
}
