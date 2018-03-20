package com.rehome.huizhouxdj.activity.sbxdj;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.XDJJHXZBean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XzjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.XscbRequestBean;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 设备巡点检管理-下载计划
 */
public class XzjhFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_xz)
    Button btn_xz;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.LL)
    LinearLayout LL;
    Unbinder unbinder;

    private WaitDialog dialog;
    private SxcdjActivity mActivity;
    private View headView;
    private View head;
    private XzjhAdapter adapter;
    private CheckBox cb;
    private List<Djjh> djjhs;
    private int selectCount = 0;
    private int requestCount = 0;
    private List<String> gwids = new ArrayList<>();
    private RequestQueue queue;


    public XzjhFragment() {

    }

    public static XzjhFragment newInstance() {
        XzjhFragment fragment = new XzjhFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_xzjh;
    }

    @Override
    protected void initView() {

        lv.setEmptyView(tvNodata);
        mActivity = (SxcdjActivity) getActivity();
        headView = View.inflate(context, R.layout.xzjh_item, null);
        head = headView.findViewById(R.id.head);
        head.setVisibility(View.VISIBLE);
        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < djjhs.size(); i++) {
                        djjhs.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < djjhs.size(); i++) {
                        djjhs.get(i).setChecked(false);
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
        djjhs = new ArrayList<>();
        //如果数据库中有数据
        if (DataSupport.count("Djjh") != 0) {
            List<Djjh> djjhall = DataSupport.where("download = ?", "0").find(Djjh.class);
            djjhs.clear();
            djjhs.addAll(djjhall);
            setListData();

        } else {

            tvNodata.setText("暂无数据");
            LL.setVisibility(View.GONE);
        }


    }

    @OnClick(R.id.btn_xz)
    public void click() {

        if (djjhs.size() != 0) {

            gwids.clear();
            for (int i = 0; i < djjhs.size(); i++) {
                if (djjhs.get(i).isChecked()) {
                    if (!dialog.isShowing()) {
                        dialog.setMessage("下载计划中...");
                        dialog.show();
                    }

                    downData(djjhs.get(i).getGWID());
                    gwids.add(djjhs.get(i).getGWID());
                }
            }
        } else {
            showToast("没有可下载计划");
        }
    }

    @OnClick(R.id.btn_delete)
    public void delete() {
        if (djjhs.size() != 0) {

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
                    List<Djjh> delete = new ArrayList<>();
                    for (int i = 0; i < djjhs.size(); i++) {
                        if (djjhs.get(i).isChecked()) {
                            delete.add(djjhs.get(i));
                            DataSupport.deleteAll(Djjh.class, "GWID = ?", djjhs.get(i).getGWID());
                        }
                    }
                    djjhs.removeAll(delete);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();

        } else {
            showToast("没有可删除计划");
        }
    }


    private void downData(String gwid) {

        selectCount++;

        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.DJJHDLB, RequestMethod.POST);

        request.setDefineRequestBodyForJson(createZyJson(gwid));

        queue.add(1, request, new HttpResponseListener<>(getActivity(), request, callback, false, false, ""));

    }

    private XDJJHXZBean xdjjhxzBean;

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            try {
                switch (what) {

                    case 1:
                        ++requestCount;

                        Log.e("dataLog", response.get());


                        /*
                        * 正式数据请打开以下代码-Start
                        * 解析JSON
                        * */

                        xdjjhxzBean = GsonUtils.GsonToBean(response.get(), XDJJHXZBean.class);

                        //数据处理
                        if (xdjjhxzBean != null && xdjjhxzBean.getState() == 1) {

                            List<XDJJHXZDataBean> xdjjhxzDataBeanList = xdjjhxzBean.getData();

                            ContentValues values = new ContentValues();

                            values.put("download", 1);//把Djjh里面的下载过的download变成1

                            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {

                                DataSupport.updateAll(Djjh.class, values, "GWID = ?", xdjjhxzDataBeanList.get(i).getGWID());

                            }

                            int dataCount = DataSupport.count(XDJJHXZDataBean.class);

                            int index;//序号

                            if (dataCount == 0) {

                                index = 0;

                            } else {

                                index = dataCount;

                            }

                            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {

                                xdjjhxzDataBeanList.get(i).setSN(++index);

                                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYD_DATA());

                                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYAQFX_DATA());

                            }
                            //保存工作列表数据
                            DataSupport.saveAll(xdjjhxzDataBeanList);

                        } else {

                            showToast(UiUtlis.getString(context, R.string.data_error));

                        }

                        /*
                        * 正式数据请打开以下代码-End
                        *
                        * */


                        break;
                }

                if (requestCount == selectCount && requestCount != 0) {
                    requestCount = 0;
                    selectCount = 0;
                    tvNodata.setText("暂无数据");
                    requestCount = 0;
                    selectCount = 0;
                    dialog.cancel();

                    //更新的点检点上传列表
                    mActivity.updataDjdsc();

                    djjhs.clear();
                    djjhs.addAll(DataSupport.where("download = ?", "0").find(Djjh.class));
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


    private void setListData() {

        adapter = new XzjhAdapter(context, djjhs, new XzjhAdapter.CallBack() {
            @Override
            public void Click(View view) {

                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                djjhs.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < djjhs.size(); a++) {
                    if (djjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == djjhs.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });


        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                djjhs.get(i - 1).setChecked(djjhs.get(i - 1).isChecked());
                int count = 0;
                for (int a = 0; a < djjhs.size(); a++) {
                    if (djjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == djjhs.size() ? true : false);
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


    private String createZyJson(String gwid) {
        String YHID = (String) SPUtils.get(context, Contans.USERNAME, "");
        XscbRequestBean info = new XscbRequestBean();
        info.setAction("DJ_RWXZ_GET");
        info.setGWID(gwid);
        info.setYHID(YHID);
        String json = GsonUtils.GsonString(info);
        return json;
    }

}
