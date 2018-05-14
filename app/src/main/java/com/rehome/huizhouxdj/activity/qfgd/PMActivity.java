package com.rehome.huizhouxdj.activity.qfgd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseCallBack;
import com.rehome.huizhouxdj.bean.BmidBean;
import com.rehome.huizhouxdj.bean.MessageEvent;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.HttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.InputLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by ruihong on 2018/4/17.
 */

public class PMActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.il_gzzt)
    InputLayout ilGzzt;
    @BindView(R.id.il_gzyxj)
    InputLayout ilGzyxj;
    @BindView(R.id.il_zrbm)
    InputLayout ilZrbm;
    @BindView(R.id.il_sbmc)
    InputLayout ilSbmc;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;

    private String BMID = "";
    private String BMMC = "";
    private String DJID = "";
    private String DJMC = "";
    private String GDZT_NO = "";
    private String GDZTMC = "";
    private String SBID = "";
    private String SBMC = "";
    private Calendar c = Calendar.getInstance();

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

                String startTime = tvStarttime.getText().toString();
                String endTime = tvEndtime.getText().toString();

                Intent intent = new Intent(PMActivity.this, PminfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("GDZT_NO", GDZT_NO);
                bundle.putString("DJID", DJID);
                bundle.putString("BMID", BMID);
                bundle.putString("SBID", SBID);
                bundle.putString("PM_ST", startTime);
                bundle.putString("PM_ET", endTime);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initData() {
        initToolbar("PM工单查询", "查询", this);
//        requestDatas();


        ilSbmc.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PMActivity.this, SblistActivity.class);
                startActivityForResult(intent, 4);
            }
        });

        ilGzzt.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PMActivity.this, GdztActivity.class);
                startActivityForResult(intent1, 1);
            }
        });

        ilGzyxj.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PMActivity.this, DjListActivity.class);
                startActivityForResult(intent2, 2);
            }
        });

        ilZrbm.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PMActivity.this, BmListActivity.class);
                startActivityForResult(intent3, 3);
            }
        });

        tvStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        c.set(year, month, dayOfMonth);
                        tvStarttime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                dialog.show();

            }
        });

        tvEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        c.set(year, month, dayOfMonth);
                        tvEndtime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                dialog.show();

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        ilSbmc.setContent(messageEvent.getMessage());
        SBID = messageEvent.getMessageid();
    }


    private void requestDatas() {

        String yhid = (String) SPUtils.get(context, Contans.NAME, "");

        HttpUtils.getApi().getbmid(yhid).enqueue(new BaseCallBack<BmidBean>(context) {

            @Override
            public void onSuccess(Call<BmidBean> call, retrofit2.Response<BmidBean> response) {
                BmidBean kcmzbean = response.body();


                String bmid = kcmzbean.getRows().get(0).getRe_tm_no_p();
                String bmmc = kcmzbean.getRows().get(0).getRe_tm_de();

            }

            @Override
            public void onError(Call<BmidBean> call, Throwable t) {

            }
        });
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

