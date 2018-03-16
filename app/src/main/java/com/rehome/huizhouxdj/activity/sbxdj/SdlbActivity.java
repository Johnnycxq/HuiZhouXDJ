package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.ContentValues;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.MyFragmentAdapter;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.weight.AutoRadioGroup;
import com.rehome.huizhouxdj.weight.NoscrollViewPager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备巡点检管理-点列表
 */
public class SdlbActivity extends BaseActivity {

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

    private List<Fragment> list;
    private MyFragmentAdapter adapter;
    private WjFragment wj;
    private YjwscFragment yjwsc;
    private YscFragment ysc;

    private ArrayList<DjjhRwQy> rwqys;
    private ArrayList<DjjhRwQy> rwwj;
    private ArrayList<DjjhRwQy> rwyjwsc;
    private ArrayList<DjjhRwQy> rwysc;

    //private String jhlx;//计划类型
    private String num = "";//二维码or条形码
    private boolean nfrorewm;//false nfc true 二维码

    private boolean aaa = false;

    private int flag = 1;

    @Override
    public int getContentViewID() {
        return R.layout.activity_sdlb;
    }

    protected void initView() {
    }

    public void initData() {

        setBack();
        num = getIntent().getExtras().getString(Contans.KEY_BQBM);
        nfrorewm = getIntent().getExtras().getBoolean(Contans.NFCOREWM);

        rwqys = new ArrayList<>();
        rwwj = new ArrayList<>();
        rwyjwsc = new ArrayList<>();
        rwysc = new ArrayList<>();
        flag = getIntent().getExtras().getInt(Contans.KEY_FLAG);

        getDataInSqlite();

        if (flag == Contans.DLB) {
            setTitle("点检任务");
            rb1.setText("未检(" + rwwj.size() + ")");
            rb2.setText("已检未上传(" + rwyjwsc.size() + ")");
            rb3.setText("已上传(" + rwysc.size() + ")");
        } else {
            setTitle("质控点列表");
            rb1.setText("未完成(0)");
            rb2.setText("已完成未上传(0)");
            rb3.setText("已上传(0)");
        }

        list = new ArrayList<>();
        wj = WjFragment.newInstance(rwwj, num, nfrorewm);
        yjwsc = YjwscFragment.newInstance(rwyjwsc, num, nfrorewm);
        ysc = YscFragment.newInstance(rwysc, num, nfrorewm);

        list.add(wj);
        list.add(yjwsc);
        list.add(ysc);

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

    public int getFlag() {
        return flag;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataInSqlite();
        rb1.setText("未检(" + rwwj.size() + ")");
        rb2.setText("已检未上传(" + rwyjwsc.size() + ")");
        rb3.setText("已上传(" + rwysc.size() + ")");
        wj.setListadapter();
        yjwsc.setListadapter();
        ysc.setListadapter();
    }

    //查询数据库数据
    private void getDataInSqlite() {
        rwqys.clear();
        rwwj.clear();
        rwyjwsc.clear();
        rwysc.clear();
        //二维码一维码
        if (nfrorewm) {
            ContentValues values = new ContentValues();
            values.put("SMFX", 1);
            DataSupport.updateAll(DjjhRwQy.class, values, "BQBM = ?", num);
//           rwyjwsc.addAll(DataSupport.where("bqbm = ? and uploaded = ? and checked = ? ", num, "0", "1").order("assetnum").order("pointnum").find(DjjhRwQy.class));
            rwqys.addAll(DataSupport.where("BQBM = ?", num).find(DjjhRwQy.class));
            rwwj.addAll(DataSupport.where("bqbm = ? and checked = ? ", num, "0").find(DjjhRwQy.class));
            rwysc.addAll(DataSupport.where("bqbm = ? and uploaded = ? ", num, "1").find(DjjhRwQy.class));
            rwyjwsc.addAll(DataSupport.where("bqbm = ? and uploaded = ? and checked = ? ", num, "0", "1").find(DjjhRwQy.class));
        } else {
            //NFC标签
            ContentValues values = new ContentValues();
            values.put("SMFX", 0);
            DataSupport.updateAll(DjjhRwQy.class, values, "NFCBH = ?", num);

            rwqys.addAll(DataSupport.where("NFCBH = ?", num).find(DjjhRwQy.class));
            rwwj.addAll(DataSupport.where("NFCBH = ? and checked = ? ", num, "0").find(DjjhRwQy.class));
            rwysc.addAll(DataSupport.where("NFCBH = ? and uploaded = ? ", num, "1").find(DjjhRwQy.class));
            rwyjwsc.addAll(DataSupport.where("NFCBH = ? and uploaded = ? and checked = ? ", num, "0", "1").find(DjjhRwQy.class));
        }
    }
}