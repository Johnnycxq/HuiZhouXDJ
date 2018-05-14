package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/5/11.
 */

public class BrqxdActivity extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;
    private List<QxdBean.DataBean> datas;
    QxdAdapter qxdadapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_brqxd;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("查询详情");
        datas = new ArrayList<>();
        String yhid = (String) SPUtils.get(context, Contans.NAME, "");
        requestDatas("", "", "", "", yhid, "");
    }

    private void requestDatas(String GZDH, String SBBH, String GZZT, String GZYXJ, String PL_WK_DO, String PL_WK_TE) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(GZDH, SBBH, GZZT, GZYXJ, PL_WK_DO, PL_WK_TE));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    QxdBean qxdBean = GsonUtils.GsonToBean(response.get(), QxdBean.class);
                    if (qxdBean != null) {
                        if (qxdBean.getState() == 1) {
                            showToast(qxdBean.getMsg());
                            datas.clear();
                            datas.addAll(qxdBean.getData());
                            setAdapter();
                        } else {
                            showToast(qxdBean.getMsg());
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

    private String createJson(String GDZT_NO, String DJID, String BMID, String SBID, String PM_ST, String PM_ET) {
        QXRequestBean info = new QXRequestBean();
        info.setAction("Q4GD_QXGD_GET");
        info.setGZDH(GDZT_NO);
        info.setSBBH(DJID);
        info.setGZZT(BMID);
        info.setGZYXJ(SBID);
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
}
