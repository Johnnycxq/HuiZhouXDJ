package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.BpbjinfoAdapter;
import com.rehome.huizhouxdj.bean.Qfkccxbean;
import com.rehome.huizhouxdj.bean.qfbpbjRequestBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class bpbjinfoActivity extends BaseActivity {


    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.LL_Head)
    LinearLayout LLHead;
    private View headView;

    BpbjinfoAdapter bpbjinfoAdapter;
    private ArrayList<Qfkccxbean.DataBean> datas;

    @Override
    public int getContentViewID() {
        return R.layout.activity_kccxinfo;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("查询详情");
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String etMwbm = bundle.getString("etMwbm");
        String etBzmc = bundle.getString("etBzmc");
        String etCkh = bundle.getString("etCkh");
        requestDatas(etMwbm, etBzmc, etCkh);
    }

    private void requestDatas(String mwbm, String bzmc, String ckh) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(mwbm, bzmc, ckh));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    Qfkccxbean qfkccxbean = GsonUtils.GsonToBean(response.get(), Qfkccxbean.class);

                    if (qfkccxbean != null) {
                        if (qfkccxbean.getState() == 1) {
                            showToast(qfkccxbean.getMsg());
                            datas.clear();
                            datas.addAll(qfkccxbean.getData());
                            setAdapter();
                        } else {
                            showToast(qfkccxbean.getMsg());
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


    private String createJson(String mwbm, String bzmc, String ckh) {
        qfbpbjRequestBean info = new qfbpbjRequestBean();
        info.setAction("Q4GD_KCCX_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setWZBM(mwbm);
        info.setWZMC(bzmc);
        info.setCKH(ckh);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        if (bpbjinfoAdapter == null) {


            headView = View.inflate(context, R.layout.item_bpbjinfo, null);
            headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
            bpbjinfoAdapter = new BpbjinfoAdapter(context, datas);
            lv.addHeaderView(headView, "", false);
            lv.setAdapter(bpbjinfoAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                }
            });

        } else {
            bpbjinfoAdapter.notifyDataSetChanged();
        }

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem >= 1) {

                    LLHead.setVisibility(View.VISIBLE);
                } else {

                    LLHead.setVisibility(View.GONE);
                }
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}