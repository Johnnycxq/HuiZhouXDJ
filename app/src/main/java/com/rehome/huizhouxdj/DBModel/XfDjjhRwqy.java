package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfDjjhRwqy extends DataSupport implements Parcelable {

    private int id;
    private String BH;
    private String XFTYPE;
    private String XFWZ;
    private String XFNAME;
    private String QYNAME;
    private String XHNUM;
    private String XMID;
    private String XFID;
    private String QYID;
    private String JHID;
    private String NEXTTIME;
    private String SL1;
    private String SL2;
    private String BZXQ;
    private XfDjjhRwqyList xfDjjhRwqyList;
    private boolean checked;
    private String CJJG;
    private String iszc;//0不正常，1正常
    private String TXMBH;
    private String QYNFC;
    private String CJR;
    private String CJSJ;
    private String SMFX;

    public String getSMFX() {
        return SMFX;
    }

    public void setSMFX(String SMFX) {
        this.SMFX = SMFX;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getCJR() {
        return CJR;
    }

    public void setCJR(String CJR) {
        this.CJR = CJR;
    }

    public String getTXMBH() {
        return TXMBH;
    }

    public void setTXMBH(String TXMBH) {
        this.TXMBH = TXMBH;
    }

    public String getQYNFC() {
        return QYNFC;
    }

    public void setQYNFC(String QYNFC) {
        this.QYNFC = QYNFC;
    }

    public String getIszc() {
        return iszc;
    }

    public void setIszc(String iszc) {
        this.iszc = iszc;
    }

    public String getCJJG() {
        return CJJG;
    }

    public void setCJJG(String CJJG) {
        this.CJJG = CJJG;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public XfDjjhRwqyList getXfDjjhRwqyList() {
        return xfDjjhRwqyList;
    }

    public void setXfDjjhRwqyList(XfDjjhRwqyList xfDjjhRwqyList) {
        this.xfDjjhRwqyList = xfDjjhRwqyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getXFTYPE() {
        return XFTYPE;
    }

    public void setXFTYPE(String XFTYPE) {
        this.XFTYPE = XFTYPE;
    }

    public String getXFWZ() {
        return XFWZ;
    }

    public void setXFWZ(String XFWZ) {
        this.XFWZ = XFWZ;
    }

    public String getXFNAME() {
        return XFNAME;
    }

    public void setXFNAME(String XFNAME) {
        this.XFNAME = XFNAME;
    }

    public String getQYNAME() {
        return QYNAME;
    }

    public void setQYNAME(String QYNAME) {
        this.QYNAME = QYNAME;
    }

    public String getXHNUM() {
        return XHNUM;
    }

    public void setXHNUM(String XHNUM) {
        this.XHNUM = XHNUM;
    }

    public String getXMID() {
        return XMID;
    }

    public void setXMID(String XMID) {
        this.XMID = XMID;
    }

    public String getXFID() {
        return XFID;
    }

    public void setXFID(String XFID) {
        this.XFID = XFID;
    }

    public String getQYID() {
        return QYID;
    }

    public void setQYID(String QYID) {
        this.QYID = QYID;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getNEXTTIME() {
        return NEXTTIME;
    }

    public void setNEXTTIME(String NEXTTIME) {
        this.NEXTTIME = NEXTTIME;
    }

    public String getSL1() {
        return SL1;
    }

    public void setSL1(String SL1) {
        this.SL1 = SL1;
    }

    public String getSL2() {
        return SL2;
    }

    public void setSL2(String SL2) {
        this.SL2 = SL2;
    }

    public String getBZXQ() {
        return BZXQ;
    }

    public void setBZXQ(String BZXQ) {
        this.BZXQ = BZXQ;
    }

    public XfDjjhRwqy() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.BH);
        dest.writeString(this.XFTYPE);
        dest.writeString(this.XFWZ);
        dest.writeString(this.XFNAME);
        dest.writeString(this.QYNAME);
        dest.writeString(this.XHNUM);
        dest.writeString(this.XMID);
        dest.writeString(this.XFID);
        dest.writeString(this.QYID);
        dest.writeString(this.JHID);
        dest.writeString(this.NEXTTIME);
        dest.writeString(this.SL1);
        dest.writeString(this.SL2);
        dest.writeString(this.BZXQ);
        dest.writeParcelable(this.xfDjjhRwqyList, flags);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeString(this.CJJG);
        dest.writeString(this.iszc);
        dest.writeString(this.TXMBH);
        dest.writeString(this.QYNFC);
    }

    protected XfDjjhRwqy(Parcel in) {
        this.id = in.readInt();
        this.BH = in.readString();
        this.XFTYPE = in.readString();
        this.XFWZ = in.readString();
        this.XFNAME = in.readString();
        this.QYNAME = in.readString();
        this.XHNUM = in.readString();
        this.XMID = in.readString();
        this.XFID = in.readString();
        this.QYID = in.readString();
        this.JHID = in.readString();
        this.NEXTTIME = in.readString();
        this.SL1 = in.readString();
        this.SL2 = in.readString();
        this.BZXQ = in.readString();
        this.xfDjjhRwqyList = in.readParcelable(XfDjjhRwqyList.class.getClassLoader());
        this.checked = in.readByte() != 0;
        this.CJJG = in.readString();
        this.iszc = in.readString();
        this.TXMBH = in.readString();
        this.QYNFC = in.readString();
    }

    public static final Creator<XfDjjhRwqy> CREATOR = new Creator<XfDjjhRwqy>() {
        @Override
        public XfDjjhRwqy createFromParcel(Parcel source) {
            return new XfDjjhRwqy(source);
        }

        @Override
        public XfDjjhRwqy[] newArray(int size) {
            return new XfDjjhRwqy[size];
        }
    };
}
