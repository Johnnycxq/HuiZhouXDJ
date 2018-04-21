package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.PMRequestBean;
import com.rehome.huizhouxdj.bean.PminfoBean;
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

    private SmartTable<PminfoBean.DataBean> table;

    private List<PminfoBean.DataBean> datas;

    @Override
    public int getContentViewID() {
        return R.layout.activity_pminfo;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("查询详情");
        table = (SmartTable<PminfoBean.DataBean>) findViewById(R.id.table);
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
        requestDatas(GDZT_NO, DJID, BMID, SBID);
    }

    private void requestDatas(String GDZT_NO, String DJID, String BMID, String SBID) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(GDZT_NO, DJID, BMID, SBID));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    PminfoBean pminfoBean = GsonUtils.GsonToBean(response.get(), PminfoBean.class);

                    if (pminfoBean != null) {
                        if (pminfoBean.getState() == 1) {
                            showToast(pminfoBean.getMsg());
                            datas.clear();
                            datas.addAll(pminfoBean.getData());
                            table.setData(datas);//设置数据给table

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


    private String createJson(String GDZT_NO, String DJID, String BMID, String SBID) {
        PMRequestBean info = new PMRequestBean();
        info.setAction("Q4GD_PMGD_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setGZZT(GDZT_NO);
        info.setZRBM(BMID);
        info.setGZYXJ(DJID);
        info.setSBBH(SBID);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}