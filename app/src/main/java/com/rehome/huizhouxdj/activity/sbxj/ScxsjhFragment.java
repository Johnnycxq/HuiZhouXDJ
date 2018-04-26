package com.rehome.huizhouxdj.activity.sbxj;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.DBModel.Xjjh;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.ScxsAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.ScxjjhBean;
import com.rehome.huizhouxdj.bean.StatusInfo2;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.HttpResponseListener;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备巡点检管理-点检点上传
 */
public class ScxsjhFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sc)
    Button btn_sc;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private XjMainActivity mActivity;
    private View headView;
    private View head;
    private CheckBox xj_cb;
    private ScxsAdapter adapter;
    private WaitDialog dialog;
    private RequestQueue queue;

    public ScxsjhFragment() {

    }

    public static ScxsjhFragment newInstance() {
        ScxsjhFragment fragment = new ScxsjhFragment();
        return fragment;
    }


    @Override
    protected void initView() {

        lv.setEmptyView(tvNodata);
        dialog = new WaitDialog(context, "上传中...");
        dialog.setCancelable(false);
        mActivity = (XjMainActivity) getActivity();
        headView = View.inflate(context, R.layout.scxscb_item, null);
        head = headView.findViewById(R.id.head);
        xj_cb = (CheckBox) headView.findViewById(R.id.xj_cb);
        xj_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (xj_cb.isChecked()) {
                    for (int i = 0; i < xsjhxzdatalist.size(); i++) {
                        xsjhxzdatalist.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < xsjhxzdatalist.size(); i++) {
                        xsjhxzdatalist.get(i).setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }


    @Override
    public int getContentViewId() {
        return R.layout.fragment_xssc;
    }

    public void initData() {
        queue = NoHttp.newRequestQueue(1);
        getDataInSQL();
        setListData();
    }


    private List<XSJJHXZDataBean> xsjjhxzDataBeanList = new ArrayList<>();

    private String zxid = "";

    private List<XSJJHXZDataBean> xsjhxzdatalist = new ArrayList<>();

    private Map<String, List<XSJJHDataBean>> xsJjhDataBeanMap = new HashMap<>();

    /**
     * 获取数据库的数据
     */
    public void getDataInSQL() {


        xsjjhxzDataBeanList.clear();
        xsjjhxzDataBeanList.addAll(DataSupport.findAll(XSJJHXZDataBean.class));

        xsjhxzdatalist.clear();
        xsJjhDataBeanMap.clear();

        for (int i = 0; i < xsjjhxzDataBeanList.size(); i++) {
            if (!xsjjhxzDataBeanList.get(i).getZxid().equals(zxid)) {
                List<XSJJHDataBean> qydDataBeen = DataSupport.where("zxid = ?", xsjjhxzDataBeanList.get(i).getZxid()).find(XSJJHDataBean.class);
                zxid = xsjjhxzDataBeanList.get(i).getZxid();

                int count = 0;
                for (int j = 0; j < qydDataBeen.size(); j++) {
                    if (qydDataBeen.get(j).isChecked()) {
                        count++;
                    }
                }

                XSJJHXZDataBean xsjjhxzDataBean = new XSJJHXZDataBean();
                xsjjhxzDataBean.setQymc(xsjjhxzDataBeanList.get(i).getJhmc());
                xsjjhxzDataBean.setZxid(xsjjhxzDataBeanList.get(i).getZxid());
                xsjjhxzDataBean.setSczt(xsjjhxzDataBeanList.get(i).getSczt());
                xsjjhxzDataBean.setQybh(xsjjhxzDataBeanList.get(i).getQybh());
                xsjjhxzDataBean.setCountPercent(count + "/" + qydDataBeen.size());
                xsjhxzdatalist.add(xsjjhxzDataBean);
                xsJjhDataBeanMap.put(xsjjhxzDataBeanList.get(i).getZxid(), qydDataBeen);

            }
        }
    }

    private void setListData() {

        adapter = new ScxsAdapter(context, xsjhxzdatalist, new ScxsAdapter.CallBack() {
            @Override
            public void Click(View view) {
                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                xsjhxzdatalist.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < xsjhxzdatalist.size(); a++) {
                    if (xsjhxzdatalist.get(a).isChecked()) {
                        count++;
                    }
                }
                xj_cb.setChecked(count == xsjhxzdatalist.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                xsjhxzdatalist.get(i - 1).setChecked(xsjhxzdatalist.get(i - 1).isChecked() ? false : true);
                int count = 0;
                for (int a = 0; a < xsjhxzdatalist.size(); a++) {
                    if (xsjhxzdatalist.get(a).isChecked()) {
                        count++;
                    }
                }
                xj_cb.setChecked(count == xsjhxzdatalist.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });


    }

    @OnClick({R.id.btn_sc, R.id.btn_hf, R.id.btn_del})

    public void click(View view) {

        switch (view.getId()) {

            case R.id.btn_sc:

                uploadData();//上传勾选中的数据

                break;

            case R.id.btn_hf:

                break;

            case R.id.btn_del:

                deleteData();//删除勾选中的数据

                break;
        }
    }

    private int noCheck = 0;

    private void uploadData() {
        noCheck = 0;//没有检查的数量
//        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");


        ScxjjhBean scxjjhbean = new ScxjjhBean();

        scxjjhbean.setAction("XSCB_ZXJHD_SET");

        List<ScxjjhBean.data> datalist = new ArrayList<>();

        for (int i = 0; i < xsjhxzdatalist.size(); i++) {


            if (xsjhxzdatalist.get(i).isChecked()) {

                List<XSJJHDataBean> xsjjhdatabeanList = xsJjhDataBeanMap.get(xsjhxzdatalist.get(i).getZxid());

                scxjjhbean.setZxid(xsjhxzdatalist.get(i).getZxid());

                scxjjhbean.setQybh(xsjhxzdatalist.get(i).getQybh());

                for (int j = 0; j < xsjjhdatabeanList.size(); j++) {

                    ScxjjhBean.data datas = new ScxjjhBean.data();

                    datas.setScid(xsjjhdatabeanList.get(j).getScid());

                    datas.setDbh(xsjjhdatabeanList.get(j).getDbh());

                    if (xsjjhdatabeanList.get(j).getCJJG() == null) {

                        datas.setCbsz("");

                    } else {
                        datas.setCbsz(xsjjhdatabeanList.get(j).getCJJG());
                    }

                    if (xsjjhdatabeanList.get(j).getDATE() == null) {

                        datas.setDjsj("");
                    } else {
                        datas.setDjsj(xsjjhdatabeanList.get(j).getDATE());
                    }

                    if (xsjjhdatabeanList.get(j).getCJJG() == null) {
                        datas.setSBZT("");
                    } else if (xsjjhdatabeanList.get(j).getCJJG().equals("已停用")) {
                        datas.setSBZT("3");
                    } else if (xsjjhdatabeanList.get(j).getCJJG().equals("大小修")) {
                        datas.setSBZT("4");
                    } else {
                        datas.setSBZT("1");
                    }

                    datas.setZcmc((String) SPUtils.get(context, Contans.BZBH, ""));
                    datas.setCbr((String) SPUtils.get(context, Contans.USERNAME, ""));
                    datas.setFxnr("");
                    datas.setSmfx("");
                    datalist.add(datas);
                }


                scxjjhbean.setData(datalist);


                final String json = GsonUtils.GsonString(scxjjhbean);

                Log.e("json", json);

                if (noCheck > 0) {     //如果未检查的数量大于0 则提示有未检查的项目

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setTitle("你还有项目未检查，是否上传？");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sCData(json);
                        }
                    });
                    builder.show();

                } else {
                    sCData(json);
                }
            } else {
                noCheck++;//代表有没有检查的数量
            }

        }

    }

    private void sCData(String json) {
        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);
        request.setDefineRequestBodyForJson(json);
        queue.add(1, request, new HttpResponseListener<>(getActivity(), request, callback, false, false, ""));
    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            Log.e("11", response.get());

            StatusInfo2 info = GsonUtils.GsonToBean(response.get(), StatusInfo2.class);

            try {
                switch (what) {

                    case 1:

                        if (info.getState() == 1) { //成功

                            showToast("上传数据成功");

                            //这里处理那个删除item，更新UI
                            for (int i = 0; i < xsjhxzdatalist.size(); i++) {

                                if (xsjhxzdatalist.get(i).isChecked()) {

                                    DataSupport.deleteAll(XSJJHXZDataBean.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());

                                    DataSupport.deleteAll(XSJJHDataBean.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());

                                    DataSupport.deleteAll(Xjjh.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());

                                    xsjhxzdatalist.remove(xsjhxzdatalist.get(i));
                                }

                            }

                            adapter.notifyDataSetChanged();

                        } else {

                            showToast("上传数据失败");

                        }

                        break;

                }


            } catch (Exception e) {

                showToast(UiUtlis.getString(context, R.string.data_error));

                dialog.dismiss();

            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    };


    private void deleteData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setTitle("你确定要删除？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (int i = 0; i < xsjhxzdatalist.size(); i++) {

                    if (xsjhxzdatalist.get(i).isChecked()) {

                        DataSupport.deleteAll(XSJJHXZDataBean.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());

                        DataSupport.deleteAll(XSJJHDataBean.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());

                        DataSupport.deleteAll(Xjjh.class, "zxid = ?", xsjhxzdatalist.get(i).getZxid());
                    }
                }

                //刷新界面
                getDataInSQL();

                if (adapter != null) {

                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.show();

    }

}