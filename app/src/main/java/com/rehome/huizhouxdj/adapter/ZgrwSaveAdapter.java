package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Uploadzgjg;
import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by ruihong on 2017/12/27.
 */

public class ZgrwSaveAdapter extends BaseAdapter {

    private Context context;
    private List<Uploadzgjg> list;
    private CallBack mCallBack;


    public ZgrwSaveAdapter(Context context, List<Uploadzgjg> list, CallBack mCallBack) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sblcsavedata_item2, viewGroup, false);
            holder.cb = (CheckBox) view.findViewById(R.id.cb);
            holder.tv_savetime = (TextView) view.findViewById(R.id.tv_savetime);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.cb.setChecked(list.get(i).isChecked());
        holder.tv_savetime.setText(list.get(i).getZGJG());
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
        TextView tv_savetime;

    }
}
