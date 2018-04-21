package com.rehome.huizhouxdj.weight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rehome.huizhouxdj.R;

import static com.rehome.huizhouxdj.R.layout.toastviewlayout;

/**
 * Created by ruihong on 2017/8/15.
 */

public class toastviewbymyself {

    private Toast mToast;

    private toastviewbymyself(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(toastviewlayout, null);
        TextView textView = (TextView) v.findViewById(R.id.textView1);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static toastviewbymyself makeText(Context context, CharSequence text, int duration) {
        return new toastviewbymyself(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
