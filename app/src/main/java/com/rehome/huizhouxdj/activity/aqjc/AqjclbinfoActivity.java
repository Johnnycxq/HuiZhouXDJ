package com.rehome.huizhouxdj.activity.aqjc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.Uploadaqjcsave;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.aqjcsaveAdapter;
import com.rehome.huizhouxdj.base.BaseCallBack;
import com.rehome.huizhouxdj.bean.AqjclbBean;
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
 * Created by ruihong on 2018/1/8.
 */

public class AqjclbinfoActivity extends BaseActivity2 implements View.OnClickListener {
    @BindView(R.id.tv_ST)
    TextView tvST;
    @BindView(R.id.tv_ET)
    TextView tvET;
    @BindView(R.id.tv_LCFW)
    TextView tvLCFW;
    @BindView(R.id.tv_LCFF)
    TextView tvLCFF;
    @BindView(R.id.tv_TJR)
    TextView tvTJR;
    @BindView(R.id.tv_TJSJ)
    TextView tvTJSJ;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.btn_sc)
    Button btnSc;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.xmll)
    LinearLayout xmll;
    private View headView;
    private View head;
    private CheckBox cb;
    private Intent intent;
    AqjclbBean.DataBean dataBean;
    private List<Uploadaqjcsave> list;//本地数据库拿出来显示在listview上
    private List<Uploadaqjcsave> removesavelist;//已上传的任务
    private List<Uploadaqjcsave> Uploadaqjcsavelist;//要上传的集合
    aqjcsaveAdapter adapter;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                intent = new Intent(AqjclbinfoActivity.this, AqjcrwSaveActivity.class);
                intent.putExtra("JHID", dataBean.getJHID());
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aqjclbinfo;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        intent = getIntent();
        dataBean = (AqjclbBean.DataBean) getIntent().getExtras().get("aqjcjh");
        initToolbar(dataBean.getJHMC(), "新增", this);

        tvST.setText("开始时间:" + dataBean.getST());
        tvET.setText("结束时间:" + dataBean.getET());
        tvLCFW.setText("联查范围:" + dataBean.getJCFW());
        tvLCFF.setText("联查方法" + dataBean.getJCFW());
        tvTJR.setText("添加人:" + dataBean.getTJR());
        tvTJSJ.setText("添加时间:" + dataBean.getTJSJ());

        list = new ArrayList<>();
        removesavelist = new ArrayList<>();
        Uploadaqjcsavelist = new ArrayList<>();

        headView = View.inflate(context, R.layout.sblcsavedata_item_wtqy, null);
        head = headView.findViewById(R.id.head);
        lv.addHeaderView(headView, null, false);

        cb = (CheckBox) headView.findViewById(R.id.cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(false);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        head.setVisibility(View.VISIBLE);
        lv.setEmptyView(tvNodata);

        getDataInSQL();
        if (adapter == null) {
            setListData();
        } else {
            adapter.notifyDataSetChanged();

        }


        btnSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadSblcRwData();
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

        List<Uploadaqjcsave> uploadaqjcsaveList = where("JHID = ?", dataBean.getJHID()).find(Uploadaqjcsave.class);

        list.addAll(uploadaqjcsaveList);

        if (uploadaqjcsaveList.size() == 0) {

            xmll.setVisibility(View.GONE);//无数据则隐藏按钮

        } else {

            xmll.setVisibility(View.VISIBLE);

        }


    }

    /**
     * 把数据库的数据显示在listview
     */
    private void setListData() {

        adapter = new aqjcsaveAdapter(context, list, new aqjcsaveAdapter.CallBack() {
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
                adapter.notifyDataSetChanged();
            }
        });


        lv.setAdapter(adapter);

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
                adapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 把数据库的数据上传给服务器
     */

    public void UploadSblcRwData() {


        String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");

        Uploadaqjcsavelist.clear();

        removesavelist.clear();


        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).isChecked()) {

                //把选中的数据放在Uploadsblcsavelist中 进行上传
                Uploadaqjcsavelist.add(list.get(i));

                //把选中的数据放在removesavelist中 上传成功后删除
                removesavelist.add(list.get(i));

            }
        }


        MultipartBody.Builder builder;

        for (Uploadaqjcsave uploadaqjcsave : Uploadaqjcsavelist) {

            String photopatglist = uploadaqjcsave.getPhotopatglist();

            String substring = photopatglist.substring(1, photopatglist.length() - 1);

            String JHID_inData = uploadaqjcsave.getJHID();

            String WTQY = uploadaqjcsave.getWTQY();

            String WTMS = uploadaqjcsave.getWTMS();

            String SaveDataTime = uploadaqjcsave.getLRSJ();

            if (substring.contains(",")) {

                builder = new MultipartBody.Builder();

                builder.setType(MultipartBody.FORM);

                String[] split = substring.split(",");

                for (String imagePath : split) {

                    File file = new File(imagePath.trim());

                    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    builder.addFormDataPart("file", file.getName(), imageBody);

                }

            } else {

                builder = new MultipartBody.Builder();

                builder.setType(MultipartBody.FORM);

                File file = new File(substring);

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                builder.addFormDataPart("file", file.getName(), imageBody);

            }
            RetrofitHttpUtils.getApi().upload_QJRWPhoto("AQJC_RW_SET", USERNAME, JHID_inData, WTQY,

                    WTMS, SaveDataTime, "", "", "", builder.build()).enqueue(new BaseCallBack<UploadPhotosBean>(context) {

                @Override
                public void onSuccess(Call<UploadPhotosBean> call, Response<UploadPhotosBean> response) {

                    UploadPhotosBean uploadPhotosBean = response.body();

                    if (uploadPhotosBean != null) {

                        if (uploadPhotosBean.getState().equals("1")) {

                            showToast("上传成功");

                            removeData();//上传成功删除本地的数据

                            Uploadaqjcsavelist.clear();//上传成功清除数据

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
        if (adapter != null) {
            cb.setChecked(false);
            adapter.notifyDataSetChanged();
        }
    }


    //移除上传成功的数据
    private void removeData() {

        for (Uploadaqjcsave uploadaqjcsave : removesavelist) {
            DataSupport.deleteAll(Uploadaqjcsave.class, "LRSJ = ?", uploadaqjcsave.getLRSJ());
        }

        getDataInSQL();
        if (adapter != null) {
            cb.setChecked(false);
            adapter.notifyDataSetChanged();
        }
    }

    //    删除选中的数据
    public void deleteData() {
        final List<Uploadaqjcsave> deletes = new ArrayList<>();
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
                    for (Uploadaqjcsave uploadaqjcsave : deletes) {
                        DataSupport.deleteAll(Uploadaqjcsave.class, "LRSJ = ?", uploadaqjcsave.getLRSJ());

                    }
                    //刷新界面
                    getDataInSQL();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            builder.show();
        } else {
            showToast("你还没有选择项目");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
