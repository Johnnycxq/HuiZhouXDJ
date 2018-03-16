package com.rehome.huizhouxdj.bean;

/**
 * Created by Administrator on 2016/8/22.
 */
public class DlbInfo {

    private int xh;
    private String dian;
    private boolean statu;
    private String cjjg;


    public DlbInfo() {
    }

    public DlbInfo(int xh, String dian, boolean statu) {
        this.xh = xh;
        this.dian = dian;
        this.statu = statu;
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

}
