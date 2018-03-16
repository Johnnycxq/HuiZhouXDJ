package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class XDJJHXZDataBean extends DataSupport implements Parcelable {


    /**
     * QYBH : 00000000021
     * QYMC : 测试区域1
     * QYEWM : asdfasd
     * QYEWMZT : 1
     * JHID : zx00000001
     * QYNFC : 12312fdwfasdf
     * QYNFCZT : 1
     * QYD_DATA : [{"SCID":"B17A736A283C47999347F8E01CB5C1A6","SBMC":"测试设备1","BJMC":"部件2","DID":"5C556F3D44DF420BB8D34593842AF8D2","DMC":"消防测试点3_SBL1","BZZ":"标准值","SJMC":"数据名称","SJDW":"数据单位","JCFS":"检查方式","XMZQ":"1","LRFS":"1","LRMRZ":""}]
     * QYAQFX_DATA : [{"AQFXID":"D3F75475F4D14A25BCBC1C798ED38368","FXLX":"安全风险测试","FXMS":"风险描述","FHCS":"防护措施"},{"AQFXID":"B0B062FDE8FF42E3B3EB59FD78E5639F","FXLX":"安全风险测试1","FXMS":"风险描述1","FHCS":"防护措施1"}]
     */
    private long id;
    private String GWID;
    private String QYBH;
    private String QYMC;
    private String QYEWM;
    private String QYEWMZT;
    private String QYNFC;
    private String QYNFCZT;
    private List<QYDDATABean> QYD_DATA = new ArrayList<>();
    private List<QYAQFXDATABean> QYAQFX_DATA = new ArrayList<>();
    private int SN; //自定义序号

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public XDJJHXZDataBean() {
    }

    protected XDJJHXZDataBean(Parcel in) {
        QYBH = in.readString();
        QYMC = in.readString();
        QYEWM = in.readString();
        QYEWMZT = in.readString();
        GWID = in.readString();
        QYNFC = in.readString();
        QYNFCZT = in.readString();
        QYD_DATA = in.createTypedArrayList(QYDDATABean.CREATOR);
        QYAQFX_DATA = in.createTypedArrayList(QYAQFXDATABean.CREATOR);
        SN = in.readInt();
    }

    public static final Creator<XDJJHXZDataBean> CREATOR = new Creator<XDJJHXZDataBean>() {
        @Override
        public XDJJHXZDataBean createFromParcel(Parcel in) {
            return new XDJJHXZDataBean(in);
        }

        @Override
        public XDJJHXZDataBean[] newArray(int size) {
            return new XDJJHXZDataBean[size];
        }
    };

    public String getQYBH() {
        return QYBH;
    }

    public void setQYBH(String QYBH) {
        this.QYBH = QYBH;
    }

    public String getQYMC() {
        return QYMC;
    }

    public void setQYMC(String QYMC) {
        this.QYMC = QYMC;
    }

    public String getQYEWM() {
        return QYEWM;
    }

    public void setQYEWM(String QYEWM) {
        this.QYEWM = QYEWM;
    }

    public String getQYEWMZT() {
        return QYEWMZT;
    }

    public void setQYEWMZT(String QYEWMZT) {
        this.QYEWMZT = QYEWMZT;
    }

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }

    public String getQYNFC() {
        return QYNFC;
    }

    public void setQYNFC(String QYNFC) {
        this.QYNFC = QYNFC;
    }

    public String getQYNFCZT() {
        return QYNFCZT;
    }

    public void setQYNFCZT(String QYNFCZT) {
        this.QYNFCZT = QYNFCZT;
    }

    public List<QYDDATABean> getQYD_DATA() {
        return QYD_DATA;
    }

    public void setQYD_DATA(List<QYDDATABean> QYD_DATA) {
        this.QYD_DATA = QYD_DATA;
    }

    public List<QYAQFXDATABean> getQYAQFX_DATA() {
        return QYAQFX_DATA;
    }

    public void setQYAQFX_DATA(List<QYAQFXDATABean> QYAQFX_DATA) {
        this.QYAQFX_DATA = QYAQFX_DATA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(QYBH);
        dest.writeString(QYMC);
        dest.writeString(QYEWM);
        dest.writeString(QYEWMZT);
        dest.writeString(GWID);
        dest.writeString(QYNFC);
        dest.writeString(QYNFCZT);
        dest.writeTypedList(QYD_DATA);
        dest.writeTypedList(QYAQFX_DATA);
        dest.writeInt(SN);
    }
}
