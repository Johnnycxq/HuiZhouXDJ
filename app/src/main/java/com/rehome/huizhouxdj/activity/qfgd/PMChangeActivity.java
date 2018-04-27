package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.MessageEvent;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.weight.InputLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/17.
 */

public class PMChangeActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.il_gzzt)
    InputLayout ilGzzt;
    @BindView(R.id.il_gzyxj)
    InputLayout ilGzyxj;
    @BindView(R.id.il_zrbm)
    InputLayout ilZrbm;
    @BindView(R.id.il_sbmc)
    InputLayout ilSbmc;

    private String BMID = "";
    private String BMMC = "";
    private String DJID = "";
    private String DJMC = "";
    private String GDZT_NO = "";
    private String GDZTMC = "";
    private String SBID = "";
    private String SBMC = "";


    @Override
    public int getLayoutId() {
        return R.layout.activity_pm;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(PMChangeActivity.this, QfpmgdListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("GDZT_NO", GDZT_NO);
                bundle.putString("DJID", DJID);
                bundle.putString("BMID", BMID);
                bundle.putString("SBID", SBID);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initData() {

        initToolbar("PM工单查询", "查询", this);

        ilSbmc.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PMChangeActivity.this, SblistActivity.class);
                startActivityForResult(intent, 4);
            }
        });

        ilGzzt.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PMChangeActivity.this, GdztActivity.class);
                startActivityForResult(intent1, 1);
            }
        });

        ilGzyxj.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PMChangeActivity.this, DjListActivity.class);
                startActivityForResult(intent2, 2);
            }
        });

        ilZrbm.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PMChangeActivity.this, BmListActivity.class);
                startActivityForResult(intent3, 3);
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        ilSbmc.setContent(messageEvent.getMessage());
        SBID = messageEvent.getMessageid();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 4:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    SBID = bundle.getString("SBID");
                    SBMC = bundle.getString("SBMC");
                    ilSbmc.setContent(SBMC);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    GDZT_NO = bundle.getString("GDZT_NO");
                    GDZTMC = bundle.getString("GDZTMC");
                    ilGzzt.setContent(GDZTMC);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    DJID = bundle.getString("DJID");
                    DJMC = bundle.getString("DJMC");
                    ilGzyxj.setContent(DJMC);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    BMID = bundle.getString("BMID");
                    BMMC = bundle.getString("BMMC");
                    ilZrbm.setContent(BMMC);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}

