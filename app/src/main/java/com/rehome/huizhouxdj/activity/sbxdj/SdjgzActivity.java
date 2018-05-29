package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.qfgd.TjqxdActivity;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity3;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.litepal.crud.DataSupport.where;

/**
 * 设备巡点检管理-点检工作
 */
public class SdjgzActivity extends BaseActivity3 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sm)
    Button btn_sm;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.spinner)
    Spinner spinner;

    private ArrayAdapter<String> spineradapter;
    private View headView;
    private CommonAdapter<XDJJHXZDataBean> adapter;
    private String ewm;//二维码或者条形码

    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();//工作列表
    private List<XDJJHXZDataBean> spinnerxdjjhxzDataBeanList = new ArrayList<>();//工作列表
    private ArrayList<QYDDATABean> qyddataBeanList = new ArrayList<>();//点检记录列表
    private ArrayList<QYAQFXDATABean> qyaqfxdataBeanList = new ArrayList<>();//点检记录列表
    Intent intent;
    private List<String> dialogDatas2;

    private String GWID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sdjgz;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                intent = new Intent(SdjgzActivity.this, TjqxdActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.djajhgz_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
    }

    public void initData() {

        initNFC();

        initToolbar("点检工作", "提单", this);

        Bundle bundle = SdjgzActivity.this.getIntent().getExtras();

        GWID = bundle.getString("GWID");

        xdjjhxzDataBeanList.clear();

        xdjjhxzDataBeanList.addAll(where("GWID = ?", GWID + "").find(XDJJHXZDataBean.class));

        dialogDatas2 = new ArrayList<>();

//        getDataInSqlite();

        setListData();

//        spinnerView();

        smOnclick();

    }

    private void smOnclick() {

        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xdjjhxzDataBeanList.size() != 0) {
                    Intent intent = new Intent(SdjgzActivity.this, MipcaActivityCapture.class);
                    startActivityForResult(intent, 1);
                } else {
                    showToast("你还没有计划");
                }
            }
        });
    }

    private void spinnerView() {
        spineradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dialogDatas2);
        spineradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spineradapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String p = spineradapter.getItem(position);
                if (p.equals("全部")) {
                    xdjjhxzDataBeanList.clear();
                    xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
                } else {
                    xdjjhxzDataBeanList.clear();
                    xdjjhxzDataBeanList.addAll(where("GWMC = ?", p + "").find(XDJJHXZDataBean.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    //加载数据库中已经下载的计划
    private void getDataInSqlite() {



        /*-----去除重复数据------*/
        spinnerxdjjhxzDataBeanList.clear();
        spinnerxdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
        Set<XDJJHXZDataBean> xdjjhxzDataBeanSet = new TreeSet<>(new Comparator<XDJJHXZDataBean>() {
            @Override
            public int compare(XDJJHXZDataBean o1, XDJJHXZDataBean o2) {
                return o1.getGWID().compareTo(o2.getGWID());
            }
        });
        xdjjhxzDataBeanSet.addAll(spinnerxdjjhxzDataBeanList);
        spinnerxdjjhxzDataBeanList.clear();
        spinnerxdjjhxzDataBeanList.addAll(xdjjhxzDataBeanSet);

        dialogDatas2.clear();
        dialogDatas2.add("全部");
        for (int i = 0; i < spinnerxdjjhxzDataBeanList.size(); i++) {
            dialogDatas2.add(spinnerxdjjhxzDataBeanList.get(i).getGWMC());
        }

        /*-----去除重复数据------*/


    }

    private void setListData() {
        if (adapter == null) {


            adapter = new CommonAdapter<XDJJHXZDataBean>(context, R.layout.djajhgz_item, xdjjhxzDataBeanList) {
                @Override
                protected void convert(ViewHolder viewHolder, XDJJHXZDataBean item, int position) {

                    int checkedCount = 0;

                    List<QYDDATABean> qyddataBeen = where("xdjjhxzDataBean_id = ?", item.getId() + "").find(QYDDATABean.class);

                    for (int i = 0; i < qyddataBeen.size(); i++) {

                        if (qyddataBeen.get(i).isChecked()) {

                            checkedCount++;

                        }

                    }

                    viewHolder.setText(R.id.tv_xh, item.getSN() + "");

                    viewHolder.setText(R.id.tv_qymc, item.getGWMC() + "--" + item.getQYMC());
                    viewHolder.setText(R.id.tv_djrw, checkedCount + "/" + qyddataBeen.size());

                }
            };


            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {

                    qyddataBeanList.clear();
                    //获取当前点击的工作栏对应的点检记录列表
                    qyddataBeanList.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBeanList.get(postion - 1).getId() + "").find(QYDDATABean.class));

                    qyaqfxdataBeanList.clear();
                    qyaqfxdataBeanList.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBeanList.get(postion - 1).getId() + "").find(QYAQFXDATABean.class));


                    if (qyddataBeanList.size() != 0) {

                        Log.e("123", xdjjhxzDataBeanList.get(postion - 1).getSN() + "");

                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(SdjgzActivity.this, SdjSbListActivity.class);
                        bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
                        bundle.putParcelableArrayList("QYFXTS", qyaqfxdataBeanList);
                        bundle.putBoolean("edit", true);
                        bundle.putInt(Contans.KEY_ITEM, 0);
//                        bundle.putInt("itemposition", postion - 1);
                        bundle.putInt("itemposition", xdjjhxzDataBeanList.get(postion - 1).getSN() - 1);
                        bundle.putString("LX", "Click");
                        bundle.putString("LXResult", "LXResult");
                        bundle.putInt("from", 0);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else {
                        showToast("暂无该区域点检任务");
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
                    ewm = bundle.getString("result");

                    List<QYDDATABean> qydDataBeen = DataSupport.where("QYEWM = ?", ewm).find(QYDDATABean.class);//ewm是根据扫描得到的二维码结果来查询

                    qyddataBeanList.clear();
                    qyddataBeanList.addAll(qydDataBeen);

                    List<QYAQFXDATABean> qyaqfxdataBeen = DataSupport.where("QYEWM = ?", ewm).find(QYAQFXDATABean.class);//ewm是根据扫描得到的二维码结果来查询
                    qyaqfxdataBeanList.clear();
                    qyaqfxdataBeanList.addAll(qyaqfxdataBeen);


                    Bundle bundle2 = new Bundle();
                    Intent intent = new Intent(SdjgzActivity.this, SdjSbListActivity.class);
                    bundle2.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
                    bundle2.putParcelableArrayList("QYFXTS", qyaqfxdataBeanList);
                    bundle2.putBoolean("edit", true);
                    bundle2.putInt(Contans.KEY_ITEM, 0);
                    bundle2.putInt("itemposition", 0);
                    bundle2.putString("LX", "QRcode");
                    bundle2.putString("LXResult", ewm);
                    bundle2.putInt("from", 0);
                    intent.putExtras(bundle2);
                    startActivity(intent);


//                    Intent intent = new Intent(SdjgzActivity.this, TipsActivity.class);
//                    Bundle bundle2 = new Bundle();
//                    bundle2.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
//                    bundle2.putParcelableArrayList("QYFXTS", qyaqfxdataBeanList);
//                    bundle2.putBoolean("edit", true);
//                    bundle2.putInt(Contans.KEY_ITEM, 0);
//                    intent.putExtras(bundle2);
//                    startActivity(intent);


                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataInSqlite();
        adapter.notifyDataSetChanged();
    }

    //处理NFC的数据
    @Override
    public void handleNfc(String result) {
        super.handleNfc(result);

        List<QYDDATABean> qydDataBeen = DataSupport.where("QYNFC = ?", result).find(QYDDATABean.class);

        qyddataBeanList.clear();
        qyddataBeanList.addAll(qydDataBeen);


        List<QYAQFXDATABean> qyaqfxdataBeen = DataSupport.where("QYNFC = ?", result).find(QYAQFXDATABean.class);
        qyaqfxdataBeanList.clear();
        qyaqfxdataBeanList.addAll(qyaqfxdataBeen);


        Bundle bundle3 = new Bundle();
        Intent intent = new Intent(SdjgzActivity.this, SdjSbListActivity.class);
        bundle3.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
        bundle3.putParcelableArrayList("QYFXTS", qyaqfxdataBeanList);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}