package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/15.
 * 巡视请求数据
 */

public class XsSaveDataInfo {

    /**
     * action : XSCB_ZXJL_GET
     * zc : 值次（班组编号）
     * jhid : 计划ID
     */

    private String action;
    private String zxid;
    private String qybh;
    private List<DataBean> data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public static class DataBean {

        private String dbh;
        private String cbsz;
        private String djsj;
        private String zcmc;
        private String cbr;
        private String fxnr;
        private String smfx;
        private String scid;


        public DataBean() {
        }

        public DataBean(String dbh, String cbsz, String djsj, String zcmc, String cbr, String fxnr, String smfx,String scid) {
            this.dbh = dbh;
            this.cbsz = cbsz;
            this.djsj = djsj;
            this.zcmc = zcmc;
            this.cbr = cbr;
            this.fxnr = fxnr;
            this.smfx = smfx;
            this.scid = scid;
        }

        public String getDbh() {
            return dbh;
        }

        public void setDbh(String dbh) {
            this.dbh = dbh;
        }

        public String getCbsz() {
            return cbsz;
        }

        public void setCbsz(String cbsz) {
            this.cbsz = cbsz;
        }

        public String getDjsj() {
            return djsj;
        }

        public void setDjsj(String djsj) {
            this.djsj = djsj;
        }

        public String getZcmc() {
            return zcmc;
        }

        public void setZcmc(String zcmc) {
            this.zcmc = zcmc;
        }

        public String getCbr() {
            return cbr;
        }

        public void setCbr(String cbr) {
            this.cbr = cbr;
        }

        public String getFxnr() {
            return fxnr;
        }

        public void setFxnr(String fxnr) {
            this.fxnr = fxnr;
        }

        public String getSmfx() {
            return smfx;
        }

        public void setSmfx(String smfx) {
            this.smfx = smfx;
        }

        public String getScid() {
            return scid;
        }

        public void setScid(String scid) {
            this.scid = scid;
        }
    }
}
