package com.rehome.huizhouxdj.activity.DaXiaoXiu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.QXRequestBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/4/17.
 */

public class ZjrwcxinfoActivity extends BaseActivity2 implements View.OnClickListener {


    @BindView(R.id.yxj_et)
    EditText yxjEt;
    @BindView(R.id.yxj_bm)
    EditText yxjBm;
    @BindView(R.id.lv)
    ListView lv;
    private String BMID = "";
    private String BMMC = "";


    private String RWRC;
    private String RWID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zjrwwjb;
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
//                requestDatas();
                break;
        }
    }

    @Override
    public void initData() {

        initToolbar("质检包列表", "", this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RWRC = bundle.getString("RWRC");
        RWID = bundle.getString("RWID");
        yxjEt.setText(RWRC);
//        datas = new ArrayList<>();
        yxjBm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ZjrwcxinfoActivity.this, DxxBmListActivity.class);
                startActivityForResult(intent1, 1);
            }
        });

        requestDatas();


    }

    private void requestDatas() {


        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.DXX, RequestMethod.POST);

        requset.setDefineRequestBodyForJson(createJson(RWID, BMID));

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {
                    Log.e("123", response.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private String createJson(String RWID, String BMID) {

        String yhid = (String) SPUtils.get(context, Contans.NAME, "");
        QXRequestBean info = new QXRequestBean();
        info.setAction("DXX_ZJBLIST_GET");
        info.setYHID(yhid);
        info.setRWID(RWID);
        info.setBMID(BMID);
        String json = GsonUtils.GsonString(info);
        return json;
    }

//    private void setAdapter() {
//
//        if (zjrwAdapter == null) {
//            zjrwAdapter = new ZjrwAdapter(context, datas);
//
//            lv.setAdapter(zjrwAdapter);
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
//            zjrwAdapter.notifyDataSetChanged();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    BMID = bundle.getString("BMID");
                    BMMC = bundle.getString("BMMC");
                    yxjBm.setText(BMMC);
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

