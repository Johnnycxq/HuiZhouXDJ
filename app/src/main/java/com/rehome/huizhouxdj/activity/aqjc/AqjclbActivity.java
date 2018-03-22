package com.rehome.huizhouxdj.activity.aqjc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.AqjclbAdapter;
import com.rehome.huizhouxdj.bean.AqjclbBean;
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
 * Created by ruihong on 2017/12/28.
 */

public class AqjclbActivity extends BaseActivity {


    @BindView(R.id.lv)
    ListView lv;

    private List<AqjclbBean.DataBean> datas;

    AqjclbAdapter aqjclbAdapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_aqjclb;
    }

    @Override
    protected void initView() {
        setTitle("安全检查计划列表");
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

                    AqjclbBean aqjclbBean = GsonUtils.GsonToBean(response.get(), AqjclbBean.class);
                    if (aqjclbBean != null) {
                        if (aqjclbBean.getState() == 1) {
                            showToast(aqjclbBean.getMsg());
                            datas.clear();
                            datas.addAll(aqjclbBean.getData());
                            setAdapter();
                        } else {
                            showToast(aqjclbBean.getMsg());
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

    private String createJson() {
        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");
        XsRequestInfo2 info = new XsRequestInfo2();
        info.setAction("AQJC_JHLIST_GET");
        info.setYHID(USERNAME);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        if (aqjclbAdapter == null) {
            aqjclbAdapter = new AqjclbAdapter(context, datas);

            lv.setAdapter(aqjclbAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    Intent intent = new Intent(AqjclbActivity.this, AqjclbinfoActivity.class);
                    intent.putExtra("aqjcjh", datas.get(position));
                    startActivity(intent);


                }
            });

        } else {
            aqjclbAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
