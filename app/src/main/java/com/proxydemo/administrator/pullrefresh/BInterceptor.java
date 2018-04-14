package com.proxydemo.administrator.pullrefresh;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class BInterceptor implements Interceptor {

    private Response response;

    @Override
    public void doRequest(Request request, ArrayList<Interceptor> arrayList,int index) {
        String requestTag = request.getRequestTag();
        request.setRequestTag(requestTag+"B");
        Interceptor interceptor = arrayList.get(index);
        interceptor.doRequest(request,arrayList,++index);
        response = interceptor.getResponse();
        response.setResponseTag(response.getResponseTag()+"B");
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
