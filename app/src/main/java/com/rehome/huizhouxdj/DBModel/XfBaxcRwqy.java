package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/11/5.
 */

public class XfBaxcRwqy extends DataSupport implements Parcelable {

    private int id;
    private String jhid;
    private String xgid;
    private String xgtype;
    private String bh;
    private String qyname;
    private String qynfc;
    private String qywz;
    private XfBaxcRwqyList xfBaxcRwqyList;
    private boolean checked;
    private String txmbh;
    private String smfx = "";//检查方式
    private String cjsj;//采集时间
    private String cjr;//采集人

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getSmfx() {
        return smfx;
    }

    public void setSmfx(String smfx) {
        this.smfx = smfx;
    }

    public String getTxmbh() {
        return txmbh;
    }

    public void setTxmbh(String txmbh) {
        this.txmbh = txmbh;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XfBaxcRwqyList getXfBaxcRwqyList() {
        return xfBaxcRwqyList;
    }

    public void setXfBaxcRwqyList(XfBaxcRwqyList xfBaxcRwqyList) {
        this.xfBaxcRwqyList = xfBaxcRwqyList;
    }

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getXgid() {
        return xgid;
    }

    public void setXgid(String xgid) {
        this.xgid = xgid;
    }

    public String getXgtype() {
        return xgtype;
    }

    public void setXgtype(String xgtype) {
        this.xgtype = xgtype;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getQyname() {
        return qyname;
    }

    public void setQyname(String qyname) {
        this.qyname = qyname;
    }

    public String getQynfc() {
        return qynfc;
    }

    public void setQynfc(String qynfc) {
        this.qynfc = qynfc;
    }

    public String getQywz() {
        return qywz;
    }

    public void setQywz(String qywz) {
        this.qywz = qywz;
    }

    public XfBaxcRwqy() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.jhid);
        dest.writeString(this.xgid);
        dest.writeString(this.xgtype);
        dest.writeString(this.bh);
        dest.writeString(this.qyname);
        dest.writeString(this.qynfc);
        dest.writeString(this.qywz);
        dest.writeParcelable(this.xfBaxcRwqyList, flags);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeString(this.txmbh);
        dest.writeString(this.smfx);
    }

    protected XfBaxcRwqy(Parcel in) {
        this.id = in.readInt();
        this.jhid = in.readString();
        this.xgid = in.readString();
        this.xgtype = in.readString();
        this.bh = in.readString();
        this.qyname = in.readString();
        this.qynfc = in.readString();
        this.qywz = in.readString();
        this.xfBaxcRwqyList = in.readParcelable(XfBaxcRwqyList.class.getClassLoader());
        this.checked = in.readByte() != 0;
        this.txmbh = in.readString();
        this.smfx = in.readString();
    }

    public static final Creator<XfBaxcRwqy> CREATOR = new Creator<XfBaxcRwqy>() {
        @Override
        public XfBaxcRwqy createFromParcel(Parcel source) {
            return new XfBaxcRwqy(source);
        }

        @Override
        public XfBaxcRwqy[] newArray(int size) {
            return new XfBaxcRwqy[size];
        }
    };
}
