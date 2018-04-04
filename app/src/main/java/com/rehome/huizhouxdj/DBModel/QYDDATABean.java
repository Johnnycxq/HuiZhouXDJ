package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class QYDDATABean extends DataSupport implements Parcelable {

    private long id;
    private String SCID;
    private String SBMC;
    private String SBID;
    private String BJMC;
    private String DID;
    private String DMC;
    private String BZZ;
    private String SJMC;
    private String SJDW;
    private String JCFS;
    private String LRFS;
    private String LRMRZ;
    private String DJ_ST;
    private String DJ_ET;
    private XDJJHXZDataBean xdjjhxzDataBean;
    private boolean checked;//是否已经检查
    private boolean uploaded;//是否上传
    private boolean deleted;//true 已删除，false 未删除
    private boolean SMFX;//扫描方式，0  NFC  1 一维码二维码
    private String CJJG;
    private String DATE;//保存时间
    private String GWMC;
    private String GWID;
    private String QYBH;
    private String QYEWM;
    private String QYNFC;


    public QYDDATABean() {
    }


    protected QYDDATABean(Parcel in) {
        SCID = in.readString();
        SBMC = in.readString();
        SBID = in.readString();
        BJMC = in.readString();
        DID = in.readString();
        DMC = in.readString();
        BZZ = in.readString();
        SJMC = in.readString();
        SJDW = in.readString();
        JCFS = in.readString();
        LRFS = in.readString();
        LRMRZ = in.readString();
        DJ_ST = in.readString();
        DJ_ET = in.readString();
        checked = in.readByte() != 0;
        uploaded = in.readByte() != 0;
        deleted = in.readByte() != 0;
        SMFX = in.readByte() != 0;
        CJJG = in.readString();
        DATE = in.readString();
        GWMC = in.readString();
        GWID = in.readString();
        QYBH = in.readString();
        QYEWM = in.readString();
        QYNFC = in.readString();
    }

    public static final Creator<QYDDATABean> CREATOR = new Creator<QYDDATABean>() {
        @Override
        public QYDDATABean createFromParcel(Parcel in) {
            return new QYDDATABean(in);
        }

        @Override
        public QYDDATABean[] newArray(int size) {
            return new QYDDATABean[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public XDJJHXZDataBean getXdjjhxzDataBean() {
        return xdjjhxzDataBean;
    }

    public void setXdjjhxzDataBean(XDJJHXZDataBean xdjjhxzDataBean) {
        this.xdjjhxzDataBean = xdjjhxzDataBean;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSCID() {
        return SCID;
    }

    public void setSCID(String SCID) {
        this.SCID = SCID;
    }

    public String getSBMC() {
        return SBMC;
    }

    public void setSBMC(String SBMC) {
        this.SBMC = SBMC;
    }

    public String getSBID() {
        return SBID;
    }

    public void setSBID(String SBID) {
        this.SBID = SBID;
    }

    public String getBJMC() {
        return BJMC;
    }

    public void setBJMC(String BJMC) {
        this.BJMC = BJMC;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }

    public String getDMC() {
        return DMC;
    }

    public void setDMC(String DMC) {
        this.DMC = DMC;
    }

    public String getBZZ() {
        return BZZ;
    }

    public void setBZZ(String BZZ) {
        this.BZZ = BZZ;
    }

    public String getSJMC() {
        return SJMC;
    }

    public void setSJMC(String SJMC) {
        this.SJMC = SJMC;
    }

    public String getSJDW() {
        return SJDW;
    }

    public void setSJDW(String SJDW) {
        this.SJDW = SJDW;
    }

    public String getJCFS() {
        return JCFS;
    }

    public void setJCFS(String JCFS) {
        this.JCFS = JCFS;
    }

    public String getLRFS() {
        return LRFS;
    }

    public void setLRFS(String LRFS) {
        this.LRFS = LRFS;
    }

    public String getLRMRZ() {
        return LRMRZ;
    }

    public void setLRMRZ(String LRMRZ) {
        this.LRMRZ = LRMRZ;
    }

    public String getDJ_ST() {
        return DJ_ST;
    }

    public void setDJ_ST(String DJ_ST) {
        this.DJ_ST = DJ_ST;
    }

    public String getDJ_ET() {
        return DJ_ET;
    }

    public void setDJ_ET(String DJ_ET) {
        this.DJ_ET = DJ_ET;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isSMFX() {
        return SMFX;
    }

    public void setSMFX(boolean SMFX) {
        this.SMFX = SMFX;
    }

    public String getCJJG() {
        return CJJG;
    }

    public void setCJJG(String CJJG) {
        this.CJJG = CJJG;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getGWMC() {
        return GWMC;
    }

    public void setGWMC(String GWMC) {
        this.GWMC = GWMC;
    }

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }

    public String getQYBH() {
        return QYBH;
    }

    public void setQYBH(String QYBH) {
        this.QYBH = QYBH;
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
        dest.writeString(SCID);
        dest.writeString(SBMC);
        dest.writeString(SBID);
        dest.writeString(BJMC);
        dest.writeString(DID);
        dest.writeString(DMC);
        dest.writeString(BZZ);
        dest.writeString(SJMC);
        dest.writeString(SJDW);
        dest.writeString(JCFS);
        dest.writeString(LRFS);
        dest.writeString(LRMRZ);
        dest.writeString(DJ_ST);
        dest.writeString(DJ_ET);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.uploaded ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.SMFX ? (byte) 1 : (byte) 0);
        dest.writeString(CJJG);
        dest.writeString(DATE);
        dest.writeString(GWMC);
        dest.writeString(GWID);
        dest.writeString(QYBH);
        dest.writeString(QYEWM);
        dest.writeString(QYNFC);
    }
}