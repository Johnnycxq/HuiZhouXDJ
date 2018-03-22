package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/26.
 */

public class XsHistoryListBean {

    private int state;
    private String msg;
    private List<DataBeanX> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {


        private String rq;
        private String xgqx;
        private List<DataBean> data;

        public String getRq() {
            return rq;
        }

        public void setRq(String rq) {
            this.rq = rq;
        }

        public String getXgqx() {
            return xgqx;
        }

        public void setXgqx(String xgqx) {
            this.xgqx = xgqx;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * jhid : JH00000000070
             * zxid : ZX00000000169
             * jhmc : TEST1
             * jhlx : 51
             * jhzq : 8
             * st : 2017/7/24 6:00:00
             * et : 2017/7/24 14:00:00
             * wczt : 0
             * ljds : 0
             * jhds : 21
             * zc : 12108020207
             * iswsc : 1
             * zymc : 1
             */

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
            private String zymc;
            private String scsj;
            private String scrq;

            public String getScrq() {
                return scrq;
            }

            public void setScrq(String scrq) {
                this.scrq = scrq;
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

            public String getZymc() {
                return zymc;
            }

            public void setZymc(String zymc) {
                this.zymc = zymc;
            }

            public String getScsj() {
                return scsj;
            }

            public void setScsj(String scsj) {
                this.scsj = scsj;
            }

        }
    }
}
