package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.ContactListBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/5.
 * 通讯录adapter
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<ContactListBean.RowsBean.OrderlistBean> datas;

    public ContactAdapter(Context context, List<ContactListBean.RowsBean.OrderlistBean> datas) {

        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ContactListBean.RowsBean.OrderlistBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactListBean.RowsBean.OrderlistBean item = datas.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact1, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvDeptName = (TextView) convertView.findViewById(R.id.tv_dept_name);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.ll);
            holder.headView = (ImageView) convertView.findViewById(R.id.headView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!TextUtils.isEmpty(item.getName())) {
            holder.ll.setVisibility(View.VISIBLE);
            holder.tvDeptName.setVisibility(View.GONE);
            holder.tvName.setText(item.getName());
            holder.tvPhone1.setText(item.getTelephone());
            holder.tvPhone2.setText(item.getAddress_tel());

//            Log.e("#############",Contans.BASE_URL + item.getAccount_head());

            if (!item.getAccount_head().equals("")){
                Glide.with(context).load(Contans.IP + item.getAccount_head()).into(holder.headView);
            }else {
                holder.headView.setImageResource(R.drawable.head_photo);
            }



        } else {

            if (position == 0) {
                holder.tvDeptName.setVisibility(View.GONE);
                holder.ll.setVisibility(View.GONE);
            } else {
                holder.tvDeptName.setText(item.getGroupName());
                holder.ll.setVisibility(View.GONE);
                holder.tvDeptName.setVisibility(View.VISIBLE);
            }
        }

        AutoUtils.auto(convertView);

        return convertView;
    }

    public static class ViewHolder {
        TextView tvName;
        TextView tvDeptName;
        TextView tvPhone1;
        TextView tvPhone2;
        ImageView headView;
        LinearLayout ll;
    }
}
