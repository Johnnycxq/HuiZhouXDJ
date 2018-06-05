package com.rehome.huizhouxdj.activity.qfgd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.PMChangeRequestBean;
import com.rehome.huizhouxdj.bean.PMRequestBean;
import com.rehome.huizhouxdj.bean.PminfoBean2;
import com.rehome.huizhouxdj.bean.ResultBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.InputLayout;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/27.
 */

public class QfpmgdchangeActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.il_GZDH)
    InputLayout ilGZDH;
    @BindView(R.id.il_GZZT)
    InputLayout ilGZZT;
    @BindView(R.id.il_ZRBM)
    InputLayout ilZRBM;
    @BindView(R.id.il_GZYXJ)
    InputLayout ilGZYXJ;
    @BindView(R.id.il_SBBH)
    InputLayout ilSBBH;
    @BindView(R.id.il_SBMC)
    InputLayout ilSBMC;
    @BindView(R.id.il_QXMS)
    InputLayout ilQXMS;
    @BindView(R.id.il_GZMS)
    InputLayout ilGZMS;
    @BindView(R.id.il_TJR)
    InputLayout ilTJR;
    @BindView(R.id.il_TJRQ)
    InputLayout ilTJRQ;
    @BindView(R.id.il_BGR)
    InputLayout ilBGR;
    @BindView(R.id.il_BGRQ)
    InputLayout ilBGRQ;
    @BindView(R.id.il_APR)
    InputLayout ilAPR;
    @BindView(R.id.il_APRQ)
    InputLayout ilAPRQ;
    @BindView(R.id.il_PZR)
    InputLayout ilPZR;
    @BindView(R.id.il_PZRQ)
    InputLayout ilPZRQ;
    @BindView(R.id.il_XMFZR)
    InputLayout ilXMFZR;
    @BindView(R.id.il_LRFS)
    InputLayout ilLRFS;
    @BindView(R.id.il_gzzt)
    InputLayout ilGzzt;
    private List<PminfoBean2.DataBean> datas;

    private String GZDH, GDZT_NO, GDZT_SO, GDZTMC;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setTitle("你确定要修改状态吗?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (!ilGzzt.getContent().equals("")){
                            TJ();
                        }else {
                            showToast("还没有点击修改工单状态");
                        }


                    }
                });
                builder.show();
                break;
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_qfpmgdchange;
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {
        initToolbar("工单状态修改", "修改状态", this);
        GZDH = getIntent().getExtras().getString("GZDH");
        requestDatas(GZDH);
        ilGZDH.setEnabled(false);
        ilGZZT.setEnabled(false);
        ilZRBM.setEnabled(false);
        ilGZYXJ.setEnabled(false);
        ilSBBH.setEnabled(false);
        ilSBMC.setEnabled(false);
        ilQXMS.setEnabled(false);
        ilGZMS.setEnabled(false);
        ilTJR.setEnabled(false);
        ilTJRQ.setEnabled(false);
        ilBGR.setEnabled(false);
        ilBGRQ.setEnabled(false);
        ilAPR.setEnabled(false);
        ilAPRQ.setEnabled(false);
        ilPZR.setEnabled(false);
        ilPZRQ.setEnabled(false);
        ilXMFZR.setEnabled(false);
        ilLRFS.setEnabled(false);

        ilGzzt.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(QfpmgdchangeActivity.this, GdztActivity.class);
                startActivityForResult(intent1, 1);
            }
        });
    }

    private void requestDatas(String GZDH) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(GZDH));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    PminfoBean2 pminfoBean = GsonUtils.GsonToBean(response.get(), PminfoBean2.class);

                    if (pminfoBean != null) {
                        if (pminfoBean.getState() == 1) {
                            datas.clear();
                            datas.addAll(pminfoBean.getData());


                            ilGZDH.setContent(datas.get(0).getGZDH());
                            ilGZZT.setContent(datas.get(0).getGZZT());
                            ilZRBM.setContent(datas.get(0).getZRBM());
                            ilGZYXJ.setContent(datas.get(0).getGZYXJ());
                            ilSBBH.setContent(datas.get(0).getSBBH());
                            ilSBMC.setContent(datas.get(0).getSBMC());
                            ilQXMS.setContent(datas.get(0).getQXMS());
                            ilGZMS.setContent(datas.get(0).getGZMS());
                            ilTJR.setContent(datas.get(0).getTJR_NAME());
                            ilTJRQ.setContent(datas.get(0).getTJRQ() + datas.get(0).getTJSJ());
                            ilBGR.setContent(datas.get(0).getBGR_NAME());
                            ilBGRQ.setContent(datas.get(0).getBGRQ() + datas.get(0).getBGSJ());
                            ilAPR.setContent(datas.get(0).getAPR_NAME());
                            ilAPRQ.setContent(datas.get(0).getAPRQ() + datas.get(0).getARSJ());
                            ilPZR.setContent(datas.get(0).getPZR_NAME());
                            ilPZRQ.setContent(datas.get(0).getPZRQ() + datas.get(0).getPZSJ());
                            ilXMFZR.setContent(datas.get(0).getXMFZR());
                            ilLRFS.setContent(datas.get(0).getLRFS());

                        } else {
                            showToast(pminfoBean.getMsg());
                            datas.clear();

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

    private void TJ() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson2());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    ResultBean resultBean = GsonUtils.GsonToBean(response.get(), ResultBean.class);

                    if (resultBean != null) {
                        if (resultBean.getState() == 1) {
                            showToast(resultBean.getMsg());
                            finish();
                        } else {
                            showToast("数据错误");
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


    private String createJson(String GZDH) {
        PMRequestBean info = new PMRequestBean();
        info.setAction("Q4GD_PMGD1_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setGZZT("");
        info.setZRBM("");
        info.setGZYXJ("");
        info.setSBBH("");
        info.setGZDH(GZDH);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private String createJson2() {
        PMChangeRequestBean info = new PMChangeRequestBean();
        info.setAction("Q4GD_ZT_SET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setGDID(GZDH);
        info.setGDZT_NO(GDZT_NO);
        info.setGDZT_SO(GDZT_SO);
        info.setZHBZ("");
        info.setXGYY("");
        info.setFSRQ("");
        info.setWCBG("");
        String json = GsonUtils.GsonString(info);
        return json;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    GDZT_NO = bundle.getString("GDZT_NO");
                    GDZT_SO = bundle.getString("GDZT_SO");
                    GDZTMC = bundle.getString("GDZTMC");
                    ilGzzt.setContent(GDZTMC);


                }
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
