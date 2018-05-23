package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.utils.BaseActivity3;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.litepal.crud.DataSupport.where;

/**
 * Created by ruihong on 2018/5/23.
 */

public class DjgwListActivity extends BaseActivity3 implements View.OnClickListener {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;

    //所有的XDJJHXZDataBean数据
    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();

    //点检计划列表数据源
    private List<XDJJHXZDataBean> xdjjhxzDataList = new ArrayList<>();

    //传递的数据源
    ArrayList<XDJJHXZDataBean> xdjjhxzDataBeanList2 = new ArrayList<>();

    private String gwid = "";
    private CommonAdapter<XDJJHXZDataBean> adapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:

                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sdjgw;
    }

    @Override
    public void initView() {
        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.djgwlist_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        initToolbar("岗位列表", "", this);
        getDataInSQL();
        setListData();
    }

    /**
     * 获取数据库的数据
     */
    public void getDataInSQL() {


        xdjjhxzDataBeanList.clear();
        xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));

        xdjjhxzDataList.clear();


        for (int i = 0; i < xdjjhxzDataBeanList.size(); i++) {

            if (!xdjjhxzDataBeanList.get(i).getGWID().equals(gwid)) {

                List<QYDDATABean> qydDataBeen = where("GWID = ?", xdjjhxzDataBeanList.get(i).getGWID()).find(QYDDATABean.class);

                gwid = xdjjhxzDataBeanList.get(i).getGWID();


                //点检计划列表bean
                XDJJHXZDataBean xdjjhxzDataBean = new XDJJHXZDataBean();
                xdjjhxzDataBean.setGWMC(xdjjhxzDataBeanList.get(i).getGWMC());
                xdjjhxzDataBean.setGWID(xdjjhxzDataBeanList.get(i).getGWID());
//                xdjjhxzDataBean.setCountPercent(count + "/" + qydDataBeen.size());
//                xdjjhxzDataBean.setQYBH(xdjjhxzDataBeanList.get(i).getQYBH());
                xdjjhxzDataList.add(xdjjhxzDataBean);


            }
        }
    }

    private void setListData() {
        if (adapter == null) {


            adapter = new CommonAdapter<XDJJHXZDataBean>(context, R.layout.djgwlist_item, xdjjhxzDataList) {
                @Override
                protected void convert(ViewHolder viewHolder, XDJJHXZDataBean item, int position) {

                    viewHolder.setText(R.id.tv_qymc, item.getGWMC());
//                    viewHolder.setText(R.id.tv_xh, item.getSN() + "");
//                    viewHolder.setText(R.id.tv_qymc, item.getGWMC() + "--" + item.getQYMC());
//                    viewHolder.setText(R.id.tv_djrw, checkedCount + "/" + qyddataBeen.size());

                }
            };


            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {

                    String gwid_gw = xdjjhxzDataList.get(postion - 1).getGWID();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(DjgwListActivity.this, SdjgzActivity.class);
//                        bundle.putParcelableArrayList("xdjjhxzDataBeanList2", xdjjhxzDataBeanList2);
                    bundle.putString("GWID", gwid_gw);
                    intent.putExtras(bundle);
                    startActivity(intent);

//
//                    List<XDJJHXZDataBean> xdjjhxzDataBeen = DataSupport.where("GWID = ?", gwid_gw + "").find(XDJJHXZDataBean.class);
//                    xdjjhxzDataBeanList2.clear();
//                    xdjjhxzDataBeanList2.addAll(xdjjhxzDataBeen);
//
//                    if (xdjjhxzDataBeanList2.size() != 0) {
//
//                    } else {
//                        showToast("暂无该区域点检任务");
//                    }


                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
