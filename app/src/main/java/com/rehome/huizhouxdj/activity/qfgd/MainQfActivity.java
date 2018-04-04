package com.rehome.huizhouxdj.activity.qfgd;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihong on 2018/3/23.
 */

public class MainQfActivity extends BaseActivity {

    private GridView gv;
    private String[] str = {"备品备件查询"};
    private int[] imageId = {R.mipmap.icon13};
    private int[] colors = {R.drawable.radius_c7};
    private boolean flag = true;

    @Override
    public int getContentViewID() {
        return R.layout.activity_ydckgl;
    }

    protected void initView() {

        gv = (GridView) findViewById(R.id.gv);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void initData() {

        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));
        setTitle("Q4工单");
        setBack();
        List<Integer> item = new ArrayList<>();
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(new GridViewAdapter(this, getGridViewData(), item, flag));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        searchDialog();
                        break;
                }
            }
        });
    }

    private void searchDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.qcdialog_edt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(nameView);
        final EditText wzmc_edit = (EditText) nameView.findViewById(R.id.wzmc_edit);
        final EditText wzbm_edit = (EditText) nameView.findViewById(R.id.wzbm_edit);
        final EditText ckh_edit = (EditText) nameView.findViewById(R.id.ckh_edit);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("查询",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(MainQfActivity.this, bpbjinfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("etMwbm", wzbm_edit.getText().toString());
                                bundle.putString("etBzmc", wzmc_edit.getText().toString());
                                bundle.putString("etCkh", ckh_edit.getText().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private List<GridViewBean> getGridViewData() {

        List<GridViewBean> datas = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            GridViewBean bean = new GridViewBean();
            bean.setTitle(str[i]);
            bean.setBackgroup(colors[i]);
            bean.setImageid(imageId[i]);
            bean.setShow(false);
            datas.add(bean);
        }
        return datas;
    }
}
