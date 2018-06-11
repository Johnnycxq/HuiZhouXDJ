package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class XSJJHXZDataBean extends DataSupport implements Parcelable {


    private long id;
    private String zxid;
    private String qybh;
    private String qymc;
    private String nfcbm;
    private String txm;
    private String sczt;
    private String scsj;
    private List<XSJJHDataBean> data;
    private int SN; //自定义序号
    private boolean isChecked;
    private String countPercent;    //已检/总数
    private String jhmc;
    private String sbmc;


    private String SBMCSTATE;//设备状态
    private String SBMCSTATEVALUE;//设备状态值

    private String TJXJZT;//1停用不需要检查了  0停用也要检查


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getNfcbm() {
        return nfcbm;
    }

    public void setNfcbm(String nfcbm) {
        this.nfcbm = nfcbm;
    }

    public String getTxm() {
        return txm;
    }

    public void setTxm(String txm) {
        this.txm = txm;
    }

    public String getSczt() {
        return sczt;
    }

    public void setSczt(String sczt) {
        this.sczt = sczt;
    }

    public String getScsj() {
        return scsj;
    }

    public void setScsj(String scsj) {
        this.scsj = scsj;
    }

    public List<XSJJHDataBean> getData() {
        return data;
    }

    public void setData(List<XSJJHDataBean> data) {
        this.data = data;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCountPercent() {
        return countPercent;
    }

    public void setCountPercent(String countPercent) {
        this.countPercent = countPercent;
    }

    public String getJhmc() {
        return jhmc;
    }

    public void setJhmc(String jhmc) {
        this.jhmc = jhmc;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getSBMCSTATE() {
        return SBMCSTATE;
    }

    public void setSBMCSTATE(String SBMCSTATE) {
        this.SBMCSTATE = SBMCSTATE;
    }

    public String getSBMCSTATEVALUE() {
        return SBMCSTATEVALUE;
    }

    public void setSBMCSTATEVALUE(String SBMCSTATEVALUE) {
        this.SBMCSTATEVALUE = SBMCSTATEVALUE;
    }

    public String getTJXJZT() {
        return TJXJZT;
    }

    public void setTJXJZT(String TJXJZT) {
        this.TJXJZT = TJXJZT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.zxid);
        dest.writeString(this.qybh);
        dest.writeString(this.qymc);
        dest.writeString(this.nfcbm);
        dest.writeString(this.txm);
        dest.writeString(this.sczt);
        dest.writeString(this.scsj);
        dest.writeTypedList(this.data);
        dest.writeInt(this.SN);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeString(this.countPercent);
        dest.writeString(this.jhmc);
        dest.writeString(this.sbmc);
        dest.writeString(this.SBMCSTATE);
        dest.writeString(this.SBMCSTATEVALUE);
        dest.writeString(this.TJXJZT);
    }

    public XSJJHXZDataBean() {
    }

    protected XSJJHXZDataBean(Parcel in) {
        this.id = in.readLong();
        this.zxid = in.readString();
        this.qybh = in.readString();
        this.qymc = in.readString();
        this.nfcbm = in.readString();
        this.txm = in.readString();
        this.sczt = in.readString();
        this.scsj = in.readString();
        this.data = in.createTypedArrayList(XSJJHDataBean.CREATOR);
        this.SN = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.countPercent = in.readString();
        this.jhmc = in.readString();
        this.sbmc = in.readString();
        this.SBMCSTATE = in.readString();
        this.SBMCSTATEVALUE = in.readString();
        this.TJXJZT = in.readString();

    }

    public static final Creator<XSJJHXZDataBean> CREATOR = new Creator<XSJJHXZDataBean>() {
        @Override
        public XSJJHXZDataBean createFromParcel(Parcel source) {
            return new XSJJHXZDataBean(source);
        }

        @Override
        public XSJJHXZDataBean[] newArray(int size) {
            return new XSJJHXZDataBean[size];
        }
    };
}
