package com.rehome.huizhouxdj.activity.sbxj;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.sbxdj.FragmentAdapter;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备巡点检管理--现场巡检
 */
public class XjMainActivity extends BaseActivity {


    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> list;
    private MyFragmentAdapter adapter;

    private XzxsjhFragment xzxsjhfragment;
    private ScxsjhFragment scxsjhfragmet;

    @Override
    public int getContentViewID() {
        return R.layout.activity_mainxj;
    }

    protected void initView() {

    }

    public void initData() {
        setTitle("巡检数据管理");

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setBack();
        xzxsjhfragment = XzxsjhFragment.newInstance();
        scxsjhfragmet = ScxsjhFragment.newInstance();
        list = new ArrayList<>();
        list.add(xzxsjhfragment);
        list.add(scxsjhfragmet);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        initViewPager();

    }

    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("数据下载");
        titles.add("数据上传");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        fragments.add(new XzxsjhFragment());
        fragments.add(new ScxsjhFragment());
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapteradapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }


    public void updataDjdsc() {
//        scxsjhfragmet.getDataInSQL();
    }
}