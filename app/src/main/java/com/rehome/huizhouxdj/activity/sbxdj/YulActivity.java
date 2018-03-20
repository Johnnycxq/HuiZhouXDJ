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
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class YulActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;

    private boolean isEdit = true;
    private int item;
    private ArrayList<QYDDATABean> djjhs = new ArrayList<>();
    private List<DlbInfo> infos = new ArrayList<>();
    private View headView;
    private DlbAdapter adapter;

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

                    QYDDATABean qyddataBean = djjhs.get(position);
                    qyddataBean.setChecked(true);
                    qyddataBean.setCJJG(name);
                    djjhs.set(position, qyddataBean);

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
        setTitle("浏览点检记录");//这里


    }

    @Override
    public void initData() {

        Bundle bundle = getIntent().getExtras();
        isEdit = bundle.getBoolean("edit");
        djjhs = bundle.getParcelableArrayList(Contans.KEY_DJJHRWQY);
        item = bundle.getInt(Contans.KEY_ITEM);
        headView = View.inflate(context, R.layout.dlb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
        setListAdapter();

        //创建filter
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contans.ACTION_YULONE);
        //注册广播
        registerReceiver(myReceiver, filter);
    }

    private void setListAdapter() {

        for (QYDDATABean rw : djjhs) {
            DlbInfo info = new DlbInfo();
            info.setCjjg(rw.getCJJG());
            info.setDian(rw.getBJMC() + " (" + rw.getDMC() + ")");
            info.setStatu(rw.isChecked());
            infos.add(info);
        }

        adapter = new DlbAdapter(context, infos);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(YulActivity.this, SbxdjcjsbActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, djjhs);
                bundle.putBoolean("edit", true);
                bundle.putInt(Contans.KEY_ITEM, position - 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //更新list
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册广播（注销）
        unregisterReceiver(myReceiver);
    }
}
