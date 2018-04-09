package com.rehome.huizhouxdj.activity.sbxj;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.DBModel.Xjjh;
import com.rehome.huizhouxdj.DBModel.XjjhList;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
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

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rehome.huizhouxdj.utils.GsonUtils.GsonToBean;

/**
 * 巡视抄表管理
 */
public class XscbglActivity extends BaseActivity {

    @BindView(R.id.gv)
    GridView gv;

    private String[] str = {"待办任务", "工作", "历史抄表", "读取NFC标签"};
    private int[] imageId = {R.mipmap.icon_xs1, R.mipmap.icon6, R.mipmap.icon_xs5, R.mipmap.icon11};
    private int[] colors = {R.drawable.radius_b1, R.drawable.radius_a4, R.drawable.radius_a2, R.drawable.radius_b2};
    private GridViewAdapter adapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xscbgl;
    }

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));

        setTitle("巡视抄表-" + Contans.YXCB_ZY_NAME);

        setBack();

        requestDatas();

        adapter = new GridViewAdapter(this, getGridViewData(), new ArrayList<Integer>(), true);
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(XscbglActivity.this, XjMainActivity.class);
                        break;
                    case 1:
                        intent = new Intent(XscbglActivity.this, SxgzActivity.class);
                        break;
                    case 2:
                        intent = new Intent(XscbglActivity.this, XsHistoryActivity.class);
                        break;
                    case 3:
                        intent = new Intent(XscbglActivity.this, NFCInfoActivity.class);
                        break;

                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private List<GridViewBean> getGridViewData() {

        List<GridViewBean> datas = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            GridViewBean bean = new GridViewBean();
            bean.setTitle(str[i]);
            bean.setBackgroup(colors[i]);
            bean.setImageid(imageId[i]);
            bean.setShow(false);
            datas.add(bean);
        }
        return datas;
    }

    private void requestDatas() {//下载巡检数据

        final Request<String> requestxs = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        requestxs.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requestxs, new HttpListener<String>() {

            @Override
            public void onSucceed(int what, Response<String> response) {

                XjjhList list = GsonToBean(response.get(), XjjhList.class);

                if (list != null) {

                    if (list.getState().equals("1")) {

                        List<Xjjh> xjjhs = list.getData();//服务器数据

                        DataSupport.deleteAll(Xjjh.class, "download = 0");

                        for (Xjjh xjjh : xjjhs) {

                            List<Xjjh> dbxjjh = DataSupport.where("zxid = ? and download = ? ", xjjh.getZxid(), "1").find(Xjjh.class);

                            if (dbxjjh.size() == 0) {
                                xjjh.save();
                            }
                        }
                    } else {

                        DataSupport.deleteAll(Xjjh.class, "download = 0");
                    }


                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private String createJson() {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("XSCB_ZXJL_GET");
        info.setZymc(Contans.YXCB_ZY_ID);
        info.setZc((String) SPUtils.get(context, Contans.BZBH, ""));
        String json = GsonUtils.GsonString(info);
        return json;
    }
}
