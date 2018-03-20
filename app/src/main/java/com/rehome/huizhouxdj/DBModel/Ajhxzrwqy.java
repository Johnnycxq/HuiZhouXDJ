package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/7.
 */

public class Ajhxzrwqy extends DataSupport implements Parcelable {

    private String YSID;
    private String JHID;
    private String BZMC;
    private String JHMC;
    private String JCNY;
    private String NFCBH;
    private String BQBM;
    private String AREACODE;
    private String AREANAME;
    private String XCZQ;
    private AjhjhxzrwList list;
    private boolean checked;
    private String MS;
    private String SCRN;//是或者否
    private boolean SFHG;//是否合格
    private String DATE;//时间
    private boolean SMFX;//扫描方式，0  NFC  1 一维码二维码

    public boolean isSMFX() {
        return SMFX;
    }

    public void setSMFX(boolean SMFX) {
        this.SMFX = SMFX;
    }

    public String getAREACODE() {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE) {
        this.AREACODE = AREACODE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getXCZQ() {
        return XCZQ;
    }

    public void setXCZQ(String XCZQ) {
        this.XCZQ = XCZQ;
    }

    public String getAREANAME() {
        return AREANAME;
    }

    public void setAREANAME(String AREANAME) {
        this.AREANAME = AREANAME;
    }

    public boolean isSFHG() {
        return SFHG;
    }

    public void setSFHG(boolean SFHG) {
        this.SFHG = SFHG;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public String getSCRN() {
        return SCRN;
    }

    public void setSCRN(String SCRN) {
        this.SCRN = SCRN;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public AjhjhxzrwList getList() {
        return list;
    }

    public void setList(AjhjhxzrwList list) {
        this.list = list;
    }

    public String getYSID() {
        return YSID;
    }

    public void setYSID(String YSID) {
        this.YSID = YSID;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getBZMC() {
        return BZMC;
    }

    public void setBZMC(String BZMC) {
        this.BZMC = BZMC;
    }

    public String getJHMC() {
        return JHMC;
    }

    public void setJHMC(String JHMC) {
        this.JHMC = JHMC;
    }

    public String getJCNY() {
        return JCNY;
    }

    public void setJCNY(String JCNY) {
        this.JCNY = JCNY;
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


    public Ajhxzrwqy() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.YSID);
        dest.writeString(this.JHID);
        dest.writeString(this.BZMC);
        dest.writeString(this.JHMC);
        dest.writeString(this.JCNY);
        dest.writeString(this.NFCBH);
        dest.writeString(this.BQBM);
        dest.writeString(this.AREACODE);
        dest.writeString(this.AREANAME);
        dest.writeString(this.XCZQ);
        dest.writeParcelable(this.list, flags);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeString(this.MS);
        dest.writeString(this.SCRN);
        dest.writeByte(this.SFHG ? (byte) 1 : (byte) 0);
        dest.writeString(this.DATE);
        dest.writeByte(this.SMFX ? (byte) 1 : (byte) 0);
    }

    protected Ajhxzrwqy(Parcel in) {
        this.YSID = in.readString();
        this.JHID = in.readString();
        this.BZMC = in.readString();
        this.JHMC = in.readString();
        this.JCNY = in.readString();
        this.NFCBH = in.readString();
        this.BQBM = in.readString();
        this.AREACODE = in.readString();
        this.AREANAME = in.readString();
        this.XCZQ = in.readString();
        this.list = in.readParcelable(AjhjhxzrwList.class.getClassLoader());
        this.checked = in.readByte() != 0;
        this.MS = in.readString();
        this.SCRN = in.readString();
        this.SFHG = in.readByte() != 0;
        this.DATE = in.readString();
        this.SMFX = in.readByte() != 0;
    }

    public static final Creator<Ajhxzrwqy> CREATOR = new Creator<Ajhxzrwqy>() {
        @Override
        public Ajhxzrwqy createFromParcel(Parcel source) {
            return new Ajhxzrwqy(source);
        }

        @Override
        public Ajhxzrwqy[] newArray(int size) {
            return new Ajhxzrwqy[size];
        }
    };
}
