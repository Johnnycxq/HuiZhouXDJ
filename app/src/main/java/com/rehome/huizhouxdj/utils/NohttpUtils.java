package com.rehome.huizhouxdj.utils;

import android.app.Activity;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Nohttp工具类
 */
public class NohttpUtils {

    private static NohttpUtils sUtils;
    private RequestQueue mQueue;

    /**
     * 下载队列.
     */
    private static DownloadQueue downloadQueue;

    private NohttpUtils() {
        mQueue = NoHttp.newRequestQueue(8);
    }

    /**
     * DCL单例模式  双层锁
     *
     * @return
     */
    public static NohttpUtils getInstance() {
        if (sUtils == null) {
            synchronized (NohttpUtils.class) {
                if (sUtils == null) {
                    sUtils = new NohttpUtils();
                }
            }
        }
        return sUtils;
    }

    /**
     * 下载队列.
     */
    public static DownloadQueue getDownloadInstance() {
        if (downloadQueue == null)
            downloadQueue = NoHttp.newDownloadQueue(1);
        return downloadQueue;
    }


    /**
     * 添加请求
     *
     * @param mActivity activity
     * @param what      请求码，用来区分队列中的请求
     * @param request   请求
     * @param callback  请求回调
     * @param canCanel  是否可以取消请求
     * @param isLoading 是否弹出对话框
     * @param <T>
     */
    public <T> void add(Activity mActivity, int what, Request<T> request, HttpListener<T> callback, boolean canCanel, boolean isLoading, String msg) {
        mQueue.add(what, request, new HttpResponseListener<T>(mActivity, request, callback, canCanel, isLoading, msg));
    }


    public <T> void add(Activity mActivity, int what, Request<T> request, HttpListener<T> callback) {
        mQueue.add(what, request, new HttpResponseListener<T>(mActivity, request, callback, true, true, "加载中..."));
    }

    /**
     * 取消这个sign这个标记的所有请求
     *
     * @param sign
     */
    public void cancelBySign(Object sign) {
        mQueue.cancelBySign(sign);
    }

    /**
     * 取消队列中所有请求
     */
    public void cancelAll() {
        mQueue.cancelAll();
    }

    /**
     * 退出App停止所有请求
     */
    public void stopAll() {
        mQueue.stop();
    }
}