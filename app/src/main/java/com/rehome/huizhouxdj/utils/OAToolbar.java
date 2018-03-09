package com.rehome.huizhouxdj.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;


/**
 * Created by Rehome-rjb1 on 2017/5/8.
 * 导航栏的封装
 *
 */

public class OAToolbar extends Toolbar {

    private LayoutInflater inflater;

    private View view;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageButton ivLeft;

    public OAToolbar(Context context) {
        super(context, null);
    }

    public OAToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        setContentInsetsRelative(10, 10);
        if (attrs != null) {
            final TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.OAToolbar);

            String title = tta.getString(R.styleable.OAToolbar_tvTitle);
            String tvRightText = tta.getString(R.styleable.OAToolbar_tvRight);
            Drawable drawable = tta.getDrawable(R.styleable.OAToolbar_ivLeftIcon);

            setIvLeftIcon(drawable);

            if (!TextUtils.isEmpty(title)) {
                setTvTitleText(title);
            }

            if (!TextUtils.isEmpty(tvRightText)) {
                setTvRightText(tvRightText);
            }

            tta.recycle();
        }
    }

    public OAToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    private void initView() {

        inflater = LayoutInflater.from(getContext());

        if (view == null) {
            view = inflater.inflate(R.layout.toolbar2, null);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvRight = (TextView) view.findViewById(R.id.tv_right);
            ivLeft = (ImageButton) view.findViewById(R.id.iv_left);
            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view, lp);
        }
    }

    public void setTvTitleText(String text) {
        if (tvTitle != null) {
            tvTitle.setText(text);
        }
    }

    public void setTvTitleColor(int color) {
        if (tvTitle != null) {
            tvTitle.setTextColor(color);
        }
    }

    public void setTvRightText(String text) {
        if (tvRight != null) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(text);
        }
    }

    public void setTvRightText(int text) {
        if (tvRight != null) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(text);
        }
    }

    public void setTvRightOnClickListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }


    public void setIvLeftIcon(int resId) {
        if (ivLeft != null) {
            ivLeft.setVisibility(VISIBLE);
            ivLeft.setImageResource(resId);
        }
    }

    public void setIvLeftIcon(Drawable drawable) {
        if (ivLeft != null) {
            ivLeft.setVisibility(VISIBLE);
            ivLeft.setImageDrawable(drawable);
        }
    }

    public void setIvLeftOnClickListener(OnClickListener listener) {
        ivLeft.setOnClickListener(listener);
    }

}
