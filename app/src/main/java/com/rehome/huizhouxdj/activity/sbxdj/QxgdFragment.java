package com.rehome.huizhouxdj.activity.sbxdj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;

import butterknife.ButterKnife;


public class QxgdFragment extends BaseFragment {


    @Override
    public int getContentViewId() {
        return R.layout.fragment_qxgd;
    }

    @Override
    protected void initView() {

    }


    @Override
    public void initData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
