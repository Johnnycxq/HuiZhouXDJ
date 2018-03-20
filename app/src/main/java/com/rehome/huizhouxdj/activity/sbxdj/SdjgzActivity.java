package com.rehome.huizhouxdj.activity.sbxdj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.QYDDATABean;
import com.rehome.huizhouxdj.DBModel.XDJJHXZDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.CommonAdapter;
import com.rehome.huizhouxdj.adapter.ViewHolder;
import com.rehome.huizhouxdj.base.MipcaActivityCapture;
import com.rehome.huizhouxdj.bean.DjAjhGzInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.UiUtlis;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static org.litepal.crud.DataSupport.where;

/**
 * 设备巡点检管理-点检工作
 */
public class SdjgzActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.btn_sm)
    Button btn_sm;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private View headView;
    private List<DjAjhGzInfo> list;
    private CommonAdapter<XDJJHXZDataBean> adapter;
    private String ewm;//二维码或者条形码
    private List<String> qys;
    private List<String> qyAll;//全部，包括相同的
    private List<XDJJHXZDataBean> xdjjhxzDataBeanList = new ArrayList<>();//工作列表
    private ArrayList<QYDDATABean> qyddataBeanList = new ArrayList<>();//点检记录列表
    private Map<String, ArrayList<QYDDATABean>> map;

    @Override
    public int getContentViewID() {
        return R.layout.activity_sdjgz;
    }

    protected void initView() {

        lv.setEmptyView(tvNodata);
        headView = View.inflate(this, R.layout.djajhgz_item, null);
        headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
    }

    public void initData() {

        initNFC();


        map = new HashMap<>();
        list = new ArrayList<>();
        qys = new ArrayList<>();
        qyAll = new ArrayList<>();


        setTitle("工作");
        setBack();

        btn_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() != 0) {
                    Intent intent = new Intent(SdjgzActivity.this, MipcaActivityCapture.class);
                    startActivityForResult(intent, 1);
                } else {
                    showToast("你还没有计划");
                }
            }
        });
        getDataInSqlite();
        setListData();
    }


    //加载数据库中已经下载的计划
    private void getDataInSqlite() {

        xdjjhxzDataBeanList.clear();
        //获取本地所有的工作列表数据
        xdjjhxzDataBeanList.addAll(DataSupport.findAll(XDJJHXZDataBean.class));
        //去除重复数据
//        Set<XDJJHXZDataBean> xdjjhxzDataBeanSet = new TreeSet<>(new Comparator<XDJJHXZDataBean>() {
//            @Override
//            public int compare(XDJJHXZDataBean o1, XDJJHXZDataBean o2) {
//                return o1.getGWID().compareTo(o2.getGWID());
//            }
//        });
//        xdjjhxzDataBeanSet.addAll(xdjjhxzDataBeanList);
//        xdjjhxzDataBeanList.clear();
//        xdjjhxzDataBeanList.addAll(xdjjhxzDataBeanSet);

    }

    private void setListData() {
        if (adapter == null) {
            adapter = new CommonAdapter<XDJJHXZDataBean>(context, R.layout.djajhgz_item, xdjjhxzDataBeanList) {
                @Override
                protected void convert(ViewHolder viewHolder, XDJJHXZDataBean item, int position) {

                    //已点检数
                    int checkedCount = 0;
                    //获取每个区域（一个item表示一个区域，这里的item也就是XDJJHXZDataBean的一个实例）对应的所有QYDDATABean条数：查询原理（根据关联表中xdjjhxzDataBean_id 进行查找）
                    List<QYDDATABean> qyddataBeen = where("xdjjhxzDataBean_id = ?", item.getId() + "").find(QYDDATABean.class);
                    //遍历这个list集合，根据checked字段判断是否已点检，true-已点检，计数加一
                    for (int i = 0; i < qyddataBeen.size(); i++) {
                        if (qyddataBeen.get(i).isChecked()) {
                            checkedCount++;
                        }
                    }

                    viewHolder.setText(R.id.tv_xh, item.getSN() + "");
                    viewHolder.setText(R.id.tv_qymc, item.getGWMC() + "--" + item.getQYMC());
                    viewHolder.setText(R.id.tv_djrw, checkedCount + "/" + qyddataBeen.size());

                }
            };
            lv.addHeaderView(headView, null, false);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {

                    qyddataBeanList.clear();
                    //获取当前点击的工作栏对应的点检记录列表
                    qyddataBeanList.addAll(where("xdjjhxzDataBean_id = ?", xdjjhxzDataBeanList.get(postion - 1).getId() + "").find(QYDDATABean.class));


                    if (qyddataBeanList.size() != 0) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(false);
                        builder.setTitle("系统提示");
                        builder.setMessage("是否要浏览该区域下的工作内容");
                        builder.setNegativeButton(UiUtlis.getString(context, R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SdjgzActivity.this, YulActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList(Contans.KEY_DJJHRWQY, qyddataBeanList);
                                bundle.putBoolean("edit", true);
                                bundle.putInt(Contans.KEY_ITEM, 0);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        showToast("暂无该区域点检任务");
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    ewm = bundle.getString("result");
//                    Intent intent = new Intent(SdjgzActivity.this, SdlbActivity.class);
//                    intent.putExtra(Contans.NFCOREWM, true);
//                    intent.putExtra(Contans.KEY_BQBM, ewm);
//                    intent.putExtra(Contans.KEY_FLAG, Contans.DLB);
//                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDataInSqlite();
        adapter.notifyDataSetChanged();
    }

    //处理NFC的数据
    @Override
    public void handleNfc(String result) {
        super.handleNfc(result);
//        if (list.size() != 0) {
//            Intent intent = new Intent(SdjgzActivity.this, SdlbActivity.class);
//            intent.putExtra(Contans.NFCOREWM, false);
//            intent.putExtra(Contans.KEY_BQBM, result);
//            intent.putExtra(Contans.KEY_FLAG, Contans.DLB);
//            startActivity(intent);
//        } else {
//            showToast("你还没有计划");
//        }
    }
}