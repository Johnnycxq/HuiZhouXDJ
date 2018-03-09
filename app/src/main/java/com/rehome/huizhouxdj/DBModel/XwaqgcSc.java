package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/8.
 */

public class XwaqgcSc extends DataSupport implements Parcelable {

    private String GZDD;//: "1号炉0米层区域",
    private String CBS;// "承包商2",
    private String BGCR;//: "李四",
    private String BT;// "1.员工的反应",
    private String XBT;//"观察到人员的异常反应",
    private String NR;//"调整个人防护装备、遮掩/离开",
    private String JCSJ;//":"2016-11-08 21:04",
    private String JCR;//":"180021",
    private String JHID;//":"GCJH00000000001"
    private String SMFX;//扫描条码"   "NFC标签"
    private String GZP;//工作票
    private String GZNR;//工作内容
    private String GCID;//观察ID，以时间作为ID
    private String XWJLJSID;//记录ID

    public String getXWJLJSID() {
        return XWJLJSID;
    }

    public void setXWJLJSID(String XWJLJSID) {
        this.XWJLJSID = XWJLJSID;
    }

    public String getGCID() {
        return GCID;
    }

    public void setGCID(String GCID) {
        this.GCID = GCID;
    }

    public String getGZP() {
        return GZP;
    }

    public void setGZP(String GZP) {
        this.GZP = GZP;
    }

    public String getGZNR() {
        return GZNR;
    }

    public void setGZNR(String GZNR) {
        this.GZNR = GZNR;
    }

    public String getSMFX() {
        return SMFX;
    }

    public void setSMFX(String SMFX) {
        this.SMFX = SMFX;
    }

    public String getGZDD() {
        return GZDD;
    }

    public void setGZDD(String GZDD) {
        this.GZDD = GZDD;
    }

    public String getCBS() {
        return CBS;
    }

    public void setCBS(String CBS) {
        this.CBS = CBS;
    }

    public String getBGCR() {
        return BGCR;
    }

    public void setBGCR(String BGCR) {
        this.BGCR = BGCR;
    }

    public String getBT() {
        return BT;
    }

    public void setBT(String BT) {
        this.BT = BT;
    }

    public String getXBT() {
        return XBT;
    }

    public void setXBT(String XBT) {
        this.XBT = XBT;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public String getJCSJ() {
        return JCSJ;
    }

    public void setJCSJ(String JCSJ) {
        this.JCSJ = JCSJ;
    }

    public String getJCR() {
        return JCR;
    }

    public void setJCR(String JCR) {
        this.JCR = JCR;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public XwaqgcSc() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.GZDD);
        dest.writeString(this.CBS);
        dest.writeString(this.BGCR);
        dest.writeString(this.BT);
        dest.writeString(this.XBT);
        dest.writeString(this.NR);
        dest.writeString(this.JCSJ);
        dest.writeString(this.JCR);
        dest.writeString(this.JHID);
        dest.writeString(this.SMFX);
        dest.writeString(this.GZP);
        dest.writeString(this.GZNR);
        dest.writeString(this.GCID);
        dest.writeString(this.XWJLJSID);
    }

    protected XwaqgcSc(Parcel in) {
        this.GZDD = in.readString();
        this.CBS = in.readString();
        this.BGCR = in.readString();
        this.BT = in.readString();
        this.XBT = in.readString();
        this.NR = in.readString();
        this.JCSJ = in.readString();
        this.JCR = in.readString();
        this.JHID = in.readString();
        this.SMFX = in.readString();
        this.GZP = in.readString();
        this.GZNR = in.readString();
        this.GCID = in.readString();
        this.XWJLJSID = in.readString();
    }

    public static final Creator<XwaqgcSc> CREATOR = new Creator<XwaqgcSc>() {
        public XwaqgcSc createFromParcel(Parcel source) {
            return new XwaqgcSc(source);
        }

        public XwaqgcSc[] newArray(int size) {
            return new XwaqgcSc[size];
        }
    };
}
