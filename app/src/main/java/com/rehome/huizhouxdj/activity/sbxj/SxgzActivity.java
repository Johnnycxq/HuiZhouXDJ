package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.utils.BaseActivity3;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static org.litepal.crud.DataSupport.where;

/**
 * 设备巡点检管理-巡视工作
 */
public class SxgzActivity extends BaseActivity3 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sm)
    Button btn_sm;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;
    private CommonAdapter<XSJJHXZDataBean> adapter;
    private String ewm;//二维码或者条形码
    private List<XSJJHXZDataBean> xsjjhxzDataBeanList = new ArrayList<>();
    private ArrayList<XSJJHDataBean> xsjjhDataBeanArrayList = new ArrayList<>();//点检记录列表
    Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xsgz;
    }

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
    public void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.xsgz_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

    }

    public void initData() {

        initNFC();
        initToolbar("巡视工作", "", this);
        getDataInSqlite();
        setListData();

        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xsjjhxzDataBeanList.size() != 0) {
                    Intent intent = new Intent(SxgzActivity.this, MipcaActivityCapture.class);
                    startActivityForResult(intent, 1);
                } else {
                    showToast("你还没有计划");
                }
            }
        });
    }


    //加载数据库中已经下载的计划
    private void getDataInSqlite() {

        xsjjhxzDataBeanList.clear();

        xsjjhxzDataBeanList.addAll(DataSupport.findAll(XSJJHXZDataBean.class));

//        xsjjhDataBeanArrayList.addAll(DataSupport.findAll(XSJJHDataBean.class));
    }

    private void setListData() {

        if (adapter == null) {


            adapter = new CommonAdapter<XSJJHXZDataBean>(context, R.layout.xsgz_item, xsjjhxzDataBeanList) {
                @Override
                protected void convert(ViewHolder viewHolder, XSJJHXZDataBean item, int position) {

                    int checkedCount = 0;
                    List<XSJJHDataBean> xsjjhdatabean = where("xsjjhxzDataBean_id = ?", item.getId() + "").find(XSJJHDataBean.class);

                    for (int i = 0; i < xsjjhdatabean.size(); i++) {
                        if (xsjjhdatabean.get(i).isChecked()) {
                            checkedCount++;
                        }
                    }

                    viewHolder.setText(R.id.tv_xh, item.getSN() + "");
                    viewHolder.setText(R.id.tv_qymc, item.getQymc());
                    viewHolder.setText(R.id.tv_djrw, checkedCount + "/" + xsjjhdatabean.size());

                }
            };


            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {


                    List<XSJJHDataBean> xsjjhdatabean = where("xsjjhxzDataBean_id = ?", 1 + "").find(XSJJHDataBean.class);


//
//                    xsjjhDataBeanArrayList.clear();
//
//                    xsjjhDataBeanArrayList.addAll(where("xsjjhxzDataBean_id = ?", xsjjhxzDataBeanList.get(postion - 1).getId() + "").find(XSJJHDataBean.class));
//
//
//                    Log.e("size", String.valueOf(xsjjhDataBeanArrayList.size()));

//
//                    if (qyddataBeanList.size() != 0) {
//
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        builder.setCancelable(false);
//                        builder.setTitle("系统提示");
//                        builder.setMessage("是否要浏览该区域下的工作内容");
//                        builder.setNegativeButton(UiUtlis.getString(context, R.string.cancel), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        });
//
//                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                Intent intent = new Intent(SdjgzActivity.this, TipsActivity.class);
//                                Bundle bundle = new Bundle();
//                                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
//                                bundle.putParcelableArrayList("QYFXTS", qyaqfxdataBeanList);
//                                bundle.putBoolean("edit", false);
//                                bundle.putInt(Contans.KEY_ITEM, 0);
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                                dialogInterface.dismiss();
//                            }
//                        });
//                        builder.show();
//                    } else {
//                        showToast("暂无该区域点检任务");
//                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }


    }


}