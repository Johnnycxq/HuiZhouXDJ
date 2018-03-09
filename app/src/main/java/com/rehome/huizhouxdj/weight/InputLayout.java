package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rehome-rjb1 on 2017/5/9.
 * <p>
 * 输入框
 * <declare-styleable name="InputLayout">
 * <attr name="tv_title" format="string" /><!-- 标题 -->
 * <attr name="isShowSelect" format="boolean" /><!-- 是否显示编辑框 -->
 * <attr name="isShowTextView" format="boolean" /><!-- 是否显示文本框 -->
 * <attr name="isShowNumSubView" format="boolean" /><!-- 是否显示可以加减的控件 -->
 * <attr name="minValue" format="integer" /><!-- 设置可以加减控件的最大值 -->
 * <attr name="hintText" format="string" /><!-- 提示文本 -->
 * <attr name="must" format="boolean" /><!-- 是否一定要填写，如果一定要填写 就显示红色的* -->
 * </declare-styleable>
 */

public class InputLayout extends AutoLinearLayout {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_content)
    ClearEditText etContent;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.numAddSubView)
    NumAddSubView numAddSubView;
    @BindView(R.id.ibtn_select)
    ImageButton ibtnSelect;

    private View view;

    public InputLayout(Context context) {
        super(context, null);
        initView();
    }

    public InputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        if (attrs != null) {
            TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);

            String title = type.getString(R.styleable.InputLayout_tv_title);
            Boolean isShowSelect = type.getBoolean(R.styleable.InputLayout_isShowSelect, false);
            Boolean isShowTextView = type.getBoolean(R.styleable.InputLayout_isShowTextView, false);
            Boolean isShowNumSubView = type.getBoolean(R.styleable.InputLayout_isShowNumSubView, false);
            String hintText = type.getString(R.styleable.InputLayout_hintText);
            Boolean must = type.getBoolean(R.styleable.InputLayout_must, false);

            int minValue = type.getInt(R.styleable.InputLayout_minValue, 1);
            numAddSubView.setMinNum(minValue);

            setHintText(hintText);
            setTvTitle(title, must);
            isShowIbtnSelect(isShowSelect);
            isShowTextView(isShowTextView);
            isShowNumSubView(isShowNumSubView);
            type.recycle();
        }
    }


    public InputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        if (view == null) {
            view = View.inflate(getContext(), R.layout.layout_input, this);
            ButterKnife.bind(this, view);
            AutoUtils.auto(view);
        }

        etContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (event != null) {
                    return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
                } else {
                    return false;
                }
            }
        });

    }

    public void setTvTitle(String title, boolean must) {

        if (must) {
            String mustTitle = title + "<font color='red'>*</font>";
            tvTitle.setText(Html.fromHtml(mustTitle + ":"));
        } else {
            tvTitle.setText(title + ":");
        }
    }

    public void isShowIbtnSelect(boolean isShow) {
        ibtnSelect.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void isShowTextView(boolean isShow) {

        if (isShow) {
            tvContent.setVisibility(VISIBLE);
            etContent.setVisibility(GONE);
        }
    }

    public void isShowNumSubView(boolean isShow) {
        numAddSubView.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * 设置选择控件的点击事件
     *
     * @param listener
     */
    public void setIbtnSelectOnClickListener(OnClickListener listener) {
        ibtnSelect.setOnClickListener(listener);
    }

    /**
     * 设置文本框点击事件
     *
     * @param listener
     */
    public void setTvContentOnClickListener(OnClickListener listener) {
        tvContent.setOnClickListener(listener);
    }


    /**
     * 设置文本内容
     *
     * @param content
     */
    public void setContent(String content) {

        if (tvContent.getVisibility() == VISIBLE) {
            tvContent.setText(content);
        } else {
            etContent.setText(content);
        }
    }

    public void setHintText(String hintText) {
        tvContent.setHint(hintText);
    }

    /**
     * 获取文本内容
     *
     * @return
     */
    public String getContent() {
        String content;
        if (tvContent.getVisibility() == VISIBLE) {
            content = tvContent.getText().toString();
        } else {
            content = etContent.getText().toString();
        }
        return content;
    }

    /**
     * 设置为+号的按钮
     */
    public void setIbtnSelectIcon() {
        ibtnSelect.setImageResource(R.mipmap.add_gray);
    }

    /**
     * 获取加减控件的值
     *
     * @return
     */
    public String getNum() {
        return numAddSubView.getNum();
    }

    public void setNum(String num) {
        numAddSubView.setNum(num);
    }

    /**
     * 设置不可编辑
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        etContent.setVisibility(VISIBLE);
        tvContent.setVisibility(GONE);

        etContent.setEnabled(enabled);
        tvContent.setEnabled(enabled);
        ibtnSelect.setEnabled(enabled);
        numAddSubView.setEnabled(enabled);
    }

    /**
     * 设置为数字类型的InputType
     */
    public void setEtNumInputType() {
        etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    /**
     * 设置为数字类型的InputType 可加小数点
     */
    public void setNo() {
        etContent.setInputType(InputType.TYPE_CLASS_PHONE);
    }
}
