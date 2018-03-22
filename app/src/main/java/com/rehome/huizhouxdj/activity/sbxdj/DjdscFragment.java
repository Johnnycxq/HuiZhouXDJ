package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.ScjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.ScdjjhBean;
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
public class DjdscFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sc)
    Button btn_sc;

    @BindView(R.id.tv_nodata)
    TextView tvNodata;

    private SxcdjActivity mActivity;
    private View headView;
    private View head;
    private CheckBox cb;
    private ScjhAdapter adapter;
    private WaitDialog dialog;
    private RequestQueue queue;
    private List<XDJJHXZDataBean> removelist;

    public DjdscFragment() {

    }

    public static DjdscFragment newInstance() {
        DjdscFragment fragment = new DjdscFragment();
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            visible();
        }
    }

    //视图显示时才查询数据库数据
    private void visible() {
        getDataInSQL();
        if (adapter == null) {
            setListData();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView() {

        lv.setEmptyView(tvNodata);
        dialog = new WaitDialog(context, "上传中...");
        dialog.setCancelable(false);
        mActivity = (SxcdjActivity) getActivity();
        headView = View.inflate(context, R.layout.scjh_item, null);
        head = headView.findViewById(R.id.head);
        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < xdjjhxzDataList.size(); i++) {
                        xdjjhxzDataList.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < xdjjhxzDataList.size(); i++) {
                        xdjjhxzDataList.get(i).setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_djdsc;
    }

    public void initData() {
        removelist = new ArrayList<>();
        queue = NoHttp.newRequestQueue(1);


    }

    //所有的XDJJHXZDataBean数据
    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();
    //当前查询的gwid
    private String gwid = "";

    //点检计划列表数据源
    private List<XDJJHXZDataBean> xdjjhxzDataList = new ArrayList<>();
    //点检计划map
    private Map<String, List<QYDDATABean>> qydDataBeanMap = new HashMap<>();


    /**
     * 获取数据库的数据
     */
    public void getDataInSQL() {


        xdjjhxzDataBeanList.clear();
        xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
        xdjjhxzDataList.clear();

        qydDataBeanMap.clear();

        for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {

            if (!xdjjhxzDataBeanList.get(i).getGWID().equals(gwid)) {

                List<QYDDATABean> qydDataBeen = DataSupport.where("GWID = ?", xdjjhxzDataBeanList.get(i).getGWID()).find(QYDDATABean.class);

                gwid = xdjjhxzDataBeanList.get(i).getGWID();

                //当前GWID下已检数量
                int count = 0;

                for (int j = 0; j < qydDataBeen.size(); j++) {

                    if (qydDataBeen.get(j).isChecked()) {

                        count++;

                    }

                }


                //点检计划列表bean
                XDJJHXZDataBean xdjjhxzDataBean = new XDJJHXZDataBean();
                xdjjhxzDataBean.setGWMC(xdjjhxzDataBeanList.get(i).getGWMC());
                xdjjhxzDataBean.setGWID(xdjjhxzDataBeanList.get(i).getGWID());
                xdjjhxzDataBean.setCountPercent(count + "/" + qydDataBeen.size());
                xdjjhxzDataBean.setQYBH(xdjjhxzDataBeanList.get(i).getQYBH());

                xdjjhxzDataList.add(xdjjhxzDataBean);

                //点检计划列表对应的map
                qydDataBeanMap.put(xdjjhxzDataBeanList.get(i).getGWID(), qydDataBeen);

            }
        }
    }

    private void setListData() {

        adapter = new ScjhAdapter(context, xdjjhxzDataList, new ScjhAdapter.CallBack() {
            @Override
            public void Click(View view) {
                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                xdjjhxzDataList.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < xdjjhxzDataList.size(); a++) {
                    if (xdjjhxzDataList.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == xdjjhxzDataList.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });

        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                xdjjhxzDataList.get(i - 1).setChecked(xdjjhxzDataList.get(i - 1).isChecked() ? false : true);
                int count = 0;
                for (int a = 0; a < xdjjhxzDataList.size(); a++) {
                    if (xdjjhxzDataList.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == xdjjhxzDataList.size() ? true : false);
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

    private void uploadData() {   //上传点检计划

        removelist.clear();

        noCheck = 0;//没有检查的数量

        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");

        ScdjjhBean scdjjhbean = new ScdjjhBean();

        scdjjhbean.setAction("DJ_GWSC_SET");

        scdjjhbean.setYHID(USERNAME);

        //第二层数据List
        List<ScdjjhBean.DJ_DATA> djDataList = new ArrayList<>();

        //第三层数据List
        List<ScdjjhBean.DJ_DATA.QYDJ_DATA> qydjDataList = new ArrayList<>();

        for (int i = 0; i < xdjjhxzDataList.size(); i++) {

            ScdjjhBean.DJ_DATA dhdata = new ScdjjhBean.DJ_DATA();

            if (xdjjhxzDataList.get(i).isChecked()) {


                showToast(xdjjhxzDataList.get(i).getGWID());

//                //获取map中选中的QYDDATABean
//
//                List<QYDDATABean> qyddataBeanList = qydDataBeanMap.get(xdjjhxzDataList.get(i).getGWID());
//
//                scdjjhbean.setGWID(xdjjhxzDataList.get(i).getGWID());
//
//                dhdata.setQYBH(xdjjhxzDataList.get(i).getQYBH());
//
//                dhdata.setQYDJ_ST(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//                for (int j = 0; j < qyddataBeanList.size(); j++) {
//
//                    ScdjjhBean.DJ_DATA.QYDJ_DATA qydj_data = new ScdjjhBean.DJ_DATA.QYDJ_DATA();
//
//                    qydj_data.setSCID(qyddataBeanList.get(j).getSCID());
//
//                    if (qyddataBeanList.get(j).getCJJG() == null) {
//
//                        qydj_data.setDJSZ("");
//
//                    } else {
//
//                        qydj_data.setDJSZ(qyddataBeanList.get(j).getCJJG());
//
//                    }
//
//                    if (qyddataBeanList.get(j).getDATE() == null) {
//
//                        qydj_data.setDJSJ("");
//
//                    } else {
//
//                        qydj_data.setDJSJ(qyddataBeanList.get(j).getDATE());
//
//                    }
//
//
//                    qydj_data.setFXNR("");
//
//                    qydj_data.setSMFS("");
//
//                    qydjDataList.add(qydj_data);
//                }
//
//                dhdata.setQYDJ_DATA(qydjDataList);
//
//                djDataList.add(dhdata);
//
//                removelist.add(xdjjhxzDataList.get(i));

            } else {
                noCheck++;//代表有没有检查的数量
            }

        }

        scdjjhbean.setDJ_DATA(djDataList);

        //上传
//        final String json = GsonUtils.GsonString(scdjjhbean);
//
//        if (noCheck > 0) {  //如果未检查的数量大于0 则提示有未检查的项目
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("提示");
//            builder.setTitle("你还有项目未检查，是否上传？");
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    sCData(json);
//                }
//            });
//            builder.show();
//
//        } else {
//            sCData(json);
//        }

    }

    private void sCData(String json) {
        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.DJJHSC, RequestMethod.POST);
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

                            removeData();

//                            //这里处理那个删除item，更新UI
//                            for (int i = 0; i < xdjjhxzDataList.size(); i++) {
//
//                                if (xdjjhxzDataList.get(i).isChecked()) {
//
//                                    xdjjhxzDataList.remove(xdjjhxzDataList.get(i));
//
//                                    DataSupport.deleteAll(XDJJHXZDataBean.class, "GWID = ?", xdjjhxzDataList.get(i).getGWID());
//
//                                    DataSupport.deleteAll(Djjh.class, "GWID = ?", xdjjhxzDataList.get(i).getGWID());
//
//                                }
//                            }

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


    //上传成功，移除数据
    private void removeData() {

        for (XDJJHXZDataBean xdjjhxzdatabean : removelist) {

            DataSupport.deleteAll(XDJJHXZDataBean.class, "GWID = ?", xdjjhxzdatabean.getGWID());

            DataSupport.deleteAll(Djjh.class, "GWID = ?", xdjjhxzdatabean.getGWID());
        }

    }


    //    //删除数据
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

                for (int i = 0; i < xdjjhxzDataList.size(); i++) {

                    if (xdjjhxzDataList.get(i).isChecked()) {

                        DataSupport.deleteAll(XDJJHXZDataBean.class, "GWID = ?", xdjjhxzDataList.get(i).getGWID());

                        DataSupport.deleteAll(QYDDATABean.class, "GWID = ?", xdjjhxzDataList.get(i).getGWID());

                        DataSupport.deleteAll(Djjh.class, "GWID = ?", xdjjhxzDataList.get(i).getGWID());
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