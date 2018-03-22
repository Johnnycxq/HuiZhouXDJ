package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.graphics.Color;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.XsjhQyBean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/17.
 */

public class XsjhqyAdapter extends CommonAdapter<XsjhQyBean.DataBeanX> {

    private List<XsjhQyBean.DataBeanX> datas;

    public XsjhqyAdapter(Context context, List<XsjhQyBean.DataBeanX> datas) {
        super(context, R.layout.xsjhqy_item, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, XsjhQyBean.DataBeanX item, int position) {

        viewHolder.setText(R.id.tv_qy, item.getQymc());

        if (item.getSczt().equals("0")) {  //未上传

            viewHolder.setTextColor(R.id.tv_qy, Color.BLACK);

        } else if (item.getSczt().equals("1")) { //已上传

            viewHolder.setTextColor(R.id.tv_qy, Color.BLUE);

        } else {

            viewHolder.setTextColor(R.id.tv_qy, Color.BLACK);

        }


    }
}
