package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.bean.SetSbModel;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Yul_SBActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    private static final int Req = 101;
    @BindView(R.id.tx_setsbmc)
    TextView txSetsbmc;
    @BindView(R.id.bt_TY)
    Button btTY;
    @BindView(R.id.bt_YC)
    Button btYC;
    private boolean isEdit = true;
    private int item;


    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();//工作列表
//    private ArrayList<QYDDATABean> qyddataBeanList = new ArrayList<>();//点检记录列表

    private ArrayList<QYDDATABean> qyddataBeanArrayList_SB = new ArrayList<>();//点检记录列表

    private ArrayList<QYAQFXDATABean> qyaqfxdataBeanArrayList = new ArrayList<>();
    private List<DlbInfo> infos = new ArrayList<>();
    private View headView;
    private DlbAdapter adapter;
    private int itemposition;
    private String LX, LXResult, SBID;
    private long xdjjhxzDataBean_id;
    /**
     * 广播
     */
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //更新指定item
            String action = intent.getAction();
            switch (action) {
                case Contans.ACTION_YULONE: //更新设备点检页面返回时编辑的item内容

                    int position = intent.getIntExtra(Contans.KEY_POSITION, -1);
                    String name = intent.getStringExtra(Contans.KEY_NAME);
                    DlbInfo dlbInfo = infos.get(position);
                    dlbInfo.setCjjg(name);
                    dlbInfo.setStatu(true);
                    infos.set(position, dlbInfo);

                    QYDDATABean qyddataBean = qyddataBeanArrayList_SB.get(position);
                    qyddataBean.setChecked(true);
                    qyddataBean.setCJJG(name);
                    qyddataBeanArrayList_SB.set(position, qyddataBean);

                    Log.e("YulActivity", "name=" + name + ", position=" + position);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public int getContentViewID() {
        return R.layout.activity_yul_sb;
    }

    @Override
    protected void initView() {

        setBack();
        setTitle("浏览点检记录");
        headView = View.inflate(context, R.layout.dlb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

        Bundle bundle = getIntent().getExtras();
        isEdit = bundle.getBoolean("edit");
        qyddataBeanArrayList_SB = bundle.getParcelableArrayList("qyddataBeanArrayList_SB");
        qyaqfxdataBeanArrayList = bundle.getParcelableArrayList("QYFXTS");
        item = bundle.getInt(Contans.KEY_ITEM);
        itemposition = bundle.getInt("itemposition");
        LX = bundle.getString("LX");
        LXResult = bundle.getString("LXResult");
        xdjjhxzDataBean_id = bundle.getLong("xdjjhxzDataBean_id");


        SBID = qyddataBeanArrayList_SB.get(0).getSBID();
        txSetsbmc.setText(qyddataBeanArrayList_SB.get(0).getSBMC());


//        searchdata();
        setListAdapter();

        TYOnclick();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Contans.ACTION_YULONE);
        registerReceiver(myReceiver, filter);
    }

    private void TYOnclick() {

        btTY.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                ContentValues values = new ContentValues();
//
//                values.put("CJJG", "已停用");
//
//                values.put("SBMCSTATE", "已停用");
//
//                values.put("checked", true);
//
//                values.put("SBMCSTATEVALUE", "3");
//
//                int i = DataSupport.updateAll(QYDDATABean.class, values, "SBID = ? ", SBID);
//
//                if (i != 0) {
//                    showToast("设置设备状态为已停用");
//                    qyddataBeanArrayList_SB.clear();
//                    qyddataBeanArrayList_SB.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBean_id + "").find(QYDDATABean.class));
//                    Bundle bundle = new Bundle();
//                    Intent resultIntent = new Intent();
////                    bundle.putLong("xdjjhxzDataBean_id", xdjjhxzDataBean_id);
//                    resultIntent.putExtras(bundle);
//                    setResult(RESULT_OK, resultIntent);
//                    finish();
//                } else {
//                    showToast("设置设备状态为已停用失败");
//
//                }
            }
        });
        btYC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yul_SBActivity.this, SbxdjcjsbActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList_SB);
                bundle.putBoolean("edit", isEdit);
                bundle.putInt(Contans.KEY_ITEM, 0);
                bundle.putInt("itemposition", itemposition);
                bundle.putString("LX", LX);
                bundle.putString("LXResult", LXResult);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void searchdata() {
//        if (LX.equals("Click")) {
//
//
//            xdjjhxzDataBeanList.clear();
//            //获取本地所有的工作列表数据
//            xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
//
////            qyddataBeanList.clear();
////            //获取当前点击的工作栏对应的点检记录列表
////            qyddataBeanList.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBeanList.get(itemposition).getId() + "").find(QYDDATABean.class));
//
//        } else if (LX.equals("QRcode")) {
//
//
//            List<QYDDATABean> qydDataBeen = where("QYEWM = ?", LXResult).find(QYDDATABean.class);//ewm是根据扫描得到的二维码结果来查询
//
////            qyddataBeanList.clear();
////            qyddataBeanList.addAll(qydDataBeen);
//
//            qyddataBeanArrayList_SB.clear();
//            qyddataBeanArrayList_SB.addAll(qydDataBeen);
//
//        } else if (LX.equals("NFC")) {
//
//
//            List<QYDDATABean> qydDataBeen = where("QYNFC = ?", LXResult).find(QYDDATABean.class);
//
////            qyddataBeanList.clear();
////            qyddataBeanList.addAll(qydDataBeen);
//
//            qyddataBeanArrayList_SB.clear();
//            qyddataBeanArrayList_SB.addAll(qydDataBeen);
//
//        }
    }

    private void setListAdapter() {

        for (QYDDATABean rw : qyddataBeanArrayList_SB) {   //这里进行了数据的一些获取 把必要的拿出来显示
            DlbInfo info = new DlbInfo();
            info.setSbid(rw.getSBID());
            info.setCjjg(rw.getCJJG());//采集结果
            info.setDian(rw.getSBMC() + " - " + rw.getBJMC());//项目名称
            info.setStatu(rw.isChecked());//状态
            infos.add(info);
        }

        adapter = new DlbAdapter(context, infos);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Intent intent = new Intent(Yul_SBActivity.this, SbxdjcjsbActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList_SB);
//                bundle.putBoolean("edit", isEdit);
//                bundle.putInt(Contans.KEY_ITEM, position - 1);
//                bundle.putInt("itemposition", itemposition);
//                bundle.putString("LX", LX);
//                bundle.putString("LXResult", LXResult);
//                intent.putExtras(bundle);
//                startActivityForResult(intent, Req);

            }
        });
    }

    private ArrayList<SetSbModel> setSbModelList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (data != null) {
            setSbModelList = data.getParcelableArrayListExtra("setSbModelList");

            if (setSbModelList == null) {

            } else {
                for (int i = 0; i < setSbModelList.size(); i++) {
                    for (int j = 0; j < infos.size(); j++) {

                        if (infos.get(j).getSbid().equals(setSbModelList.get(i).getSbId()))

                            infos.get(j).setCjjg(setSbModelList.get(i).getValue());

//                        infos.get(j).setStatu(setSbModelList.get(i).getStatu());

                    }
                }
                adapter.notifyDataSetChanged();
            }
            adapter.notifyDataSetChanged();

        }
        //获取返回时的数据


    }

    @Override
    protected void onResume() {
        super.onResume();
//        searchdata();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册广播（注销）
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
