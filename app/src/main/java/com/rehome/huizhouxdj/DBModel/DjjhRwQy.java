package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * 点检计划任务区域
 */

public class DjjhRwQy extends DataSupport implements Parcelable {

    private int id;
    private DjjhRwQyList djjhRwQyList;
    private String POINTNUM;
    private String JHID;
    private String JHMC;
    private String NFCBH;
    private String BQBM;
    private String JHLX;
    private String POINTNAME;
    private String MEAAREA;
    private String AREACODE;
    private String SBMC;
    private String SBBH;
    private String BJMC;
    private String BJBH;
    private String MEAMETHOD;
    private String JCLB;
    private String JCBZ;
    private String LRLX;
    private String LRNR;
    private String ASSETNUM;//设备编号
    private String UNITOFMEASURE;
    private String DESCRIPTION;
    private String LOWERWARNING;
    private String UPPERWARNING;
    private String LOWERACTION;
    private String UPPERACTION;
    private String MEAPOS;
    private String MEASTATUS;
    private String MEASTANDARD;
    private boolean checked;//是否已经检查
    private String CJJG;
    private String fxnr;
    private boolean uploaded;
    private boolean deleted;//true 已删除，false 未删除
    private String JHDW;
    private boolean SMFX;//扫描方式，0  NFC  1 一维码二维码
    private boolean SBZT = true;//
    private String DATE;//保存时间
    private boolean BYZT;//备用状态
    private String SCID;


    public String getSBMC() {
        return SBMC;
    }

    public void setSBMC(String SBMC) {
        this.SBMC = SBMC;
    }

    public String getSBBH() {
        return SBBH;
    }

    public void setSBBH(String SBBH) {
        this.SBBH = SBBH;
    }

    public String getBJMC() {
        return BJMC;
    }

    public void setBJMC(String BJMC) {
        this.BJMC = BJMC;
    }

    public String getBJBH() {
        return BJBH;
    }

    public void setBJBH(String BJBH) {
        this.BJBH = BJBH;
    }

    public String getLRLX() {
        return LRLX;
    }

    public void setLRLX(String LRLX) {
        this.LRLX = LRLX;
    }

    public String getLRNR() {
        return LRNR;
    }

    public void setLRNR(String LRNR) {
        this.LRNR = LRNR;
    }


    public String getJCLB() {
        return JCLB;
    }

    public void setJCLB(String JCLB) {
        this.JCLB = JCLB;
    }

    public String getJCBZ() {
        return JCBZ;
    }

    public void setJCBZ(String JCBZ) {
        this.JCBZ = JCBZ;
    }

    public boolean isBYZT() {
        return BYZT;
    }

    public void setBYZT(boolean BYZT) {
        this.BYZT = BYZT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public boolean isSBZT() {
        return SBZT;
    }

    public void setSBZT(boolean SBZT) {
        this.SBZT = SBZT;
    }

    public String getASSETNUM() {
        return ASSETNUM;
    }

    public void setASSETNUM(String ASSETNUM) {
        this.ASSETNUM = ASSETNUM;
    }

    public String getAREACODE() {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE) {
        this.AREACODE = AREACODE;
    }

    public String getUNITOFMEASURE() {
        return UNITOFMEASURE;
    }

    public void setUNITOFMEASURE(String UNITOFMEASURE) {
        this.UNITOFMEASURE = UNITOFMEASURE;
    }

    public boolean isSMFX() {
        return SMFX;
    }

    public void setSMFX(boolean SMFX) {
        this.SMFX = SMFX;
    }

    public String getJHDW() {
        return JHDW;
    }

    public void setJHDW(String JHDW) {
        this.JHDW = JHDW;
    }

    public String getFxnr() {
        return fxnr;
    }

    public void setFxnr(String fxnr) {
        this.fxnr = fxnr;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
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

    public DjjhRwQyList getDjjhRwQyList() {
        return djjhRwQyList;
    }

    public void setDjjhRwQyList(DjjhRwQyList djjhRwQyList) {
        this.djjhRwQyList = djjhRwQyList;
    }

    public String getPOINTNUM() {
        return POINTNUM;
    }

    public void setPOINTNUM(String POINTNUM) {
        this.POINTNUM = POINTNUM;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getJHMC() {
        return JHMC;
    }

    public void setJHMC(String JHMC) {
        this.JHMC = JHMC;
    }

    public String getNFCBH() {
        return NFCBH;
    }

    public void setNFCBH(String NFCBH) {
        this.NFCBH = NFCBH;
    }

    public String getBQBM() {
        return BQBM;
    }

    public void setBQBM(String BQBM) {
        this.BQBM = BQBM;
    }

    public String getJHLX() {
        return JHLX;
    }

    public void setJHLX(String JHLX) {
        this.JHLX = JHLX;
    }

    public String getPOINTNAME() {
        return POINTNAME;
    }

    public void setPOINTNAME(String POINTNAME) {
        this.POINTNAME = POINTNAME;
    }

    public String getMEAAREA() {
        return MEAAREA;
    }

    public void setMEAAREA(String MEAAREA) {
        this.MEAAREA = MEAAREA;
    }

    public String getMEAMETHOD() {
        return MEAMETHOD;
    }

    public void setMEAMETHOD(String MEAMETHOD) {
        this.MEAMETHOD = MEAMETHOD;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOWERWARNING() {
        return LOWERWARNING;
    }

    public void setLOWERWARNING(String LOWERWARNING) {
        this.LOWERWARNING = LOWERWARNING;
    }

    public String getUPPERWARNING() {
        return UPPERWARNING;
    }

    public void setUPPERWARNING(String UPPERWARNING) {
        this.UPPERWARNING = UPPERWARNING;
    }

    public String getLOWERACTION() {
        return LOWERACTION;
    }

    public void setLOWERACTION(String LOWERACTION) {
        this.LOWERACTION = LOWERACTION;
    }

    public String getUPPERACTION() {
        return UPPERACTION;
    }

    public void setUPPERACTION(String UPPERACTION) {
        this.UPPERACTION = UPPERACTION;
    }

    public String getMEAPOS() {
        return MEAPOS;
    }

    public void setMEAPOS(String MEAPOS) {
        this.MEAPOS = MEAPOS;
    }

    public String getMEASTATUS() {
        return MEASTATUS;
    }

    public void setMEASTATUS(String MEASTATUS) {
        this.MEASTATUS = MEASTATUS;
    }

    public String getMEASTANDARD() {
        return MEASTANDARD;
    }

    public void setMEASTANDARD(String MEASTANDARD) {
        this.MEASTANDARD = MEASTANDARD;
    }

    public String getSCID() {
        return SCID;
    }

    public void setSCID(String SCID) {
        this.SCID = SCID;
    }

    public DjjhRwQy() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.djjhRwQyList, flags);
        dest.writeString(this.POINTNUM);
        dest.writeString(this.JHID);
        dest.writeString(this.JHMC);
        dest.writeString(this.NFCBH);
        dest.writeString(this.BQBM);
        dest.writeString(this.JHLX);
        dest.writeString(this.POINTNAME);
        dest.writeString(this.MEAAREA);
        dest.writeString(this.MEAMETHOD);
        dest.writeString(this.DESCRIPTION);
        dest.writeString(this.LOWERWARNING);
        dest.writeString(this.UPPERWARNING);
        dest.writeString(this.LOWERACTION);
        dest.writeString(this.UPPERACTION);
        dest.writeString(this.UNITOFMEASURE);
        dest.writeString(this.MEAPOS);
        dest.writeString(this.MEASTATUS);
        dest.writeString(this.MEASTANDARD);
        dest.writeString(this.SBMC);
        dest.writeString(this.SBBH);
        dest.writeString(this.BJMC);
        dest.writeString(this.BJBH);
        dest.writeString(this.JCLB);
        dest.writeString(this.JCBZ);
        dest.writeString(this.LRLX);
        dest.writeString(this.LRNR);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeString(this.CJJG);
        dest.writeString(this.fxnr);
        dest.writeByte(this.uploaded ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeString(this.JHDW);
        dest.writeByte(this.SMFX ? (byte) 1 : (byte) 0);
        dest.writeString(this.AREACODE);
        dest.writeString(this.ASSETNUM);
        dest.writeByte(this.SBZT ? (byte) 1 : (byte) 0);
        dest.writeString(this.DATE);
        dest.writeByte(this.BYZT ? (byte) 1 : (byte) 0);
        dest.writeString(this.SCID);


    }

    protected DjjhRwQy(Parcel in) {
        this.id = in.readInt();
        this.djjhRwQyList = in.readParcelable(DjjhRwQyList.class.getClassLoader());
        this.POINTNUM = in.readString();
        this.JHID = in.readString();
        this.JHMC = in.readString();
        this.NFCBH = in.readString();
        this.BQBM = in.readString();
        this.JHLX = in.readString();
        this.POINTNAME = in.readString();
        this.MEAAREA = in.readString();
        this.MEAMETHOD = in.readString();
        this.DESCRIPTION = in.readString();
        this.LOWERWARNING = in.readString();
        this.UPPERWARNING = in.readString();
        this.LOWERACTION = in.readString();
        this.UPPERACTION = in.readString();
        this.UNITOFMEASURE = in.readString();
        this.MEAPOS = in.readString();
        this.MEASTATUS = in.readString();
        this.MEASTANDARD = in.readString();
        this.SBMC = in.readString();
        this.SBBH = in.readString();
        this.BJMC = in.readString();
        this.BJBH = in.readString();
        this.JCLB = in.readString();
        this.JCBZ = in.readString();
        this.LRLX = in.readString();
        this.LRNR = in.readString();
        this.checked = in.readByte() != 0;
        this.CJJG = in.readString();
        this.fxnr = in.readString();
        this.uploaded = in.readByte() != 0;
        this.deleted = in.readByte() != 0;
        this.JHDW = in.readString();
        this.SMFX = in.readByte() != 0;
        this.AREACODE = in.readString();
        this.ASSETNUM = in.readString();
        this.SBZT = in.readByte() != 0;
        this.DATE = in.readString();
        this.BYZT = in.readByte() != 0;
        this.SCID = in.readString();
    }

    public static final Creator<DjjhRwQy> CREATOR = new Creator<DjjhRwQy>() {
        @Override
        public DjjhRwQy createFromParcel(Parcel source) {
            return new DjjhRwQy(source);
        }

        @Override
        public DjjhRwQy[] newArray(int size) {
            return new DjjhRwQy[size];
        }
    };
}
