package com.rehome.huizhouxdj.activity.sbxj;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.DBModel.XSJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.utils.BaseActivity3;
import com.rehome.huizhouxdj.weight.ListDialog;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;

/**
 * 设备巡点检管理-巡视工作
 */
public class SxSbListActivity extends BaseActivity3 implements View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;
    private CommonAdapter<XSJJHXZDataBean> adapter;
    //    private CommonAdapter<sbInfo> adapter;
    private List<XSJJHXZDataBean> xsjjhxzDataBeanList_sb = new ArrayList<>();//工作列表
    Intent intent;
    private int pos = -1;//点击的设备item
    private String state;
//    private List<sbInfo> infos = new ArrayList<>();
//    private ArrayList<SetxjSbModel> setSbModelList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_xsxzsbstate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                intent = new Intent(SxSbListActivity.this, SxgzActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.xsgzsblist_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);

    }

    public void initData() {

        initNFC();
        initToolbar("选择设备状态", "巡检数据", this);
        getDataInSqlite();
        setListData();
    }


    private void getDataInSqlite() {

        xsjjhxzDataBeanList_sb.clear();
        xsjjhxzDataBeanList_sb.addAll(DataSupport.findAll(XSJJHXZDataBean.class));

        Set<XSJJHXZDataBean> xsjjhxzDataBeanSet = new TreeSet<>(new Comparator<XSJJHXZDataBean>() {
            @Override
            public int compare(XSJJHXZDataBean o1, XSJJHXZDataBean o2) {
                return o1.getSbmc().compareTo(o2.getSbmc());
            }
        });
        xsjjhxzDataBeanSet.addAll(xsjjhxzDataBeanList_sb);
        xsjjhxzDataBeanList_sb.clear();
        xsjjhxzDataBeanList_sb.addAll(xsjjhxzDataBeanSet);


    }

    private void setListData() {


        if (adapter == null) {


            adapter = new CommonAdapter<XSJJHXZDataBean>(context, R.layout.xsgzsblist_item, xsjjhxzDataBeanList_sb) {
                @Override
                protected void convert(ViewHolder viewHolder, XSJJHXZDataBean item, int position) {

                    viewHolder.setText(R.id.tv_qymc, item.getSbmc());

                    if (item.getSBMCSTATE() == null) {
                        viewHolder.setText(R.id.tv_sbstate, " ");
                    } else {
                        viewHolder.setText(R.id.tv_sbstate, item.getSBMCSTATE() + "");
                    }

                }
            };


            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {

//                    showToast(xsjjhxzDataBeanList_sb.get(postion - 1).getSbmc());

                    pos = postion;
                    List<String> datas = new ArrayList<String>();
                    datas.add("已停用");
                    datas.add("大小修");
                    datas.add("已运行");
                    ListDialog dialog = new ListDialog(context, datas, new ListDialog.ListDialogListener() {

                        @Override
                        public void selectText(String text, int position) {

                            state = text;


                            if (text.equals("已停用")) {

                                ContentValues values = new ContentValues();

                                values.put("checked", true);

                                values.put("SBMCSTATEVALUE", "3");

                                values.put("CJJG", text);

                                values.put("SBMCSTATE", text);

                                int i = DataSupport.updateAll(XSJJHDataBean.class, values, "sbmc = ? and  TJXJZT = ? ", xsjjhxzDataBeanList_sb.get(postion - 1).getSbmc(), "1");

                                if (i != 0) {
                                    showToast("修改设备状态成功");
                                } else {
                                    showToast("修改设备状态失败");
                                }

                            } else if (text.equals("大小修")) {

                                ContentValues values2 = new ContentValues();

                                values2.put("checked", true);

                                values2.put("SBMCSTATEVALUE", "4");

                                values2.put("CJJG", text);

                                values2.put("SBMCSTATE", text);

                                int i = DataSupport.updateAll(XSJJHDataBean.class, values2, "sbmc = ? and  TJXJZT = ? ", xsjjhxzDataBeanList_sb.get(postion - 1).getSbmc(), "1");

                                if (i != 0) {
                                    showToast("修改设备状态成功");
                                } else {
                                    showToast("修改设备状态失败");
                                }

                            } else if (text.equals("已运行")) {

                                ContentValues values3 = new ContentValues();

                                values3.put("checked", false);

                                values3.put("CJJG", "");

                                values3.put("SBMCSTATE", text);

                                values3.put("SBMCSTATEVALUE", "5");

                                int i = DataSupport.updateAll(XSJJHDataBean.class, values3, "sbmc = ?", xsjjhxzDataBeanList_sb.get(postion - 1).getSbmc());

                                if (i != 0) {
                                    showToast("修改设备状态成功");
                                } else {
                                    showToast("修改设备状态失败");
                                }

                            }


                            ContentValues values2 = new ContentValues();

                            if (text.equals("已停用")) {

                                values2.put("SBMCSTATE", text);

                                values2.put("SBMCSTATEVALUE", "3");

                            } else if (text.equals("大小修")) {

                                values2.put("SBMCSTATE", text);

                                values2.put("SBMCSTATEVALUE", "4");

                            } else if (text.equals("已运行")) {

                                values2.put("SBMCSTATE", text);

                                values2.put("SBMCSTATEVALUE", "5");
                            }

                            int J = DataSupport.updateAll(XSJJHXZDataBean.class, values2, "sbmc = ? ", xsjjhxzDataBeanList_sb.get(postion - 1).getSbmc());


                            if (J != 0) {
                                showToast("修改设备状态成功");
                            } else {
                                showToast("修改设备状态失败");
                            }
                        }
                    });
                    dialog.setTvTitle("选择设备状态");
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (pos != -1) {
                                xsjjhxzDataBeanList_sb.get(pos - 1).setSBMCSTATE(state);
                                adapter.notifyDataSetChanged();

//                                SetxjSbModel setxjSbModel = new SetxjSbModel();
//                                setxjSbModel.setSbId(infos.get(pos - 1).getSbmc());
//                                setxjSbModel.setValue(state);
//                                setxjSbModel.setStatu(true);
//                                setSbModelList.add(setxjSbModel);
                            }
                        }
                    });
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }


    }

}