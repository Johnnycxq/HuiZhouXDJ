package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfDjjh extends DataSupport {

    private int id;
    private String jhid;
    private String jhmc;
    private String xdjzq;
    private String khzq;
    private String nexttime;
    private boolean checked;//是否选择
    private int download;
    private XfDjjhList xfDjjhList;
    private String xctypes;//1：消防 2：保安  3：楼宇

    public String getXctypes() {
        return xctypes;
    }

    public void setXctypes(String xctypes) {
        this.xctypes = xctypes;
    }

    public XfDjjhList getXfDjjhList() {
        return xfDjjhList;
    }

    public void setXfDjjhList(XfDjjhList xfDjjhList) {
        this.xfDjjhList = xfDjjhList;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
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

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getJhmc() {
        return jhmc;
    }

    public void setJhmc(String jhmc) {
        this.jhmc = jhmc;
    }

    public String getXdjzq() {
        return xdjzq;
    }

    public void setXdjzq(String xdjzq) {
        this.xdjzq = xdjzq;
    }

    public String getKhzq() {
        return khzq;
    }

    public void setKhzq(String khzq) {
        this.khzq = khzq;
    }

    public String getNexttime() {
        return nexttime;
    }

    public void setNexttime(String nexttime) {
        this.nexttime = nexttime;
    }
}
