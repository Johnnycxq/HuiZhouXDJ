package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/3/23.
 */

public class Qfkccxbean {

    private int state;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String WZBM;
        private String CKH;
        private String WZMC;
        private String SL;
        private String DW;
        private String DJ;
        private String CC;

        public String getWZBM() {
            return WZBM;
        }

        public void setWZBM(String WZBM) {
            this.WZBM = WZBM;
        }

        public String getCKH() {
            return CKH;
        }

        public void setCKH(String CKH) {
            this.CKH = CKH;
        }

        public String getWZMC() {
            return WZMC;
        }

        public void setWZMC(String WZMC) {
            this.WZMC = WZMC;
        }

        public String getSL() {
            return SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }

        public String getDW() {
            return DW;
        }

        public void setDW(String DW) {
            this.DW = DW;
        }

        public String getDJ() {
            return DJ;
        }

        public void setDJ(String DJ) {
            this.DJ = DJ;
        }

        public String getCC() {
            return CC;
        }

        public void setCC(String CC) {
            this.CC = CC;
        }
    }
}
