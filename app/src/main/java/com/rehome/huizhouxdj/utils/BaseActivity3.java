package com.rehome.huizhouxdj.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rehome.huizhouxdj.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ruihong on 2017/12/23.
 */

public abstract class BaseActivity3 extends AutoLayoutActivity {

    public static final int REQUEST_CODE_ADD = 1;

    public static final int REQUEST_CODE_AUDIT = 2;


    @BindView(R.id.toolbar)
    OAToolbar toolbar;

    private Unbinder unbinder;

    public Context context;

    public String simpleName;

    private NfcAdapter nfcAdapter;
    private String readResult = "";
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isFirst = true;
    private IntentFilter ndef;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏

        setContentView(getLayoutId());

        AppManager.getAppManager().addActivity(this);

        simpleName = "ydoa == " + getClass().getSimpleName();

        context = this;

        unbinder = ButterKnife.bind(this);

        initView();

        initData();

        initNFC();

//        showToast(getClass().getSimpleName());
    }

    /**
     * 检测工作,判断设备的NFC支持情况
     *
     * @return
     */
    private Boolean ifNFCUse() {
        if (nfcAdapter == null) {
//            Toast.makeText(this, "设备不支持NFC！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nfcAdapter != null && !nfcAdapter.isEnabled()) {
            Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 初始化过程
     */
    public void initNFC() {

        //NFC适配器，所有的关于NFC的操作从该适配器进行
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!ifNFCUse()) {
            return;
        }
        //将被调用的Intent，用于重复被Intent触发后将要执行的跳转
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        //设定要过滤的标签动作，这里只接收ACTION_NDEF_DISCOVERED类型
        ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[]{ndef};// 过滤器
        mTechLists = new String[][]{new String[]{NfcA.class.getName()},
                new String[]{NfcF.class.getName()},
                new String[]{NfcB.class.getName()},
                new String[]{NfcV.class.getName()},
                new String[]{MifareClassic.class.getName()},
                new String[]{MifareUltralight.class.getName()},
                new String[]{IsoDep.class.getName()}};// 允许扫描的标签类型

        if (isFirst) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent()
                    .getAction())) {
                ndef = new IntentFilter();
                if (readFromTag(getIntent())) {
                    Toast.makeText(this, readResult, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "标签数据为空", Toast.LENGTH_SHORT).show();
                }
            }
            isFirst = false;
        }
    }

    private String ByteArrayToHexString(byte[] inarray) { //converts byte arrays to string
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";
        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }


    /*
 * 重写onResume回调函数的意义在于处理多次读取NFC标签时的情况
 */
    @Override
    protected void onResume() {
        super.onResume();

        //设置强制竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 前台分发系统,这里的作用在于第二次检测NFC标签时该应用有最高的捕获优先权.
        if (nfcAdapter != null && pendingIntent != null && mFilters != null && mTechLists != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mFilters,
                    mTechLists);
        }

    }

    /*
 * 有必要要了解onNewIntent回调函数的调用时机,请自行上网查询
 */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction()) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {

            String id = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));

            if (id != null) {
                handleNfc(id);
            }

//            if (readFromTag(intent)) {
//                Toast.makeText(this, readResult, Toast.LENGTH_SHORT).show();
//                handleNfc(readResult);
//            } else {
//                Toast.makeText(this, "ID:" + id + "  标签数据为空", Toast.LENGTH_SHORT).show();
//            }
        }
    }
    /**
     * 读取NFC标签数据的操作
     */
    private boolean readFromTag(Intent intent) {

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {

            Parcelable[] rawArray = intent
                    .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawArray != null) {
                NdefMessage mNdefMsg = (NdefMessage) rawArray[0];
                NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
                try {
                    if (mNdefRecord != null) {
                        readResult = new String(mNdefRecord.getPayload(), "UTF-8");
                        return true;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //return false;
            }
            return false;
        }
        return false;
    }

    //处理NFC
    public void handleNfc(String result) {
        //showToast(result);
        //Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

        AppManager.getAppManager().finishActivity(this);
    }

    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int strId) {
        Toast.makeText(context, strId, Toast.LENGTH_SHORT).show();
    }

    public void initToolbar(String title) {
        toolbar.setTvTitleText(title);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTvTitleText(title);
    }

    public void initToolbar(String title, String rightText, View.OnClickListener listener) {
        toolbar.setTvTitleText(title);
        toolbar.setTvRightText(rightText);
        toolbar.setTvRightOnClickListener(listener);
        setLeftOnClickListener(listener);
    }

    public void initToolbar(String title, int rightTextId, View.OnClickListener listener) {
        toolbar.setTvTitleText(title);
        toolbar.setTvRightText(rightTextId);
        toolbar.setTvRightOnClickListener(listener);
        setLeftOnClickListener(listener);
    }

    public void setTvRightTitle(String text) {
        toolbar.setTvRightText(text);
    }

    public void setLeftOnClickListener(View.OnClickListener listener) {
        toolbar.setIvLeftOnClickListener(listener);
        toolbar.setIvLeftIcon(R.mipmap.ac_back_icon);
    }

    public String getText(EditText editText) {
        return editText.getText().toString();
    }

    public void setToolbarColor() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.lucency));
        toolbar.setTvTitleColor(getResources().getColor(R.color.login_title_color));
    }
}
