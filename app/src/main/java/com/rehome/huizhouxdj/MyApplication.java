package com.rehome.huizhouxdj;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import org.litepal.LitePalApplication;

import java.util.List;

/**
 * Created by ruihong on 2018/2/7.
 */

public class MyApplication extends LitePalApplication {


    public static Context context = null;
    public static Handler handler = null;

    public static final String APP_ID = "2882303761517567712";
    public static final String APP_KEY = "5671756771712";
    public static final String TAG = "xiaomipush";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        NoHttp.initialize(this);
        NoHttp.setDefaultConnectTimeout(60 * 1000);
        NoHttp.setDefaultReadTimeout(60 * 1000);
        //开启调试模式
        Logger.setDebug(true);
        Logger.setTag("NoHttp");
//        AutoLayoutConifg.getInstance().useDeviceSize();
//        com.orhanobut.logger.Logger.init("rehome");
//        MyCrashHandler.getInstance().init(this);

        //初始化push推送服务
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        com.xiaomi.mipush.sdk.Logger.setLogger(this, newLogger);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
