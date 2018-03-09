package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class QYDDATABean extends DataSupport implements Parcelable {

    /**
     * SCID : B17A736A283C47999347F8E01CB5C1A6
     * SBMC : 测试设备1
     * BJMC : 部件2
     * DID : 5C556F3D44DF420BB8D34593842AF8D2
     * DMC : 消防测试点3_SBL1
     * BZZ : 标准值
     * SJMC : 数据名称
     * SJDW : 数据单位
     * JCFS : 检查方式
     * XMZQ : 1
     * LRFS : 1
     * LRMRZ :
     */

    private long id;
    private String SCID;
    private String SBMC;
    private String BJMC;
    private String DID;
    private String DMC;
    private String BZZ;
    private String SJMC;
    private String SJDW;
    private String JCFS;
    private String XMZQ;
    private String LRFS;
    private String LRMRZ;
    private XDJJHXZDataBean xdjjhxzDataBean;


    public QYDDATABean() {
    }

    protected QYDDATABean(Parcel in) {
        SCID = in.readString();
        SBMC = in.readString();
        BJMC = in.readString();
        DID = in.readString();
        DMC = in.readString();
        BZZ = in.readString();
        SJMC = in.readString();
        SJDW = in.readString();
        JCFS = in.readString();
        XMZQ = in.readString();
        LRFS = in.readString();
        LRMRZ = in.readString();
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

    public String getXMZQ() {
        return XMZQ;
    }

    public void setXMZQ(String XMZQ) {
        this.XMZQ = XMZQ;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(SCID);
        dest.writeString(SBMC);
        dest.writeString(BJMC);
        dest.writeString(DID);
        dest.writeString(DMC);
        dest.writeString(BZZ);
        dest.writeString(SJMC);
        dest.writeString(SJDW);
        dest.writeString(JCFS);
        dest.writeString(XMZQ);
        dest.writeString(LRFS);
        dest.writeString(LRMRZ);
    }
}