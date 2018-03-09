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

import com.rehome.huizhouxdj.DBModel.AjhScInfo;
import com.rehome.huizhouxdj.DBModel.Ajhjh;
import com.rehome.huizhouxdj.DBModel.Ajhxcjs;
import com.rehome.huizhouxdj.DBModel.Ajhxzrwqy;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.DBModel.LyXcjsInfo;
import com.rehome.huizhouxdj.DBModel.LyYhpcInfo;
import com.rehome.huizhouxdj.DBModel.LyxcXm;
import com.rehome.huizhouxdj.DBModel.LyxcXmJg;
import com.rehome.huizhouxdj.DBModel.Lyxcrwqy;
import com.rehome.huizhouxdj.DBModel.QxgdInfo;
import com.rehome.huizhouxdj.DBModel.XcjsInfo;
import com.rehome.huizhouxdj.DBModel.XfBaxcRwqy;
import com.rehome.huizhouxdj.DBModel.XfDjjh;
import com.rehome.huizhouxdj.DBModel.XfDjjhRwqy;
import com.rehome.huizhouxdj.DBModel.XfXcjsInfo;
import com.rehome.huizhouxdj.DBModel.XfXcmhqc;
import com.rehome.huizhouxdj.DBModel.XfXcxm;
import com.rehome.huizhouxdj.DBModel.XfXcxmjg;
import com.rehome.huizhouxdj.DBModel.XwaqgcJh;
import com.rehome.huizhouxdj.DBModel.XwaqgcJs;
import com.rehome.huizhouxdj.DBModel.XwaqgcSc;
import com.rehome.huizhouxdj.DBModel.YhpcInfo;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.sbxdj.MainSbxdjglActivity;
import com.rehome.huizhouxdj.adapter.GridViewAdapter;
import com.rehome.huizhouxdj.bean.GridViewBean;
import com.rehome.huizhouxdj.bean.PushInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.service.PushService;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.ControllerActivity;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;

import org.litepal.crud.DataSupport;

import java.io.File;
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
    private List<String> dialogDatas;
    private boolean isTask = false;//是否有任务
    private long exitTime = 0;
    private String str[] = {"设备巡点检"};
    private int[] imageId = {R.mipmap.icon8};
    private int[] colors = {R.drawable.radius_a1};
    private List<Integer> item;


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

//
//    //后台上传数据
//    private void upLoadData() {
//        //如果有网络，就上传数据
//        if (NetworkAvailableUtils.isNetworkAvailable(this)) {
//            Intent intent = new Intent(this, UploadDataService.class);
//            startService(intent);
//        }
//    }


    public void initData() {

        mToolbar.setBackgroundColor(Color.parseColor("#00000000"));

        title.setText("设备设施巡查");

        setExitZx();

        item = new ArrayList<>();

        item.addAll(PermissionsResult());

        checkPush();

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

        beanList.addAll(getGridViewData(isTask));

        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridViewAdapter(this, beanList, item, Contans.TEST);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, MainSbxdjglActivity.class);
                        startActivity(intent);
                        break;


                }
            }
        });

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

    //删除过期计划
    private void deleteOverdueJH() {

        List<String> djjhs = new ArrayList<>();
        List<String> xfDjjhs = new ArrayList<>();
        List<String> ajhjhs = new ArrayList<>();
        List<String> xwaqgcJhs = new ArrayList<>();

        List<Djjh> djjh = DataSupport.findAll(Djjh.class);
        for (Djjh jh : djjh) {
            if (UiUtlis.isdelete(jh.getDQSJ())) {
                djjhs.add(jh.getJHID());
            }
        }
        List<Ajhjh> ajhjh = DataSupport.findAll(Ajhjh.class);
        for (Ajhjh jh : ajhjh) {
            if (UiUtlis.isdelete(jh.getDQSJ())) {
                ajhjhs.add(jh.getJHID());
            }
        }
        List<XfDjjh> xfDjjh = DataSupport.findAll(XfDjjh.class);
        for (XfDjjh jh : xfDjjh) {
            if (UiUtlis.isdelete(jh.getNexttime())) {
                xfDjjhs.add(jh.getJhid());
            }
        }

        List<XwaqgcJh> xwaqgcJh = DataSupport.findAll(XwaqgcJh.class);

        for (XwaqgcJh jh : xwaqgcJh) {
            if (UiUtlis.isdelete(jh.getDQSJ())) {
                xwaqgcJhs.add(jh.getJHID());
            }
        }
        //删除点检数据
        for (String jhid : djjhs) {
            DataSupport.deleteAll(Djjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(DjjhRwQy.class, "jhid = ?", jhid);
            List<XcjsInfo> infos = DataSupport.where("jhid = ?", jhid).find(XcjsInfo.class);
            for (XcjsInfo info : infos) {
                File file = new File(info.getFilename());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(XcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(QxgdInfo.class);
        }

        //删除安健环数据
        for (String jhid : ajhjhs) {
            List<Ajhxcjs> ajhxcjses = DataSupport.findAll(Ajhxcjs.class);
            for (Ajhxcjs js : ajhxcjses) {
                File file = new File(js.getFile());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(Ajhxcjs.class, "jhid = ?", jhid);
            DataSupport.deleteAll(Ajhjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(Ajhxzrwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(AjhScInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(YhpcInfo.class);
        }

        //删除消防数据
        for (String jhid : xfDjjhs) {

            List<XfXcjsInfo> infos = DataSupport.where("jhid = ?", jhid).find(XfXcjsInfo.class);
            for (XfXcjsInfo info : infos) {
                File file = new File(info.getPath());
                if (file.isFile()) {
                    file.delete();
                }
            }

            List<LyXcjsInfo> lyjs = DataSupport.where("jhid = ?", jhid).find(LyXcjsInfo.class);
            for (LyXcjsInfo info : lyjs) {
                File file = new File(info.getPath());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(XfDjjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfDjjhRwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcxmjg.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcmhqc.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfBaxcRwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcxm.class, "jhid = ?", jhid);
            DataSupport.deleteAll(Lyxcrwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyxcXm.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyXcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyxcXmJg.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyYhpcInfo.class);
        }

        for (String jh : xwaqgcJhs) {
            DataSupport.deleteAll(XwaqgcJh.class, "jhid = ?", jh);
            DataSupport.deleteAll(XwaqgcSc.class, "jhid = ?", jh);
            DataSupport.deleteAll(XwaqgcJs.class, "jhid = ?", jh);
        }
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


