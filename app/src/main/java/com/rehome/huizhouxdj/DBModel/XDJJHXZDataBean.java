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
    private String GWMC;
    private String countPercent;    //已检/总数
    private boolean isChecked;


    public String getCountPercent() {
        return countPercent;
    }

    public void setCountPercent(String countPercent) {
        this.countPercent = countPercent;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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
        isChecked = in.readByte() != 0;
        countPercent = in.readString();
        GWMC = in.readString();
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


    public String getGWMC() {
        return GWMC;
    }

    public void setGWMC(String GWMC) {
        this.GWMC = GWMC;
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
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeString(countPercent);
        dest.writeString(GWMC);
    }
}
