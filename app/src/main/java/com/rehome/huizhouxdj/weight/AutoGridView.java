package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Rehome-rjb1 on 2017/5/16.
 * 自适应的gridview
 */

public class AutoGridView extends GridView {

    public AutoGridView(Context context) {
        super(context);
    }

    public AutoGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
