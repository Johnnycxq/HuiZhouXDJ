package com.rehome.huizhouxdj.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.bean.PhoneInfo;
import com.rehome.huizhouxdj.bean.UserInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.AutoToolbar;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.ControllerActivity;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NetworkAvailableUtils;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.rehome.huizhouxdj.vpn.LoginVpnActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.yanzhenjie.permission.AndPermission;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.litepal.tablemanager.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VPNforLoginActivity extends BaseActivity {
    @BindView(R.id.title)
    AppCompatTextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn_wwdl)
    Button btn_wwdl;
    @BindView(R.id.save_pw_user)
    CheckBox savePwUser;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.et_use)
    EditText etUse;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String username;
    private String pwd;
    private long exitTime = 0;
    private boolean first;
    private boolean NETWORK_STATE;//网络状态
    private String NEWWORK_TYPE;//网络类型

    @Override
    public int getContentViewID() {
        return R.layout.activity_loginforvpn;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        AndPermission.with(this).requestCode(100).permission(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).send();

        //第一次进入，就创建文件夹
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sd = Environment.getExternalStorageDirectory();
            String path = sd.getAbsolutePath() + File.separator + "/com.rehome.huizhouxdj/images";
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();
            }
        }


//        rb1.setChecked((Boolean) SPUtils.get(context, Contans.WIFI_4G, true));
//
//
//        rb2.setChecked(!(Boolean) SPUtils.get(context, Contans.WIFI_4G, true));


        rb1.setChecked(true);


        tvVersion.setText("版本" + getVersionName());
        username = (String) SPUtils.get(VPNforLoginActivity.this, Contans.USERNAME, "");
        pwd = (String) SPUtils.get(VPNforLoginActivity.this, "pwd", "");

        boolean save = (boolean) SPUtils.get(VPNforLoginActivity.this, "save", true);
        savePwUser.setChecked(save);
        etUse.setText(username);

        if (save) {
            etPwd.setText(pwd);
        }
        first = (boolean) SPUtils.get(this, Contans.FIRST, true);

        String wifi = (String) SPUtils.get(context, Contans.KEY_WIFI_IP, "");
        String mob = (String) SPUtils.get(context, Contans.KEY_4G_IP, "");

        if (wifi.equals("") && mob.equals("")) {

//            SPUtils.put(this, Contans.KEY_4G_IP, "http://hzl-dj.gdyd.com:8082/");

            SPUtils.put(this, Contans.KEY_4G_IP, "http://192.168.2.189:8092/");

            SPUtils.put(this, Contans.KEY_WIFI_IP, "http://hzl-dj.gdyd.com:8082/");

        }
    }

    public void initData() {


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rb1.isChecked()) {
                    if (!TextUtils.isEmpty((String) SPUtils.get(context, Contans.KEY_WIFI_IP, ""))) {
                        Contans.IP = (String) SPUtils.get(context, Contans.KEY_WIFI_IP, "");

                        Log.e("ip", Contans.IP);
                    }
                }
                if (rb2.isChecked()) {
                    if (!TextUtils.isEmpty((String) SPUtils.get(context, Contans.KEY_4G_IP, ""))) {
                        Contans.IP = (String) SPUtils.get(context, Contans.KEY_4G_IP, "");

                        Log.e("ip", Contans.IP);
                    }
                }

                //如果没网络，然后有账号，直接登录
                if (NetworkAvailableUtils.isNetworkAvailable(context)) {

                    if (isLogin()) {

                        String json = getPhoneInfo();

                        Logger.json(json);

                        Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.LOGIN, RequestMethod.POST);

                        request.setDefineRequestBodyForJson(json);

                        NohttpUtils.getInstance().add(VPNforLoginActivity.this, 0, request, callback, true, true, "登录中...");


                    }
                } else {
                    if (isLogin()) {
                        if (UiUtlis.getText(etUse).equals(SPUtils.get(context, Contans.USERNAME, ""))
                                && UiUtlis.getText(etPwd).equals(SPUtils.get(context, "pwd", ""))) {
                            Intent intent = new Intent(VPNforLoginActivity.this, VPNforLoginActivity.class);
                            startActivity(intent);
                        } else {
                            showToast("你还没有登录过");
                        }
                    }
                }

            }
        });


        btn_wwdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VPNforLoginActivity.this, LoginVpnActivity.class);
                startActivity(intent);
            }
        });


        if (first) {
            //如果是第一次进入程序，就创建数据库
            SQLiteDatabase db = Connector.getDatabase();
            SPUtils.put(this, Contans.FIRST, false);
            if (db != null) {
                //showToast("数据库创建成功");
            }
        }
    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            String json = response.get();
            UserInfo userInfo = GsonUtils.GsonToBean(json, UserInfo.class);
            if (userInfo != null) {

                UserInfo.User user = userInfo.getRows().get(0);
                String status = user.getStatus();
                String username = user.getUsernames();
                if (status.equals("0")) {
                    showToast("用户名或密码错误");
                } else if (status.equals("1")) {

                    if (!rb1.isChecked()) {
                        MiPushClient.setAlias(context, etUse.getText().toString(), null);
                    }

                    SPUtils.put(VPNforLoginActivity.this, Contans.WIFI_4G, rb1.isChecked());
                    SPUtils.put(VPNforLoginActivity.this, "save", savePwUser.isChecked());
                    SPUtils.put(VPNforLoginActivity.this, Contans.USERNAME, etUse.getText().toString());
                    SPUtils.put(VPNforLoginActivity.this, "pwd", etPwd.getText().toString());


                    SPUtils.put(VPNforLoginActivity.this, Contans.NAME, username);
                    SPUtils.put(VPNforLoginActivity.this, Contans.BZBH, user.getBzbh() == null ? "" : user.getBzbh());
                    SPUtils.put(VPNforLoginActivity.this, Contans.BZMC, user.getBzmc() == null ? "" : user.getBzmc());
                    SPUtils.put(VPNforLoginActivity.this, Contans.PERMISSIONSRESULT, user.getPermissionsResult() == null ? "" : user.getPermissionsResult());
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);


                    Intent intent = new Intent(VPNforLoginActivity.this, TabMainActivity.class);
                    startActivity(intent);


                } else if (status.equals("2")) {
                    showToast("登录异常");
                } else if (status.equals("3")) {
                    showToast("未激活或未授权");
                } else if (status.equals("4")) {
                    showToast("禁止登陆");
                }
            } else {
                showToast(UiUtlis.getString(context, R.string.data_error));
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
        }
    };

    public String getPhoneInfo() {
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = manager.getDeviceId();//IMEI
        String model = Build.MODEL;//手机型号
        String sysVersion = Build.VERSION.RELEASE;//系统版本
        String phonenum = manager.getLine1Number();//手机号码
        if (phonenum == null) {
            phonenum = "";
        }
        Logger.v("IMEI：" + imei + "  手机型号:" + model + "  系统版本:" + sysVersion + "  手机号码:" + phonenum);
        PhoneInfo info = new PhoneInfo();
        info.setTotal(1);
        List<PhoneInfo.UserInfo> list = new ArrayList<>();
        PhoneInfo.UserInfo userInfo = new PhoneInfo.UserInfo();
        userInfo.setImeinum(imei);
        userInfo.setSysversion(sysVersion);
        userInfo.setPhonemodel(model);

        if (phonenum.equals("")) {
        } else {
            userInfo.setPhonenum(phonenum.substring(3, phonenum.length()));
        }
        userInfo.setUsername(UiUtlis.getText(etUse));
        userInfo.setPassword(UiUtlis.getText(etPwd));
        list.add(userInfo);
        info.setRows(list);
        String json = GsonUtils.GsonString(info);
        Logger.v(json);
        return json;
    }

    private boolean isLogin() {
        if (TextUtils.isEmpty(etUse.getText().toString()) && TextUtils.isEmpty(etPwd.getText().toString())) {
            showToast("用户名和密码不能为空");
            return false;
        } else if (TextUtils.isEmpty(etUse.getText().toString())) {
            showToast("用户名不能为空");
            return false;
        } else if (TextUtils.isEmpty(etPwd.getText().toString())) {
            showToast("密码不能为空");
            return false;
        } else {
            return true;
        }
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

    /**
     * 获取版本名称:清单文件中
     *
     * @return 应用版本名称    返回null代表异常
     */
    private String getVersionName() {
        //1,包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //权限允许，创建文件夹
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File sd = Environment.getExternalStorageDirectory();
                String path = sd.getAbsolutePath() + File.separator + "/com.rehome.huizhouxdj/images";
                File file1 = new File(path);
                if (!file1.exists()) {
                    file1.mkdirs();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
