package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/5/21.
 */

public class ZjrwBean {


    /**
     * state : 0
     * msg :
     * data : [{"RWID":"16","RWMC":"公共系统D级检修","RWST":"2017/10/1 0:00:00","RWET":"","YJWCSJ":"2017/10/10 0:00:00","RWLX":"D","RWZT":"0","TJR":"280517","TJSJ":"2017/9/20 10:47:39","XGR":"280517","XGSJ":"2017/9/20 10:47:39","BZSM":"","ZJJZ":"0","JXRWID":"156"},{"RWID":"17","RWMC":"307B#3机组B级检修","RWST":"2018/2/8 0:00:00","RWET":"","YJWCSJ":"2018/3/24 0:00:00","RWLX":"B","RWZT":"5","TJR":"280517","TJSJ":"2017/10/18 10:53:13","XGR":"280517","XGSJ":"2017/10/18 10:53:13","BZSM":"","ZJJZ":"3","JXRWID":"157"},{"RWID":"15","RWMC":"207C(C)#2机组C级检修","RWST":"2018/1/4 0:00:00","RWET":"","YJWCSJ":"2018/2/7 0:00:00","RWLX":"C","RWZT":"4","TJR":"280865","TJSJ":"2017/8/29 14:25:14","XGR":"280865","XGSJ":"2017/8/29 14:25:14","BZSM":"","ZJJZ":"2","JXRWID":"119"},{"RWID":"13","RWMC":"107C(C)#1机组C级检修","RWST":"2017/6/5 0:00:00","RWET":"","YJWCSJ":"2017/7/4 0:00:00","RWLX":"C","RWZT":"5","TJR":"280865","TJSJ":"2017/1/5 14:51:05","XGR":"280865","XGSJ":"2017/1/5 14:51:05","BZSM":"","ZJJZ":"1","JXRWID":"113"}]
     */

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
         * RWID : 16
         * RWMC : 公共系统D级检修
         * RWST : 2017/10/1 0:00:00
         * RWET :
         * YJWCSJ : 2017/10/10 0:00:00
         * RWLX : D
         * RWZT : 0
         * TJR : 280517
         * TJSJ : 2017/9/20 10:47:39
         * XGR : 280517
         * XGSJ : 2017/9/20 10:47:39
         * BZSM :
         * ZJJZ : 0
         * JXRWID : 156
         */

        private String RWID;
        private String RWMC;
        private String RWST;
        private String RWET;
        private String YJWCSJ;
        private String RWLX;
        private String RWZT;
        private String TJR;
        private String TJSJ;
        private String XGR;
        private String XGSJ;
        private String BZSM;
        private String ZJJZ;
        private String JXRWID;

        public String getRWID() {
            return RWID;
        }

        public void setRWID(String RWID) {
            this.RWID = RWID;
        }

        public String getRWMC() {
            return RWMC;
        }

        public void setRWMC(String RWMC) {
            this.RWMC = RWMC;
        }

        public String getRWST() {
            return RWST;
        }

        public void setRWST(String RWST) {
            this.RWST = RWST;
        }

        public String getRWET() {
            return RWET;
        }

        public void setRWET(String RWET) {
            this.RWET = RWET;
        }

        public String getYJWCSJ() {
            return YJWCSJ;
        }

        public void setYJWCSJ(String YJWCSJ) {
            this.YJWCSJ = YJWCSJ;
        }

        public String getRWLX() {
            return RWLX;
        }

        public void setRWLX(String RWLX) {
            this.RWLX = RWLX;
        }

        public String getRWZT() {
            return RWZT;
        }

        public void setRWZT(String RWZT) {
            this.RWZT = RWZT;
        }

        public String getTJR() {
            return TJR;
        }

        public void setTJR(String TJR) {
            this.TJR = TJR;
        }

        public String getTJSJ() {
            return TJSJ;
        }

        public void setTJSJ(String TJSJ) {
            this.TJSJ = TJSJ;
        }

        public String getXGR() {
            return XGR;
        }

        public void setXGR(String XGR) {
            this.XGR = XGR;
        }

        public String getXGSJ() {
            return XGSJ;
        }

        public void setXGSJ(String XGSJ) {
            this.XGSJ = XGSJ;
        }

        public String getBZSM() {
            return BZSM;
        }

        public void setBZSM(String BZSM) {
            this.BZSM = BZSM;
        }

        public String getZJJZ() {
            return ZJJZ;
        }

        public void setZJJZ(String ZJJZ) {
            this.ZJJZ = ZJJZ;
        }

        public String getJXRWID() {
            return JXRWID;
        }

        public void setJXRWID(String JXRWID) {
            this.JXRWID = JXRWID;
        }
    }
}
