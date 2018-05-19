package com.rehome.huizhouxdj.activity.sbxj;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHXZBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.DBModel.Xjjh;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XzxsjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.HttpResponseListener;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static org.litepal.crud.DataSupport.where;

/**
 * Created by ruihong on 2018/4/8.
 */

public class XzxsjhFragment extends BaseFragment {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.FL)
    FrameLayout FL;
    @BindView(R.id.btn_xz)
    Button btnXz;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.LL)
    LinearLayout LL;
    Unbinder unbinder;


    private WaitDialog dialog;
    private XjMainActivity mActivity;
    private View headView;
    private View head;
    private XzxsjhAdapter adapter;
    private CheckBox cb;
    private List<Xjjh> xjjhs;
    private int selectCount = 0;
    private int requestCount = 0;
    private List<String> zxids = new ArrayList<>();
    private List<String> jhmcs = new ArrayList<>();
    private RequestQueue queue;

    public XzxsjhFragment() {

    }

    public static XzxsjhFragment newInstance() {
        XzxsjhFragment fragment = new XzxsjhFragment();
        return fragment;
    }


    @Override
    public int getContentViewId() {
        return R.layout.fragment_xzxsjh;
    }

    @Override
    protected void initView() {
        lv.setEmptyView(tvNodata);
        mActivity = (XjMainActivity) getActivity();
        headView = View.inflate(context, R.layout.xzxsjh_item, null);
        head = headView.findViewById(R.id.head);
        head.setVisibility(View.VISIBLE);
        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < xjjhs.size(); i++) {
                        xjjhs.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < xjjhs.size(); i++) {
                        xjjhs.get(i).setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void initData() {

        queue = NoHttp.newRequestQueue(1);
        dialog = new WaitDialog(getActivity(), "加载中...");
        dialog.setCancelable(false);
        xjjhs = new ArrayList<>();
        //如果数据库中有数据
        if (DataSupport.count("Xjjh") != 0) {
            List<Xjjh> xjjhListall = where("download = ?", "0").find(Xjjh.class);
            xjjhs.clear();
            xjjhs.addAll(xjjhListall);
            setListData();

        } else {

            tvNodata.setText("暂无数据");
            LL.setVisibility(View.GONE);
        }


    }

    @OnClick(R.id.btn_xz)
    public void click() {

        if (xjjhs.size() != 0) {

            zxids.clear();
            jhmcs.clear();
            for (int i = 0; i < xjjhs.size(); i++) {
                if (xjjhs.get(i).isChecked()) {
                    if (!dialog.isShowing()) {
                        dialog.setMessage("下载计划中...");
                        dialog.show();
                    }

                    downData(xjjhs.get(i).getZxid(), xjjhs.get(i).getJhmc());
                    zxids.add(xjjhs.get(i).getZxid());
                }
            }
        } else {
            showToast("没有可下载计划");
        }
    }

    private void downData(String zxid, String jhmc) {

        selectCount++;

        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        request.setDefineRequestBodyForJson(createZyJson(zxid, jhmc));

        queue.add(1, request, new HttpResponseListener<>(getActivity(), request, callback, false, false, ""));

    }

    private XSJJHXZBean xsjjhxzBean;

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            try {
                switch (what) {
                    case 1:
                        ++requestCount;

                        Log.e("dataLog", response.get());

                        xsjjhxzBean = GsonUtils.GsonToBean(response.get(), XSJJHXZBean.class);

                        if (xsjjhxzBean != null && xsjjhxzBean.getState() == 1) {

                            List<XSJJHXZDataBean> xsjjhxzDataBeanList = xsjjhxzBean.getData();

                            ContentValues values = new ContentValues();

                            values.put("download", 1);

                            for (int i = 0; i < xsjjhxzDataBeanList.size(); i++) {
                                DataSupport.updateAll(Xjjh.class, values, "zxid = ?", xsjjhxzDataBeanList.get(i).getZxid());
                            }
                            int dataCount = DataSupport.count(XSJJHXZDataBean.class);
                            int index;//序号
                            if (dataCount == 0) {
                                index = 0;
                            } else {
                                index = dataCount;
                            }

                            for (int i = 0; i < xsjjhxzDataBeanList.size(); i++) {
                                xsjjhxzDataBeanList.get(i).setSN(++index);
                                DataSupport.saveAll(xsjjhxzDataBeanList.get(i).getData());
                            }
                            DataSupport.saveAll(xsjjhxzDataBeanList);


                        } else {

                            showToast(UiUtlis.getString(context, R.string.data_error));

                        }
                        break;

                }

                if (requestCount == selectCount && requestCount != 0) {
                    requestCount = 0;
                    selectCount = 0;
                    tvNodata.setText("暂无数据");
                    requestCount = 0;
                    selectCount = 0;
                    dialog.cancel();

                    //更新上传列表
                    mActivity.updataDjdsc();

                    xjjhs.clear();
                    xjjhs.addAll(where("download = ?", "0").find(Xjjh.class));
                    if (adapter != null) {
                        cb.setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                    showToast("下载成功");
                    dialog.dismiss();
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


    private String createZyJson(String zxid, String jhmc) {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("XSCB_ZXJHD_GET");
        info.setZxid(zxid);
        info.setJhmc(jhmc);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    @OnClick(R.id.btn_delete)
    public void delete() {
        if (xjjhs.size() != 0) {

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
                    List<Xjjh> delete = new ArrayList<>();
                    for (int i = 0; i < xjjhs.size(); i++) {
                        if (xjjhs.get(i).isChecked()) {
                            delete.add(xjjhs.get(i));
                            DataSupport.deleteAll(Xjjh.class, "zxid = ?", xjjhs.get(i).getZxid());
                        }
                    }
                    xjjhs.removeAll(delete);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();

        } else {
            showToast("没有可删除计划");
        }
    }

    private void setListData() {

        adapter = new XzxsjhAdapter(context, xjjhs, new XzxsjhAdapter.CallBack() {
            @Override
            public void Click(View view) {

                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                xjjhs.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < xjjhs.size(); a++) {
                    if (xjjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == xjjhs.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });


        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                xjjhs.get(i - 1).setChecked(xjjhs.get(i - 1).isChecked());
                int count = 0;
                for (int a = 0; a < xjjhs.size(); a++) {
                    if (xjjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == xjjhs.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
