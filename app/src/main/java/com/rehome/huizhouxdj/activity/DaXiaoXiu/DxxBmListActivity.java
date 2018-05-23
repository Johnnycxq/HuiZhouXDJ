package com.rehome.huizhouxdj.activity.DaXiaoXiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfBmlbinfoAdapter;
import com.rehome.huizhouxdj.bean.QfbmlistBean;
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

public class DxxBmListActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    List<QfbmlistBean.DataBean> datas;
    QfBmlbinfoAdapter qfBmlbinfoAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                for (QfbmlistBean.DataBean dataBean : qfBmlbinfoAdapter.getDatas()) {

                    if (dataBean.isSelected()) {

                        Intent resultIntent = new Intent();

                        Bundle bundle = new Bundle();

                        bundle.putString("BMID", dataBean.getBMID());

                        bundle.putString("BMMC", dataBean.getBMMC());

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
        return R.layout.activity_qfbmlist;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initToolbar("部门列表", "确定", this);
        datas = new ArrayList<>();
        requestDatas();
    }

    private void requestDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.DXX, RequestMethod.POST);
        requset.setDefineRequestBodyForJson(createJson());
        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    QfbmlistBean qfbmlistBean = GsonUtils.GsonToBean(response.get(), QfbmlistBean.class);

                    datas.clear();
                    datas.addAll(qfbmlistBean.getData());
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

        qfBmlbinfoAdapter = new QfBmlbinfoAdapter(context, datas);

        rlv.setLayoutManager(new LinearLayoutManager(context));

        rlv.setAdapter(qfBmlbinfoAdapter);

        qfBmlbinfoAdapter.setOnChildItemClickListener(new QfBmlbinfoAdapter.OnChildItemClickListener() {
            @Override
            public void onClick(QfbmlistBean.DataBean dataBean) {
//                showToast(dataBean.getBMID());
            }
        });

    }


    private String createJson() {
        QfsblistRequestBean info = new QfsblistRequestBean();
        info.setAction("DXX_BMLIST_GET");
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
