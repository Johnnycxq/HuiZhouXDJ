package com.rehome.huizhouxdj.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rehome-rjb1 on 2017/5/31.
 * 是否审核的对话框
 */

public class AuditDialog extends Dialog {

    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.dialog_cancel)
    TextView dialogCancel;
    @BindView(R.id.dialog_commit)
    TextView dialogCommit;
    @BindView(R.id.lay_dialog_cancel)
    LinearLayout layDialogCancel;

    private AuditDialogListener listener;

    public AuditDialog(@NonNull Context context, String msg, AuditDialogListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.layout_audit_dialog);
        ButterKnife.bind(this);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (getScreenWidth(context)) * 2 / 3;
        window.setGravity(Gravity.CENTER);
        this.listener = listener;
        setCancelable(false);
        setTvMsg(msg);
    }


    public void setTvMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }

    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    @OnClick({R.id.dialog_cancel, R.id.dialog_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                if (listener != null) {
                    listener.cancel();
                }
                break;
            case R.id.dialog_commit:
                dismiss();
                if (listener != null) {
                    listener.confirm();
                }
                break;
        }
    }

    public interface AuditDialogListener {
        void confirm();

        void cancel();
    }
}