package com.rehome.huizhouxdj.activity.xj;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.bean.XJRequestBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.AutoRadioGroup;
import com.rehome.huizhouxdj.weight.NoscrollViewPager;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备巡点检管理--现场点检
 */
public class xjActivity extends BaseActivity {

    @BindView(R.id.vp_centent)
    NoscrollViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    AutoRadioGroup rg;

    private List<Fragment> list;
    private MyFragmentAdapter adapter;

    private XjxzFragment xzjh;
    private XjscFragment djdsc;


    @Override
    public int getContentViewID() {
        return R.layout.activity_xj;
    }

    protected void initView() {

    }

    public void initData() {
        setTitle("巡检下载计划");
        setBack();
        requestdjData();
        xzjh = XjxzFragment.newInstance();
        djdsc = XjscFragment.newInstance();
        list = new ArrayList<>();
        list.add(xzjh);
        list.add(djdsc);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(adapter);


        vp.setOffscreenPageLimit(2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.rb1:
                        rb1.setTextColor(Color.WHITE);
                        rb2.setTextColor(Color.GRAY);
                        rb3.setTextColor(Color.GRAY);
                        vp.setCurrentItem(0, false);
                        break;
                    case R.id.rb2:
                        rb1.setTextColor(Color.GRAY);
                        rb2.setTextColor(Color.WHITE);
                        rb3.setTextColor(Color.GRAY);
                        vp.setCurrentItem(1, false);
                        break;

                }
            }
        });
    }


    private void requestdjData() {

        Request<String> requestdj = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        requestdj.setDefineRequestBodyForJson(createZyJson());

        NohttpUtils.getInstance().add(this, 0, requestdj, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    Log.e("123", response.get());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private String createZyJson() {

        XJRequestBean info = new XJRequestBean();
        info.setAction("XSCB_ZXJL_GET");
//        info.setZymc(Contans.YXCB_ZY_ID);
        info.setZymc("1");
        info.setZc((String) SPUtils.get(context, Contans.BZBH, ""));
        String json = GsonUtils.GsonString(info);
        return json;
    }


}