package com.rehome.huizhouxdj.activity.sbxj;

import android.util.Log;
import android.widget.ListView;

import com.rehome.huizhouxdj.DBModel.Xjjh;
import com.rehome.huizhouxdj.DBModel.XjjhList;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

import static com.rehome.huizhouxdj.utils.GsonUtils.GsonToBean;

/**
 * 待办任务
 */
public class XsBackLogActivity extends BaseActivity {
    @BindView(R.id.lv)
    ListView lv;

    @Override
    public int getContentViewID() {
        return R.layout.activity_xs_back_log;
    }

//    private List<XsJhListBean.DataBean> datas;

//    private XsjhListAdapter adapter;

    @Override
    protected void initView() {
        setTitle("待办任务");
        setBack();
//        requestDatas();

//        datas = new ArrayList<>();
    }

    private void requestDatas() {


//        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);
//
//        requset.setDefineRequestBodyForJson(createJson());
//
//        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//
//                try {
//                    XsJhListBean xsJhListBean = GsonUtils.GsonToBean(response.get(), XsJhListBean.class);
//                    if (xsJhListBean != null) {
//                        if (xsJhListBean.getState() == 1) {
////                            showToast(xsJhListBean.getMsg());
//                            datas.clear();
//                            datas.addAll(xsJhListBean.getData());
//                            setAdapter();
//                        } else {
//                            showToast(xsJhListBean.getMsg());
//                            datas.clear();
//                            setAdapter();
//                        }
//                    } else {
//                        showToast(R.string.data_error);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//
//            }
//        });


        final Request<String> requestxs = NoHttp.createStringRequest(Contans.IP + Contans.XSCB, RequestMethod.POST);

        requestxs.setDefineRequestBodyForJson(createJson());

        NohttpUtils.getInstance().add(this, 0, requestxs, new HttpListener<String>() {

            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("Data", response.get());

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

    private void setAdapter() {

//        if (adapter == null) {
//            adapter = new XsjhListAdapter(context, datas);
//
//            lv.setAdapter(adapter);
//
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    Intent intent = new Intent(XsBackLogActivity.this, XscbqyActivity.class);
//                    intent.putExtra(Contans.KEY_XSCBJH, datas.get(position));
//                    startActivity(intent);
//                }
//            });
//
//        } else {
//            adapter.notifyDataSetChanged();
//        }
    }

    private String createJson() {
//        XsRequestInfo info = new XsRequestInfo();
//        info.setAction("XSCB_ZXJL_GET");
//        info.setZymc(Contans.YXCB_ZY_ID);
//        info.setZc((String) SPUtils.get(context, Contans.BZBH, ""));
        String json = "";
        return json;


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestDatas();
    }
}
