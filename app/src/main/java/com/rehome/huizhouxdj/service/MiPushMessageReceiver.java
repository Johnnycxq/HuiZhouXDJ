package com.rehome.huizhouxdj.service;


import android.content.Context;
import android.content.Intent;

import com.rehome.huizhouxdj.base.GzqkPushckActivity;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;
import java.util.Map;

/**
 * Created by Rehome-rjb1 on 2017/4/21.
 */

public class MiPushMessageReceiver extends PushMessageReceiver {


    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {

    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {

        Map<String, String> map = message.getExtra();
        String id = map.get("id");

        Intent intent = new Intent(context, GzqkPushckActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
        Contans.IP = (String) SPUtils.get(context, Contans.KEY_4G_IP, "");
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {

    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);

        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                //打印日志：注册成功
                System.out.println("xiaomipush" + "注册成功");
            } else {
                //打印日志：注册失败
                System.out.println("xiaomipush" + "注册失败");
            }
        } else {
            System.out.println("xiaomipush" + "其他情况" + message.getReason());
        }
    }
}
