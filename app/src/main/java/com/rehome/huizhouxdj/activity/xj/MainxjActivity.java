package com.rehome.huizhouxdj.activity.xj;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.MainActivity;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.BasicDataBean;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.weight.ListDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ruihong on 2017/11/24.
 */

public class MainxjActivity extends BaseActivity {

    @BindView(R.id.gv)
    GridView gv;
    private String[] str = {"任务", "工作"};
    private int[] imageId = {R.mipmap.icon3, R.mipmap.icon6};
    private int[] colors = {R.drawable.radius_b1, R.drawable.radius_b2};
    private GridViewAdapter adapter;

    private int[] item = {2};


    private List<String> dialogDatas;
    private List<BasicDataBean.DataBean> zys;

    @Override
    public int getContentViewID() {
        return R.layout.activity_mainxj;
    }

    protected void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void initData() {
        zys = new ArrayList<>();
        dialogDatas = new ArrayList<>();
        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));
        setTitle("设备巡检");
        requestZyDatas();
        if (getIntent().getExtras() != null) {
            mToolbar.setNavigationIcon(R.mipmap.back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainxjActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            setBack();

        }

        adapter = new GridViewAdapter(this, getGridViewData(), new ArrayList<Integer>(), true);
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:


                        if (dialogDatas.size() == 0) {
                            intent = new Intent(MainxjActivity.this, xjActivity.class);
                            startActivity(intent);
                        } else {
                            ListDialog dialog2 = new ListDialog(context, dialogDatas, new ListDialog.ListDialogListener() {
                                @Override
                                public void selectText(String str, int position) {

                                    Contans.YXCB_ZY_ID = zys.get(position).getId();
                                    Contans.YXCB_ZY_NAME = str;
                                    Intent intent = new Intent(MainxjActivity.this, xjActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dialog2.show();
                        }
                        break;

//                    case 1:
//                        intent = new Intent(MainxjActivity.this, SdjgzActivity.class);
//                        startActivity(intent);
//                        break;

                }
            }
        });
    }

    private List<GridViewBean> getGridViewData() {

        List<GridViewBean> datas = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            GridViewBean bean = new GridViewBean();
            bean.setTitle(str[i]);
            bean.setBackgroup(colors[i]);
            bean.setImageid(imageId[i]);
            bean.setShow(false);
            datas.add(bean);
        }
        return datas;
    }

    private void requestZyDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XS_JCSJ, RequestMethod.
                POST);

        requset.setDefineRequestBodyForJson(createZyJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {
                    BasicDataBean bean = GsonUtils.GsonToBean(response.get(), BasicDataBean.class);
                    if (bean != null) {
                        if (bean.getState() == 1) {
                            if (bean.getData().size() != 0) {
                                zys.clear();
                                zys.addAll(bean.getData());
                                initDialogDatas();
                            }
                        }
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

    private void initDialogDatas() {

        for (BasicDataBean.DataBean bean : zys) {
            dialogDatas.add(bean.getName());
        }
    }


    private String createZyJson() {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("GGJK_JCSJ_GET");
        info.setModuletype("XJGL_ZYMC");
        String json = GsonUtils.GsonString(info);
        return json;
    }

}