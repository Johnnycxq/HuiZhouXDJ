package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * gridview适配器
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;

    private List<GridViewBean> datas;

    private int item = 0;

    private List<Integer> items;//通过传入一些item，让传入的item不可以点击

    private boolean flag;

    public GridViewAdapter(Context context, List<GridViewBean> datas, List<Integer> items, boolean flag) {
        this.context = context;
        this.items = items;
        this.flag = flag;
        this.datas = datas;
    }

    @Override
    public int getCount() {

        return datas.size();
    }

    @Override
    public GridViewBean getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item, viewGroup, false);
            holder.iv = (ImageView) view.findViewById(R.id.iv);
            holder.tv = (TextView) view.findViewById(R.id.tv);
            holder.ll = (LinearLayout) view.findViewById(R.id.ll);
            holder.red_dot = (ImageView) view.findViewById(R.id.iv_reddot);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        GridViewBean bean = getItem(position);

//        holder.ll.setBackgroundColor(context.getResources().getColor(bean.getBackgroup()));

        holder.ll.setBackgroundResource(bean.getBackgroup());
        holder.red_dot.setVisibility(bean.isShow() ? View.VISIBLE : View.GONE);

        if (bean.getTitle() != " ") {
            holder.iv.setImageResource(bean.getImageid());
        }
        holder.tv.setText(bean.getTitle());
        return view;
    }

    static class ViewHolder {
        TextView tv;
        ImageView iv;
        LinearLayout ll;
        ImageView red_dot;
    }

    //重写isEnabled,传入哪个position，
    @Override
    public boolean isEnabled(int position) {

        if (!flag) {
            for (int i = 0; i < items.size(); i++) {
                if (position == items.get(i)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
