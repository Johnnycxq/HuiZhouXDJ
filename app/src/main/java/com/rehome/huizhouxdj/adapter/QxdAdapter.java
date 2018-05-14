package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.QxdBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ruihong on 2017/12/28.
 */

public class QxdAdapter extends CommonAdapter<QxdBean.DataBean> {

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
    private List<QxdBean.DataBean> datas;


    public QxdAdapter(Context context, List<QxdBean.DataBean> datas) {
        super(context, R.layout.item_qxdinfo, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, QxdBean.DataBean item, int position) {
        TextView tv_JDBH = viewHolder.getView(R.id.tv_JDBH);
        TextView tv_SBMC = viewHolder.getView(R.id.tv_SBMC);
        TextView tv_QXMS = viewHolder.getView(R.id.tv_QXMS);
        TextView tv_GZMS = viewHolder.getView(R.id.tv_GZMS);
        TextView tv_ZT = viewHolder.getView(R.id.tv_ZT);
        TextView tv_ZRBZ = viewHolder.getView(R.id.tv_ZRBZ);
        TextView tv_XMFZR = viewHolder.getView(R.id.tv_XMFZR);
        TextView tv_JXBZ = viewHolder.getView(R.id.tv_JXBZ);


        tv_JDBH.setText("工单编号: " + item.getPl_wk_wn_p());
        tv_SBMC.setText("设备名称: " + item.getEq_ma_de());
        tv_QXMS.setText("缺陷描述: " + item.getPl_wk_fd());
        tv_GZMS.setText("工作描述: " + item.getPl_wk_de());
        tv_ZT.setText("状态: " + item.getWo_st_de());
        tv_ZRBZ.setText("责任班组: " + item.getRe_tm_de());
        tv_XMFZR.setText("项目负责人: " + item.getPl_wk_do());
        tv_JXBZ.setText("检修班组: " + item.getPl_wk_wg());

    }
}
