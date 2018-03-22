package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.XsHistoryListBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/26.
 */

public class XsHistoryExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;


    List<XsHistoryListBean.DataBeanX> datas;

    public XsHistoryExpandableListAdapter(Context context, List<XsHistoryListBean.DataBeanX> datas) {
        this.datas = datas;
        this.context = context;
    }

    /**
     * 父项的数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return datas.size();
    }

    /**
     * 子项的数量
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupPosition).getData().size();
    }

    /**
     * 获得父项
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition);
    }

    /**
     * 获得子项
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(groupPosition).getData().get(childPosition);
    }

    /**
     * 获得父项ID
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }


    /**
     * 获得子项ID
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            holder = new GroupViewHolder();
            holder.ivLeft = (ImageView) convertView.findViewById(R.id.iv_left);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(datas.get(groupPosition).getRq()+"数据");
        if (isExpanded) {
            holder.ivLeft.setImageResource(R.mipmap.unfold);
        } else {
            holder.ivLeft.setImageResource(R.mipmap.packup);
        }

        AutoUtils.auto(convertView);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
            holder = new ChildViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.time_TX = (TextView) convertView.findViewById(R.id.time_TX);


            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(datas.get(groupPosition).getData().get(childPosition).getJhmc());

        holder.time_TX.setText(datas.get(groupPosition).getData().get(childPosition).getScsj());

        AutoUtils.auto(convertView);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        ImageView ivLeft;
        TextView tvTitle;
    }

    class ChildViewHolder {
        TextView tvTitle;
        TextView time_TX;
    }
}
