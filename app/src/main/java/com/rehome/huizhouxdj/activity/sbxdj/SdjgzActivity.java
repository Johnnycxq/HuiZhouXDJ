package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.DBModel.DjjhRwQyList;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.bean.DjAjhGzInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.UiUtlis;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

/**
 * 设备巡点检管理-点检工作
 */
public class SdjgzActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sm)
    Button btn_sm;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;


    private List<DjAjhGzInfo> list;
    private Map<String, ArrayList<DjjhRwQy>> map;

    private List<DjjhRwQyList> rwqylist;
    private List<DjjhRwQy> rwqys;

    private List<String> qys;
    private List<String> qyAll;//全部，包括相同的

    private CommonAdapter<DjAjhGzInfo> adapter;

    private String ewm;//二维码或者条形码

    @Override
    public int getContentViewID() {
        return R.layout.activity_sdjgz;
    }

    protected void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.djajhgz_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
    }

    public void initData() {

        initNFC();

        rwqylist = new ArrayList<>();
        rwqys = new ArrayList<>();
        map = new HashMap<>();
        list = new ArrayList<>();
        qys = new ArrayList<>();
        qyAll = new ArrayList<>();

        setTitle("工作");
        setBack();

        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() != 0) {
                    Intent intent = new Intent(SdjgzActivity.this, MipcaActivityCapture.class);
                    startActivityForResult(intent, 1);
                } else {
                    showToast("你还没有计划");
                }
            }
        });
        getDataInSqlite();
        setListData();
    }

    //加载数据库中已经下载的计划
    private void getDataInSqlite() {

        list.clear();
        map.clear();
        rwqylist.clear();
        rwqys.clear();
        qyAll.clear();
        qys.clear();
        Cursor cursor = DataSupport.findBySQL("select * from djjhrwqy group by areacode");
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("areacode");
            qyAll.add(cursor.getString(index));
        }
        cursor.close();
        Cursor cursor1 = DataSupport.findBySQL("select * from ajhxzrwqy group by areacode");
        while (cursor1.moveToNext()) {
            int index = cursor1.getColumnIndex("areacode");
            qyAll.add(cursor1.getString(index));
        }
        cursor1.close();
        //去除相同数据
        Set<String> set = new HashSet<>();
        set.addAll(qyAll);
        qys.addAll(set);
        int i = 0;
        for (String code : qys) {
            int djyj = 0;
            DjAjhGzInfo info = new DjAjhGzInfo();
            ArrayList<DjjhRwQy> djjhs = new ArrayList<>();
            djjhs.addAll(DataSupport.where("AREACODE = ?", code).find(DjjhRwQy.class));

            map.put(code, djjhs);

            for (DjjhRwQy djjh : djjhs) {
                info.setQy(djjh.getMEAAREA());
                if (djjh.isChecked()) {
                    djyj++;
                }

            }
            info.setXh(++i + "");
            info.setDjrw(djyj + "/" + djjhs.size());
            list.add(info);
        }

    }

    private void setListData() {
        if (adapter == null) {
            adapter = new CommonAdapter<DjAjhGzInfo>(context, R.layout.djajhgz_item, list) {
                @Override
                protected void convert(ViewHolder viewHolder, DjAjhGzInfo item, int position) {
                    viewHolder.setText(R.id.tv_xh, item.getXh() + "");
                    viewHolder.setText(R.id.tv_qy, item.getQy());
                    viewHolder.setText(R.id.tv_djrw, item.getDjrw());
                    viewHolder.setText(R.id.tv_ajhrw, item.getAjhrw());
                }
            };
            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {


                    if (map.get(qys.get(postion - 1)).size() != 0) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(false);
                        builder.setTitle("系统提示");
                        builder.setMessage("是否要浏览该区域下的工作内容");
                        builder.setNegativeButton(UiUtlis.getString(context, R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SdjgzActivity.this, YulActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, map.get(qys.get(postion - 1)));
                                bundle.putBoolean("edit", true);
                                bundle.putInt(Contans.KEY_ITEM, 0);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
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
//                    Intent intent = new Intent(SdjgzActivity.this, SdlbActivity.class);
//                    intent.putExtra(Contans.NFCOREWM, true);
//                    intent.putExtra(Contans.KEY_BQBM, ewm);
//                    intent.putExtra(Contans.KEY_FLAG, Contans.DLB);
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
//        if (list.size() != 0) {
//            Intent intent = new Intent(SdjgzActivity.this, SdlbActivity.class);
//            intent.putExtra(Contans.NFCOREWM, false);
//            intent.putExtra(Contans.KEY_BQBM, result);
//            intent.putExtra(Contans.KEY_FLAG, Contans.DLB);
//            startActivity(intent);
//        } else {
//            showToast("你还没有计划");
//        }
    }
}