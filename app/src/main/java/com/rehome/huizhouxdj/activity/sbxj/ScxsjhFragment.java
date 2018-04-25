package com.rehome.huizhouxdj.activity.sbxj;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.ScxsAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

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
    private CheckBox cb;
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
        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < xsjjhxzDataBeanList.size(); i++) {
                        xsjjhxzDataBeanList.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < xsjjhxzDataBeanList.size(); i++) {
                        xsjjhxzDataBeanList.get(i).setChecked(false);
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
                xsjjhxzDataBean.setQymc(xsjjhxzDataBeanList.get(i).getQymc());
                xsjjhxzDataBean.setSczt(xsjjhxzDataBeanList.get(i).getSczt());
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
                cb.setChecked(count == xsjhxzdatalist.size() ? true : false);
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
                cb.setChecked(count == xsjhxzdatalist.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });


    }
    @OnClick({R.id.btn_sc, R.id.btn_hf, R.id.btn_del})

    public void click(View view) {

        switch (view.getId()) {

            case R.id.btn_sc:

//                uploadData();//上传勾选中的数据

                break;

            case R.id.btn_hf:

                break;

            case R.id.btn_del:

//                deleteData();//删除勾选中的数据

                break;
        }
    }

}