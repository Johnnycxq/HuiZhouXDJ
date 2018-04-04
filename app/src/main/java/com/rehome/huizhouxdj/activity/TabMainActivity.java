package com.rehome.huizhouxdj.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.xj.MainFragment;
import com.rehome.huizhouxdj.utils.AutoToolbar;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.CanBanScrollViewPager;
import com.rehome.huizhouxdj.weight.AutoRadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/2.
 */

public class TabMainActivity extends BaseActivity {


    @BindView(R.id.vp)
    CanBanScrollViewPager vp;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_contact)
    RadioButton rbContact;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg)
    AutoRadioGroup rg;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;


    private List<Fragment> fragments = new ArrayList<>();

    private FragmentPagerAdapter adapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_tabmain;
    }

    @Override
    protected void initView() {

        toolbar.setVisibility(View.GONE);


        fragments.add(MainFragment.getInstance());
        fragments.add(ContactFragment.getInstance());
        fragments.add(MineFragment.getInstance());



        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                initTextColor();

                switch (position) {
                    case 0:
                        title.setText("运行");
                        vp.setCurrentItem(0, false);
                        rbHome.setTextColor(Color.parseColor("#0099ff"));
                        rbHome.setChecked(true);
                        break;

                    case 1:
                        title.setText("通讯录");
                        vp.setCurrentItem(1, false);
                        rbContact.setTextColor(Color.parseColor("#0099ff"));
                        rbContact.setChecked(true);
//                        if (ContactFragment.getInstance().datas != null && ContactFragment.getInstance().datas.size() == 0) {
//                            ContactFragment.getInstance().getDatas();
//                        }

                        break;
                    case 2:
                        title.setText("我的");
                        vp.setCurrentItem(2, false);
                        rbMine.setTextColor(Color.parseColor("#0099ff"));
                        rbMine.setChecked(true);

                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setBackgroundColor(getResources().getColor(R.color.white));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                initTextColor();
                switch (i) {
                    case R.id.rb_home:
                        title.setText("运行");
                        vp.setCurrentItem(0, false);
                        rbHome.setTextColor(Color.parseColor("#0099ff"));
                        break;
                    case R.id.rb_contact:
                        title.setText("通讯录");
                        vp.setCurrentItem(1, false);
                        rbContact.setTextColor(Color.parseColor("#0099ff"));
                        break;
                    case R.id.rb_mine:
                        title.setText("我的");
                        vp.setCurrentItem(2, false);
                        rbMine.setTextColor(Color.parseColor("#0099ff"));
                        break;
                }
            }
        });
    }

    private void initTextColor() {
        rbHome.setTextColor(Color.parseColor("#abadbb"));
        rbContact.setTextColor(Color.parseColor("#abadbb"));
        rbMine.setTextColor(Color.parseColor("#abadbb"));
    }


    public void initData() {
        title.setText("首页");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
