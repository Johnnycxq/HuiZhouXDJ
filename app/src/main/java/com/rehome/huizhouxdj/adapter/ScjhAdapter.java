package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;


/**
 * 下载计划适配器
 */
public class ScjhAdapter extends BaseAdapter {

    private Context context;
    private List<XDJJHXZDataBean> list;
    private CallBack mCallBack;

    public ScjhAdapter(Context context, List<XDJJHXZDataBean> list, CallBack mCallBack) {
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
            view = LayoutInflater.from(context).inflate(R.layout.scjh_item, viewGroup, false);
            holder.cb = (CheckBox) view.findViewById(R.id.cb);
            holder.tv_gwmc = (TextView) view.findViewById(R.id.tv_gwmc);
            holder.tv_yjzj = (TextView) view.findViewById(R.id.tv_yjzj);

            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.cb.setChecked(list.get(i).isChecked());
        holder.tv_gwmc.setText(list.get(i).getQYBH());
        holder.tv_yjzj.setText(list.get(i).getCountPercent());

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
        TextView tv_gwmc;
        TextView tv_yjzj;

    }
}
