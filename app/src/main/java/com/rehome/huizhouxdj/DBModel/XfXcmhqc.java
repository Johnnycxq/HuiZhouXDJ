package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfXcmhqc extends DataSupport {

    private int id;
    private String bh;
    private String xftype;
    private String xhnum;
    private String xmid;
    private String xfid;
    private String qyid;
    private String jhid;
    private String nexttime;
    private String scrq;
    private String yxrq;

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }

    public String getYxrq() {
        return yxrq;
    }

    public void setYxrq(String yxrq) {
        this.yxrq = yxrq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getXftype() {
        return xftype;
    }

    public void setXftype(String xftype) {
        this.xftype = xftype;
    }

    public String getXhnum() {
        return xhnum;
    }

    public void setXhnum(String xhnum) {
        this.xhnum = xhnum;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getXfid() {
        return xfid;
    }

    public void setXfid(String xfid) {
        this.xfid = xfid;
    }

    public String getQyid() {
        return qyid;
    }

    public void setQyid(String qyid) {
        this.qyid = qyid;
    }

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getNexttime() {
        return nexttime;
    }

    public void setNexttime(String nexttime) {
        this.nexttime = nexttime;
    }
}
