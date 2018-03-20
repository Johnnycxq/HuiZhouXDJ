package com.rehome.huizhouxdj.bean;

/**
 * Created by ruihong on 2018/3/14.
 */

public class XscbRequestBean {
    private String Action;
    private String YHID;
    private String GWID;

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getYHID() {
        return YHID;
    }

    public void setYHID(String YHID) {
        this.YHID = YHID;
    }

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }
}
