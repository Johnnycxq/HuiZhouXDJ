package com.rehome.huizhouxdj.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;

import com.orhanobut.logger.Logger;
import com.rehome.huizhouxdj.contans.Contans;

/**
 * 后台上传文件
 */
public class BackUploadService extends Service {

    private boolean WIFI = false;

    public BackUploadService() {
    }

    // 实时监听网络状态改变
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(final Context context, Intent intent) {

            if (isNetworkConnected(context)) {
                WIFI = false;
                Contans.NEWWORK_STATE = true;
                //Toast.makeText(context, "移动网络", Toast.LENGTH_SHORT).show();
            }
            if (isWifiConnected(context)) {
                Contans.NEWWORK_STATE = true;
                WIFI = true;
                //Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Logger.v("network" + "查询数据库数据");
//                            Thread.sleep(2000);
//                            Logger.v("network" + "上传数据");
//                            Thread.sleep(2000);
//                            Logger.v("network" + "上传成功");
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
            }
            if (!isNetworkConnected(context) && !isWifiConnected(context)) {
                WIFI = false;
                //Toast.makeText(context, "没有网络连接", Toast.LENGTH_SHORT).show();
                Contans.NEWWORK_STATE = false;
            }
        }
    };

    private Binder binder = new MyBinder();
    private boolean isContected = true;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {// 注册广播
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION); // 添加接收网络连接状态改变的Action
        registerReceiver(mReceiver, mFilter);
    }

    /*
     * 判断是3G否有网络连接
     */
    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (state == NetworkInfo.State.CONNECTING || state == NetworkInfo.State.CONNECTED) {
                WIFI = false;
                return true;
            }
        }
        return false;
    }

    /*
     * 判断是否有wifi连接
     */
    private boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                //Toast.makeText(context, "wifi", Toast.LENGTH_SHORT).show();
                WIFI = true;
                return true;
            }
        }
        return false;
    }

    public class MyBinder extends Binder {
        public BackUploadService getService() {
            return BackUploadService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver); // 删除广播
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        WIFI = isWifiConnected(this);
        Logger.v("开启服务..." + WIFI);
        return super.onStartCommand(intent, flags, startId);
    }
}


