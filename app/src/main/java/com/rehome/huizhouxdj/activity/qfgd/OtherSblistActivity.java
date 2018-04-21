package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfSblbinfoAdapter;
import com.rehome.huizhouxdj.bean.MessageEvent;
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

/**
 * Created by ruihong on 2018/4/18.
 */

public class OtherSblistActivity extends BaseActivity2 implements View.OnClickListener {

    List<QfsblistBean.DataBean> datas;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private QfSblbinfoAdapter qfSblbinfoAdapter;
    private String FSBID, SBMC;
    private Intent intent;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:

                for (QfsblistBean.DataBean dataBean : qfSblbinfoAdapter.getDatas()) {

                    if (dataBean.isSelected()) {

                        EventBus.getDefault().post(new MessageEvent(dataBean.getSBMC(), dataBean.getSBID()));

                        finish();

                    }
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_qfsblist;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        datas = new ArrayList<>();
        FSBID = getIntent().getStringExtra("FSBID");
        SBMC = getIntent().getStringExtra("SBMC");

        initToolbar(SBMC, "确定", this);
        requestDatas(FSBID);
    }

    private void requestDatas(String FSBID) {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);
        requset.setDefineRequestBodyForJson(createJson(FSBID));
        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    QfsblistBean qfsblistBean = GsonUtils.GsonToBean(response.get(), QfsblistBean.class);

                    if (qfsblistBean.getData().size() == 0) {
                        showToast("已经是最后一级了");
                    } else {
                        datas.clear();
                        datas.addAll(qfsblistBean.getData());
                        setAdapter();
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

    private String createJson(String FSBID) {
        QfsblistRequestBean info = new QfsblistRequestBean();
        info.setAction("Q4GD_SBLIST_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setFSBID(FSBID);
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


//                showToast(dataBean.getSBID());

                requestDatas(dataBean.getSBID());

                qfSblbinfoAdapter.notifyDataSetChanged();

            }
        });

    }

}
