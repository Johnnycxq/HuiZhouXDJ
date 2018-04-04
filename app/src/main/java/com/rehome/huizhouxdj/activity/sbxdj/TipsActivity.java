package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;

import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends BaseActivity2 implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private ArrayList<QYAQFXDATABean> qyaqfxdataBeanArrayList;
    private ArrayList<QYDDATABean> qyddataBeanArrayList;
    private boolean isEdit = true;
    private int item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tips;
    }

    @Override
    public void initView() {
    }


    public void initData() {
        initToolbar("风险提示查看", "进入区域", this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);


        Bundle bundle = TipsActivity.this.getIntent().getExtras();
        qyaqfxdataBeanArrayList = new ArrayList<>();
        qyddataBeanArrayList = new ArrayList<>();
        qyaqfxdataBeanArrayList = bundle.getParcelableArrayList("QYFXTS");
        qyddataBeanArrayList = bundle.getParcelableArrayList(Contans.KEY_DJJHRWQY);
        isEdit = bundle.getBoolean("edit");
        item = bundle.getInt(Contans.KEY_ITEM);


        initViewPager();

    }

    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("高危风险");
        titles.add("缺陷工单");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        fragments.add(new FXFragment());
        fragments.add(new QxgdFragment());
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(TipsActivity.this, YulActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanArrayList);
                bundle.putBoolean("edit", isEdit);
                bundle.putInt(Contans.KEY_ITEM, item);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}

