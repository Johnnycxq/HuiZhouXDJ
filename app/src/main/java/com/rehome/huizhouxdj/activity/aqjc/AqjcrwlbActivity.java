package com.rehome.huizhouxdj.activity.aqjc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.AqjcrwlbListAdapter;
import com.rehome.huizhouxdj.bean.AqjcrwListBean;
import com.rehome.huizhouxdj.bean.XsRequestInfo2;
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
 * Created by ruihong on 2018/1/20.
 */

public class AqjcrwlbActivity extends BaseActivity {


    @BindView(R.id.lv)
    ListView lv;

    private List<AqjcrwListBean.DataBean> datas;

    private AqjcrwlbListAdapter aqjcrwlbListAdapter;


    @Override
    public int getContentViewID() {
        return R.layout.activity_aqjcrwlb;
    }

    @Override
    protected void initView() {
        setTitle("安全检查整改列表");
        setBack();
        datas = new ArrayList<>();
        requestDatas();
    }


    private void requestDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.AQJC, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {

                    AqjcrwListBean aqjcrwListBean = GsonUtils.GsonToBean(response.get(), AqjcrwListBean.class);

                    if (aqjcrwListBean != null) {
                        if (aqjcrwListBean.getState() == 1) {
                            showToast(aqjcrwListBean.getMsg());
                            datas.clear();
                            datas.addAll(aqjcrwListBean.getData());
                            setAdapter();
                        } else {
                            showToast(aqjcrwListBean.getMsg());
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

    private String createJson() {
        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");
        XsRequestInfo2 info = new XsRequestInfo2();
        info.setAction("AQJC_RWLIST_GET");
        info.setYHID(USERNAME);
        info.setRWZT("2");
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        if (aqjcrwlbListAdapter == null) {
            aqjcrwlbListAdapter = new AqjcrwlbListAdapter(context, datas);
            lv.setAdapter(aqjcrwlbListAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Intent intent = new Intent(AqjcrwlbActivity.this, AqjcrwlbinfoActivity.class);
                    intent.putExtra("aqjcrwlb", datas.get(position));
                    startActivity(intent);

                }
            });

        } else {
            aqjcrwlbListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
