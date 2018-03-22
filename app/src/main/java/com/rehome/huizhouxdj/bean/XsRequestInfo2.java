package com.rehome.huizhouxdj.bean;

/**
 * Created by Rehome-rjb1 on 2017/7/15.
 * 巡视请求数据
 */

public class XsRequestInfo2 {


    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    /**
     * action : XSCB_ZXJL_GET
     * zc : 值次（班组编号）
     * jhid : 计划ID
     */

    private String Action;
    private String zc;

    private String YHID;
    private String BMID;
    private String RWZT;

    private String JHID;
    private String zymc;
    private String zxid;
    private String bglx;
    private String spzt;
    private String rownum;
    private String zxmc;
    private String zy;
    private String rqts;
    private String moduletype;
    private String shmk;
    private String userid;
    private String spr;
    private String sis;
    private String jz;

    public String getYHID() {
        return YHID;
    }

    public void setYHID(String YHID) {
        this.YHID = YHID;
    }


    public String getRWZT() {
        return RWZT;
    }

    public void setRWZT(String RWZT) {
        this.RWZT = RWZT;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }


    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }


    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public String getBglx() {
        return bglx;
    }

    public void setBglx(String bglx) {
        this.bglx = bglx;
    }

    public String getSpzt() {
        return spzt;
    }

    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getZxmc() {
        return zxmc;
    }

    public void setZxmc(String zxmc) {
        this.zxmc = zxmc;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getRqts() {
        return rqts;
    }

    public void setRqts(String rqts) {
        this.rqts = rqts;
    }

    public String getModuletype() {
        return moduletype;
    }

    public void setModuletype(String moduletype) {
        this.moduletype = moduletype;
    }

    public String getShmk() {
        return shmk;
    }

    public void setShmk(String shmk) {
        this.shmk = shmk;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public String getSis() {
        return sis;
    }

    public void setSis(String sis) {
        this.sis = sis;
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz;
    }
}
