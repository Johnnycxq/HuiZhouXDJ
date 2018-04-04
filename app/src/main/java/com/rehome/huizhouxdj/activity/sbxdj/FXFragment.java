package com.rehome.huizhouxdj.activity.sbxdj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rehome.huizhouxdj.DBModel.QYAQFXDATABean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.GwfxListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.rehome.huizhouxdj.MyApplication.context;


public class FXFragment extends Fragment {


    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    private ArrayList<QYAQFXDATABean> qyddataBeanArrayList;
    GwfxListAdapter gwfxlistadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fx, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        qyddataBeanArrayList = new ArrayList<>();
        requestDatas();
    }

    private void requestDatas() {
        Bundle bundle = getActivity().getIntent().getExtras();
        qyddataBeanArrayList = bundle.getParcelableArrayList("QYFXTS");
        setAdapter();
    }

    private void setAdapter() {
            gwfxlistadapter = new GwfxListAdapter(context, qyddataBeanArrayList);
            lv.setAdapter(gwfxlistadapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                }
            });


    }

}
