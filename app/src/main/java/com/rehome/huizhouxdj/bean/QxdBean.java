package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/5/11.
 */

public class QxdBean {

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
        /**
         * pl_wk_wn_p : WK180213.9001
         * pl_wk_st_no : 7
         * wo_st_de : 已悬挂
         * pl_wk_te : 93
         * re_tm_de : 电气分部
         * pl_wk_pi : 4
         * pl_wk_do : 黄颖
         * pl_wk_wg :
         * priorityclass : B(一般性维修)
         * pl_wk_eq_ma_no_p : 30BTB01
         * eq_ma_de : #3机组220V蓄电池组系统
         * pl_wk_fd : DCS出现#3机“220V BATTERY CAPACITY LOW”，就地检查单节电池电压离群，#36电池电压0.81V。
         * pl_wk_de : DCS出现#3机“220V BATTERY CAPACITY LOW”，就地检查单节电池电压离群，#36电池电压0.81V。
         */

        private String pl_wk_wn_p;
        private String pl_wk_st_no;
        private String wo_st_de;
        private String pl_wk_te;
        private String re_tm_de;
        private String pl_wk_pi;
        private String pl_wk_do;
        private String pl_wk_wg;
        private String priorityclass;
        private String pl_wk_eq_ma_no_p;
        private String eq_ma_de;
        private String pl_wk_fd;
        private String pl_wk_de;

        public String getPl_wk_wn_p() {
            return pl_wk_wn_p;
        }

        public void setPl_wk_wn_p(String pl_wk_wn_p) {
            this.pl_wk_wn_p = pl_wk_wn_p;
        }

        public String getPl_wk_st_no() {
            return pl_wk_st_no;
        }

        public void setPl_wk_st_no(String pl_wk_st_no) {
            this.pl_wk_st_no = pl_wk_st_no;
        }

        public String getWo_st_de() {
            return wo_st_de;
        }

        public void setWo_st_de(String wo_st_de) {
            this.wo_st_de = wo_st_de;
        }

        public String getPl_wk_te() {
            return pl_wk_te;
        }

        public void setPl_wk_te(String pl_wk_te) {
            this.pl_wk_te = pl_wk_te;
        }

        public String getRe_tm_de() {
            return re_tm_de;
        }

        public void setRe_tm_de(String re_tm_de) {
            this.re_tm_de = re_tm_de;
        }

        public String getPl_wk_pi() {
            return pl_wk_pi;
        }

        public void setPl_wk_pi(String pl_wk_pi) {
            this.pl_wk_pi = pl_wk_pi;
        }

        public String getPl_wk_do() {
            return pl_wk_do;
        }

        public void setPl_wk_do(String pl_wk_do) {
            this.pl_wk_do = pl_wk_do;
        }

        public String getPl_wk_wg() {
            return pl_wk_wg;
        }

        public void setPl_wk_wg(String pl_wk_wg) {
            this.pl_wk_wg = pl_wk_wg;
        }

        public String getPriorityclass() {
            return priorityclass;
        }

        public void setPriorityclass(String priorityclass) {
            this.priorityclass = priorityclass;
        }

        public String getPl_wk_eq_ma_no_p() {
            return pl_wk_eq_ma_no_p;
        }

        public void setPl_wk_eq_ma_no_p(String pl_wk_eq_ma_no_p) {
            this.pl_wk_eq_ma_no_p = pl_wk_eq_ma_no_p;
        }

        public String getEq_ma_de() {
            return eq_ma_de;
        }

        public void setEq_ma_de(String eq_ma_de) {
            this.eq_ma_de = eq_ma_de;
        }

        public String getPl_wk_fd() {
            return pl_wk_fd;
        }

        public void setPl_wk_fd(String pl_wk_fd) {
            this.pl_wk_fd = pl_wk_fd;
        }

        public String getPl_wk_de() {
            return pl_wk_de;
        }

        public void setPl_wk_de(String pl_wk_de) {
            this.pl_wk_de = pl_wk_de;
        }
    }
}
