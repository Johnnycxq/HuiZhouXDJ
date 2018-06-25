package com.rehome.huizhouxdj.activity.qfgd;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.draw.MultiLineDrawFormat;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.Qfkccxbean;
import com.rehome.huizhouxdj.bean.qfbpbjRequestBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class bpbjinfoActivity extends BaseActivity {


    private List<Qfkccxbean.DataBean> datas;

    private SmartTable<Qfkccxbean.DataBean> table;


    @Override
    public int getContentViewID() {
        return R.layout.activity_kccxinfo;
    }

    @Override
    protected void initView() {
        setBack();
        setTitle("查询详情");
        table = (SmartTable<Qfkccxbean.DataBean>) findViewById(R.id.table_bbbj);
        table.setZoom(true);
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String etMwbm = bundle.getString("etMwbm");
        String etBzmc = bundle.getString("etBzmc");
        String etCkh = bundle.getString("etCkh");
        requestDatas(etMwbm, etBzmc, etCkh);
    }

    private void requestDatas(String mwbm, String bzmc, String ckh) {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(mwbm, bzmc, ckh));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    Qfkccxbean qfkccxbean = GsonUtils.GsonToBean(response.get(), Qfkccxbean.class);

                    if (qfkccxbean != null) {
                        if (qfkccxbean.getState() == 1) {
                            showToast(qfkccxbean.getMsg());
                            datas.clear();
                            datas.addAll(qfkccxbean.getData());
                            parsePG1Data(datas);


//                            table.setData(datas);//设置数据给table
                        } else {
                            showToast(qfkccxbean.getMsg());
                            datas.clear();

                        }
                    } else {
                        showToast(R.string.data_error);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void parsePG1Data(List<Qfkccxbean.DataBean> list) {
        Column<String> mLine1 = new Column<>("物资编码", "WZBM");
        mLine1.setFixed(true);
        mLine1.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
            @Override
            public void onClick(Column<String> column, String value, String s, int position) {
//                showToast(value);
            }
        });

        Column<String> mLine2 = new Column<>("仓库号", "CKH");
        Column<String> mLine3 = new Column<>("物质名称", "WZMC");
        mLine3.setDrawFormat(new MultiLineDrawFormat<String>(500));

        Column<String> mLine4 = new Column<>("数量", "SL");
        Column<String> mLine5 = new Column<>("单位", "DW");
        Column<String> mLine6 = new Column<>("单价", "DJ");
        Column<String> mLine7 = new Column<>("仓储", "CC");
        TableData<Qfkccxbean.DataBean> data = new TableData("备品查询", list, mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7);
        table.setTableData(data);
        table.setZoom(true, 3, 1);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
    }


    private String createJson(String mwbm, String bzmc, String ckh) {
        qfbpbjRequestBean info = new qfbpbjRequestBean();
        info.setAction("Q4GD_KCCX_GET");
        info.setYHID((String) SPUtils.get(context, Contans.USERNAME, ""));
        info.setWZBM(mwbm);
        info.setWZMC(bzmc);
        info.setCKH(ckh);
        String json = GsonUtils.GsonString(info);
        return json;
    }

//    private void setAdapter() {
//
//        if (bpbjinfoAdapter == null) {
//
//
//            headView = View.inflate(context, R.layout.item_bpbjinfo, null);
//            headView.findViewById(R.id.head).setVisibility(View.VISIBLE);
//            bpbjinfoAdapter = new BpbjinfoAdapter(context, datas);
//            lv.addHeaderView(headView, "", false);
//            lv.setAdapter(bpbjinfoAdapter);
//
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//
//                }
//            });
//
//        } else {
//            bpbjinfoAdapter.notifyDataSetChanged();
//        }
//
//        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                if (firstVisibleItem >= 1) {
//
//                    LLHead.setVisibility(View.VISIBLE);
//                } else {
//
//                    LLHead.setVisibility(View.GONE);
//                }
//            }
//        });
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}