package com.rehome.huizhouxdj.activity.sbxj;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.bean.SetxjSbModel;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity3;
import com.rehome.huizhouxdj.weight.AutoRadioGroup;
import com.rehome.huizhouxdj.weight.NoscrollViewPager;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ruihong on 2017/11/30.
 */

public class SbxjcjsbActivity extends BaseActivity3 implements View.OnClickListener {

    public static int Req = 101;
    @BindView(R.id.vp)
    NoscrollViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    AutoRadioGroup rg;
    @BindView(R.id.btn_save_next)
    Button btnSaveNext;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.btn_last)
    Button btnLast;

    private MyFragmentAdapter adapter;
    private List<Fragment> list;
    private XJCJFragment xjcjFragment;
    private int item = 0;
    private boolean isEdit = false;
    private int index = 0;
    private int itemposition;
    private String LX, LXResult;

    //新数据
    private ArrayList<XSJJHDataBean> xsjjhDataBeanArrayList;
    private ArrayList<SetxjSbModel> setSbModelList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sbxjcjsb;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:


                Intent intent2 = new Intent(SbxjcjsbActivity.this, SbxjcjsbActivity.class);
                intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                setResult(RESULT_OK, intent2);
                finish();

                break;
            case R.id.tv_right://这里
                Bundle bundle = new Bundle();
                Intent intent = new Intent(SbxjcjsbActivity.this, XjSbListActivity.class);
                bundle.putParcelableArrayList("xsjjhDataBeanArrayList", xsjjhDataBeanArrayList);
                bundle.putBoolean("edit", isEdit);
                bundle.putInt(Contans.KEY_ITEM, 0);
                bundle.putInt("itemposition", itemposition);
                bundle.putString("LX", LX);
                bundle.putString("LXResult", LXResult);
                bundle.putInt("from", 1);
                intent.putExtras(bundle);
                startActivityForResult(intent, Req);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        if (requestCode == Req) {

            if (data != null) {

                setSbModelList = data.getParcelableArrayListExtra("setSbModelList");

                Log.e("123", "size=" + setSbModelList);
                //更新数据源
                for (int i = 0; i < setSbModelList.size(); i++) {
                    for (int j = 0; j < xsjjhDataBeanArrayList.size(); j++) {
                        if (xsjjhDataBeanArrayList.get(j).getSbid().equals(setSbModelList.get(i).getSbId())) {
                            xsjjhDataBeanArrayList.get(j).setCJJG(setSbModelList.get(i).getValue());
                            xsjjhDataBeanArrayList.get(j).setChecked(setSbModelList.get(i).getStatu());
                        }
                    }
                }

                //更新当前的检查配件状态
                if (xjcjFragment != null) {
                    xjcjFragment.updateState(xsjjhDataBeanArrayList.get(index).getCJJG());
                    xjcjFragment.updatecheck(xsjjhDataBeanArrayList.get(index).isChecked());
                }
            }
        }


    }

    public void initData() {

        initToolbar("设备巡检", "修改设备状态", this);

        Bundle bundle = SbxjcjsbActivity.this.getIntent().getExtras();
        if (bundle != null) {
            isEdit = bundle.getBoolean("edit");
            xsjjhDataBeanArrayList = bundle.getParcelableArrayList("xsjjhDataBeanArrayList");
            index = bundle.getInt(Contans.KEY_ITEM);
            item = bundle.getInt(Contans.KEY_ITEM) + 1;
            itemposition = bundle.getInt("itemposition");
            LX = bundle.getString("LX");
            LXResult = bundle.getString("LXResult");
        }
        ButterKnife.bind(this);

        if (!isEdit) {
            btnSaveNext.setVisibility(View.GONE);
        }
        list = new ArrayList<>();
        if (xsjjhDataBeanArrayList.size() != 0) {
            xjcjFragment = XJCJFragment.newInstance(isEdit, xsjjhDataBeanArrayList.get(index), xsjjhDataBeanArrayList.size(), index + 1);
        } else {
            xjcjFragment = XJCJFragment.newInstance(isEdit, new XSJJHDataBean(), 0, 0);

        }
        list.add(xjcjFragment);

        adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.rb1:
                        rb1.setTextColor(Color.WHITE);
                        rb2.setTextColor(Color.GRAY);
                        rb3.setTextColor(Color.GRAY);
                        vp.setCurrentItem(0, false);
                        break;
                    case R.id.rb2:
                        rb1.setTextColor(Color.GRAY);
                        rb2.setTextColor(Color.WHITE);
                        rb3.setTextColor(Color.GRAY);
                        vp.setCurrentItem(1, false);
                        break;
                    case R.id.rb3:
                        rb1.setTextColor(Color.GRAY);
                        rb2.setTextColor(Color.GRAY);
                        rb3.setTextColor(Color.WHITE);
                        vp.setCurrentItem(2, false);
                        break;
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.btn_last, R.id.btn_next, R.id.btn_save_next, R.id.btn_exit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                --item;
                if (item == 0) {
                    btnLast.setEnabled(false);
                    item++;
                    showToast("当前为第一条");
                } else {
                    xjcjFragment.updata(xsjjhDataBeanArrayList.get(item - 1), item, xsjjhDataBeanArrayList.size());

                }
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.btn_exit:
                if (isEdit) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("你确定要退出吗?");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isEdit = true;

                            //点击退出，处理退出的逻辑
                            if (xsjjhDataBeanArrayList.size() != 0) {

                                if (!xjcjFragment.getCJJG().equals("")) {
                                    ContentValues values = new ContentValues();
                                    values.put("checked", true);
                                    values.put("CJJG", xjcjFragment.getCJJG());

                                    Log.e("xjcjFragmentgetCJJG()", xjcjFragment.getCJJG());

                                    values.put("DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                    //更新数据库数据
                                    int i = DataSupport.updateAll(XSJJHDataBean.class, values, "scid = ? and dbh = ? ",
                                            xsjjhDataBeanArrayList.get(item - 1).getScid(), xsjjhDataBeanArrayList.get(item - 1).getDbh());
                                    if (i != 0) {
                                        showToast("保存成功");
                                        //更新编辑的内容
                                        updateItem(xjcjFragment.getCJJG(), item - 1);


                                        Intent intent2 = new Intent(SbxjcjsbActivity.this, XjSbListActivity.class);
                                        intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                                        setResult(RESULT_OK, intent2);
                                        finish();
                                    }
                                } else {
                                    showToast("你没有数据采集结果");
                                }

                            }
                            Intent intent2 = new Intent(SbxjcjsbActivity.this, XjSbListActivity.class);
                            intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                            setResult(RESULT_OK, intent2);
                            finish();
                        }
                    });
                    builder.create().show();
                } else {
                    Intent intent2 = new Intent(SbxjcjsbActivity.this, XjSbListActivity.class);
                    intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                    setResult(RESULT_OK, intent2);
                    finish();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void next() {
        if (xsjjhDataBeanArrayList.size() != 0) {
            if (!btnLast.isEnabled()) {
                btnLast.setEnabled(true);
            }
            if (isEdit) {

                if (!xjcjFragment.getCJJG().equals("")) {

                    ContentValues values = new ContentValues();

                    values.put("checked", true);

                    values.put("CJJG", xjcjFragment.getCJJG());

                    Log.e("xjcjFragmentgetCJJG()", xjcjFragment.getCJJG());

                    values.put("DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                    //更新数据库数据
                    int i = DataSupport.updateAll(XSJJHDataBean.class, values, "scid = ? and dbh = ?  ",
                            xsjjhDataBeanArrayList.get(item - 1).getScid(), xsjjhDataBeanArrayList.get(item - 1).getDbh());
                    if (i != 0) {
                        xsjjhDataBeanArrayList.get(item - 1).setChecked(true);
                        xsjjhDataBeanArrayList.get(item - 1).setCJJG(xjcjFragment.getCJJG());
                        showToast("保存成功");
                        //更新编辑的内容
                        updateItem(xjcjFragment.getCJJG(), item - 1);
                    }
                } else {
                    showToast("你没有数据采集结果");
                }

                ++item;
                if (item > xsjjhDataBeanArrayList.size()) {
                    item--;
                    xjcjFragment.updata(xsjjhDataBeanArrayList.get(item - 1), item, xsjjhDataBeanArrayList.size());
                    //showToast("到底了");
                } else {
                    xjcjFragment.updata(xsjjhDataBeanArrayList.get(item - 1), item, xsjjhDataBeanArrayList.size());

                }
            } else {
                if (xsjjhDataBeanArrayList.size() != 0) {
                    ++item;
                    if (!btnLast.isEnabled()) {
                        btnLast.setEnabled(true);
                    }
                    if (item > xsjjhDataBeanArrayList.size()) {
                        item--;
                        //showToast("到底了");
                    } else {
                        xjcjFragment.updata(xsjjhDataBeanArrayList.get(item - 1), item, xsjjhDataBeanArrayList.size());

                    }
                }
            }
        }
    }

    public int getItem() {
        return item - 1;
    }

    /**
     * 更新点检item
     *
     * @param name     当前编辑的item 内容
     * @param position 当前item index
     */
    private void updateItem(String name, int position) {
        Intent intent = new Intent(Contans.ACTION_YULONE);
        intent.putExtra(Contans.KEY_POSITION, position);
        intent.putExtra(Contans.KEY_NAME, name);
        //发送广播
        sendBroadcast(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent2 = new Intent(SbxjcjsbActivity.this, XjSbListActivity.class);
            intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
            setResult(RESULT_OK, intent2);
            finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
