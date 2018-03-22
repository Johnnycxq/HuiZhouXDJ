package com.rehome.huizhouxdj.adapter;

import android.content.Context;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.XsJhListBean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/17.
 */

public class XsjhListAdapter extends CommonAdapter<XsJhListBean.DataBean> {

    private List<XsJhListBean.DataBean> datas;

    public XsjhListAdapter(Context context, List<XsJhListBean.DataBean> datas) {
        super(context, R.layout.xsjh_item2, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, XsJhListBean.DataBean item, int position) {

        viewHolder.setText(R.id.tv_jhmc, item.getJhmc());
    }
}
