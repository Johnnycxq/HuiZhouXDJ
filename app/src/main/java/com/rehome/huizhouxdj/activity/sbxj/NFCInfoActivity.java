package com.rehome.huizhouxdj.activity.sbxj;

import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.utils.BaseActivity;

import butterknife.BindView;

public class NFCInfoActivity extends BaseActivity {

    @BindView(R.id.tv_nfc)
    TextView tvNfc;

    @Override
    public int getContentViewID() {
        return R.layout.activity_nfcinfo;
    }

    @Override
    protected void initView() {
        setTitle("读取NFC标签");
        setBack();
        initNFC();
    }

    @Override
    public void handleNfc(String result) {
        super.handleNfc(result);
        tvNfc.setText(result);
    }
}
