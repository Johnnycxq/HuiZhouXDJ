package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.bean.SetSbModel;
import com.rehome.huizhouxdj.bean.sbInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity3;
import com.rehome.huizhouxdj.weight.ListDialog;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 设备巡点检管理-点检工作
 */
public class SdjSbListActivity extends BaseActivity3 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;
    private CommonAdapter<sbInfo> adapter;
    private ArrayList<QYAQFXDATABean> qyaqfxdataBeanArrayList;
    private ArrayList<QYDDATABean> qyddataBeanArrayList = new ArrayList<>();
    private boolean isEdit = true;
    private int item;
    private List<sbInfo> infos = new ArrayList<>();
    Intent intent;
    private int pos = -1;//点击的设备item
    private String state, LX, LXResult;
    private int itemposition;

    private int from;//0-来自工作页面；1-采集页面

    @Override
    public int getLayoutId() {
        return R.layout.activity_sblist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                if (from == 0) {//工作页面
                    finish();
                } else if (from == 1) {//采集页面
                    Intent intent = new Intent(SdjSbListActivity.this, SbxdjcjsbActivity.class);
                    //传list《设备号，内容》
                    intent.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.tv_right:
                intent = new Intent(SdjSbListActivity.this, TipsActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList);
                bundle2.putParcelableArrayList("QYFXTS", qyaqfxdataBeanArrayList);
                bundle2.putBoolean("edit", isEdit);
                bundle2.putInt(Contans.KEY_ITEM, 0);
                bundle2.putInt("itemposition", itemposition);
                bundle2.putString("LX", LX);
                bundle2.putString("LXResult", LXResult);
                intent.putExtras(bundle2);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.djajhsb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
    }

    public void initData() {

        initNFC();


        Bundle bundle = SdjSbListActivity.this.getIntent().getExtras();

        qyaqfxdataBeanArrayList = new ArrayList<>();
        qyddataBeanArrayList = new ArrayList<>();


        qyaqfxdataBeanArrayList = bundle.getParcelableArrayList("QYFXTS");
        qyddataBeanArrayList = bundle.getParcelableArrayList(Contans.KEY_DJJHRWQY);
        isEdit = bundle.getBoolean("edit");
        item = bundle.getInt(Contans.KEY_ITEM);
        itemposition = bundle.getInt("itemposition");
        LX = bundle.getString("LX");
        LXResult = bundle.getString("LXResult");
        from = bundle.getInt("from");

        if (from == 0) {
            initToolbar("当前设备", "点检内容", this);
        } else if (from == 1) {
            initToolbar("当前设备", "", this);
        }


        setListData();


    }

    private ArrayList<SetSbModel> setSbModelList = new ArrayList<>();

    private void setListData() {

        Map<String, sbInfo> maps = new HashMap<String, sbInfo>();
        for (QYDDATABean rw : qyddataBeanArrayList) {
            sbInfo sbinfo = new sbInfo();
            sbinfo.setSbmc(rw.getSBMC());
            sbinfo.setSbid(rw.getSBID());
            sbinfo.setSbstate(rw.getSBMCSTATE());
            String Key = rw.getSBID() + "_" + rw.getSBMC();
            sbInfo data = maps.get(Key);
            if (data == null) {
                maps.put(Key, sbinfo);
            }
        }
        infos.addAll(maps.values());

        if (adapter == null) {
            adapter = new CommonAdapter<sbInfo>(context, R.layout.djajhsb_item, infos) {
                @Override
                protected void convert(ViewHolder viewHolder, sbInfo item, int position) {

                    viewHolder.setText(R.id.tv_sbmc, item.getSbmc() + "");

                    if (item.getSbstate() == null) {

                        viewHolder.setText(R.id.tv_sbstate, " ");

                    } else {
                        viewHolder.setText(R.id.tv_sbstate, item.getSbstate() + "");

                    }

                }
            };
            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);

            if (isEdit) {


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                        //赋值当前点击的item：从1开始
                        pos = postion;
                        List<String> datas = new ArrayList<String>();
                        datas.add("已停用");
//                        datas.add("大小修");

                        ListDialog dialog = new ListDialog(context, datas, new ListDialog.ListDialogListener() {
                            @Override
                            public void selectText(String text, int position) {
                                state = text;
                                ContentValues values = new ContentValues();

                                values.put("CJJG", text);

                                values.put("SBMCSTATE", text);

                                if (text.equals("已停用")) {

                                    values.put("checked", true);

                                    values.put("SBMCSTATEVALUE", "3");

                                }

//                                else if (text.equals("大小修")) {
//
//                                    values.put("checked", true);
//
//                                    values.put("SBMCSTATEVALUE", "4");
//
//                                }

                                int i = DataSupport.updateAll(QYDDATABean.class, values, "SBID = ? ", infos.get(postion - 1).getSbid());

                                if (i != 0) {
                                    showToast("修改设备状态成功");
//                                    finish();
                                } else {
                                    showToast("修改设备状态失败");
//                                    finish();
                                }

                            }

                        });
                        dialog.setTvTitle("选择设备状态");
                        dialog.show();

                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if (pos != -1) {
                                    infos.get(pos - 1).setSbstate(state);
                                    adapter.notifyDataSetChanged();
                                    //填充选择的数据
                                    SetSbModel setSbModel = new SetSbModel();
                                    setSbModel.setSbId(infos.get(pos - 1).getSbid());
                                    setSbModel.setValue(state);
                                    setSbModel.setStatu(true);
                                    setSbModelList.add(setSbModel);
                                }
                            }
                        });


//                    Intent intent = new Intent(SdjSbListActivity.this, TipsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList);
//                    bundle.putParcelableArrayList("QYFXTS", qyaqfxdataBeanArrayList);
//                    bundle.putBoolean("edit", false);
//                    bundle.putInt(Contans.KEY_ITEM, 0);
//                    intent.putExtras(bundle);
//                    startActivity(intent);

                    }
                });
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            showToast("需要扫描二维码或者贴近NFC才能进入");
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (from == 0) {//工作页面
                finish();
            } else if (from == 1) {//采集页面
                Intent intent = new Intent(SdjSbListActivity.this, SbxdjcjsbActivity.class);
                intent.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}