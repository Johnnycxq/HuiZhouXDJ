package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.PminfoBean2;

import java.util.List;

/**
 * Created by ruihong on 2017/12/28.
 */

public class QfPmListAdapter extends CommonAdapter<PminfoBean2.DataBean> {


    private List<PminfoBean2.DataBean> datas;


    public QfPmListAdapter(Context context, List<PminfoBean2.DataBean> datas) {
        super(context, R.layout.item_qfgdlist, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, PminfoBean2.DataBean item, int position) {
        TextView tv_zrmb = viewHolder.getView(R.id.tv_zrmb);
        TextView tv_sbmc = viewHolder.getView(R.id.tv_sbmc);
        TextView tv_gzzt = viewHolder.getView(R.id.tv_gzzt);
        TextView tv_bgr = viewHolder.getView(R.id.tv_bgr);


        tv_zrmb.setText("责任部门: " + item.getZRBM());
        tv_sbmc.setText("设备名称: " + item.getSBMC());
        tv_bgr.setText("报告人: " + item.getBGR());
        tv_gzzt.setText(item.getGZZT());

    }
}
