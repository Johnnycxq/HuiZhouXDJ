package com.rehome.huizhouxdj.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一个ui的工具类
 */
public class UiUtlis {

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 隐藏键盘
     *
     * @param editText EditText
     * @param context  Context
     */
    public static void hideInput(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * URL加密
     *
     * @param text 加密的文本
     * @return
     */
    public static String encoder(String text) {

        String name = "";
        try {
            name = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * String类型转double
     *
     * @param text 文本
     * @return
     */
    public static double getNum(String text) {
        double num = 0;
        try {
            num = Double.parseDouble(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 判断计划
     *
     * @param time
     * @return
     */
    public static boolean isdelete(String time) {

        if (!time.isEmpty()) {

            String str = "";
            if (time.indexOf("-") != -1) {
                str = "yyyy-MM-dd HH:mm:ss";
            }

            if (time.indexOf("/") != -1) {
                str = "yyyy/MM/dd HH:mm:ss";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(str);
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long l1 = date.getTime();//服务器返回时间
            long l2 = System.currentTimeMillis();//系统时间
            if (l1 < l2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(EditText editText) {

        if (TextUtils.isEmpty(editText.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}