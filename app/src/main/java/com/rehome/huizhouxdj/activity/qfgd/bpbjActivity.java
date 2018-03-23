package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.QfkccxlbListAdapter;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class bpbjActivity extends BaseActivity {


    @BindView(R.id.et_bzmc)
    EditText etBzmc;
    @BindView(R.id.et_mwbm)
    EditText etMwbm;
    @BindView(R.id.et_ckh)
    EditText etCkh;
    @BindView(R.id.btn_cx)
    Button btnCx;
    @BindView(R.id.lv)
    ListView lv;

    private List<Qfkccxbean.DataBean> datas;

    QfkccxlbListAdapter qfkccxlbListAdapter;


    @Override
    public int getContentViewID() {
        return R.layout.activity_kccx;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("库存查询");
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {


        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDatas();
            }
        });
    }


    private void requestDatas() {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson());

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

    private void setAdapter() {

        if (qfkccxlbListAdapter == null) {
            qfkccxlbListAdapter = new QfkccxlbListAdapter(context, datas);

            lv.setAdapter(qfkccxlbListAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                }
            });

        } else {
            qfkccxlbListAdapter.notifyDataSetChanged();
        }
    }

    private String createJson() {
        qfbpbjRequestBean info = new qfbpbjRequestBean();
        info.setAction("Q4GD_KCCX_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setWZBM(etMwbm.getText().toString());
        info.setWZMC(etBzmc.getText().toString());
        info.setCKH(etCkh.getText().toString());
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}