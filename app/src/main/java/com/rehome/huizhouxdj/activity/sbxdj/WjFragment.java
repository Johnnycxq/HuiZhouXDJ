package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.DlbAdapter;
import com.rehome.huizhouxdj.adapter.ZkdAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.DlbInfo;
import com.rehome.huizhouxdj.bean.ZkdInfo;
import com.rehome.huizhouxdj.contans.Contans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备巡点检管理-未检 or 未完成
 */

public class WjFragment extends BaseFragment {

    @BindView(R.id.tv_project)
    TextView tv_project;
    @BindView(R.id.lv)
    ListView lv;

    private View headView;
    private SdlbActivity mActivity;

    private List<DlbInfo> list;
    private DlbAdapter adapter;

    private String num;
    private boolean nfcorewm;

    private ZkdAdapter zAdapter;
    private List<ZkdInfo> zList;

    private ArrayList<DjjhRwQy> rwqys;

    public WjFragment() {
    }

    public static WjFragment newInstance(ArrayList<DjjhRwQy> lists, String num, boolean nfcorewm) {
        WjFragment fragment = new WjFragment();
        Bundle bundle = new Bundle();
        bundle.putString("num", num);
        bundle.putBoolean("nfcorewm", nfcorewm);
        bundle.putParcelableArrayList("list", lists);
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
        return R.layout.fragment_wj;
    }

    @Override
    protected void initView() {
        mActivity = (SdlbActivity) getActivity();
    }

    public void initData() {

        if (mActivity.getFlag() == Contans.DLB) {
            initDjd();
        } else {
//            initZkd();
        }
    }

    /**
     * 质控点
     */
    private void initZkd() {

        zList = new ArrayList<>();

        ZkdInfo info = new ZkdInfo(1, "H-2：传热元件", "未完成", "H", "质检A", "质检B", "质检C", "监理");
        ZkdInfo info2 = new ZkdInfo(2, "W-3：驱动装置检查", "未完成", "w", "质检A", "质检B", "质检C", "监理");
        zList.add(info);
        zList.add(info2);

        headView = View.inflate(mActivity, R.layout.zkd_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

        tv_project.setVisibility(View.VISIBLE);
        zAdapter = new ZkdAdapter(context, zList);
        lv.addHeaderView(headView, null, false);
        lv.setAdapter(zAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(mActivity, SqdActivity.class);
//                intent.putExtra(Contans.KEY_IS_EDIT, true);
//                intent.putExtra(Contans.NFCOREWM, true);
//                intent.putExtra(Contans.KEY_BQBM, num);
//                startActivity(intent);
            }
        });
    }

    /**
     * 点检点
     */
    private void initDjd() {

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
                Bundle bundle = new Bundle();
                bundle.putInt(Contans.KEY_ITEM, i - 1);
                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, rwqys);
                bundle.putBoolean("edit", true);
                bundle.putBoolean(Contans.NFCOREWM, nfcorewm);
                bundle.putString(Contans.KEY_BQBM, num);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0x00);
            }
        });
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

    public void setListadapter() {
        list.clear();
        initDlbinfo();
        adapter.notifyDataSetChanged();
    }
}
