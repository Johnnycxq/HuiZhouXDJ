package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.bean.SetSbModel;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static org.litepal.crud.DataSupport.where;

public class YulActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    private static final int Req = 101;
    private boolean isEdit = true;
    private int item;
    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();//工作列表
    private ArrayList<QYDDATABean> qyddataBeanList = new ArrayList<>();//点检记录列表
    private List<DlbInfo> infos = new ArrayList<>();
    private View headView;
    private DlbAdapter adapter;
    private int itemposition;
    private String LX, LXResult;

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

                    QYDDATABean qyddataBean = qyddataBeanList.get(position);
                    qyddataBean.setChecked(true);
                    qyddataBean.setCJJG(name);
                    qyddataBeanList.set(position, qyddataBean);

                    Log.e("YulActivity", "name=" + name + ", position=" + position);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public int getContentViewID() {
        return R.layout.activity_yul;
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
//        qyddataBeanList = bundle.getParcelableArrayList(Contans.KEY_DJJHRWQY);
        item = bundle.getInt(Contans.KEY_ITEM);
        itemposition = bundle.getInt("itemposition");
        LX = bundle.getString("LX");
        LXResult = bundle.getString("LXResult");
        searchdata();
        setListAdapter();


        IntentFilter filter = new IntentFilter();
        filter.addAction(Contans.ACTION_YULONE);
        registerReceiver(myReceiver, filter);
    }

    private void searchdata() {
        if (LX.equals("Click")) {


            xdjjhxzDataBeanList.clear();
            //获取本地所有的工作列表数据
            xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
            qyddataBeanList.clear();
            //获取当前点击的工作栏对应的点检记录列表
            qyddataBeanList.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBeanList.get(itemposition).getId() + "").find(QYDDATABean.class));

        } else if (LX.equals("QRcode")) {


            List<QYDDATABean> qydDataBeen = DataSupport.where("QYEWM = ?", LXResult).find(QYDDATABean.class);//ewm是根据扫描得到的二维码结果来查询

            qyddataBeanList.clear();
            qyddataBeanList.addAll(qydDataBeen);

        } else if (LX.equals("NFC")) {


            List<QYDDATABean> qydDataBeen = DataSupport.where("QYNFC = ?", LXResult).find(QYDDATABean.class);

            qyddataBeanList.clear();
            qyddataBeanList.addAll(qydDataBeen);

        }
    }

    private void setListAdapter() {

        for (QYDDATABean rw : qyddataBeanList) {   //这里进行了数据的一些获取 把必要的拿出来显示
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//这里应该

                Intent intent = new Intent(YulActivity.this, SbxdjcjsbActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
                bundle.putBoolean("edit", isEdit);
                bundle.putInt(Contans.KEY_ITEM, position - 1);
                bundle.putInt("itemposition", itemposition);
                bundle.putString("LX", LX);
                bundle.putString("LXResult", LXResult);
                intent.putExtras(bundle);
                startActivityForResult(intent, Req);
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
        searchdata();
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
}
