package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.Qfkccxbean;

import java.util.List;

/**
 * Created by ruihong on 2018/3/28.
 */

public class BpbjinfoAdapter extends CommonAdapter<Qfkccxbean.DataBean> {
    private List<Qfkccxbean.DataBean> datas;

    public BpbjinfoAdapter(Context context, List<Qfkccxbean.DataBean> datas) {
        super(context, R.layout.item_bpbjinfo, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, Qfkccxbean.DataBean item, int position) {
        TextView tv_WZBM = viewHolder.getView(R.id.tv_wzbm);
        TextView tv_CKH = viewHolder.getView(R.id.tv_ckh);
        TextView tv_WZMC = viewHolder.getView(R.id.tv_wzmc);
        TextView tv_SL = viewHolder.getView(R.id.tv_sl);
        TextView tv_dw = viewHolder.getView(R.id.tv_dw);
        TextView tv_dj = viewHolder.getView(R.id.tv_dj);
        TextView tv_cc = viewHolder.getView(R.id.tv_cc);


        tv_WZBM.setText(item.getWZBM());
        tv_CKH.setText(item.getCKH());
        tv_WZMC.setText(item.getWZMC());
        tv_SL.setText(item.getSL());
        tv_dw.setText(item.getDW());
        tv_dj.setText(item.getDJ());
        tv_cc.setText(item.getCC());
    }
}
