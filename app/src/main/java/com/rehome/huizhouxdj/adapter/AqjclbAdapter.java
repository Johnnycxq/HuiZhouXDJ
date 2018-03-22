package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.AqjclbBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ruihong on 2017/12/28.
 */

public class AqjclbAdapter extends CommonAdapter<AqjclbBean.DataBean> {

    @BindView(R.id.tv_JHMC)
    TextView tvJHMC;
    @BindView(R.id.tv_ST)
    TextView tvST;
    @BindView(R.id.tv_ET)
    TextView tvET;
    @BindView(R.id.tv_LCFW)
    TextView tvLCFW;
    @BindView(R.id.tv_TJR)
    TextView tvTJR;
    @BindView(R.id.tv_TJSJ)
    TextView tvTJSJ;
    private List<AqjclbBean.DataBean> datas;


    public AqjclbAdapter(Context context, List<AqjclbBean.DataBean> datas) {
        super(context, R.layout.item_aqjclb, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, AqjclbBean.DataBean item, int position) {
        TextView tvJHMC = viewHolder.getView(R.id.tv_JHMC);
        TextView tvST = viewHolder.getView(R.id.tv_ST);
        TextView tvET = viewHolder.getView(R.id.tv_ET);
        TextView tvLCFW = viewHolder.getView(R.id.tv_LCFW);
        TextView tvTJR = viewHolder.getView(R.id.tv_TJR);
        TextView tvTJSJ = viewHolder.getView(R.id.tv_TJSJ);

        tvJHMC.setText("计划名称: " + item.getJHMC());
        tvST.setText("开始时间: " + item.getST());
        tvET.setText("结束时间: " + item.getET());
        tvLCFW.setText("联查范围: " + item.getJCFW());
        tvTJR.setText("提交人: " + item.getTJR());
        tvTJSJ.setText("提交时间: " + item.getTJSJ());
    }
}
