package com.rehome.huizhouxdj.bean;

/**
 * Created by ruihong on 2018/4/17.
 */

public class QfsblistRequestBean {

    private String Action;
    private String YHID;
    private String SJBM;
    private String FSBID;
    private String BMID;
    private String BMBSF;

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

    public String getSJBM() {
        return SJBM;
    }

    public void setSJBM(String SJBM) {
        this.SJBM = SJBM;
    }

    public String getFSBID() {
        return FSBID;
    }

    public void setFSBID(String FSBID) {
        this.FSBID = FSBID;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }

    public String getBMBSF() {
        return BMBSF;
    }

    public void setBMBSF(String BMBSF) {
        this.BMBSF = BMBSF;
    }
}
