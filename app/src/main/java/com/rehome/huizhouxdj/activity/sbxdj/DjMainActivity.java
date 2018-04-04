package com.rehome.huizhouxdj.activity.sbxdj;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备巡点检管理--现场点检
 */
public class DjMainActivity extends BaseActivity {

//    @BindView(R.id.vp_centent)
//    NoscrollViewPager vp;
//    @BindView(R.id.rb1)
//    RadioButton rb1;
//    @BindView(R.id.rb2)
//    RadioButton rb2;
//    @BindView(R.id.rb3)
//    RadioButton rb3;
//    @BindView(R.id.rg)
//    AutoRadioGroup rg;

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> list;
    private MyFragmentAdapter adapter;

    private XzjhFragment xzjh;
    private DjdscFragment djdsc;

    @Override
    public int getContentViewID() {
        return R.layout.activity_maindj;
    }

    protected void initView() {

    }

    public void initData() {
        setTitle("点检数据管理");
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setBack();
        xzjh = XzjhFragment.newInstance();
        djdsc = DjdscFragment.newInstance();
        list = new ArrayList<>();
        list.add(xzjh);
        list.add(djdsc);
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
        fragments.add(new XzjhFragment());
        fragments.add(new DjdscFragment());
        FragmentAdapter mFragmentAdapteradapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapteradapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }

    public void updataDjdsc() {
        djdsc.getDataInSQL();
    }
}