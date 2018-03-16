package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.contans.Contans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备巡点检管理-已检未上传 or 已完成未上传
 */
public class YjwscFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;

    private SdlbActivity mActivity;

    private ArrayList<DjjhRwQy> rwqys;

    private String num;
    private boolean nfcorewm;

    private List<DlbInfo> list;
    private View headView;
    private DlbAdapter adapter;

    public YjwscFragment() {
    }

    public static YjwscFragment newInstance(ArrayList<DjjhRwQy> list, String num, boolean nfcorewm) {
        YjwscFragment fragment = new YjwscFragment();
        Bundle bundle = new Bundle();
        bundle.putString("num", num);
        bundle.putBoolean("nfcorewm", nfcorewm);
        bundle.putParcelableArrayList("list", list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            rwqys = bundle.getParcelableArrayList("list");
            num = bundle.getString("num");
            nfcorewm = bundle.getBoolean("nfcorewm");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_yjwsc;
    }

    @Override
    protected void initView() {
        mActivity = (SdlbActivity) getActivity();
    }

    public void initData() {

        if (mActivity.getFlag() == Contans.DLB) {

            initDlb();
        } else {

            initZkd();
        }
    }

    /**
     * 质控点
     */
    private void initZkd() {
    }

    /**
     * 点检点
     */
    private void initDlb() {


        list = new ArrayList<>();
        initDlbinfo();
        adapter = new DlbAdapter(context, list);
        headView = View.inflate(context, R.layout.dlb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SbxdjcjsbActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra(Contans.NFCOREWM, nfcorewm);
                intent.putExtra(Contans.KEY_BQBM, num);
                intent.putExtra(Contans.KEY_ITEM, i - 1);
                intent.putParcelableArrayListExtra(Contans.KEY_DJJHRWQY, rwqys);
                startActivity(intent);
            }
        });
    }

    public void setListadapter() {
        this.list.clear();
        initDlbinfo();
        adapter.notifyDataSetChanged();
    }

    private void initDlbinfo() {
        for (int i = 0; i < rwqys.size(); i++) {
            DlbInfo info = new DlbInfo();
            info.setXh(i + 1);
            info.setDian(rwqys.get(i).getPOINTNAME() + "(" + rwqys.get(i).getDESCRIPTION() + ")");
            info.setStatu(rwqys.get(i).isChecked());
            info.setCjjg(rwqys.get(i).getCJJG());
            list.add(info);
        }

    }
}
