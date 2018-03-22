package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 巡视抄表管理
 */
public class XscbglActivity extends BaseActivity {

    @BindView(R.id.gv)
    GridView gv;

    private String[] str = {"待办任务", "历史抄表", "读取NFC标签"};
    private int[] imageId = {R.mipmap.icon_xs1, R.mipmap.icon_xs5, R.mipmap.icon11};
    private int[] colors = {R.drawable.radius_b1, R.drawable.radius_a4, R.drawable.radius_b2};
    private GridViewAdapter adapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xscbgl;
    }

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));

        setTitle("巡视抄表-" + Contans.YXCB_ZY_NAME);

        setBack();

        adapter = new GridViewAdapter(this, getGridViewData(), new ArrayList<Integer>(), true);
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(XscbglActivity.this, XsBackLogActivity.class);
                        break;
                    case 1:
                        intent = new Intent(XscbglActivity.this, XsHistoryActivity.class);
                        break;
                    case 2:
                        intent = new Intent(XscbglActivity.this, NFCInfoActivity.class);
                        break;

                }
                if (intent != null) {
                    startActivity(intent);
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
