package com.rehome.huizhouxdj.bean;

/**
 * Created by Administrator on 2016/8/22.
 */
public class DlbInfo {

    private int xh;
    private String dian;
    private boolean statu;
    private String cjjg;
    private String sbid;//后来加上的设备id


    public DlbInfo() {
    }

    public DlbInfo(int xh, String dian, boolean statu, String sbid) {
        this.xh = xh;
        this.dian = dian;
        this.statu = statu;
        this.sbid = sbid;
    }

    public String getCjjg() {
        return cjjg;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getDian() {
        return dian;
    }

    public void setDian(String dian) {
        this.dian = dian;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }
}
