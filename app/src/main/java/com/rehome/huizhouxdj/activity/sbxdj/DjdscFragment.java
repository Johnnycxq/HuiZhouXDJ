package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.DBModel.QxgdInfo;
import com.rehome.huizhouxdj.DBModel.XcjsInfo;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XzjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.ScDjjhInfo;
import com.rehome.huizhouxdj.bean.StatusInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.HttpResponseListener;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static org.litepal.crud.DataSupport.findAll;
import static org.litepal.crud.DataSupport.updateAll;
import static org.litepal.crud.DataSupport.where;

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
    private XzjhAdapter adapter;

    private List<Djjh> djjhs;
    private List<DjjhRwQy> rwqys;
    private List<Djjh> list;
    private Map<String, List<DjjhRwQy>> map;
    private List<ScDjjhInfo> scs;//上传的集合
    private boolean first = true;
    private List<DjjhRwQy> yjqys;//已检查项目
    private List<Djjh> removelist;//上传的计划

    private int jscount;
    private int jsrequestcount;

    private int requestCount = 0;//请求数量
    private int resultCount = 0;//请求成功数量
    private int checkedCount = 0;//已检数量

    private WaitDialog dialog;

    private RequestQueue queue;

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
        headView = View.inflate(context, R.layout.xzjh_item, null);
        head = headView.findViewById(R.id.head);
        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        TextView yjzj = (TextView) headView.findViewById(R.id.tv_jhlx);
        yjzj.setText("已检/总计");
        head.setVisibility(View.VISIBLE);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_djdsc;
    }

    public void initData() {

        queue = NoHttp.newRequestQueue(1);
        djjhs = new ArrayList<>();
        rwqys = new ArrayList<>();
        //rwLists = new ArrayList<>();
        list = new ArrayList<>();
        map = new HashMap<>();
        scs = new ArrayList<>();
        removelist = new ArrayList<>();


        List<DjjhRwQy> list = findAll(DjjhRwQy.class);

        for (int i = 0; i < list.size(); i++) {
            Log.d("litepal", list.get(i).getSBMC());
        }


    }

    /**
     * 获取数据库的数据
     */
    public void getDataInSQL() {
        rwqys.clear();
        djjhs.clear();
        list.clear();
        map.clear();

        //获取点检计划
        djjhs = where("download = ?", "1").find(Djjh.class);
        //获取点检计划中所有的区域
        for (Djjh djjh : djjhs) {
            List<DjjhRwQy> rwqy = where("JHID = ? and deleted = ?", djjh.getJHID(), "0").order("DATE").find(DjjhRwQy.class);
            rwqys.addAll(rwqy);
            map.put(djjh.getJHID(), rwqy);
        }

        for (int i = 0; i < djjhs.size(); i++) {
            int zong = 0;
            int wz = 0;//已检总数
            List<DjjhRwQy> rwqy = new ArrayList<>();
            for (int a = 0; a < rwqys.size(); a++) {
                if (rwqys.get(a).isChecked() && djjhs.get(i).getJHID().equals(rwqys.get(a).getJHID())) {
                    wz++;
                }

                if (djjhs.get(i).getJHID().equals(rwqys.get(a).getJHID())) {
                    zong++;
                }
            }
            Djjh djjh = new Djjh();
            djjh.setJHMC(djjhs.get(i).getJHMC());
            djjh.setDQSJ(wz + "/" + zong);
            djjh.setJHID(djjhs.get(i).getJHID());
            list.add(djjh);
        }
    }

    private void setListData() {

        adapter = new XzjhAdapter(context, list, new XzjhAdapter.CallBack() {
            @Override
            public void Click(View view) {
                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                list.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < list.size(); a++) {
                    if (list.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == list.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });

        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                list.get(i - 1).setChecked(list.get(i - 1).isChecked() ? false : true);
                int count = 0;
                for (int a = 0; a < list.size(); a++) {
                    if (list.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == list.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.btn_sc, R.id.btn_hf, R.id.btn_del})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_sc:
                uploadData();
                //toJson();
                break;
            case R.id.btn_hf:
                //recoveryData();
                //showToast("恢复");
                break;
            case R.id.btn_del:
                deleteData();
                //showToast("删除");
                break;
        }
    }

    //上传数据
    private void uploadData() {

        removelist.clear();
        //已检查的项目
        yjqys = new ArrayList<>();
        scs.clear();
        int select = 0;
        int allCount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {

                List<DjjhRwQy> rwqy = new ArrayList<>();

                rwqy.addAll(map.get(list.get(i).getJHID()));

                for (DjjhRwQy djjh : rwqy) {


                    if (!djjh.isUploaded()) {
                        //那没有上传的添加到这里面
                        yjqys.add(djjh);

                    }

                    if (djjh.isChecked()) {

                        ++checkedCount;
                    }

                    allCount++;
                }
                //把选择的加入集合中
                removelist.add(list.get(i));
                select++;
            }
        }

        if (select != 0) {
            if (checkedCount == allCount) {
                sCData(toJson());
                checkedCount = 0;
            } else {
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
                        sCData(toJson());
                        Logger.v(toJson());
                    }
                });
                builder.show();
            }
        } else {
            showToast("没有可上传计划");
        }
    }

    //    //删除数据
    private void deleteData() {
        //已检查的项目
        final List<Djjh> deletes = new ArrayList<>();
        int select = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                deletes.add(list.get(i));
                select++;
            }
        }

        if (select != 0) {
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
                    //删除数据
                    for (Djjh djjh : deletes) {
                        DataSupport.deleteAll(DjjhRwQy.class, "jhid = ?", djjh.getJHID());
                        DataSupport.deleteAll(Djjh.class, "jhid = ?", djjh.getJHID());
                        DataSupport.deleteAll(XcjsInfo.class, "jhid = ?", djjh.getJHID());
                    }
                    //刷新界面
                    getDataInSQL();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        } else {
            showToast("你还没有选择项目");
        }
    }

    private void sCData(String json) {
        dialog.show();
        ++requestCount;
        Logger.v(json);
        //计划
        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.DJJHSC, RequestMethod.POST);
        request.setDefineRequestBodyForJson(json);


        queue.add(0, request, new HttpResponseListener<>(getActivity(), request, callback, false, false, ""));

        //NohttpUtils.getInstance().add(getActivity(), 0, request, callback, true, false, "上传中...");

        //缺陷工单，如果缺陷工单为空，就没有上传
        List<QxgdInfo> infos = findAll(QxgdInfo.class);
        if (infos.size() != 0) {
            ++requestCount;
            Request<String> request1 = NoHttp.createStringRequest(Contans.IP + Contans.DJJHQXGD, RequestMethod.POST);
            String qxgdjson = "{\"Rows\":" + GsonUtils.GsonString(infos) + ",\"Total\": " + infos.size() + "}";
            Logger.json(qxgdjson);
            System.out.println(qxgdjson);
            try {
                qxgdjson = new String(qxgdjson.getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request1.setDefineRequestBodyForJson(UiUtlis.encoder(qxgdjson));
            queue.add(1, request1, new HttpResponseListener<>(getActivity(), request1, callback, false, false, ""));
            //NohttpUtils.getInstance().add(getActivity(), 1, request1, callback, true, false, "上传中...");
        }
        //现场记事
        List<XcjsInfo> xcjss = findAll(XcjsInfo.class);
        jscount = xcjss.size();
        for (XcjsInfo info : xcjss) {
            ++requestCount;
            Request<String> xcjs = NoHttp.createStringRequest(Contans.IP + Contans.DJJHXCJSSC + "?ms=" + UiUtlis.encoder(info.getMs()) +
                    "&jhid=" + UiUtlis.encoder(info.getJhid()) + "&pointnum=" + UiUtlis.encoder(info.getPointnum()) +
                    "&djr=" + UiUtlis.encoder(info.getDjr()), RequestMethod.POST);
            xcjs.add(Contans.FILEPS, new FileBinary(new File(info.getFilename())));
            queue.add(2, xcjs, new HttpResponseListener<>(getActivity(), xcjs, callback, false, false, ""));

            //NohttpUtils.getInstance().add(getActivity(), 2, xcjs, callback, true, false, "上传中...");
        }
    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {

            Logger.v(response.get());
            System.out.println(response.get());
            StatusInfo info = GsonUtils.GsonToBean(response.get(), StatusInfo.class);
            if (info != null) {
                try {
                    switch (what) {
                        case 0:
                            if (info.getRows().get(0).getStatus().equals("1")) {
                                ++resultCount;
                            }
                            break;
                        case 1:
//                            ++resultCount;
                            if (info.getRows().get(0).getStatus().equals("1")) {
                                ++resultCount;
                            }
                            break;
                        case 2:
                            if (info.getRows().get(0).getStatus().equals("1")) {
                                ++resultCount;
                            }
                            break;
                    }
                } catch (Exception e) {
                    showToast("数据异常");
                    dialog.dismiss();
                }
                if (resultCount == requestCount) {
                    resultCount = 0;
                    requestCount = 0;

                    for (int i = 0; i < yjqys.size(); i++) {
                        ContentValues values = new ContentValues();
                        values.put("uploaded", "1");
                        updateAll(DjjhRwQy.class, values, "jhid = ? and pointnum = ?", yjqys.get(i).getJHID(), yjqys.get(i).getPOINTNUM());
                    }
                    showToast("上传成功");
                    //上传成功删除已经上传的数据
                    removeData();
                    dialog.dismiss();
                }
            } else {
                dialog.dismiss();
                showToast(UiUtlis.getString(context, R.string.data_error));
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                requestCount = 0;
                resultCount = 0;
            }
        }
    };

    private String toJson() {

        for (DjjhRwQy rwqy : yjqys) {

            ScDjjhInfo info = new ScDjjhInfo();

            info.setChecked(rwqy.isChecked());

            info.setCjjg(rwqy.getCJJG() == null ? "" : rwqy.getCJJG());

            info.setJhid(rwqy.getJHID() == null ? "" : rwqy.getJHID());

            info.setPointnum(rwqy.getPOINTNUM() == null ? "" : rwqy.getPOINTNUM());

            info.setDate(rwqy.getDATE());

            info.setDjr((String) SPUtils.get(context, Contans.USERNAME, ""));

            info.setBzmc((String) SPUtils.get(context, Contans.BZBH, ""));

            info.setFxnr(rwqy.getFxnr() == null ? "" : rwqy.getFxnr());

            info.setSmfx(rwqy.isSMFX() ? "扫描条码" : "NFC标签");

            info.setASSETNUM(rwqy.getASSETNUM());

            if (!rwqy.isBYZT()) {

                info.setSBZT(rwqy.isSBZT() ? (1 + "") : (0 + ""));

            } else {

                info.setSBZT("2");

            }

            info.setSCID(rwqy.getSCID());

            scs.add(info);
        }

        String json = "{\"Rows\":" + GsonUtils.GsonString(scs) + ",\"Total\": " + scs.size() + "}";

        System.out.println("点检记录" + json);

        Logger.json(json);

        try {
            json = new String(json.getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return json;
    }

    //上传成功，移除数据
    private void removeData() {


        for (Djjh djjh : removelist) {
            DataSupport.deleteAll(Djjh.class, "jhid = ?", djjh.getJHID());
            DataSupport.deleteAll(DjjhRwQy.class, "jhid = ?", djjh.getJHID());
            List<XcjsInfo> infos = where("jhid = ?", djjh.getJHID()).find(XcjsInfo.class);
            for (XcjsInfo info : infos) {
                File file = new File(info.getFilename());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(XcjsInfo.class, "jhid = ?", djjh.getJHID());
            DataSupport.deleteAll(QxgdInfo.class);
        }


        getDataInSQL();
        if (adapter != null) {
            cb.setChecked(false);
            adapter.notifyDataSetChanged();
        }
    }
}