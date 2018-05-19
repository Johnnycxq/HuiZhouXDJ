package com.rehome.huizhouxdj.activity.sbxj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.bean.SetxjSbModel;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rehome.huizhouxdj.activity.sbxdj.SbxdjcjsbActivity.Req;
import static org.litepal.crud.DataSupport.where;

public class XjYulActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;

    private boolean isEdit = true;
    private int item;
    private ArrayList<XSJJHXZDataBean> xsjjhxzDataBeanArrayList = new ArrayList<>();//工作列表
    private ArrayList<XSJJHDataBean> xsjjhDataBeanArrayList = new ArrayList<>();
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

                    XSJJHDataBean xsjjhDataBean = xsjjhDataBeanArrayList.get(position);
                    xsjjhDataBean.setChecked(true);
                    xsjjhDataBean.setCJJG(name);
                    xsjjhDataBeanArrayList.set(position, xsjjhDataBean);

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
        setTitle("浏览巡检记录");
        headView = View.inflate(context, R.layout.dlb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

        Bundle bundle = getIntent().getExtras();
        isEdit = bundle.getBoolean("edit");
//        xsjjhDataBeanArrayList = bundle.getParcelableArrayList("xsjjhDataBeanArrayList");
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


            xsjjhxzDataBeanArrayList.clear();
            //获取本地所有的工作列表数据
            xsjjhxzDataBeanArrayList.addAll(DataSupport.findAll(XSJJHXZDataBean.class));
            xsjjhDataBeanArrayList.clear();
            //获取当前点击的工作栏对应的点检记录列表
            xsjjhDataBeanArrayList.addAll(where("xsjjhxzDataBean_id = ?", xsjjhxzDataBeanArrayList.get(itemposition).getId() + "").find(XSJJHDataBean.class));

        } else if (LX.equals("QRcode")) {

            List<XSJJHDataBean> xsjjhDataBeen = DataSupport.where("txm = ?", LXResult).find(XSJJHDataBean.class);//ewm是根据扫描得到的二维码结果来查询

            xsjjhDataBeanArrayList.clear();
            xsjjhDataBeanArrayList.addAll(xsjjhDataBeen);


        } else if (LX.equals("NFC")) {

            List<XSJJHDataBean> xsjjhDataBeen = DataSupport.where("nfcbm = ?", LXResult).find(XSJJHDataBean.class);

            xsjjhDataBeanArrayList.clear();
            xsjjhDataBeanArrayList.addAll(xsjjhDataBeen);
        }
    }

    private void setListAdapter() {

        for (XSJJHDataBean rw : xsjjhDataBeanArrayList) {
            DlbInfo info = new DlbInfo();
            info.setSbid(rw.getSbid());
            info.setCjjg(rw.getCJJG());
            info.setDian(rw.getSb() + "--" + rw.getBJMC());
            info.setStatu(rw.isChecked());
            infos.add(info);
        }

        adapter = new DlbAdapter(context, infos);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(XjYulActivity.this, SbxjcjsbActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("xsjjhDataBeanArrayList", xsjjhDataBeanArrayList);
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

    private ArrayList<SetxjSbModel> setxjSbModelArrayList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (data != null) {
            setxjSbModelArrayList = data.getParcelableArrayListExtra("setSbModelList");

            if (setxjSbModelArrayList == null) {

            } else {
                for (int i = 0; i < setxjSbModelArrayList.size(); i++) {
                    for (int j = 0; j < infos.size(); j++) {

                        if (infos.get(j).getSbid().equals(setxjSbModelArrayList.get(i).getSbId()))

                            infos.get(j).setCjjg(setxjSbModelArrayList.get(i).getValue());

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
