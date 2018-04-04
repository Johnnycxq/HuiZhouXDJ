package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny_Chen
 * on 2018/3/1.
 */

public class QYAQFXDATABean extends DataSupport implements Parcelable {
    /**
     * AQFXID : D3F75475F4D14A25BCBC1C798ED38368
     * FXLX : 安全风险测试
     * FXMS : 风险描述
     * FHCS : 防护措施
     */

    private long id;
    private String AQFXID;
    private String FXLX;
    private String FXMS;
    private String FHCS;
    private XDJJHXZDataBean xdjjhxzDataBean;
    private String QYEWM;
    private String QYNFC;


    public QYAQFXDATABean() {
    }

    protected QYAQFXDATABean(Parcel in) {
        AQFXID = in.readString();
        FXLX = in.readString();
        FXMS = in.readString();
        FHCS = in.readString();
        QYEWM = in.readString();
        QYNFC = in.readString();

    }

    public static final Creator<QYAQFXDATABean> CREATOR = new Creator<QYAQFXDATABean>() {
        @Override
        public QYAQFXDATABean createFromParcel(Parcel in) {
            return new QYAQFXDATABean(in);
        }

        @Override
        public QYAQFXDATABean[] newArray(int size) {
            return new QYAQFXDATABean[size];
        }
    };

    public void setId(long id) {
        this.id = id;
    }

    public XDJJHXZDataBean getXdjjhxzDataBean() {
        return xdjjhxzDataBean;
    }

    public void setXdjjhxzDataBean(XDJJHXZDataBean xdjjhxzDataBean) {
        this.xdjjhxzDataBean = xdjjhxzDataBean;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAQFXID() {
        return AQFXID;
    }

    public void setAQFXID(String AQFXID) {
        this.AQFXID = AQFXID;
    }

    public String getFXLX() {
        return FXLX;
    }

    public void setFXLX(String FXLX) {
        this.FXLX = FXLX;
    }

    public String getFXMS() {
        return FXMS;
    }

    public void setFXMS(String FXMS) {
        this.FXMS = FXMS;
    }

    public String getFHCS() {
        return FHCS;
    }

    public void setFHCS(String FHCS) {
        this.FHCS = FHCS;
    }

    public String getQYEWM() {
        return QYEWM;
    }

    public void setQYEWM(String QYEWM) {
        this.QYEWM = QYEWM;
    }

    public String getQYNFC() {
        return QYNFC;
    }

    public void setQYNFC(String QYNFC) {
        this.QYNFC = QYNFC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AQFXID);
        dest.writeString(FXLX);
        dest.writeString(FXMS);
        dest.writeString(FHCS);
        dest.writeString(QYEWM);
        dest.writeString(QYNFC);
    }
}
