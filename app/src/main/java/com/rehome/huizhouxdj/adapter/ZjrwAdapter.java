package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.ZjrwBean;

import java.util.List;

/**
 * Created by ruihong on 2017/12/28.
 */

public class ZjrwAdapter extends CommonAdapter<ZjrwBean.DataBean> {

    private List<ZjrwBean.DataBean> datas;

    public ZjrwAdapter(Context context, List<ZjrwBean.DataBean> datas) {
        super(context, R.layout.item_zjrw, datas);
        this.datas = datas;

    }

    @Override
    protected void convert(ViewHolder viewHolder, ZjrwBean.DataBean item, int position) {
        TextView tv_RWMC = viewHolder.getView(R.id.tv_RWMC);
        TextView tv_RWKSSJ = viewHolder.getView(R.id.tv_RWKSSJ);
        TextView tv_RWJSSJ = viewHolder.getView(R.id.tv_RWJSSJ);
        TextView tv_YJWCSJ = viewHolder.getView(R.id.tv_YJWCSJ);
        TextView tv_RWLX = viewHolder.getView(R.id.tv_RWLX);
        TextView tv_RWZT = viewHolder.getView(R.id.tv_RWZT);
        TextView tv_TJR = viewHolder.getView(R.id.tv_TJR);
        TextView tv_TJSJ = viewHolder.getView(R.id.tv_TJSJ);
        TextView tv_XGR = viewHolder.getView(R.id.tv_XGR);
        TextView tv_XGSJ = viewHolder.getView(R.id.tv_XGSJ);
        TextView tv_BZSM = viewHolder.getView(R.id.tv_BZSM);
        TextView tv_ZJJZ = viewHolder.getView(R.id.tv_ZJJZ);


        tv_RWMC.setText("任务名称: " + item.getRWMC());
        tv_RWKSSJ.setText("任务开始时间: " + item.getRWST());
        tv_RWJSSJ.setText("任务结束时间: " + item.getRWET());
        tv_YJWCSJ.setText("预计完成时间 : " + item.getYJWCSJ());
        tv_RWLX.setText("任务类型: " + item.getRWLX());
        tv_RWZT.setText("任务状态: " + item.getRWZT());
        tv_TJR.setText("添加人: " + item.getTJR());
        tv_TJSJ.setText("添加时间: " + item.getTJSJ());
        tv_XGR.setText("修改人: " + item.getXGR());
        tv_XGSJ.setText("修改时间: " + item.getXGSJ());
        tv_BZSM.setText("备注说明: " + item.getBZSM());
        tv_ZJJZ.setText("质检机组: " + item.getZJJZ());

    }
}
