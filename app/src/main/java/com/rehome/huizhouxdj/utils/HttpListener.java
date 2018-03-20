package com.rehome.huizhouxdj.utils;

import com.yolanda.nohttp.rest.Response;

/**
 * NoHttp请求回调的接口
 */
public interface HttpListener<T> {

    /**
     * 请求成功
     *
     * @param what     请求队列的标志
     * @param response 请求结果
     */
    void onSucceed(int what, Response<T> response);

    /**
     * 请求失败
     *
     * @param what     请求队列的标志
     * @param response 请求结果
     */
    void onFailed(int what, Response<T> response);
}
