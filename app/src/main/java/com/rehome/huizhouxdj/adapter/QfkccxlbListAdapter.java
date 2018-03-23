package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.Qfkccxbean;

import java.util.List;

/**
 * Created by ruihong on 2017/11/23.
 */

public class QfkccxlbListAdapter extends CommonAdapter<Qfkccxbean.DataBean> {


    private List<Qfkccxbean.DataBean> datas;


    public QfkccxlbListAdapter(Context context, List<Qfkccxbean.DataBean> datas) {
        super(context, R.layout.item_qfkccxlb, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, Qfkccxbean.DataBean item, int position) {
        TextView tv_WZBM = viewHolder.getView(R.id.tv_WZBM);
        TextView tv_CKH = viewHolder.getView(R.id.tv_CKH);
        TextView tv_WZMC = viewHolder.getView(R.id.tv_WZMC);
        TextView tv_SL = viewHolder.getView(R.id.tv_SL);
        TextView tv_dw = viewHolder.getView(R.id.tv_dw);
        TextView tv_dj = viewHolder.getView(R.id.tv_dj);
        TextView tv_cc = viewHolder.getView(R.id.tv_cc);


        tv_WZBM.setText("物资编码: " + item.getWZBM());
        tv_CKH.setText("仓库号: " + item.getCKH());
        tv_WZMC.setText("物资名称: " + item.getWZMC());
        tv_SL.setText("数量: " + item.getSL());
        tv_dw.setText("单位: " + item.getDW());
        tv_dj.setText("单价: " + item.getDJ());
        tv_cc.setText("储仓: " + item.getCC());


    }

}
