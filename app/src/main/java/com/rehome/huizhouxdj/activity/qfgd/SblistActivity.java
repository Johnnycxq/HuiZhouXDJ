package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfSblbinfoAdapter;
import com.rehome.huizhouxdj.bean.QfsblistBean;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/17.
 */

public class SblistActivity extends BaseActivity2 implements View.OnClickListener {

    List<QfsblistBean.DataBean> datas;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private QfSblbinfoAdapter qfSblbinfoAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_qfsblist;
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

                for (QfsblistBean.DataBean dataBean : qfSblbinfoAdapter.getDatas()) {

                    if (dataBean.isSelected()) {

                        Intent resultIntent = new Intent();

                        Bundle bundle = new Bundle();

                        bundle.putString("SBID", dataBean.getSBID());

                        bundle.putString("SBMC", dataBean.getSBMC());

                        resultIntent.putExtras(bundle);

                        setResult(RESULT_OK, resultIntent);

                        finish();

                    }

                }
                break;
        }
    }

    @Override
    public void initData() {
        initToolbar("设备列表", "确定", this);
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
                    QfsblistBean qfsblistBean = GsonUtils.GsonToBean(response.get(), QfsblistBean.class);

                    datas.clear();
                    datas.addAll(qfsblistBean.getData());
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

    private String createJson() {
        QfsblistRequestBean info = new QfsblistRequestBean();
        info.setAction("Q4GD_SBLIST_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setFSBID("");
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        qfSblbinfoAdapter = new QfSblbinfoAdapter(context, datas);

        rlv.setLayoutManager(new LinearLayoutManager(context));

        rlv.setAdapter(qfSblbinfoAdapter);

        qfSblbinfoAdapter.setOnChildItemClickListener(new QfSblbinfoAdapter.OnChildItemClickListener() {
            @Override
            public void onClick(QfsblistBean.DataBean dataBean) {

                Intent intent = new Intent(SblistActivity.this, OtherSblistActivity.class);
                intent.putExtra("FSBID", dataBean.getSBID());
                intent.putExtra("SBMC", dataBean.getSBMC());
                startActivity(intent);
                finish();

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
