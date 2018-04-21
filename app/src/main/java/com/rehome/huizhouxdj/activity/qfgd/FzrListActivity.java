package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QffzrAdapter;
import com.rehome.huizhouxdj.bean.QfFzrBean;
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
 * Created by ruihong on 2018/4/19.
 */

public class FzrListActivity extends BaseActivity2 implements View.OnClickListener {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private Intent intent;
    private String BMID;

    List<QfFzrBean.DataBean> datas;

    private QffzrAdapter qffzradapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                for (QfFzrBean.DataBean dataBean : qffzradapter.getDatas()) {

                    if (dataBean.isSelected()) {

                        Intent resultIntent = new Intent();

                        Bundle bundle = new Bundle();

                        bundle.putString("YHID", dataBean.getYHID());

                        bundle.putString("YHMC", dataBean.getYHMC());

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
        return R.layout.activity_fzrlist;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initToolbar("负责人列表", "确定", this);
        datas = new ArrayList<>();
        BMID = getIntent().getStringExtra("zrbzBMID");
        requestDatas(BMID);
    }

    private void requestDatas(String BMID) {
        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);
        requset.setDefineRequestBodyForJson(createJson(BMID));
        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {


                    QfFzrBean qfFzrBean = GsonUtils.GsonToBean(response.get(), QfFzrBean.class);

                    datas.clear();
                    datas.addAll(qfFzrBean.getData());
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
        qffzradapter = new QffzrAdapter(context, datas);

        rlv.setLayoutManager(new LinearLayoutManager(context));

        rlv.setAdapter(qffzradapter);

        qffzradapter.setOnChildItemClickListener(new QffzrAdapter.OnChildItemClickListener() {
            @Override
            public void onClick(QfFzrBean.DataBean dataBean) {
//                showToast(dataBean.getBMID());
            }
        });


    }

    private String createJson(String BMID) {
        QfsblistRequestBean info = new QfsblistRequestBean();
        info.setAction("Q4GD_YHLIST_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setBMID(BMID);
        info.setBMBSF("");
        String json = GsonUtils.GsonString(info);
        return json;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
