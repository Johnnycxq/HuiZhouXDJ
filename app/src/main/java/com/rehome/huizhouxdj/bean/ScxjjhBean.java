package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/3/19.
 */

public class ScxjjhBean {//用于上传训检计划的bean

    private String action;
    private String zxid;
    private String qybh;
    private List<data> data;

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

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public List<ScxjjhBean.data> getData() {
        return data;
    }

    public void setData(List<ScxjjhBean.data> data) {
        this.data = data;
    }

    public static class data {
        private String scid;
        private String dbh;
        private String cbsz;
        private String djsj;
        private String zcmc;
        private String cbr;
        private String fxnr;
        private String smfx;
        private String SBZT;

        public String getScid() {
            return scid;
        }

        public void setScid(String scid) {
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

        public String getSBZT() {
            return SBZT;
        }

        public void setSBZT(String SBZT) {
            this.SBZT = SBZT;
        }
    }


}
