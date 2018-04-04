package com.rehome.huizhouxdj.activity.xj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.aqjc.MainAqjcActivity;
import com.rehome.huizhouxdj.activity.qfgd.MainQfActivity;
import com.rehome.huizhouxdj.activity.sbxdj.MainSbxdjglActivity;
import com.rehome.huizhouxdj.activity.sbxj.XscbglActivity;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.bean.BasicDataBean;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.bean.PushInfo;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.service.PushService;
import com.rehome.huizhouxdj.utils.AutoToolbar;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.ListDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruihong on 2018/4/2.
 */

public class MainFragment extends BaseFragment {

    private static MainFragment instance = null;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.gv)
    GridView gv;
    Unbinder unbinder;
    private List<GridViewBean> beanList = new ArrayList<>();
    private GridViewAdapter adapter;
    private MsgReceiver msgReceiver;
    private PushInfo.Push push;
    private boolean isTask = false;//是否有任务
    private long exitTime = 0;
    private String str[] = {"设备巡点检", "巡视抄表", "安全检查", "Q4工单"};
    private int[] imageId = {R.mipmap.icon8, R.mipmap.icon6, R.mipmap.icon10, R.mipmap.icon9};
    private int[] colors = {R.drawable.radius_a1, R.drawable.radius_e3, R.drawable.radius_a3, R.drawable.radius_a2};
    private List<Integer> item;
    private List<String> dialogDatas;
    private List<BasicDataBean.DataBean> zys;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        toolbar.setVisibility(View.GONE);

        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.rehome.huizhouxdj.RECEIVER");
        getActivity().registerReceiver(msgReceiver, intentFilter);
    }

    public void initData() {


        zys = new ArrayList<>();
        dialogDatas = new ArrayList<>();
        item = new ArrayList<>();
        item.addAll(PermissionsResult());
        checkPush();


        beanList.addAll(getGridViewData(isTask));

        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridViewAdapter(getActivity(), beanList, item, Contans.TEST);
        gv.setAdapter(adapter);

//        requestZyDatas();


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), MainSbxdjglActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

//                        intent = new Intent(MainActivity.this, MainxjActivity.class);
//                        startActivity(intent);
//                        break;

                        if (dialogDatas.size() == 0) {
                            intent = new Intent(getActivity(), XscbglActivity.class);
                            startActivity(intent);
                        } else {
                            ListDialog dialog2 = new ListDialog(context, dialogDatas, new ListDialog.ListDialogListener() {
                                @Override
                                public void selectText(String str, int position) {

                                    Contans.YXCB_ZY_ID = zys.get(position).getId();
                                    Contans.YXCB_ZY_NAME = str;
                                    Intent intent = new Intent(getActivity(), XscbglActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dialog2.show();
                        }
                        break;
                    case 2:
                        intent = new Intent(getActivity(), MainAqjcActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(getActivity(), MainQfActivity.class);
                        startActivity(intent);
                        break;


                }
            }
        });

    }

    private void requestZyDatas() {

        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.XS_JCSJ, RequestMethod.
                POST);

        requset.setDefineRequestBodyForJson(createZyJson());

        NohttpUtils.getInstance().add(getActivity(), 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {

                try {
                    BasicDataBean bean = GsonUtils.GsonToBean(response.get(), BasicDataBean.class);
                    if (bean != null) {
                        if (bean.getState() == 1) {
                            if (bean.getData().size() != 0) {
                                zys.clear();
                                zys.addAll(bean.getData());
                                initDialogDatas();
                            }
                        }
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

    private void initDialogDatas() {

        for (BasicDataBean.DataBean bean : zys) {
            dialogDatas.add(bean.getName());
        }
    }


    private String createZyJson() {
        XsRequestInfo info = new XsRequestInfo();
        info.setAction("GGJK_JCSJ_GET");
        info.setModuletype("XJGL_ZYMC");
        String json = GsonUtils.GsonString(info);
        return json;
    }


    private List<Integer> PermissionsResult() {

        List<Integer> item = new ArrayList<>();


        try {
            String result = (String) SPUtils.get(context, Contans.PERMISSIONSRESULT, "");

            if (!result.isEmpty()) {
                String[] results = result.split(";");
                item.add(0);
                item.add(1);
                item.add(2);
                item.add(3);
                for (String name : results) {
                    if (name.equals("AppDJGL")) {
                        item.add(0);
                    } else if (name.equals("AppSBLC")) {
                        item.add(1);
                    } else if (name.equals("AppAQJC")) {
                        item.add(2);
                    } else if (name.equals("AppGWZY")) {
                        item.add(3);
                    } else if (name.equals("AppZDCS")) {
                        item.add(4);
                    }
                }
            }
        } catch (Exception e) {
        }
        return item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * @param isTask 是否有行为安全观察任务
     * @return
     */
    private List<GridViewBean> getGridViewData(boolean isTask) {

        List<GridViewBean> datas = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            GridViewBean bean = new GridViewBean();
            bean.setTitle(str[i]);
            bean.setBackgroup(colors[i]);
            bean.setImageid(imageId[i]);
            bean.setShow(false);
            if (i == 4) {
                if (isTask) {
                    bean.setShow(true);
                } else {
                    bean.setShow(false);
                }
            }
            datas.add(bean);
        }

        return datas;
    }

    //检查系统是否有任务推送
    private void checkPush() {

        int[] whats = new int[]{-1, -1, -1, -1, -1};
        int[] test = {0, 1, 2, 3, 4};
        for (int i = 0; i < item.size(); i++) {

            if (item.get(i) == 0) {
                //点检
                whats[0] = 1;
            } else if (item.get(i) == 2) {
                //安健环
                whats[1] = 2;
                whats[2] = 3;
            } else if (item.get(i) == 3) {
                //消防
                whats[4] = 0;
            }
        }

        Intent intent = new Intent(getActivity(), PushService.class);
        Bundle bundle = new Bundle();
        bundle.putIntArray("what", whats);
        intent.putExtras(bundle);
        getActivity().startService(intent);

    }

    /**
     * 广播接收器
     */
    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            isTask = intent.getBooleanExtra("isTask", false);
            beanList.clear();
            beanList.addAll(getGridViewData(isTask));
            adapter.notifyDataSetChanged();
        }
    }
}
