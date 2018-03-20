package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 可以加减的控件
 */
public class NumAddSubView extends LinearLayout {

    @BindView(R.id.ibtn_sub)
    ImageButton ibtnSub;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ibtn_add)
    ImageButton ibtnAdd;
    private View view;

    private int maxNum = 100;
    private int minNum = 1;

    public NumAddSubView(Context context) {
        super(context, null);
        initView();
    }

    public NumAddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

        if (attrs != null) {
            TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.NumAddSubView);
            maxNum = type.getInt(R.styleable.NumAddSubView_maxNum, 100);
            minNum = type.getInt(R.styleable.NumAddSubView_minNum, 1);
        }
    }

    public NumAddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        if (view == null) {
            view = View.inflate(getContext(), R.layout.num_add_sub_layout, this);
            ButterKnife.bind(view);
        }

        tvNum.setText("1");
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public void setNum(String num) {
        tvNum.setText(num);
    }

    @OnClick({R.id.ibtn_add, R.id.ibtn_sub})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_add:
                int nowMax = getNum(tvNum) + 1;
                if (nowMax <= maxNum) {
                    tvNum.setText(nowMax + "");
                    if (listener != null) {
                        listener.numChange(tvNum.getText().toString());
                    }
                }
                break;
            case R.id.ibtn_sub:

                int nowMin = getNum(tvNum) - 1;
                if (nowMin >= minNum) {
                    tvNum.setText(nowMin + "");
                    if (listener != null) {
                        listener.numChange(tvNum.getText().toString());
                    }
                }
                break;
        }
    }

    public int getNum(TextView num) {
        return Integer.parseInt(num.getText().toString());
    }

    public String getNum() {
        return tvNum.getText().toString();
    }

    private NumChangeListener listener;

    public void setListener(NumChangeListener listener) {
        this.listener = listener;
    }

    public interface NumChangeListener {
        void numChange(String num);
    }
}
