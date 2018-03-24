package com.rehome.huizhouxdj.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.XsjhQyBean;
import com.rehome.huizhouxdj.weight.ListDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/17.
 */

public class XsjhqyWorkXsAdapter extends CommonAdapter<XsjhQyBean.DataBeanX.DataBean> {


    private Context context;
    private List<XsjhQyBean.DataBeanX.DataBean> datas;
    private boolean histroy;

    public XsjhqyWorkXsAdapter(Context context, List<XsjhQyBean.DataBeanX.DataBean> datas, boolean histroy) {
        super(context, R.layout.qy_work_xs_item, datas);
        this.datas = datas;
        this.context = context;
        this.histroy = histroy;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final XsjhQyBean.DataBeanX.DataBean item, int position) {
//        viewHolder.setText(R.id.tv_qy, item.getQymc());

        EditText etEnter = viewHolder.getView(R.id.et_enter);
        final TextView tvEnter = viewHolder.getView(R.id.tv_enter);
        TextView tvInput = viewHolder.getView(R.id.tv_input);
        tvInput.setVisibility(View.GONE);

        viewHolder.setText(R.id.tv_mc, item.getSb() + item.getDw());
        viewHolder.setText(R.id.tv_dbjz, item.getDz());
        viewHolder.setText(R.id.tv_gbjz, item.getGz());
        viewHolder.setText(R.id.tv_zcz, item.getZczt());

        if (TextUtils.isEmpty(item.getXcnr())) {//如果不是选择的
            etEnter.setVisibility(View.VISIBLE);
            tvEnter.setVisibility(View.GONE);

        } else {
            etEnter.setVisibility(View.GONE);
            tvEnter.setVisibility(View.VISIBLE);
        }

        final String[] items = item.getXcnr().split(";");

        if (TextUtils.isEmpty(item.getCbsz())) {
            tvEnter.setText(items[0]);
            item.setCbsz(items[0]);
        } else {
            tvEnter.setText(item.getCbsz());
        }

        if (!histroy) {
            tvEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> lists = new ArrayList<String>();
                    for (String str : items) {
                        lists.add(str);
                    }
                    final ListDialog dialog = new ListDialog(context, lists, new ListDialog.ListDialogListener() {
                        @Override
                        public void selectText(String str, int position) {
                            tvEnter.setText(str);
                            item.setCbsz(str);
                            item.setDjsj(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                        }
                    });
                    dialog.show();
                }
            });
        }

        etEnter.clearFocus();

        if (etEnter.getTag() instanceof TextWatcher) {
            etEnter.removeTextChangedListener((TextWatcher) etEnter.getTag());
        }

        if (histroy) {
            etEnter.setVisibility(View.GONE);
            tvEnter.setVisibility(View.VISIBLE);
            tvEnter.setText(datas.get(position).getCbsz());
        } else {
            if (TextUtils.isEmpty(datas.get(position).getSisData())) {
                etEnter.setText(datas.get(position).getCbsz());
            } else {
                etEnter.setText(datas.get(position).getSisData());
            }
        }

        final TextWatcher watcher = new MyTextWatcher() {
            @Override
            public void beforeTextChange(String s) {

                if (TextUtils.isEmpty(s)) {
                    item.setCbsz("");
                } else {
                    item.setCbsz(s);
                    item.setDjsj(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                }
            }
        };

        etEnter.addTextChangedListener(watcher);
        etEnter.setTag(watcher);
    }

    public abstract class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            beforeTextChange(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        public abstract void beforeTextChange(String s);
    }
}
