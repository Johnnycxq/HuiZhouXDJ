package com.rehome.huizhouxdj.activity.DaXiaoXiu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.qfgd.GdztActivity;
import com.rehome.huizhouxdj.adapter.ZjrwAdapter;
import com.rehome.huizhouxdj.bean.QXRequestBean;
import com.rehome.huizhouxdj.bean.ZjrwBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.weight.InputLayout;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/17.
 */

public class ZjrwcxActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.il_rwzt)
    InputLayout ilRwzt;
    @BindView(R.id.lv)
    ListView lv;
    private String GDZT_NO = "";
    private String GDZTMC = "";
    private Calendar c = Calendar.getInstance();
    ZjrwAdapter zjrwAdapter;
    private List<ZjrwBean.DataBean> datas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zjcx;
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
                requestDatas();
                break;
        }
    }

    @Override
    public void initData() {

        initToolbar("质检任务", "查询", this);
        datas = new ArrayList<>();
        ilRwzt.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ZjrwcxActivity.this, GdztActivity.class);
                startActivityForResult(intent1, 1);
            }
        });
        tvStarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        c.set(year, month, dayOfMonth);
                        tvStarttime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                dialog.show();

            }
        });

        tvEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        c.set(year, month, dayOfMonth);
                        tvEndtime.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                dialog.show();

            }
        });

    }

    private void requestDatas() {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.DXX, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(GDZT_NO, tvStarttime.getText().toString(), tvEndtime.getText().toString()));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {

                    ZjrwBean zjrwBean = GsonUtils.GsonToBean(response.get(), ZjrwBean.class);
                    if (zjrwBean != null) {

                        if (zjrwBean.getState() == 1) {
                            datas.clear();
                            datas.addAll(zjrwBean.getData());
                            setAdapter();
                        } else {
                            datas.clear();
                            setAdapter();
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

    private String createJson(String RWZT, String ST, String ET) {
        QXRequestBean info = new QXRequestBean();
        info.setAction("DXX_ZJRW_GET");
        info.setRWZT(RWZT);
        info.setST(ST);
        info.setET(ET);
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void setAdapter() {

        if (zjrwAdapter == null) {
            zjrwAdapter = new ZjrwAdapter(context, datas);

            lv.setAdapter(zjrwAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Intent intent = new Intent(ZjrwcxActivity.this, ZjrwcxinfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("RWRC", datas.get(position).getRWMC());
                    bundle.putString("RWID", datas.get(position).getRWID());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        } else {
            zjrwAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    GDZT_NO = bundle.getString("GDZT_NO");
                    GDZTMC = bundle.getString("GDZTMC");
                    ilRwzt.setContent(GDZTMC);
                }
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}

