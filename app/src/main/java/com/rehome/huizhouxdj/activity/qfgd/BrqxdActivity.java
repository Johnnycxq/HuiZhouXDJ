package com.rehome.huizhouxdj.activity.qfgd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QxdAdapter;
import com.rehome.huizhouxdj.bean.QXRequestBean;
import com.rehome.huizhouxdj.bean.QxdBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/5/11.
 */

public class BrqxdActivity extends BaseActivity2 implements View.OnClickListener {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.yxj_et)
    EditText yxjEt;
    @BindView(R.id.yxj_sbmc)
    EditText yxjSbmc;
    @BindView(R.id.yxj_gzzt)
    EditText yxjGzzt;
    @BindView(R.id.tv_starttime)
    EditText tvStarttime;
    @BindView(R.id.tv_endtime)
    EditText tvEndtime;

    private String DJID;
    private String DJMC;

    private String SBID;
    private String SBMC;

    private List<QxdBean.DataBean> datas;
    QxdAdapter qxdadapter;
    private String yhid;

    private Calendar c = Calendar.getInstance();

    @Override
    public int getLayoutId() {
        return R.layout.activity_brqxd;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:

//                requestDatas(yhid, "", "", DJID, SBID, "", tvStarttime.getText().toString(), tvEndtime.getText().toString());

                requestDatas(yhid, "", "", "", "", "", "", "");

                break;
        }
    }


    @Override
    public void initData() {
        initToolbar("个人缺陷工单查询", "", this);
        yhid = (String) SPUtils.get(context, Contans.NAME, "");
        datas = new ArrayList<>();
        requestDatas(yhid, "", "", "", "", "", "", "");

        yxjEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrqxdActivity.this, DjListActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        yxjSbmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrqxdActivity.this, SblistActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        yxjGzzt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(BrqxdActivity.this, DjListActivity.class);
//                startActivityForResult(intent, 3);
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

    private void requestDatas(String yhid, String GDZT_NO, String zrbm, String DJID, String BMID, String SBID, String PM_ST, String PM_ET) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(yhid, GDZT_NO, zrbm, DJID, BMID, SBID, PM_ST, PM_ET));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    QxdBean qxdBean = GsonUtils.GsonToBean(response.get(), QxdBean.class);
                    if (qxdBean != null) {
                        if (qxdBean.getState() == 1) {
                            datas.clear();
                            datas.addAll(qxdBean.getData());
                            setAdapter();
                        } else {
                            datas.clear();
                            setAdapter();
                        }
                    } else {
                        showToast(R.string.data_error);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private String createJson(String yhid, String GDZT_NO, String zrbm, String DJID, String SBID, String GZZTID, String PM_ST, String PM_ET) {
        QXRequestBean info = new QXRequestBean();
        info.setAction("Q4GD_QXGD_GET");
        info.setYHID(yhid);
        info.setGZDH(GDZT_NO);
        info.setZRBM(zrbm);
        info.setGZYXJ(DJID);
        info.setSBBH(SBID);
        info.setGZZT(GZZTID);
        info.setPL_WK_DO(PM_ST);
        info.setPL_WK_TE(PM_ET);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        if (qxdadapter == null) {
            qxdadapter = new QxdAdapter(context, datas);

            lv.setAdapter(qxdadapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                }
            });

        } else {
            qxdadapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    DJID = bundle.getString("DJID");
                    DJMC = bundle.getString("DJMC");
                    yxjEt.setText(DJMC);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    SBID = bundle.getString("SBID");
                    SBMC = bundle.getString("SBMC");
                    yxjSbmc.setText(SBMC);
                }
                break;
        }
    }
}
