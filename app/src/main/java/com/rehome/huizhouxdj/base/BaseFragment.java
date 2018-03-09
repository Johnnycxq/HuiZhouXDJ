package com.rehome.huizhouxdj.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/9/icon5.
 */
public abstract class BaseFragment extends Fragment {

    public static final int CX_FLAG = 0;//查询的状态码
    public static final int SC_FLAG = 1;//上传的状态码

    public abstract int getContentViewId();

    public Activity mActivity;
    public View view;

    public Context context;

    public Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, view);//绑定framgent
        //Logger.v("fragment:" + getClass().getSimpleName());
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    public void initData() {

    }

    public void showToast(String text) {
        if (text != null && !text.trim().equals("")) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
