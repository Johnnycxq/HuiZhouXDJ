package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfPmListAdapter;
import com.rehome.huizhouxdj.bean.PMRequestBean;
import com.rehome.huizhouxdj.bean.PminfoBean2;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/27.
 */

public class QfpmgdListActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    private List<PminfoBean2.DataBean> datas;
    QfPmListAdapter adapter;
    private String GDZT_NO, DJID, BMID, SBID;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_qfpmgdlist;
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {
        initToolbar("缺陷工单列表", "", this);
        Bundle bundle = getIntent().getExtras();
        GDZT_NO = bundle.getString("GDZT_NO");
        DJID = bundle.getString("DJID");
        BMID = bundle.getString("BMID");
        SBID = bundle.getString("SBID");
        requestDatas(GDZT_NO, DJID, BMID, SBID);
    }

    private void requestDatas(String GDZT_NO, String DJID, String BMID, String SBID) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(GDZT_NO, DJID, BMID, SBID));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    PminfoBean2 pminfoBean = GsonUtils.GsonToBean(response.get(), PminfoBean2.class);

                    if (pminfoBean != null) {
                        if (pminfoBean.getState() == 1) {
                            datas.clear();
                            datas.addAll(pminfoBean.getData());
                            setAdapter();
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

    private void setAdapter() {
        if (adapter == null) {
            adapter = new QfPmListAdapter(context, datas);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    Intent intent = new Intent(QfpmgdListActivity.this, QfpmgdchangeActivity.class);
                    intent.putExtra("GZDH", datas.get(position).getGZDH());
                    startActivity(intent);

                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }
    }


    private String createJson(String GDZT_NO, String DJID, String BMID, String SBID) {
        PMRequestBean info = new PMRequestBean();
        info.setAction("Q4GD_PMGD1_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setGZZT(GDZT_NO);
        info.setZRBM(BMID);
        info.setGZYXJ(DJID);
        info.setSBBH(SBID);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestDatas(GDZT_NO, DJID, BMID, SBID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
