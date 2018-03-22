package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XsHistoryExpandableListAdapter;
import com.rehome.huizhouxdj.bean.XsHistoryListBean;
import com.rehome.huizhouxdj.bean.XsJhListBean;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
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

/**
 * 历史抄表
 */
public class XsHistoryActivity extends BaseActivity {

    @BindView(R.id.elv)
    ExpandableListView elv;

    private List<XsHistoryListBean.DataBeanX> datas;


    private XsHistoryExpandableListAdapter adapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xs_history;
    }

    @Override
    protected void initView() {

        setTitle("历史抄表");
        setBack();

        datas = new ArrayList<>();
        requestDatas();

    }

    private void requestDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.
        POST);

        requset.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {
                    XsHistoryListBean xsJhListBean = GsonUtils.GsonToBean(response.get(), XsHistoryListBean.class);
                    if (xsJhListBean != null) {
                        if (xsJhListBean.getState() == 1) {
                            datas.clear();
                            datas.addAll(xsJhListBean.getData());
                            setAdapter();
                        } else {
                            showToast(xsJhListBean.getMsg());
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

        if (adapter == null) {
            adapter = new XsHistoryExpandableListAdapter(context, datas);
            elv.setAdapter(adapter);
            elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                    XsHistoryListBean.DataBeanX.DataBean jh = datas.get(groupPosition).getData().get(childPosition);


                    XsJhListBean.DataBean xsjh = new XsJhListBean.DataBean();


                    xsjh.setZxid(jh.getZxid());
                    xsjh.setJhmc(jh.getJhmc());
                    xsjh.setJhlx(jh.getJhlx());
                    xsjh.setScsj(jh.getScsj());


                    Intent intent = new Intent(XsHistoryActivity.this, XscbqyActivity.class);
                    intent.putExtra(Contans.KEY_XSCBJH, xsjh);
                    intent.putExtra("QX", datas.get(groupPosition).getXgqx());
                    intent.putExtra(Contans.KEY_XS_HISTORY, true);
                    startActivity(intent);

                    return true;
                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private String createJson() {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("XSCB_BBGL_GET");
        info.setZc((String) SPUtils.get(context, Contans.BZBH, ""));
        info.setRqts("4");
        info.setZymc(Contans.YXCB_ZY_ID);
        info.setYhid((String) SPUtils.get(context, Contans.USERNAME, ""));//抄表人
        String json = GsonUtils.GsonString(info);

        return json;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestDatas();
    }
}
