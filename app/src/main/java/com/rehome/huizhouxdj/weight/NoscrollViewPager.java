package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止滑动的ViewPager
 */

public class NoscrollViewPager extends ViewPager {

    public NoscrollViewPager(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    public NoscrollViewPager(Context context) {
	super(context);
    }

    /**
     * 什么都不用做
     * @param arg0
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
	return false;
    }

    /**
     * false表示不拦截事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
	return false;
    }

}
