package com.rehome.huizhouxdj.activity.sbxdj;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.weight.AutoRadioGroup;
import com.rehome.huizhouxdj.weight.NoscrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备巡点检管理--现场点检
 */
public class SxcdjActivity extends BaseActivity {

    @BindView(R.id.vp_centent)
    NoscrollViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    AutoRadioGroup rg;

    private List<Fragment> list;
    private MyFragmentAdapter adapter;

    private XzjhFragment xzjh;
    private DjdscFragment djdsc;

    @Override
    public int getContentViewID() {
        return R.layout.activity_sxcdj;
    }

    protected void initView() {

    }

    public void initData() {
        setTitle("点检下载计划");
        setBack();
        xzjh = XzjhFragment.newInstance();
        djdsc = DjdscFragment.newInstance();
        list = new ArrayList<>();
        list.add(xzjh);
        list.add(djdsc);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(adapter);


        vp.setOffscreenPageLimit(2);
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

                }
            }
        });
    }

    public void updataDjdsc() {
        djdsc.getDataInSQL();
    }
}