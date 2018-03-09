package com.rehome.huizhouxdj.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rehome-rjb1 on 2017/5/18.
 * 列表模式的dialog
 */

public class ListDialog extends Dialog {

    @BindView(R.id.lv)
    AutoListView lv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;

    private CommonAdapter<String> adapter;
    private List<String> datas;

    private ListDialogListener listener;


    public ListDialog(@NonNull Context context, List<String> datas, ListDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.layout_take_out_time);
        ButterKnife.bind(this);
        this.listener = listener;
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (getScreenWidth(context)) * 2 / 3;
        window.setGravity(Gravity.CENTER);

        this.datas = datas;
        if (datas != null) {
            setAdapter();
        }
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    public void hideTitle() {
        llTitle.setVisibility(View.GONE);
    }

    private void setAdapter() {

        if (adapter == null) {
            adapter = new CommonAdapter<String>(getContext(), R.layout.item_content, datas) {
                @Override
                protected void convert(ViewHolder viewHolder, String item, int position) {
                    viewHolder.setText(R.id.tv_text, item);
                }
            };

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        listener.selectText(datas.get(position), position);
                        dismiss();
                    }
                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }

    }

    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public interface ListDialogListener {
        void selectText(String text, int position);
    }
}
