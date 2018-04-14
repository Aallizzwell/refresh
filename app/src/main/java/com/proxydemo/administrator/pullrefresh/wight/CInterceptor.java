package com.proxydemo.administrator.pullrefresh.wight;

import com.proxydemo.administrator.pullrefresh.Interceptor;
import com.proxydemo.administrator.pullrefresh.Request;
import com.proxydemo.administrator.pullrefresh.Response;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class CInterceptor implements Interceptor {

    private Response response;

    @Override
    public void doRequest(Request request, ArrayList<Interceptor> arrayList, int index) {
        String requestTag = request.getRequestTag();
        request.setRequestTag(requestTag+"C");
        response = new Response();
        response.setResponseTag(request.getRequestTag()+"responseC");
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
