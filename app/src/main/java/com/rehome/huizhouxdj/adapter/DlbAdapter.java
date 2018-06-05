package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * 点列表适配器
 */
public class DlbAdapter extends BaseAdapter {

    private Context context;
    //    private List<QYDDATABean> list;
    private List<DlbInfo> list;

    public DlbAdapter(Context context, List<DlbInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.dlb_item, viewGroup, false);
            holder.tv_d = (TextView) view.findViewById(R.id.tv_d);
            holder.tv_cjjg = (TextView) view.findViewById(R.id.tv_cjjg);
            holder.tv_zt = (TextView) view.findViewById(R.id.tv_zt);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.tv_d.setText(list.get(i).getDian());
        holder.tv_cjjg.setText(list.get(i).getCjjg());


        if (list.get(i).getCjjg() != null) {
            if (list.get(i).getCjjg().equals("异常")) {
                holder.tv_cjjg.setTextColor(Color.RED);
            } else if (list.get(i).getCjjg().equals("已停用")) {
                holder.tv_cjjg.setTextColor(Color.RED);
            } else if (list.get(i).getCjjg().equals("不正常")) {
                holder.tv_cjjg.setTextColor(Color.RED);
            } else {
                holder.tv_cjjg.setTextColor(Color.GRAY);
            }

        }


        if (list.get(i).getCjjg() == null) {

        } else if (list.get(i).getCjjg().equals("已停用")) {
            holder.tv_zt.setText("已检");
            holder.tv_zt.setTextColor(Color.GREEN);
        } else {
            holder.tv_zt.setText("未检");
            holder.tv_zt.setTextColor(Color.RED);

        }

        if (list.get(i).isStatu()) {
            holder.tv_zt.setText("已检");
            holder.tv_zt.setTextColor(Color.GREEN);
        } else {
            holder.tv_zt.setText("未检");
            holder.tv_zt.setTextColor(Color.RED);
        }
        return view;
    }

    static class ViewHolder {
        TextView tv_cjjg;
        TextView tv_d;
        TextView tv_zt;
    }
}
