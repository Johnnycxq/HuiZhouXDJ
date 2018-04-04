package com.rehome.huizhouxdj.activity.xj;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备巡点检管理-点检点上传
 */
public class XjscFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sc)
    Button btn_sc;

    @BindView(R.id.tv_nodata)
    TextView tvNodata;


    public XjscFragment() {

    }

    public static XjscFragment newInstance() {
        XjscFragment fragment = new XjscFragment();
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
        }
    }


    @Override
    protected void initView() {

        lv.setEmptyView(tvNodata);


    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_xjsc;
    }

    public void initData() {


    }


    @OnClick({R.id.btn_sc, R.id.btn_hf, R.id.btn_del})

    public void click(View view) {

        switch (view.getId()) {

            case R.id.btn_sc:


                break;

            case R.id.btn_hf:

                break;

            case R.id.btn_del:


                break;
        }
    }


}