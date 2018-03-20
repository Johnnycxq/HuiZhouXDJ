package com.rehome.huizhouxdj.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruihong on 2017/12/23.
 */

public abstract class BaseActivity2 extends AutoLayoutActivity {

    public static final int REQUEST_CODE_ADD = 1;

    public static final int REQUEST_CODE_AUDIT = 2;


    @BindView(R.id.toolbar)
    OAToolbar toolbar;

    private Unbinder unbinder;

    public Context context;

    public String simpleName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏

        setContentView(getLayoutId());

        AppManager.getAppManager().addActivity(this);

        simpleName = "ydoa == " + getClass().getSimpleName();

        context = this;

        unbinder = ButterKnife.bind(this);

        initView();

        initData();

//        showToast(getClass().getSimpleName());
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

        AppManager.getAppManager().finishActivity(this);
    }

    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int strId) {
        Toast.makeText(context, strId, Toast.LENGTH_SHORT).show();
    }

    public void initToolbar(String title) {
        toolbar.setTvTitleText(title);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTvTitleText(title);
    }

    public void initToolbar(String title, String rightText, View.OnClickListener listener) {
        toolbar.setTvTitleText(title);
        toolbar.setTvRightText(rightText);
        toolbar.setTvRightOnClickListener(listener);
        setLeftOnClickListener(listener);
    }

    public void initToolbar(String title, int rightTextId, View.OnClickListener listener) {
        toolbar.setTvTitleText(title);
        toolbar.setTvRightText(rightTextId);
        toolbar.setTvRightOnClickListener(listener);
        setLeftOnClickListener(listener);
    }

    public void setTvRightTitle(String text) {
        toolbar.setTvRightText(text);
    }

    public void setLeftOnClickListener(View.OnClickListener listener) {
        toolbar.setIvLeftOnClickListener(listener);
        toolbar.setIvLeftIcon(R.mipmap.ac_back_icon);
    }

    public String getText(EditText editText) {
        return editText.getText().toString();
    }

    public void setToolbarColor() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.lucency));
        toolbar.setTvTitleColor(getResources().getColor(R.color.login_title_color));
    }
}
