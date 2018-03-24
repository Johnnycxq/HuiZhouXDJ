package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XsjhqyAdapter;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.bean.XsJhListBean;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
import com.rehome.huizhouxdj.bean.XsjhQyBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 巡视工作区域列表
 */
public class XscbqyActivity extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;
    private XsJhListBean.DataBean xscbjh;

    private List<XsjhQyBean.DataBeanX> qys;

    private XsjhqyAdapter adapter;

    private boolean isHistory;

    private String QX;

    private boolean isSHOW;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xscbqy;
    }

    @Override
    protected void initView() {

        xscbjh = (XsJhListBean.DataBean) getIntent().getExtras().get(Contans.KEY_XSCBJH);
        isHistory = getIntent().getExtras().getBoolean(Contans.KEY_XS_HISTORY, false);

        if (QX == null){

        }else {

            QX = getIntent().getExtras().getString("QX");//如果权限为1 可以修改历史记录

            isSHOW = "0".equals(QX);


        }




        qys = new ArrayList<>();
        requestDatas();
        setTitle(xscbjh.getJhmc());
        setBack();
        if (!isHistory) {
            initNFC();
        }
    }

    private void requestDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {
                    String json = response.get();

                    XsjhQyBean xsjhQyBean = GsonUtils.GsonToBean(response.get(), XsjhQyBean.class);

                    if (xsjhQyBean != null) {

                        if (xsjhQyBean.getState() == 1) {
                            qys.clear();
                            qys.addAll(xsjhQyBean.getData());
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

        if (adapter == null) {
            adapter = new XsjhqyAdapter(context, qys);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    /**
                     * 如果是历史，直接跳转
                     */
                    if (isHistory) {

                        if (xscbjh.getJhlx().equals(XscbqyWorkActivity.JHLX_XS)) {
                            Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkXsActivity.class);
                            intent.putExtra(Contans.KEY_QY, qys.get(position));
                            intent.putExtra(Contans.KEY_EWM_OR_NFC, "扫描条码");
                            intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
                            intent.putExtra(Contans.KEY_IS_HIS, isSHOW);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkActivity.class);
                            intent.putExtra(Contans.KEY_QY, qys.get(position));
                            intent.putExtra(Contans.KEY_EWM_OR_NFC, "扫描条码");
                            intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
                            intent.putExtra(Contans.KEY_IS_HIS, isSHOW);
                            startActivity(intent);
                        }

                    } else {

                        /**
                         * 如果二维码和NFC标签都为空，直接跳转
                         */
                        if (TextUtils.isEmpty(qys.get(position).getNfcbm()) && TextUtils.isEmpty(qys.get(position).getTxm())) {

                            if (xscbjh.getJhlx().equals(XscbqyWorkActivity.JHLX_XS)) {
                                Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkXsActivity.class);
                                intent.putExtra(Contans.KEY_QY, qys.get(position));
                                intent.putExtra(Contans.KEY_EWM_OR_NFC, " ");
                                intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
                                intent.putExtra(Contans.KEY_IS_HIS, false);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkActivity.class);
                                intent.putExtra(Contans.KEY_QY, qys.get(position));
                                intent.putExtra(Contans.KEY_EWM_OR_NFC, " ");
                                intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
                                intent.putExtra(Contans.KEY_IS_HIS, false);
                                startActivity(intent);
                            }

                        } else {
                            Intent intent = new Intent(XscbqyActivity.this, MipcaActivityCapture.class);
                            startActivityForResult(intent, 1);
                        }
                    }
                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String ewm = bundle.getString("result");
                    for (XsjhQyBean.DataBeanX qy : qys) {
                        if (ewm.equals(qy.getTxm())) {

                            if (xscbjh.getJhlx().equals(XscbqyWorkActivity.JHLX_XS)) {
                                Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkXsActivity.class);
                                intent.putExtra(Contans.KEY_QY, qy);
                                intent.putExtra(Contans.KEY_EWM_OR_NFC, "扫描条码");
                                intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
//                                intent.putExtra(Contans.KEY_IS_HIS, qy.getSczt().equals("1") ? true : false);
                                intent.putExtra(Contans.KEY_IS_HIS, false);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkActivity.class);
                                intent.putExtra(Contans.KEY_QY, qy);
                                intent.putExtra(Contans.KEY_EWM_OR_NFC, "扫描条码");
                                intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
//                                intent.putExtra(Contans.KEY_IS_HIS, qy.getSczt().equals("1") ? true : false);
                                intent.putExtra(Contans.KEY_IS_HIS, false);
                                startActivity(intent);
                            }
                            return;
                        }
                    }
                }
                break;
        }
    }

    private String createJson() {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("XSCB_ZXJHD_GET");
        info.setZxid(xscbjh.getZxid());
        info.setSis(isHistory ? "0" : "1");
        info.setJhid(xscbjh.getJhid());
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @Override
    public void handleNfc(String result) {
        super.handleNfc(result);

        for (XsjhQyBean.DataBeanX qy : qys) {
            if (result.equals(qy.getNfcbm())) {
                if (xscbjh.getJhlx().equals(XscbqyWorkActivity.JHLX_XS)) {
                    Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkXsActivity.class);
                    intent.putExtra(Contans.KEY_QY, qy);
                    intent.putExtra(Contans.KEY_EWM_OR_NFC, "NFC标签");
                    intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
//                    intent.putExtra(Contans.KEY_IS_HIS, qy.getSczt().equals("1") ? true : false);
                    intent.putExtra(Contans.KEY_IS_HIS, false);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(XscbqyActivity.this, XscbqyWorkActivity.class);
                    intent.putExtra(Contans.KEY_QY, qy);
                    intent.putExtra(Contans.KEY_EWM_OR_NFC, "NFC标签");
                    intent.putExtra(Contans.KEY_JHLX, xscbjh.getJhlx());
//                    intent.putExtra(Contans.KEY_IS_HIS, qy.getSczt().equals("1") ? true : false);
                    intent.putExtra(Contans.KEY_IS_HIS, false);
                    startActivity(intent);
                }
                return;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestDatas();
    }
}
