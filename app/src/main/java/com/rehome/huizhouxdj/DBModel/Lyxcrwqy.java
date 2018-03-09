package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/12.
 */

public class Lyxcrwqy extends DataSupport {

    /**
     * BH : 5
     * XMNAME : 楼梯、走廊瓷砖
     * QYNAME : 大仓库
     * TXMBH : 5
     * QYNFC : 5
     * XMID : e9692c642e2a457f9b954314c373e1b6
     * QYID : 2f90396dd8324dda835c5439f241e994
     * JHID : 20161103192018
     * NEXTTIME : 2016/11/15 0:00:00
     */

    private String BH;
    private String XMNAME;
    private String QYNAME;
    private String TXMBH;
    private String QYNFC;
    private String XMID;
    private String QYID;
    private String JHID;
    private String NEXTTIME;
    private String ISZC;// 1表示正常  0表示不正常, 不正常就生成缺陷工单
    private String CJJG;//备注
    private String CJSJ;//日期
    private boolean checked;
    private String CJR;//采集人
    private String SMFX;//扫描方式

    public String getSMFX() {
        return SMFX;
    }

    public void setSMFX(String SMFX) {
        this.SMFX = SMFX;
    }

    public String getCJJG() {
        return CJJG;
    }

    public void setCJJG(String CJJG) {
        this.CJJG = CJJG;
    }

    public String getCJR() {
        return CJR;
    }

    public void setCJR(String CJR) {
        this.CJR = CJR;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getISZC() {
        return ISZC;
    }

    public void setISZC(String ISZC) {
        this.ISZC = ISZC;
    }


    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getXMNAME() {
        return XMNAME;
    }

    public void setXMNAME(String XMNAME) {
        this.XMNAME = XMNAME;
    }

    public String getQYNAME() {
        return QYNAME;
    }

    public void setQYNAME(String QYNAME) {
        this.QYNAME = QYNAME;
    }

    public String getTXMBH() {
        return TXMBH;
    }

    public void setTXMBH(String TXMBH) {
        this.TXMBH = TXMBH;
    }

    public String getQYNFC() {
        return QYNFC;
    }

    public void setQYNFC(String QYNFC) {
        this.QYNFC = QYNFC;
    }

    public String getXMID() {
        return XMID;
    }

    public void setXMID(String XMID) {
        this.XMID = XMID;
    }

    public String getQYID() {
        return QYID;
    }

    public void setQYID(String QYID) {
        this.QYID = QYID;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getNEXTTIME() {
        return NEXTTIME;
    }

    public void setNEXTTIME(String NEXTTIME) {
        this.NEXTTIME = NEXTTIME;
    }
}
