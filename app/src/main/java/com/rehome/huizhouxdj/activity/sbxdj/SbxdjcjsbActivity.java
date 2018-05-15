package com.rehome.huizhouxdj.activity.sbxdj;

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

import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.bean.SetSbModel;
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

public class SbxdjcjsbActivity extends BaseActivity3 implements View.OnClickListener {

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
    private CJFragment cj;
    private FfFragment ff;
    private BzFragment bz;
    private int item = 0;
    private boolean isEdit = false;
    private int index = 0;
    private int itemposition;
    private String LX, LXResult;
    //新数据
    private ArrayList<QYDDATABean> qyddataBeanArrayList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_sbxdjcjsb;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:


                Intent intent2 = new Intent(SbxdjcjsbActivity.this, SbxdjcjsbActivity.class);
                intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                setResult(RESULT_OK, intent2);
                finish();

                break;
            case R.id.tv_right://这里
                Bundle bundle = new Bundle();
                Intent intent = new Intent(SbxdjcjsbActivity.this, SdjSbListActivity.class);
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList);
                bundle.putBoolean("edit", true);
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

    private ArrayList<SetSbModel> setSbModelList;

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
                    for (int j = 0; j < qyddataBeanArrayList.size(); j++) {
                        if (qyddataBeanArrayList.get(j).getSBID().equals(setSbModelList.get(i).getSbId())) {
                            qyddataBeanArrayList.get(j).setCJJG(setSbModelList.get(i).getValue());
                            qyddataBeanArrayList.get(j).setChecked(setSbModelList.get(i).getStatu());
                        }
                    }
                }

                //更新当前的检查配件状态
                if (cj != null) {
                    cj.updateState(qyddataBeanArrayList.get(index).getCJJG());
                    cj.updatecheck(qyddataBeanArrayList.get(index).isChecked());
                }
            }
        }


    }

    public void initData() {

        initToolbar("设备点检", "修改设备状态", this);

        Bundle bundle = SbxdjcjsbActivity.this.getIntent().getExtras();
        if (bundle != null) {
            isEdit = bundle.getBoolean("edit");
            qyddataBeanArrayList = bundle.getParcelableArrayList(Contans.KEY_DJJHRWQY);
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
        if (qyddataBeanArrayList.size() != 0) {
            cj = CJFragment.newInstance(isEdit, qyddataBeanArrayList.get(index), qyddataBeanArrayList.size(), index + 1);
            ff = FfFragment.newInstance(isEdit, qyddataBeanArrayList.get(index).getJCFS());
            bz = BzFragment.newInstance(isEdit, qyddataBeanArrayList.get(index).getBZZ());
        } else {
            cj = CJFragment.newInstance(isEdit, new QYDDATABean(), 0, 0);
            ff = FfFragment.newInstance(isEdit, "");
            bz = BzFragment.newInstance(isEdit, "");
        }
        list.add(cj);
        list.add(ff);
        list.add(bz);
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
                    cj.updata(qyddataBeanArrayList.get(item - 1), item, qyddataBeanArrayList.size());
                    bz.updata(qyddataBeanArrayList.get(item - 1).getBZZ());
                    ff.update(qyddataBeanArrayList.get(item - 1).getJCFS());
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
                            if (qyddataBeanArrayList.size() != 0) {

                                if (!cj.getCJJG().equals("")) {
                                    ContentValues values = new ContentValues();
                                    values.put("checked", true);
                                    values.put("CJJG", cj.getCJJG());
                                    values.put("DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                    //更新数据库数据
                                    int i = DataSupport.updateAll(QYDDATABean.class, values, "SCID = ? and DID = ? ",
                                            qyddataBeanArrayList.get(item - 1).getSCID(), qyddataBeanArrayList.get(item - 1).getDID());
                                    if (i != 0) {
                                        showToast("保存成功");
                                        //更新编辑的内容
                                        updateItem(cj.getCJJG(), item - 1);


                                        Intent intent2 = new Intent(SbxdjcjsbActivity.this, SbxdjcjsbActivity.class);
                                        intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                                        setResult(RESULT_OK, intent2);
                                        finish();
                                    }
                                } else {
                                    showToast("你没有数据采集结果");
                                }

                            }

                            Intent intent2 = new Intent(SbxdjcjsbActivity.this, SbxdjcjsbActivity.class);
                            intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                            setResult(RESULT_OK, intent2);
                            finish();
////                            ControllerActivity.getAppManager().finishActivity(Sd.class);
//                            finish();
                        }
                    });
                    builder.create().show();
                } else {
                    Intent intent2 = new Intent(SbxdjcjsbActivity.this, SbxdjcjsbActivity.class);
                    intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
                    setResult(RESULT_OK, intent2);
                    finish();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void next() {
        if (qyddataBeanArrayList.size() != 0) {
            if (!btnLast.isEnabled()) {
                btnLast.setEnabled(true);
            }
            if (isEdit) {

                if (!cj.getCJJG().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("checked", true);
                    values.put("CJJG", cj.getCJJG());
                    values.put("DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                    //更新数据库数据
                    int i = DataSupport.updateAll(QYDDATABean.class, values, "SCID = ? and DID = ? ",
                            qyddataBeanArrayList.get(item - 1).getSCID(), qyddataBeanArrayList.get(item - 1).getDID());
                    if (i != 0) {
                        qyddataBeanArrayList.get(item - 1).setChecked(true);
                        qyddataBeanArrayList.get(item - 1).setCJJG(cj.getCJJG());
                        showToast("保存成功");
                        //更新编辑的内容
                        updateItem(cj.getCJJG(), item - 1);
                    }
                } else {
                    showToast("你没有数据采集结果");
                }

                ++item;
                if (item > qyddataBeanArrayList.size()) {
                    item--;
                    cj.updata(qyddataBeanArrayList.get(item - 1), item, qyddataBeanArrayList.size());
                    //showToast("到底了");
                } else {
                    cj.updata(qyddataBeanArrayList.get(item - 1), item, qyddataBeanArrayList.size());
                    bz.updata(qyddataBeanArrayList.get(item - 1).getBZZ());
                    ff.update(qyddataBeanArrayList.get(item - 1).getJCFS());
                }
            } else {
                if (qyddataBeanArrayList.size() != 0) {
                    ++item;
                    if (!btnLast.isEnabled()) {
                        btnLast.setEnabled(true);
                    }
                    if (item > qyddataBeanArrayList.size()) {
                        item--;
                        //showToast("到底了");
                    } else {
                        cj.updata(qyddataBeanArrayList.get(item - 1), item, qyddataBeanArrayList.size());
                        bz.updata(qyddataBeanArrayList.get(item - 1).getBZZ());
                        ff.update(qyddataBeanArrayList.get(item - 1).getJCFS());
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
            Intent intent2 = new Intent(SbxdjcjsbActivity.this, SbxdjcjsbActivity.class);
            intent2.putParcelableArrayListExtra("setSbModelList", setSbModelList);
            setResult(RESULT_OK, intent2);
            finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
