package com.proxydemo.administrator.pullrefresh;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class AInterceptor implements Interceptor {

    private Response response;

    @Override
    public void doRequest(Request request, ArrayList<Interceptor> arrayList, int index) {
        String requestTag = request.getRequestTag();
        request.setRequestTag(requestTag+"A");
        Interceptor interceptor = arrayList.get(index);
        interceptor.doRequest(request,arrayList,++index);
        response = interceptor.getResponse();
        response.setResponseTag(response.getResponseTag()+"A");
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
