package com.rehome.huizhouxdj.activity.sbxdj;

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

import com.rehome.huizhouxdj.DBModel.Ajhxzrwqy;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhList;
import com.rehome.huizhouxdj.DBModel.DjjhRwList;
import com.rehome.huizhouxdj.DBModel.XDJJHXZBean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XzjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.HttpResponseListener;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.NoHttp;
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


    private DjjhList list;

    private DjjhRwList rwlist;

//    private XDJJHXZBean xdjjhxzBeanlist;

    private List<String> jhids = new ArrayList<>();

    private List<Ajhxzrwqy> rwqys;

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
        rwqys = new ArrayList<>();
        //如果数据库中有数据
//        if (DataSupport.count("Djjh") != 0) {
//            List<Djjh> djjhall = DataSupport.where("download = ?", "0").find(Djjh.class);
//            djjhs.clear();
//            djjhs.addAll(djjhall);
//            setListData();
//
//        } else {
//
//            tvNodata.setText("暂无数据");
//            LL.setVisibility(View.GONE);
//        }

        /*我写死的测试数据代码-Start，暂时注释**/
        String string = "{\n" +
                "  \"state\": 0,\n" +
                "  \"msg\": \"暂无数据\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"QYBH\": \"00000000021\",\n" +
                "      \"QYMC\": \"测试区域1\",\n" +
                "      \"QYEWM\": \"asdfasd\",\n" +
                "      \"QYEWMZT\": \"1\",\n" +
                "      \"JHID\": \"zx00000001\",\n" +
                "      \"QYNFC\": \"12312fdwfasdf\",\n" +
                "      \"QYNFCZT\": \"1\",\n" +
                "      \"QYD_DATA\": [\n" +
                "        {\n" +
                "          \"SCID\": \"B17A736A283C47999347F8E01CB5C1A6\",\n" +
                "          \"SBMC\": \"测试设备1\",\n" +
                "          \"BJMC\": \"部件2\",\n" +
                "          \"DID\": \"5C556F3D44DF420BB8D34593842AF8D2\",\n" +
                "          \"DMC\": \"消防测试点3_SBL1\",\n" +
                "          \"BZZ\": \"标准值\",\n" +
                "          \"SJMC\": \"数据名称\",\n" +
                "          \"SJDW\": \"数据单位\",\n" +
                "          \"JCFS\": \"检查方式\",\n" +
                "          \"XMZQ\": \"1\",\n" +
                "          \"LRFS\": \"1\",\n" +
                "          \"LRMRZ\": \"\"\n" +
                "        },{\n" +
                "          \"SCID\": \"B17A736A283C47999347F8E01CB5C1A6\",\n" +
                "          \"SBMC\": \"测试设备11\",\n" +
                "          \"BJMC\": \"部件21\",\n" +
                "          \"DID\": \"5C556F3D44DF420BB8D34593842AF8D2\",\n" +
                "          \"DMC\": \"消防测试点3_SBL1\",\n" +
                "          \"BZZ\": \"标准值\",\n" +
                "          \"SJMC\": \"数据名称\",\n" +
                "          \"SJDW\": \"数据单位\",\n" +
                "          \"JCFS\": \"检查方式\",\n" +
                "          \"XMZQ\": \"1\",\n" +
                "          \"LRFS\": \"1\",\n" +
                "          \"LRMRZ\": \"\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"QYAQFX_DATA\": [\n" +
                "        {\n" +
                "          \"AQFXID\": \"D3F75475F4D14A25BCBC1C798ED38368\",\n" +
                "          \"FXLX\": \"安全风险测试\",\n" +
                "          \"FXMS\": \"风险描述\",\n" +
                "          \"FHCS\": \"防护措施\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"AQFXID\": \"B0B062FDE8FF42E3B3EB59FD78E5639F\",\n" +
                "          \"FXLX\": \"安全风险测试1\",\n" +
                "          \"FXMS\": \"风险描述1\",\n" +
                "          \"FHCS\": \"防护措施1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },{\n" +
                "      \"QYBH\": \"00000000022\",\n" +
                "      \"QYMC\": \"测试区域2\",\n" +
                "      \"QYEWM\": \"asdfasd\",\n" +
                "      \"QYEWMZT\": \"2\",\n" +
                "      \"JHID\": \"zx00000002\",\n" +
                "      \"QYNFC\": \"12312fdwfasdf\",\n" +
                "      \"QYNFCZT\": \"2\",\n" +
                "      \"QYD_DATA\": [\n" +
                "        {\n" +
                "          \"SCID\": \"B17A736A283C47999347F8E01CB5C1A6\",\n" +
                "          \"SBMC\": \"测试设备2\",\n" +
                "          \"BJMC\": \"部件2\",\n" +
                "          \"DID\": \"5C556F3D44DF420BB8D34593842AF8D2\",\n" +
                "          \"DMC\": \"消防测试点3_SBL1\",\n" +
                "          \"BZZ\": \"标准值\",\n" +
                "          \"SJMC\": \"数据名称\",\n" +
                "          \"SJDW\": \"数据单位\",\n" +
                "          \"JCFS\": \"检查方式\",\n" +
                "          \"XMZQ\": \"1\",\n" +
                "          \"LRFS\": \"1\",\n" +
                "          \"LRMRZ\": \"\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"QYAQFX_DATA\": [\n" +
                "        {\n" +
                "          \"AQFXID\": \"D3F75475F4D14A25BCBC1C798ED38368\",\n" +
                "          \"FXLX\": \"安全风险测试2\",\n" +
                "          \"FXMS\": \"风险描述2\",\n" +
                "          \"FHCS\": \"防护措施2\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"AQFXID\": \"B0B062FDE8FF42E3B3EB59FD78E5639F\",\n" +
                "          \"FXLX\": \"安全风险测试22\",\n" +
                "          \"FXMS\": \"风险描述22\",\n" +
                "          \"FHCS\": \"防护措施22\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },{\n" +
                "      \"QYBH\": \"00000000023\",\n" +
                "      \"QYMC\": \"测试区域3\",\n" +
                "      \"QYEWM\": \"asdfasd\",\n" +
                "      \"QYEWMZT\": \"1\",\n" +
                "      \"JHID\": \"zx00000003\",\n" +
                "      \"QYNFC\": \"12312fdwfasdf\",\n" +
                "      \"QYNFCZT\": \"1\",\n" +
                "      \"QYD_DATA\": [\n" +
                "        {\n" +
                "          \"SCID\": \"B17A736A283C47999347F8E01CB5C1A6\",\n" +
                "          \"SBMC\": \"测试设备3\",\n" +
                "          \"BJMC\": \"部件2\",\n" +
                "          \"DID\": \"5C556F3D44DF420BB8D34593842AF8D2\",\n" +
                "          \"DMC\": \"消防测试点3_SBL1\",\n" +
                "          \"BZZ\": \"标准值\",\n" +
                "          \"SJMC\": \"数据名称\",\n" +
                "          \"SJDW\": \"数据单位\",\n" +
                "          \"JCFS\": \"检查方式\",\n" +
                "          \"XMZQ\": \"1\",\n" +
                "          \"LRFS\": \"1\",\n" +
                "          \"LRMRZ\": \"\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"QYAQFX_DATA\": [\n" +
                "        {\n" +
                "          \"AQFXID\": \"D3F75475F4D14A25BCBC1C798ED38368\",\n" +
                "          \"FXLX\": \"安全风险测试3\",\n" +
                "          \"FXMS\": \"风险描述3\",\n" +
                "          \"FHCS\": \"防护措施3\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"AQFXID\": \"B0B062FDE8FF42E3B3EB59FD78E5639F\",\n" +
                "          \"FXLX\": \"安全风险测试13\",\n" +
                "          \"FXMS\": \"风险描述13\",\n" +
                "          \"FHCS\": \"防护措施13\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //解析JSON
        xdjjhxzBean = GsonUtils.GsonToBean(string, XDJJHXZBean.class);

        //数据处理
        if (xdjjhxzBean != null && xdjjhxzBean.getState() == 0) {
            List<XDJJHXZDataBean> xdjjhxzDataBeanList = xdjjhxzBean.getData();
//            ContentValues values = new ContentValues();
//            values.put("download", 1);
//            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {
//                DataSupport.updateAll(XDJJHXZDataBean.class, values, "JHID = ?", xdjjhxzDataBeanList.get(i).getJHID());
//            }
            int index = 1;//序号（请自己优化）
            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {
                xdjjhxzDataBeanList.get(i).setSN(index++);
                //保存点检记录列表
                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYD_DATA());
                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYAQFX_DATA());
            }
            //保存工作列表数据
            DataSupport.saveAll(xdjjhxzDataBeanList);
        } else {
            showToast(UiUtlis.getString(context, R.string.data_error));
        }
        /*我写死的测试数据代码-End**/

    }

    @OnClick(R.id.btn_xz)
    public void click() {

        if (djjhs.size() != 0) {

            jhids.clear();
            for (int i = 0; i < djjhs.size(); i++) {
                if (djjhs.get(i).isChecked()) {
                    if (!dialog.isShowing()) {
                        dialog.setMessage("下载计划中...");
                        dialog.show();
                    }

                    downData(djjhs.get(i).getJHID());
                    jhids.add(djjhs.get(i).getJHID());
                    final int finalI = i;
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
                            DataSupport.deleteAll(Djjh.class, "jhid = ?", djjhs.get(i).getJHID());
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


    private void downData(String jhid) {
        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");
        selectCount++;
        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.DJJHDLB);
        request.add("JHID", UiUtlis.encoder(jhid));
        request.add("XZR", USERNAME);
        queue.add(1, request, new HttpResponseListener<>(getActivity(), request, callback, false, false, ""));

    }

    private XDJJHXZBean xdjjhxzBean;

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            //用来捕获数据解析的异常
            try {
                switch (what) {
                    case 0:
//                        list = GsonUtils.GsonToBean(response.get(), DjjhList.class);
//                        if (list != null) {
//                            if (list.getTotal() != 0) {
//                                djjhs.clear();
//                                djjhs.addAll(list.getRows());
//                                setListData();
//                                savaDataInSQLite();
//                            }
//                        } else {
//                            showToast(UiUtlis.getString(context, R.string.data_error));
//                        }
                        break;
                    case 1:
                        ++requestCount;

                        Log.e("dataLog", response.get());

                        //正式数据请打开以下代码-Start
//                        //解析JSON
//                        xdjjhxzBean = GsonUtils.GsonToBean(response.get(), XDJJHXZBean.class);
//
//                        //数据处理
//                        if (xdjjhxzBean != null && xdjjhxzBean.getState() == 0) {
//                            List<XDJJHXZDataBean> xdjjhxzDataBeanList = xdjjhxzBean.getData();
////            ContentValues values = new ContentValues();
////            values.put("download", 1);
////            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {
////                DataSupport.updateAll(XDJJHXZDataBean.class, values, "JHID = ?", xdjjhxzDataBeanList.get(i).getJHID());
////            }
//                            int index = 1;//序号（请自己优化）
//                            for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {
//                                xdjjhxzDataBeanList.get(i).setSN(index++);
//                                //保存点检记录列表
//                                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYD_DATA());
//                                DataSupport.saveAll(xdjjhxzDataBeanList.get(i).getQYAQFX_DATA());
//                            }
//                            //保存工作列表数据
//                            DataSupport.saveAll(xdjjhxzDataBeanList);
//                        } else {
//                            showToast(UiUtlis.getString(context, R.string.data_error));
//                        }
                        //正式数据请打开以下代码-End

                        //保存数据
//                        if (rwlist != null) {
//
////                            ContentValues values = new ContentValues();
//
////                            values.put("download", 1);
//
//                            DataSupport.updateAll(Djjh.class, values, "JHID = ?", rwlist.getRows().get(0).getDjjhRqqys().get(0).getJHID());
//
//                            Log.e("dataLog", rwlist.getRows().get(0).getDjjhRqqys().get(0).getJHID());
//
//                            for (int i = 0; i < rwlist.getRows().size(); i++) {
//
//                                DataSupport.saveAll(rwlist.getRows().get(i).getDjjhRqqys());
//
//                            }
//                        } else {
//
//                            showToast(UiUtlis.getString(context, R.string.data_error));
//                        }

                        break;

                    case 2:
//                        ++requestCount;
//                        AjhjhxzrwList list = GsonUtils.GsonToBean(response.get(), AjhjhxzrwList.class);
//                        if (list != null) {
//                            if (list.getTotal() != 0) {
//                                rwqys.clear();
//                                for (int i = 0; i < list.getRows().size(); i++) {
//                                    rwqys.addAll(list.getRows().get(i).getAjhxzrwqys());
//                                }
//                                //保存下载数据
//                                DataSupport.saveAll(rwqys);
//
//                                ContentValues values = new ContentValues();
//                                values.put("download", 1);
//                                DataSupport.updateAll(Ajhjh.class, values, "JHID = ?", rwqys.get(0).getJHID());
//
//                            }
//                        } else {
//                            showToast(UiUtlis.getString(context, R.string.data_error));
//                        }

                        break;
                    case 9:
//                        ZyInfo info = GsonUtils.GsonToBean(response.get(), ZyInfo.class);
//                        if (info != null) {
//                            if (DataSupport.count("zy") != 0) {
//                                DataSupport.deleteAll(Zy.class);
//                            }
//                            List<Zy> zys = info.getRows();
//                            //保存专业信息
//                            DataSupport.saveAll(zys);
//                        } else {
//                            showToast(UiUtlis.getString(context, R.string.data_error));
//                        }
//                        dialog.dismiss();
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


    /**
     * 保存计划数据到本地数据库
     */
    private void savaDataInSQLite() {
        list.save();
        DataSupport.saveAll(list.getRows());
    }

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
}
