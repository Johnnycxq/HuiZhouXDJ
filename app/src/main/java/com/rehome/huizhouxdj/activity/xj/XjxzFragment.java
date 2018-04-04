package com.rehome.huizhouxdj.activity.xj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.XzjhAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.weight.WaitDialog;
import com.yolanda.nohttp.rest.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 设备巡点检管理-下载计划
 */
public class XjxzFragment extends BaseFragment {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_xz)
    Button btn_xz;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.LL)
    LinearLayout LL;
    Unbinder unbinder;

    private WaitDialog dialog;
    private xjActivity mActivity;
    private View headView;
    private View head;
    private XzjhAdapter adapter;
    private CheckBox cb;
    private List<Djjh> djjhs;
    private int selectCount = 0;
    private int requestCount = 0;
    private List<String> gwids = new ArrayList<>();
    private RequestQueue queue;


    public XjxzFragment() {

    }

    public static XjxzFragment newInstance() {
        XjxzFragment fragment = new XjxzFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_xzxjjh;
    }

    @Override
    protected void initView() {

        lv.setEmptyView(tvNodata);


    }

    public void initData() {


    }

    @OnClick(R.id.btn_xz)
    public void click() {


    }

    @OnClick(R.id.btn_delete)
    public void delete() {

    }


    private void setListData() {

        adapter = new XzjhAdapter(context, djjhs, new XzjhAdapter.CallBack() {
            @Override
            public void Click(View view) {

                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                djjhs.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < djjhs.size(); a++) {
                    if (djjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == djjhs.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });


        lv.addHeaderView(headView, null, false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                djjhs.get(i - 1).setChecked(djjhs.get(i - 1).isChecked());
                int count = 0;
                for (int a = 0; a < djjhs.size(); a++) {
                    if (djjhs.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == djjhs.size() ? true : false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
