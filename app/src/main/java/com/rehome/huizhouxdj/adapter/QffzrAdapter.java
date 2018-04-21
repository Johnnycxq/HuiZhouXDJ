package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.QfFzrBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/3/28.
 */

public class QffzrAdapter extends RecyclerView.Adapter<QffzrAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<QfFzrBean.DataBean> datas;
    private Context mContext;

    private int mSelectedPos = 0;   //实现单选，保存当前选中的position

    private OnChildItemClickListener onChildItemClickListener;


    public QffzrAdapter(Context context, List<QfFzrBean.DataBean> data) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.datas = data;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
    }

    public List<QfFzrBean.DataBean> getDatas() {
        return datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_qfsblist, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.cb.setChecked(datas.get(position).isSelected());

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datas.get(mSelectedPos).setSelected(false);

                mSelectedPos = position;

                datas.get(mSelectedPos).setSelected(true);

                notifyDataSetChanged();
            }
        });

        holder.tv_smmc.setText(datas.get(position).getYHMC());
        holder.tv_smmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChildItemClickListener.onClick(datas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener) { //接口回调
        this.onChildItemClickListener = onChildItemClickListener;
    }

    public interface OnChildItemClickListener {
        void onClick(QfFzrBean.DataBean dataBean);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.tv_smmc)
        TextView tv_smmc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
