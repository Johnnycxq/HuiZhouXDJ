package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.qfgd.TjqxdActivity;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity3;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static org.litepal.crud.DataSupport.where;

/**
 * 设备巡点检管理-巡视工作
 */
public class SxgzActivity extends BaseActivity3 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sm)
    Button btn_sm;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;
    private CommonAdapter<XSJJHXZDataBean> adapter;
    private List<XSJJHXZDataBean> xsjjhxzDataBeanList = new ArrayList<>();
    private ArrayList<XSJJHDataBean> xsjjhDataBeanArrayList = new ArrayList<>();//点检记录列表
    Intent intent;
    private String txm;//二维码或者条形码

    @Override
    public int getLayoutId() {
        return R.layout.activity_xsgz;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                intent = new Intent(SxgzActivity.this, TjqxdActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.xsgz_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

    }

    public void initData() {

        initNFC();
        initToolbar("巡视工作", "提单", this);
        getDataInSqlite();
        setListData();


        smOnclick();
    }

    private void smOnclick() {
        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xsjjhxzDataBeanList.size() != 0) {
                    Intent intent = new Intent(SxgzActivity.this, MipcaActivityCapture.class);
                    startActivityForResult(intent, 1);
                } else {
                    showToast("你还没有计划");
                }
            }
        });
    }


    //加载数据库中已经下载的计划
    private void getDataInSqlite() {

        xsjjhxzDataBeanList.clear();

        xsjjhxzDataBeanList.addAll(DataSupport.findAll(XSJJHXZDataBean.class));

    }

    private void setListData() {

        if (adapter == null) {


            adapter = new CommonAdapter<XSJJHXZDataBean>(context, R.layout.xsgz_item, xsjjhxzDataBeanList) {
                @Override
                protected void convert(ViewHolder viewHolder, XSJJHXZDataBean item, int position) {

                    int checkedCount = 0;
                    List<XSJJHDataBean> xsjjhdatabean = where("xsjjhxzDataBean_id = ?", item.getId() + "").find(XSJJHDataBean.class);

                    for (int i = 0; i < xsjjhdatabean.size(); i++) {
                        if (xsjjhdatabean.get(i).isChecked()) {
                            checkedCount++;
                        }
                    }

                    viewHolder.setText(R.id.tv_xh, item.getSN() + "");
                    viewHolder.setText(R.id.tv_qymc, item.getQymc());
                    viewHolder.setText(R.id.tv_djrw, checkedCount + "/" + xsjjhdatabean.size());

                }
            };


            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {


                    xsjjhDataBeanArrayList.clear();

                    xsjjhDataBeanArrayList.addAll(where("xsjjhxzDataBean_id = ?", xsjjhxzDataBeanList.get(postion - 1).getId() + "").find(XSJJHDataBean.class));

                    Log.e("size", String.valueOf(xsjjhDataBeanArrayList.size()));


                    if (xsjjhDataBeanArrayList.size() != 0) {

                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(SxgzActivity.this, XjSbListActivity.class);
                        bundle.putParcelableArrayList("xsjjhDataBeanArrayList", xsjjhDataBeanArrayList);
                        bundle.putBoolean("edit", false);
                        bundle.putInt(Contans.KEY_ITEM, 0);
                        bundle.putInt("itemposition", postion - 1);
                        bundle.putString("LX", "Click");
                        bundle.putString("LXResult", "LXResult");
                        bundle.putInt("from", 0);
                        intent.putExtras(bundle);
                        startActivity(intent);

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
                    txm = bundle.getString("result");

                    List<XSJJHDataBean> xsjjhDataBeen = DataSupport.where("txm = ?", txm).find(XSJJHDataBean.class);//txm是根据扫描得到的二维码结果来查询

                    xsjjhDataBeanArrayList.clear();
                    xsjjhDataBeanArrayList.addAll(xsjjhDataBeen);


                    Bundle bundle2 = new Bundle();
                    Intent intent = new Intent(SxgzActivity.this, XjSbListActivity.class);
                    bundle2.putParcelableArrayList("xsjjhDataBeanArrayList", xsjjhDataBeanArrayList);
                    bundle2.putBoolean("edit", true);
                    bundle2.putInt(Contans.KEY_ITEM, 0);
                    bundle2.putInt("itemposition", 0);
                    bundle2.putString("LX", "QRcode");
                    bundle2.putString("LXResult", txm);
                    bundle2.putInt("from", 0);
                    intent.putExtras(bundle2);
                    startActivity(intent);


                }
                break;
        }
    }

    //处理NFC的数据
    @Override
    public void handleNfc(String result) {
        super.handleNfc(result);

        List<XSJJHDataBean> xsjjhDataBeen = DataSupport.where("nfcbm = ?", result).find(XSJJHDataBean.class);

        xsjjhDataBeanArrayList.clear();
        xsjjhDataBeanArrayList.addAll(xsjjhDataBeen);


        Bundle bundle3 = new Bundle();
        Intent intent = new Intent(SxgzActivity.this, XjSbListActivity.class);
        bundle3.putParcelableArrayList("xsjjhDataBeanArrayList", xsjjhDataBeanArrayList);
        bundle3.putBoolean("edit", true);
        bundle3.putInt(Contans.KEY_ITEM, 0);
        bundle3.putInt("itemposition", 0);
        bundle3.putString("LX", "NFC");
        bundle3.putString("LXResult", result);
        bundle3.putInt("from", 0);
        intent.putExtras(bundle3);
        startActivity(intent);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataInSqlite();
        adapter.notifyDataSetChanged();
    }
}