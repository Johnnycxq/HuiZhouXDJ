package com.rehome.huizhouxdj.DBModel;

/**
 * 安建环上传信息
 */

public class AjhScInfo {

    private String SCNR;//检查结果  是，否
    private String JCSJ;//时间
    private String BZID;//班组ID
    private String JCR;//检查人
    private String JHID;//计划ID
    private String YSID;//
    private String MS;//描述
    private boolean CHECKED;//是否已检
    private String SMFX;//扫码方式

    public boolean isCHECKED() {
        return CHECKED;
    }

    public String getSMFX() {
        return SMFX;
    }

    public void setSMFX(String SMFX) {
        this.SMFX = SMFX;
    }

    public String getSCNR() {
        return SCNR;
    }

    public void setSCNR(String SCNR) {
        this.SCNR = SCNR;
    }

    public String getJCSJ() {
        return JCSJ;
    }

    public void setJCSJ(String JCSJ) {
        this.JCSJ = JCSJ;
    }

    public String getBZID() {
        return BZID;
    }

    public void setBZID(String BZID) {
        this.BZID = BZID;
    }

    public String getJCR() {
        return JCR;
    }

    public void setJCR(String JCR) {
        this.JCR = JCR;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getYSID() {
        return YSID;
    }

    public void setYSID(String YSID) {
        this.YSID = YSID;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public boolean getCHECKED() {
        return CHECKED;
    }

    public void setCHECKED(boolean CHECKED) {
        this.CHECKED = CHECKED;
    }
}