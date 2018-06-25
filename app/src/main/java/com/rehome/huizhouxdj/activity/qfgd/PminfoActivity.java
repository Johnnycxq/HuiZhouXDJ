package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.draw.MultiLineDrawFormat;
import com.bin.david.form.data.table.TableData;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.PMRequestBean;
import com.rehome.huizhouxdj.bean.PminfoNewBean;
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
import java.util.List;

import butterknife.ButterKnife;

public class PminfoActivity extends BaseActivity {

    private SmartTable<PminfoNewBean.DataBean> table;

    private List<PminfoNewBean.DataBean> datas;

    @Override
    public int getContentViewID() {
        return R.layout.activity_pminfo;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("查询详情");
        table = (SmartTable<PminfoNewBean.DataBean>) findViewById(R.id.table);
        table.setZoom(true);
        datas = new ArrayList<>();


    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String GDZT_NO = bundle.getString("GDZT_NO");
        String DJID = bundle.getString("DJID");
        String BMID = bundle.getString("BMID");
        String SBID = bundle.getString("SBID");
        String PM_ST = bundle.getString("PM_ST");
        String PM_ET = bundle.getString("PM_ET");


        requestDatas(GDZT_NO, DJID, BMID, SBID, PM_ST, PM_ET);
    }

    private void requestDatas(String GDZT_NO, String DJID, String BMID, String SBID, String PM_ST, String PM_ET) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson("", "", BMID, "", PM_ST, PM_ET));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    PminfoNewBean pminfoBean = GsonUtils.GsonToBean(response.get(), PminfoNewBean.class);

                    if (pminfoBean != null) {
                        if (pminfoBean.getState() == 1) {

                            showToast(pminfoBean.getMsg());

                            datas.clear();

                            datas.addAll(pminfoBean.getData());

                            parseData(datas);

//                            table.setData(datas);//设置数据给table

                        } else {
                            showToast(pminfoBean.getMsg());
                            datas.clear();

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


    private String createJson(String GDZT_NO, String DJID, String BMID, String SBID, String PM_ST, String PM_ET) {
        PMRequestBean info = new PMRequestBean();
        info.setAction("Q4GD_PMGD_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setGZZT(GDZT_NO);
        info.setZRBM(BMID);
        info.setGZYXJ(DJID);
        info.setSBBH(SBID);
        info.setPM_ST(PM_ST);
        info.setPM_ET(PM_ET);
        String json = GsonUtils.GsonString(info);
        return json;
    }


    private void parseData(List<PminfoNewBean.DataBean> list) {
        Column<String> mLine1 = new Column<>("PM单号", "ta_pm_no_p");
        mLine1.setFixed(true);
        Column<String> mLine2 = new Column<>("设备描述", "eq_ma_de");
        Column<String> mLine3 = new Column<>("工作规范描述", "ta_ws_de");
        mLine3.setDrawFormat(new MultiLineDrawFormat<String>(800));
        Column<String> mLine4 = new Column<>("数据日期", "ta_pm_ld");
        Column<String> mLine5 = new Column<>("下次到期", "ta_pm_nextdue");
        Column<String> mLine6 = new Column<>("PM状态", "ta_pm_fl");
        Column<String> mLine7 = new Column<>("责任单位", "re_tm_de");
        Column<String> mLine8 = new Column<>("工作规范", "ta_md_de");
        TableData<PminfoNewBean.DataBean> data = new TableData("PM工单查询", list, mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7, mLine8);
        table.setTableData(data);
        table.setZoom(true, 3, 1);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}