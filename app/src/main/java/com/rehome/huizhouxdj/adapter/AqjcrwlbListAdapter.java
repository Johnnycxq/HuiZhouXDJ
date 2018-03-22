package com.rehome.huizhouxdj.adapter;

import android.content.Context;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.AqjcrwListBean;

import java.util.List;

/**
 * Created by ruihong on 2018/1/20.
 */

public class AqjcrwlbListAdapter extends CommonAdapter<AqjcrwListBean.DataBean> {

    private List<AqjcrwListBean.DataBean> datas;

    public AqjcrwlbListAdapter(Context context, List<AqjcrwListBean.DataBean> datas) {
        super(context, R.layout.item_sqjcrwlist, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, AqjcrwListBean.DataBean item, int position) {


        viewHolder.setText(R.id.tv_JHMC, "计划名称: " + item.getJHMC());
        viewHolder.setText(R.id.tv_WTQY, "问题区域: " + item.getWTQY());
        viewHolder.setText(R.id.tv_WTMS, "问题描述: " + item.getWTMS());
        viewHolder.setText(R.id.tv_FXLB, "风险类别: " + item.getFXLB());
        viewHolder.setText(R.id.tv_ZRBM, "责任部门: " + item.getZRBM());
        viewHolder.setText(R.id.tv_ZGZRR, "整改人: " + item.getZGR());


    }
}
