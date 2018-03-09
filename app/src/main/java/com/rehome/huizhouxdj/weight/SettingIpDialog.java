package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;


/**
 * Created by Administrator on 2016/10/22.
 */

public class SettingIpDialog {

    private Context mContext;
    private AlertDialog dialog;
    private TextView tv_title;
    private Button btn_save;
    private Button btn_cancel;
    private Window mWindow;
    private EditText wifi_ip;
    private EditText mobile_ip;
    private InputMethodManager input;

    public SettingIpDialog(Context context) {
        mContext = context;
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(true);
        dialog.show();
        mWindow = dialog.getWindow();
        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mWindow.setContentView(R.layout.setting_ip);
        wifi_ip = (EditText) mWindow.findViewById(R.id.et_wifi_ip);
        mobile_ip = (EditText) mWindow.findViewById(R.id.et_4g_ip);
        tv_title = (TextView) mWindow.findViewById(R.id.tv_title);
        btn_cancel = (Button) mWindow.findViewById(R.id.btn_qx);
        btn_save = (Button) mWindow.findViewById(R.id.btn_qd);
    }

    public void setSaveOnClick(View.OnClickListener listener) {
        btn_save.setOnClickListener(listener);
    }


    public void setCancelOnClick(View.OnClickListener listener) {
        btn_cancel.setOnClickListener(listener);
    }

    /**
     * 设置Title
     *
     * @param title
     */
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setWifi_ip(String wifi_ip) {
        this.wifi_ip.setText(wifi_ip);
    }

    public void setMobile_ip(String mobile_ip) {
        this.mobile_ip.setText(mobile_ip);
    }

    public String getWifiIp() {
        return wifi_ip.getText().toString().trim();
    }

    public String getMobileIp() {
        return mobile_ip.getText().toString().trim();
    }

    public EditText getWifi_ip() {
        return wifi_ip;
    }

    public EditText getMobile_ip() {
        return mobile_ip;
    }

    public void close() {
        dialog.dismiss();
    }
}
