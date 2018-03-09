package com.rehome.huizhouxdj.base;

import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.Gzqk;
import com.rehome.huizhouxdj.bean.GzqkInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.AutoToolbar;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import butterknife.BindView;

public class GzqkPushckActivity extends BaseActivity {

    @BindView(R.id.title)
    AppCompatTextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.tv_lrr)
    TextView tvLrr;
    @BindView(R.id.tv_inputtime)
    TextView tvInputtime;
    @BindView(R.id.tv_bm)
    TextView tvBm;
    @BindView(R.id.et_cbs)
    TextView etCbs;
    @BindView(R.id.et_gzdd)
    TextView etGzdd;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_ms)
    TextView tvMs;
    private String id;

    @Override
    public int getContentViewID() {
        return R.layout.activity_gzqk_pushck;
    }

    @Override
    protected void initView() {

        setBack();
        setTitle("工作情况查看");

        id = getIntent().getStringExtra("id");
        requestData();
    }

    private void requestData() {

        String json = "{ \"Rows\": [ { \"id\": \"" + id + "\"} ],\"Total\": 1}";

        String url = "http://192.168.2.106:9091/";
        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.GZQKID, RequestMethod.POST);
        request.setDefineRequestBodyForJson(json);
        NohttpUtils.getInstance().add(this, 0, request, callback, true, true, "加载中...");
    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            System.out.println("返回数据" + response.get());

            try {
                GzqkInfo info = GsonUtils.GsonToBean(response.get(), GzqkInfo.class);

                if (info != null) {
                    if (!info.getTotal().equals("0")) {
                        Gzqk gzqk = info.getRows().get(0);
                        showView(gzqk);
                    } else {
                    }
                } else {
                    showToast("数据出错");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showToast("数据出错");
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };

    public void showView(Gzqk gzqk) {
        tvLrr.setText(gzqk.getLrrname().isEmpty() ? gzqk.getLrr() : gzqk.getLrrname());
        tvInputtime.setText(gzqk.getAddtime());
        tvBm.setText(gzqk.getBm());
        etGzdd.setText(gzqk.getGgplace());
        tvStarttime.setText(gzqk.getGgtime_s());
        tvMs.setText(gzqk.getGgcontent());
        etCbs.setText(gzqk.getCbs());
    }
}
