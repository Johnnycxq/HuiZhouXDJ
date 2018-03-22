package com.rehome.huizhouxdj.activity.aqjc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Uploadzgjg;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.ZgrwSaveAdapter;
import com.rehome.huizhouxdj.base.BaseCallBack;
import com.rehome.huizhouxdj.bean.AqjcrwListBean;
import com.rehome.huizhouxdj.bean.UploadPhotosBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.RetrofitHttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.litepal.crud.DataSupport.where;

/**
 * Created by ruihong on 2018/1/20.
 */

public class AqjcrwlbinfoActivity extends BaseActivity2 implements View.OnClickListener {

    AqjcrwListBean.DataBean dataBean;
    @BindView(R.id.tv_jhmc)
    TextView tvJhmc;
    @BindView(R.id.tv_wtqy)
    TextView tvWtqy;
    @BindView(R.id.tv_wtms)
    TextView tvWtms;
    @BindView(R.id.tv_fxlb)
    TextView tvFxlb;
    @BindView(R.id.tv_yhdj)
    TextView tvYhdj;
    @BindView(R.id.tv_zrbm)
    TextView tvZrbm;
    @BindView(R.id.tv_scsj)
    TextView tvScsj;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.FLLL)
    FrameLayout FLLL;
    @BindView(R.id.btn_sc)
    Button btnSc;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.xmll)
    LinearLayout xmll;

    private Intent intent;
    private View headView;
    private View head;
    private CheckBox cb;


    private List<Uploadzgjg> list;
    private List<Uploadzgjg> removesavelist;
    private List<Uploadzgjg> Uploadzgjglist;

    private ZgrwSaveAdapter zgrwSaveAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_aqjcrwlbinfo;
    }

    @Override
    public void initView() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                intent = new Intent(AqjcrwlbinfoActivity.this, AqZgrwSaveActivity.class);
                intent.putExtra("JHID", dataBean.getJHID());
                intent.putExtra("RWID", dataBean.getRWID());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initData() {
        intent = getIntent();
        dataBean = (AqjcrwListBean.DataBean) getIntent().getExtras().get("aqjcrwlb");
        initToolbar(dataBean.getJHMC(), "新增", this);
        tvJhmc.setText("计划名称:" + dataBean.getJHMC());
        tvWtqy.setText("问题区域:" + dataBean.getWTQY());
        tvWtms.setText("问题描述:" + dataBean.getWTMS());
        tvFxlb.setText("风险类别:" + dataBean.getFXLB());
        tvYhdj.setText("隐患等级:" + dataBean.getYHDJ());
        tvZrbm.setText("责任部门:" + dataBean.getZRBM());
        tvScsj.setText("上传时间:" + dataBean.getSCSJ());

        list = new ArrayList<>();
        removesavelist = new ArrayList<>();
        Uploadzgjglist = new ArrayList<>();

        headView = View.inflate(context, R.layout.sblcsavedata_item2, null);
        head = headView.findViewById(R.id.head);
        lv.addHeaderView(headView, null, false);

        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(true);
                        zgrwSaveAdapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(false);
                        zgrwSaveAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        head.setVisibility(View.VISIBLE);
        lv.setEmptyView(tvNodata);


        getDataInSQL();
        if (zgrwSaveAdapter == null) {
            setListData();
        } else {
            zgrwSaveAdapter.notifyDataSetChanged();

        }

        btnSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadZgjgData();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

    }

    /**
     * 数据库查询获取数据
     */
    public void getDataInSQL() {

        list.clear();

        List<Uploadzgjg> uploadzgjgs = where("JHID = ?", dataBean.getJHID()).find(Uploadzgjg.class);

        list.addAll(uploadzgjgs);

        if (uploadzgjgs.size() == 0) {

            xmll.setVisibility(View.GONE);//无数据则隐藏按钮

        } else {

            xmll.setVisibility(View.VISIBLE);

        }


    }

    /**
     * 把数据库的数据显示在listview
     */
    private void setListData() {

        zgrwSaveAdapter = new ZgrwSaveAdapter(context, list, new ZgrwSaveAdapter.CallBack() {
            @Override
            public void Click(View view) {
                CheckBox checkBox = (CheckBox) view;
                int index = (int) checkBox.getTag();
                list.get(index).setChecked(checkBox.isChecked());
                int count = 0;
                for (int a = 0; a < list.size(); a++) {
                    if (list.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == list.size() ? true : false);
                zgrwSaveAdapter.notifyDataSetChanged();
            }
        });


        lv.setAdapter(zgrwSaveAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                list.get(i - 1).setChecked(list.get(i - 1).isChecked() ? false : true);
                int count = 0;
                for (int a = 0; a < list.size(); a++) {
                    if (list.get(a).isChecked()) {
                        count++;
                    }
                }
                cb.setChecked(count == list.size() ? true : false);
                zgrwSaveAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 把数据库的数据上传给服务器
     */

    private void uploadZgjgData() {

        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");

        Uploadzgjglist.clear();
        removesavelist.clear();


        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).isChecked()) {

                //把选中的数据放在Uploadsblcsavelist中 进行上传
                Uploadzgjglist.add(list.get(i));

                //把选中的数据放在removesavelist中 上传成功后删除
                removesavelist.add(list.get(i));

            }
        }

        MultipartBody.Builder builder = new MultipartBody.Builder();

        builder.setType(MultipartBody.FORM);

        for (Uploadzgjg uploadzgjg : Uploadzgjglist) {

            String photopatglist = uploadzgjg.getPhotopatglist();

            String substring = photopatglist.substring(1, photopatglist.length() - 1);

            String JHID_inData = uploadzgjg.getJHID();
            String RWID_inData = uploadzgjg.getRWID();
            String ZGJG_inData = uploadzgjg.getZGJG();


            if (substring.contains(",")) {
                String[] split = substring.split(",");

                for (String imagePath : split) {
                    File file = new File(imagePath.trim());
                    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    builder.addFormDataPart("file", file.getName(), imageBody);
                }
            } else {
                File file = new File(substring);
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file", file.getName(), imageBody);
            }


            RetrofitHttpUtils.getApi().upload_AQZGJG("AQJC_ZG_SET", USERNAME, JHID_inData, RWID_inData, ZGJG_inData, builder.build()).enqueue(new BaseCallBack<UploadPhotosBean>(context) {

                @Override
                public void onSuccess(Call<UploadPhotosBean> call, Response<UploadPhotosBean> response) {

                    UploadPhotosBean uploadPhotosBean = response.body();

                    if (uploadPhotosBean != null) {

                        if (uploadPhotosBean.getState().equals("1")) {

                            showToast("上传成功");

                            removeData();//上传成功删除本地的数据

                            Uploadzgjglist.clear();//上传成功清除数据

                        }
                    }

                }

                @Override
                public void onError(Call<UploadPhotosBean> call, Throwable t) {

                }
            });
        }


    }










    @Override
    protected void onResume() {
        super.onResume();
        getDataInSQL();
        if (zgrwSaveAdapter != null) {
            cb.setChecked(false);
            zgrwSaveAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //移除上传成功的数据
    private void removeData() {

        for (Uploadzgjg uploadzgjg : removesavelist) {
            DataSupport.deleteAll(Uploadzgjg.class, "LRSJ = ?", uploadzgjg.getLRSJ());
        }

        getDataInSQL();
        if (zgrwSaveAdapter != null) {
            cb.setChecked(false);
            zgrwSaveAdapter.notifyDataSetChanged();
        }
    }

    //    删除选中的数据
    public void deleteData() {
        final List<Uploadzgjg> deletes = new ArrayList<>();
        int select = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                deletes.add(list.get(i));
                select++;
            }
        }

        if (select != 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("提示");
            builder.setTitle("你确定要删除？");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //删除数据
                    for (Uploadzgjg uploadzgjg : deletes) {
                        DataSupport.deleteAll(Uploadzgjg.class, "LRSJ = ?", uploadzgjg.getLRSJ());

                    }
                    //刷新界面
                    getDataInSQL();
                    if (zgrwSaveAdapter != null) {
                        zgrwSaveAdapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        } else {
            showToast("你还没有选择项目");
        }
    }
}
