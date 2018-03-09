package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/10/22.
 */

public class QxgdInfo extends DataSupport {

    private String qxms;
    private String zyid;
    private String bgr;
    private String date;

    public String getQxms() {
        return qxms;
    }

    public void setQxms(String qxms) {
        this.qxms = qxms;
    }

    public String getZyid() {
        return zyid;
    }

    public void setZyid(String zyid) {
        this.zyid = zyid;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
