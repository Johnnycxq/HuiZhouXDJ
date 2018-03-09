package com.rehome.huizhouxdj.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * NoHttp的封装
 */
public class HttpResponseListener<T> implements OnResponseListener {

    private Activity mActivity;

    /**
     * dialog
     */
    private WaitDialog mDialog;

    /**
     * 当前请求
     */
    private Request<T> mRequest;

    /**
     * 请求回调
     */
    private HttpListener<T> callback;

    /**
     * 是否显示dialog
     */
    private boolean isLoading;


    /**
     * @param mActivity 用来实例化dialog
     * @param mRequest  请求
     * @param callback  请求回调
     * @param canCancel 是否允许用户请求
     * @param isLoading 是否显示dialog
     */
    public HttpResponseListener(Activity mActivity, final Request<T> mRequest, HttpListener<T> callback, boolean canCancel, boolean isLoading, String msg) {
        this.mActivity = mActivity;
        this.mRequest = mRequest;
        if (isLoading) {
            mDialog = new WaitDialog(mActivity, msg);
            mDialog.setCancelable(canCancel);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    HttpResponseListener.this.mRequest.cancel();//取消请求
                }
            });
        }
        this.callback = callback;
        this.isLoading = isLoading;
    }

    /**
     * 请求开始
     *
     * @param what
     */
    @Override
    public void onStart(int what) {

        if (mDialog != null && !mDialog.isShowing() && !mActivity.isFinishing()) {
            mDialog.show();
        }
    }

    /**
     * 成功回调
     *
     * @param what
     * @param response
     */
    @Override
    public void onSucceed(int what, Response response) {
        if (callback != null) {
            callback.onSucceed(what, response);
        }
    }

    /**
     * 失败回调
     *
     * @param what
     * @param response
     */
    @Override
    public void onFailed(int what, Response response) {

        Exception exception = response.getException();
        if (mActivity != null) {
            if (exception instanceof NetworkError) {// 网络不好
                Toast.makeText(mActivity, "网络异常", Toast.LENGTH_SHORT).show();
            } else if (exception instanceof TimeoutError) {// 请求超时
                Toast.makeText(mActivity, "请求超时", Toast.LENGTH_SHORT).show();
            } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                Toast.makeText(mActivity, "找不到服务器", Toast.LENGTH_SHORT).show();
            } else if (exception instanceof URLError) {// URL是错的
                Toast.makeText(mActivity, "url出错", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity, "网络连接超时", Toast.LENGTH_SHORT).show();
            }
        }
        Logger.e("错误：" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what, response);
    }

    /**
     * 请求结束
     *
     * @param what
     */
    @Override
    public void onFinish(int what) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
