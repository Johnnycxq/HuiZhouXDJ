package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * 下载计划适配器
 */
public class XzjhAdapter extends BaseAdapter {

    private Context context;
    private List<Djjh> list;
    private CallBack mCallBack;

    public XzjhAdapter(Context context, List<Djjh> list, CallBack mCallBack) {
        this.context = context;
        this.list = list;
        this.mCallBack = mCallBack;
    }

    public interface CallBack {
        public void Click(View view);
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
            view = LayoutInflater.from(context).inflate(R.layout.xzjh_item, viewGroup, false);
            holder.cb = (CheckBox) view.findViewById(R.id.cb);
            holder.tv_jhlx = (TextView) view.findViewById(R.id.tv_jhlx);
            holder.tv_jhmc = (TextView) view.findViewById(R.id.tv_jhmc);
            holder.tv_xh = (TextView) view.findViewById(R.id.tv_xh);
            holder.tv_xzr = (TextView) view.findViewById(R.id.tv_xzr);
            holder.tv_xzsj = (TextView) view.findViewById(R.id.tv_xzsj);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.cb.setChecked(list.get(i).isChecked());
        holder.tv_jhlx.setText(list.get(i).getDQSJ());
        holder.tv_jhmc.setText(list.get(i).getJHMC());
        holder.tv_xzr.setText(list.get(i).getXZR());
        holder.tv_xzsj.setText(list.get(i).getXZSJ());


        holder.tv_xh.setText(i + 1 + "");
        holder.cb.setTag(i);
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.Click(view);
            }
        });
        return view;
    }

    static class ViewHolder {
        CheckBox cb;
        TextView tv_xh;
        TextView tv_jhmc;
        TextView tv_jhlx;
        TextView tv_xzr;
        TextView tv_xzsj;
    }
}
