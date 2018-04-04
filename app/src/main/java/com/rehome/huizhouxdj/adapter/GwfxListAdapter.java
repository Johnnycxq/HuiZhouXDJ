package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.R;

import java.util.List;

/**
 * Created by ruihong on 2017/11/23.
 */

public class GwfxListAdapter extends CommonAdapter<QYAQFXDATABean> {


    private List<QYAQFXDATABean> datas;


    public GwfxListAdapter(Context context, List<QYAQFXDATABean> datas) {
        super(context, R.layout.item_fxts, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, QYAQFXDATABean item, int position) {


        TextView tv_fxlx = viewHolder.getView(R.id.tv_fxlx);
        TextView tv_fxms = viewHolder.getView(R.id.tv_fxms);
        TextView tv_fhcs = viewHolder.getView(R.id.tv_fhcs);


        tv_fxlx.setText("风险类型: " + item.getFXLX());
        tv_fxms.setText("风险描述: " + item.getFXMS());
        tv_fhcs.setText("风险措施: " + item.getFHCS());


    }

}
