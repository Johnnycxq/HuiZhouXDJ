package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class bpbjActivity extends BaseActivity {


    @BindView(R.id.et_bzmc)
    EditText etBzmc;
    @BindView(R.id.et_mwbm)
    EditText etMwbm;
    @BindView(R.id.et_ckh)
    EditText etCkh;
    @BindView(R.id.btn_cx)
    Button btnCx;

    @Override
    public int getContentViewID() {
        return R.layout.activity_kccx;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("库存查询");
    }

    @Override
    public void initData() {


        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(bpbjActivity.this, bpbjinfoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("etMwbm", etMwbm.getText().toString());
//                bundle.putString("etBzmc", etBzmc.getText().toString());
//                bundle.putString("etCkh", etCkh.getText().toString());
//                intent.putExtras(bundle);
//                startActivity(intent);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}