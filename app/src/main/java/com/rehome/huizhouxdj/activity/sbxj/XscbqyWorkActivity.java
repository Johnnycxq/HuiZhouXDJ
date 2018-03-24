package com.rehome.huizhouxdj.activity.sbxj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XsjhqyWorkCbAdapter;
import com.rehome.huizhouxdj.adapter.XsjhqyWorkDjAdapter;
import com.rehome.huizhouxdj.bean.XsResultBaseBean;
import com.rehome.huizhouxdj.bean.XsSaveDataInfo;
import com.rehome.huizhouxdj.bean.XsjhQyBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.AutoListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 抄表/点检
 */
public class XscbqyWorkActivity extends BaseActivity {

    public static final String JHLX_XS = "1";//巡视
    public static final String JHLX_CB = "2";//抄表
    public static final String JHLX_DJ = "3";//定检

    @BindView(R.id.lv)
    AutoListView lv;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    private XsjhQyBean.DataBeanX qy;

    private String jhlx;

    private List<XsjhQyBean.DataBeanX.DataBean> qyds;

    private XsjhqyWorkDjAdapter adapterDj;

    private XsjhqyWorkCbAdapter adapterCb;

    private boolean isHis;

    private String smfx;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xscbqy_work;
    }

    @Override
    protected void initView() {

        jhlx = (String) getIntent().getExtras().get(Contans.KEY_JHLX);

        qy = (XsjhQyBean.DataBeanX) getIntent().getExtras().get(Contans.KEY_QY);
        smfx = getIntent().getExtras().getString(Contans.KEY_EWM_OR_NFC);
        isHis = getIntent().getExtras().getBoolean(Contans.KEY_IS_HIS, false);

        if (isHis) {
            btnCommit.setVisibility(View.GONE);
        }

        btnCommit.setVisibility(isHis ? View.GONE : View.VISIBLE);

        qyds = new ArrayList<>();

        if (qy != null) {
            setTitle(qy.getQymc());
            qyds.addAll(qy.getData());

            switch (jhlx) {
                case JHLX_CB:
                    setAdapterCb();
                    break;
                case JHLX_DJ:
                    setAdapterDj();
                    break;
            }
        }
        setBack();
    }

    private void setAdapterDj() {

        if (adapterDj == null) {
            adapterDj = new XsjhqyWorkDjAdapter(context, qyds, isHis);
            View headView = View.inflate(context, R.layout.qy_work_dj_item, null);
            headView.findViewById(R.id.tv_input).setVisibility(View.VISIBLE);
            headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapterDj);
        } else {
            adapterDj.notifyDataSetChanged();
        }
    }

    private void setAdapterCb() {

        if (adapterCb == null) {
            adapterCb = new XsjhqyWorkCbAdapter(context, qyds, isHis);
            View headView = View.inflate(context, R.layout.qy_work_cb_item, null);
            headView.findViewById(R.id.tv_input).setVisibility(View.VISIBLE);
            headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapterCb);
        } else {
            adapterCb.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("你确定要提交吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                saveData();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    private void saveData() {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {
                    XsResultBaseBean xsResultBaseBean = GsonUtils.GsonToBean(response.get(), XsResultBaseBean.class);
                    if (xsResultBaseBean != null) {
                        showToast(xsResultBaseBean.getMsg());
                        if (xsResultBaseBean.getState() == 1) {
                            finish();
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

    private String createJson() {

        XsSaveDataInfo info = new XsSaveDataInfo();
        info.setAction("XSCB_ZXJHD_SET");
        info.setZxid(qy.getZxid());
        info.setQybh(qy.getQybh());

        List<XsSaveDataInfo.DataBean> dataBeanList = new ArrayList<>();
        for (XsjhQyBean.DataBeanX.DataBean dataBean : qyds) {

            XsSaveDataInfo.DataBean bean = new XsSaveDataInfo.DataBean();
            bean.setDbh(dataBean.getDbh());//点编号
            bean.setCbsz(dataBean.getCbsz());//抄表数值
            bean.setDjsj(dataBean.getDjsj());//点检时间
            bean.setScid(dataBean.getScid());


            if (!TextUtils.isEmpty(dataBean.getCbsz())) {
                if (TextUtils.isEmpty(dataBean.getDjsj())) {
                    bean.setDjsj(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                }
            } else {
                bean.setDjsj(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                if (!TextUtils.isEmpty(dataBean.getSisData())) {
                    bean.setCbsz(dataBean.getSisData());
                }
            }


            bean.setZcmc((String) SPUtils.get(context, Contans.BZBH, ""));//班组

            bean.setCbr((String) SPUtils.get(context, Contans.USERNAME, ""));//抄表人

            bean.setFxnr("");//分析内容
            bean.setSmfx(smfx);//扫描方式
            dataBeanList.add(bean);
        }

        info.setData(dataBeanList);

        String json = GsonUtils.GsonString(info);

//        Log.e("#########",json);

        return json;
    }
}
