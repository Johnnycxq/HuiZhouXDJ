package com.rehome.huizhouxdj.activity.sbxdj;

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
 * 设备巡点检管理-已上传
 */
public class YscFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;

    private View headView;
    private DlbAdapter adapter;

    private String num;
    private boolean nfcorewm;

    private View head;
    private SdlbActivity mActivity;
    private List<DlbInfo> list;

    private List<DjjhRwQy> rwqys;

    public YscFragment() {
    }

    public static YscFragment newInstance(ArrayList<DjjhRwQy> list, String num, boolean nfcorewm) {
        YscFragment fragment = new YscFragment();
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
        return R.layout.fragment_ysc;
    }

    @Override
    protected void initView() {
        mActivity = (SdlbActivity) getActivity();
    }

    public void initData() {
        if (mActivity.getFlag() == Contans.DLB) {
            initDLB();
        } else {
            initZKD();
        }
    }

    /**
     * 质控点
     */
    private void initZKD() {
    }

    /**
     * 点检点
     */
    private void initDLB() {

        headView = View.inflate(context, R.layout.dlb_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
        list = new ArrayList<>();

        initDlbinfo();

        adapter = new DlbAdapter(context, list);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
