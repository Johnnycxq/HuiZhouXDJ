package com.rehome.huizhouxdj.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.rehome.huizhouxdj.DBModel.XwaqgcJh;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.aqjc.MainAqjcActivity;
import com.rehome.huizhouxdj.activity.sbxdj.MainSbxdjglActivity;
import com.rehome.huizhouxdj.activity.sbxj.XscbglActivity;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.BasicDataBean;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.bean.PushInfo;
import com.rehome.huizhouxdj.bean.XsRequestInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.service.PushService;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.ControllerActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.ListDialog;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.gv)
    GridView gv;
    private List<GridViewBean> beanList = new ArrayList<>();
    private GridViewAdapter adapter;
    private MsgReceiver msgReceiver;
    private PushInfo.Push push;
    private boolean isTask = false;//是否有任务
    private long exitTime = 0;
    private String str[] = {"设备巡点检", "巡视抄表", "安全检查"};
    private int[] imageId = {R.mipmap.icon8, R.mipmap.icon6, R.mipmap.icon10};
    private int[] colors = {R.drawable.radius_a1, R.drawable.radius_e3, R.drawable.radius_a3};
    private List<Integer> item;
    private List<String> dialogDatas;
    private List<BasicDataBean.DataBean> zys;

    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }

    protected void initView() {


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.rehome.huizhouxdj.RECEIVER");
        registerReceiver(msgReceiver, intentFilter);

    }


    public void initData() {


        title.setText("设备设施巡查");
        setExitZx();

        zys = new ArrayList<>();
        dialogDatas = new ArrayList<>();
        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));


        item = new ArrayList<>();

        item.addAll(PermissionsResult());

        checkPush();


        beanList.addAll(getGridViewData(isTask));

        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridViewAdapter(this, beanList, item, Contans.TEST);
        gv.setAdapter(adapter);

        requestZyDatas();


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, MainSbxdjglActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        if (dialogDatas.size() == 0) {
                            intent = new Intent(MainActivity.this, XscbglActivity.class);
                            startActivity(intent);
                        } else {
                            ListDialog dialog2 = new ListDialog(context, dialogDatas, new ListDialog.ListDialogListener() {
                                @Override
                                public void selectText(String str, int position) {

                                    Contans.YXCB_ZY_ID = zys.get(position).getId();
                                    Contans.YXCB_ZY_NAME = str;
                                    Intent intent = new Intent(MainActivity.this, XscbglActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dialog2.show();
                        }
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, MainAqjcActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });

    }

    private void requestZyDatas() {

        final Request<String> requset = NoHttp.createStringRequest("http://219.131.195.3:8081/" + Contans.XS_JCSJ, RequestMethod.
                POST);

        requset.setDefineRequestBodyForJson(createZyJson());

        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
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
        info.setModuletype("Set_ZYMC");
        String json = GsonUtils.GsonString(info);
        return json;
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

        Intent intent = new Intent(this, PushService.class);
        Bundle bundle = new Bundle();
        bundle.putIntArray("what", whats);
        intent.putExtras(bundle);
        startService(intent);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 按两次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                ControllerActivity.getAppManager().finishAllActivity();
                finish();
                System.exit(0);
                NohttpUtils.getInstance().cancelAll();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setExitZx() {
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("你确定要退出程序?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ControllerActivity.getAppManager().finishAllActivity();
                        finish();
                        System.exit(0);
                        NohttpUtils.getInstance().cancelAll();
                        dialog.dismiss();
                    }
                });
                builder.create().show();

            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.zx:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("提示");
                        builder.setMessage("你确定要重新登录?");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ControllerActivity.getAppManager().finishAllActivity();
                                NohttpUtils.getInstance().cancelAll();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int size = DataSupport.findAll(XwaqgcJh.class).size();
        if (size != 0) {
            isTask = true;
        } else {
            isTask = false;
        }
        beanList.clear();
        beanList.addAll(getGridViewData(isTask));
        adapter.notifyDataSetChanged();
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


