package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2018/3/29.
 */

public class Xjjh extends DataSupport {
    private int id;
    private String jhid;
    private String zxid;
    private String jhmc;
    private String jhlx;
    private String jhzq;
    private String st;
    private String et;
    private String wczt;
    private String ljds;
    private String jhds;
    private String zc;
    private String iswsc;
    private boolean checked;//0:未选中,1:已选中
    private int download;//0:未下载,1:已经下载
    private XjjhList xjjhList;

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

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public String getJhmc() {
        return jhmc;
    }

    public void setJhmc(String jhmc) {
        this.jhmc = jhmc;
    }

    public String getJhlx() {
        return jhlx;
    }

    public void setJhlx(String jhlx) {
        this.jhlx = jhlx;
    }

    public String getJhzq() {
        return jhzq;
    }

    public void setJhzq(String jhzq) {
        this.jhzq = jhzq;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public String getWczt() {
        return wczt;
    }

    public void setWczt(String wczt) {
        this.wczt = wczt;
    }

    public String getLjds() {
        return ljds;
    }

    public void setLjds(String ljds) {
        this.ljds = ljds;
    }

    public String getJhds() {
        return jhds;
    }

    public void setJhds(String jhds) {
        this.jhds = jhds;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getIswsc() {
        return iswsc;
    }

    public void setIswsc(String iswsc) {
        this.iswsc = iswsc;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public XjjhList getXjjhList() {
        return xjjhList;
    }

    public void setXjjhList(XjjhList xjjhList) {
        this.xjjhList = xjjhList;
    }
}
