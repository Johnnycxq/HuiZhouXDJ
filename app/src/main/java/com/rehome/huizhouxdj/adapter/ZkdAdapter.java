package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.ZkdInfo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * 质控点适配器
 */
public class ZkdAdapter extends BaseAdapter {

    private Context context;
    private List<ZkdInfo> list;

    public ZkdAdapter(Context context, List<ZkdInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ZkdInfo getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ZkdInfo info;
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.zkd_item, viewGroup,false);
            holder.tv_xh = (TextView) view.findViewById(R.id.tv_xh);
            holder.tv_gx = (TextView) view.findViewById(R.id.tv_gx);
            holder.tv_zt = (TextView) view.findViewById(R.id.tv_zt);
            holder.tv_fl = (TextView) view.findViewById(R.id.tv_zjdfl);
            holder.tv_aj = (TextView) view.findViewById(R.id.tv_ajzjy);
            holder.tv_bj = (TextView) view.findViewById(R.id.tv_bjzjy);
            holder.tv_cj = (TextView) view.findViewById(R.id.tv_cjzjy);
            holder.tv_jl = (TextView) view.findViewById(R.id.tv_jlgcs);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        info = getItem(i);
        holder.tv_xh.setText(info.getXh()+"");
        holder.tv_gx.setText(info.getGx());
        holder.tv_zt.setText(info.getZt());
        holder.tv_fl.setText(info.getFl());
        holder.tv_aj.setText(info.getAj());
        holder.tv_bj.setText(info.getBj());
        holder.tv_cj.setText(info.getCj());
        holder.tv_jl.setText(info.getJl());
        return view;
    }

    static class ViewHolder {
        TextView tv_xh;
        TextView tv_gx;
        TextView tv_zt;
        TextView tv_fl;
        TextView tv_aj;
        TextView tv_bj;
        TextView tv_cj;
        TextView tv_jl;
    }
}
