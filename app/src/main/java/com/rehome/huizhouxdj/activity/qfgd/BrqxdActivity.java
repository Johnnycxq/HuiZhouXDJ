package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QxdAdapter;
import com.rehome.huizhouxdj.bean.QXRequestBean;
import com.rehome.huizhouxdj.bean.QxdBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/5/11.
 */

public class BrqxdActivity extends BaseActivity {

    private String DJID;
    private String DJMC;

    private String SBID;
    private String SBMC;

    private SmartTable<QxdBean.DataBean> table;

    private List<QxdBean.DataBean> datas;

    QxdAdapter qxdadapter;
    private String yhid;

    private Calendar c = Calendar.getInstance();

    @Override
    public int getContentViewID() {
        return R.layout.activity_brqxd;
    }

    @Override
    public void initView() {
        setBack();
        setTitle("个人缺陷工单查询");
        datas = new ArrayList<>();
        table = (SmartTable<QxdBean.DataBean>) findViewById(R.id.tablegrqxd);
        table.setZoom(true);

    }


    @Override
    public void initData() {

        yhid = (String) SPUtils.get(context, Contans.USERNAME, "");
        requestDatas(yhid, "", "", "", "", "", "", "");

//        yxjEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BrqxdActivity.this, DjListActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        yxjSbmc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BrqxdActivity.this, SblistActivity.class);
//                startActivityForResult(intent, 2);
//            }
//        });
//
//        yxjGzzt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(BrqxdActivity.this, DjListActivity.class);
////                startActivityForResult(intent, 3);
//            }
//        });
//
//        tvStarttime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        c.set(year, month, dayOfMonth);
//                        tvStarttime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
//                    }
//                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
//
//                dialog.show();
//            }
//        });
//
//        tvEndtime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        c.set(year, month, dayOfMonth);
//                        tvEndtime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
//                    }
//                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
//
//                dialog.show();
//            }
//        });


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
                            parseData(datas);
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

    private void parseData(List<QxdBean.DataBean> list) {
        Column<String> mLine1 = new Column<>("工单编号", "pl_wk_wn_p");
        mLine1.setFixed(true);
        Column<String> mLine2 = new Column<>("设备名称", "eq_ma_de");
        Column<String> mLine3 = new Column<>("缺陷描述", "pl_wk_fd");
        Column<String> mLine4 = new Column<>("工作描述", "pl_wk_de");
        Column<String> mLine5 = new Column<>("状态", "wo_st_de");
        Column<String> mLine6 = new Column<>("优先级", "priorityclass");
        Column<String> mLine7 = new Column<>("责任班组", "re_tm_de");
        Column<String> mLine8 = new Column<>("项目负责人", "pl_wk_do");
        Column<String> mLine9 = new Column<>("检修班组", "pl_wk_wg");
        TableData<QxdBean.DataBean> data = new TableData("个人缺陷单查询", list, mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7);
        table.setTableData(data);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1:
//                if (resultCode == RESULT_OK) {
//                    Bundle bundle = data.getExtras();
//                    DJID = bundle.getString("DJID");
//                    DJMC = bundle.getString("DJMC");
//                    yxjEt.setText(DJMC);
//                }
//                break;
//            case 2:
//                if (resultCode == RESULT_OK) {
//                    Bundle bundle = data.getExtras();
//                    SBID = bundle.getString("SBID");
//                    SBMC = bundle.getString("SBMC");
//                    yxjSbmc.setText(SBMC);
//                }
//                break;
//        }
//    }
}
