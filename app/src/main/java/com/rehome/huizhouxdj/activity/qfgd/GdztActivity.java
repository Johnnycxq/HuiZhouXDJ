package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfgdztinfoAdapter;
import com.rehome.huizhouxdj.bean.QfgdztlistBean;
import com.rehome.huizhouxdj.bean.QfsblistRequestBean;
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
 * Created by ruihong on 2018/4/18.
 */

public class GdztActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    List<QfgdztlistBean.DataBean> datas;
    QfgdztinfoAdapter qfgdztinfoAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                for (QfgdztlistBean.DataBean dataBean : qfgdztinfoAdapter.getDatas()) {

                    if (dataBean.isSelected()) {

                        Intent resultIntent = new Intent();

                        Bundle bundle = new Bundle();

                        bundle.putString("GDZT_NO", dataBean.getGDZT_NO());

                        bundle.putString("GDZTMC", dataBean.getGDZTMC());

                        resultIntent.putExtras(bundle);

                        setResult(RESULT_OK, resultIntent);

                        finish();

                    }
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_qfgdzt;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initToolbar("工单状态", "确定", this);
        datas = new ArrayList<>();
        requestDatas();
    }

    private void requestDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);
        requset.setDefineRequestBodyForJson(createJson());
        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    QfgdztlistBean qfgdztlistBean = GsonUtils.GsonToBean(response.get(), QfgdztlistBean.class);

                    datas.clear();
                    datas.addAll(qfgdztlistBean.getData());
                    setAdapter();

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

        qfgdztinfoAdapter = new QfgdztinfoAdapter(context, datas);

        rlv.setLayoutManager(new LinearLayoutManager(context));

        rlv.setAdapter(qfgdztinfoAdapter);

        qfgdztinfoAdapter.setOnChildItemClickListener(new QfgdztinfoAdapter.OnChildItemClickListener() {
            @Override
            public void onClick(QfgdztlistBean.DataBean dataBean) {
//                showToast(dataBean.getBMID());
            }
        });

    }


    private String createJson() {
        QfsblistRequestBean info = new QfsblistRequestBean();
        info.setAction("Q4GD_ZTLIST_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
