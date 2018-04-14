package com.proxydemo.administrator.pullrefresh;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public interface Interceptor {
    public void doRequest(Request request, ArrayList<Interceptor> arrayList, int index);
    public Response getResponse();
}
