package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class XSJJHDataBean extends DataSupport implements Parcelable {


    private long id;
    private String scid;
    private String dbh;
    private String dlxbh;
    private String dlxmc;
    private String sbid;
    private String sb;
    private String sbmc;
    private String zymc;
    private String dw;
    private String dz;
    private String gz;
    private String zczt;
    private String bsyl;
    private String bqyl;
    private String xcnr;
    private String cbsz;
    private String djsj;
    private String zcmc;
    private String cbr;
    private String fxnr;
    private String smfs;
    private String dpx;
    private String sisData;
    private String LRFS;
    private String MRNR;
    private XSJJHXZDataBean xsjjhxzDataBean;
    private boolean checked;//是否已经检查
    private boolean uploaded;//是否上传
    private boolean deleted;//true 已删除，false 未删除
    private String DATE;//保存时间
    private String zxid;

    private String SBMCSTATE;//设备状态
    private String SBMCSTATEVALUE;//设备状态值


    private String CJJG;
    private String BJMC;

    private String nfcbm;
    private String txm;

    private String TJXJZT;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getDbh() {
        return dbh;
    }

    public void setDbh(String dbh) {
        this.dbh = dbh;
    }

    public String getDlxbh() {
        return dlxbh;
    }

    public void setDlxbh(String dlxbh) {
        this.dlxbh = dlxbh;
    }

    public String getDlxmc() {
        return dlxmc;
    }

    public void setDlxmc(String dlxmc) {
        this.dlxmc = dlxmc;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }

    public String getZczt() {
        return zczt;
    }

    public void setZczt(String zczt) {
        this.zczt = zczt;
    }

    public String getBsyl() {
        return bsyl;
    }

    public void setBsyl(String bsyl) {
        this.bsyl = bsyl;
    }

    public String getBqyl() {
        return bqyl;
    }

    public void setBqyl(String bqyl) {
        this.bqyl = bqyl;
    }

    public String getXcnr() {
        return xcnr;
    }

    public void setXcnr(String xcnr) {
        this.xcnr = xcnr;
    }

    public String getCbsz() {
        return cbsz;
    }

    public void setCbsz(String cbsz) {
        this.cbsz = cbsz;
    }

    public String getDjsj() {
        return djsj;
    }

    public void setDjsj(String djsj) {
        this.djsj = djsj;
    }

    public String getZcmc() {
        return zcmc;
    }

    public void setZcmc(String zcmc) {
        this.zcmc = zcmc;
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr;
    }

    public String getFxnr() {
        return fxnr;
    }

    public void setFxnr(String fxnr) {
        this.fxnr = fxnr;
    }

    public String getSmfs() {
        return smfs;
    }

    public void setSmfs(String smfs) {
        this.smfs = smfs;
    }

    public String getDpx() {
        return dpx;
    }

    public void setDpx(String dpx) {
        this.dpx = dpx;
    }

    public String getSisData() {
        return sisData;
    }

    public void setSisData(String sisData) {
        this.sisData = sisData;
    }

    public String getLRFS() {
        return LRFS;
    }

    public void setLRFS(String LRFS) {
        this.LRFS = LRFS;
    }

    public String getMRNR() {
        return MRNR;
    }

    public void setMRNR(String MRNR) {
        this.MRNR = MRNR;
    }

    public XSJJHXZDataBean getXsjjhxzDataBean() {
        return xsjjhxzDataBean;
    }

    public void setXsjjhxzDataBean(XSJJHXZDataBean xsjjhxzDataBean) {
        this.xsjjhxzDataBean = xsjjhxzDataBean;
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

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
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

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }

    public String getCJJG() {
        return CJJG;
    }

    public void setCJJG(String CJJG) {
        this.CJJG = CJJG;
    }

    public String getBJMC() {
        return BJMC;
    }

    public void setBJMC(String BJMC) {
        this.BJMC = BJMC;
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
        dest.writeString(this.scid);
        dest.writeString(this.dbh);
        dest.writeString(this.dlxbh);
        dest.writeString(this.dlxmc);
        dest.writeString(this.sbid);
        dest.writeString(this.sb);
        dest.writeString(this.sbmc);
        dest.writeString(this.zymc);
        dest.writeString(this.dw);
        dest.writeString(this.dz);
        dest.writeString(this.gz);
        dest.writeString(this.zczt);
        dest.writeString(this.bsyl);
        dest.writeString(this.bqyl);
        dest.writeString(this.xcnr);
        dest.writeString(this.cbsz);
        dest.writeString(this.djsj);
        dest.writeString(this.zcmc);
        dest.writeString(this.cbr);
        dest.writeString(this.fxnr);
        dest.writeString(this.smfs);
        dest.writeString(this.dpx);
        dest.writeString(this.sisData);
        dest.writeString(this.LRFS);
        dest.writeString(this.MRNR);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.uploaded ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeString(this.DATE);
        dest.writeString(this.zxid);
        dest.writeString(this.SBMCSTATE);
        dest.writeString(this.SBMCSTATEVALUE);
        dest.writeString(this.CJJG);
        dest.writeString(this.BJMC);
        dest.writeString(this.nfcbm);
        dest.writeString(this.txm);
        dest.writeString(this.TJXJZT);
    }

    public XSJJHDataBean() {
    }

    protected XSJJHDataBean(Parcel in) {
        this.id = in.readLong();
        this.scid = in.readString();
        this.dbh = in.readString();
        this.dlxbh = in.readString();
        this.dlxmc = in.readString();
        this.sbid = in.readString();
        this.sb = in.readString();
        this.sbmc = in.readString();
        this.zymc = in.readString();
        this.dw = in.readString();
        this.dz = in.readString();
        this.gz = in.readString();
        this.zczt = in.readString();
        this.bsyl = in.readString();
        this.bqyl = in.readString();
        this.xcnr = in.readString();
        this.cbsz = in.readString();
        this.djsj = in.readString();
        this.zcmc = in.readString();
        this.cbr = in.readString();
        this.fxnr = in.readString();
        this.smfs = in.readString();
        this.dpx = in.readString();
        this.sisData = in.readString();
        this.LRFS = in.readString();
        this.MRNR = in.readString();
        this.checked = in.readByte() != 0;
        this.uploaded = in.readByte() != 0;
        this.deleted = in.readByte() != 0;
        this.DATE = in.readString();
        this.zxid = in.readString();
        this.SBMCSTATE = in.readString();
        this.SBMCSTATEVALUE = in.readString();
        this.CJJG = in.readString();
        this.BJMC = in.readString();
        this.nfcbm = in.readString();
        this.txm = in.readString();
        this.TJXJZT = in.readString();

    }

    public static final Parcelable.Creator<XSJJHDataBean> CREATOR = new Parcelable.Creator<XSJJHDataBean>() {
        @Override
        public XSJJHDataBean createFromParcel(Parcel source) {
            return new XSJJHDataBean(source);
        }

        @Override
        public XSJJHDataBean[] newArray(int size) {
            return new XSJJHDataBean[size];
        }
    };
}
