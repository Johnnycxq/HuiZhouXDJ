package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.MainActivity;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ruihong on 2017/11/24.
 */

public class MainSbxdjglActivity extends BaseActivity {

    @BindView(R.id.gv)
    GridView gv;
    private String[] str = {"任务", "工作"};
    private int[] imageId = {R.mipmap.icon3, R.mipmap.icon6};
    private int[] colors = {R.drawable.radius_b1, R.drawable.radius_b2};
    private GridViewAdapter adapter;

    private int[] item = {2};

    @Override
    public int getContentViewID() {
        return R.layout.activity_sbxdjgl;
    }

    protected void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void initData() {

        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));
        setTitle("设备巡点检");

        if (getIntent().getExtras() != null) {
            mToolbar.setNavigationIcon(R.mipmap.back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainSbxdjglActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            setBack();

        }

        adapter = new GridViewAdapter(this, getGridViewData(), new ArrayList<Integer>(), true);
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(MainSbxdjglActivity.this, SxcdjActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainSbxdjglActivity.this, SdjgzActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
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