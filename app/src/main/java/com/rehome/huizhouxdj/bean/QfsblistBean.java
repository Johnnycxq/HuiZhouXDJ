package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/4/17.
 */

public class QfsblistBean {


    /**
     * state : 1
     * msg :
     * data : [{"FSBID":"","SBID":"0","SBMC":"全厂公用系统"},{"FSBID":"","SBID":"1","SBMC":"#1机组"},{"FSBID":"","SBID":"2","SBMC":"#2机组"},{"FSBID":"","SBID":"3","SBMC":"#3机组"},{"FSBID":"","SBID":"F","SBMC":"#1～3机组公用系统"},{"FSBID":"","SBID":"M","SBMC":"#1～6机组公用系统"},{"FSBID":"","SBID":"P","SBMC":"供热公用系统"},{"FSBID":"","SBID":"U","SBMC":"供热#1炉"},{"FSBID":"","SBID":"V","SBMC":"供热#2炉"},{"FSBID":"","SBID":"W","SBMC":"倒班宿舍及其附属设备"}]
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
         * FSBID :
         * SBID : 0
         * SBMC : 全厂公用系统
         */

        private String FSBID;
        private String SBID;
        private String SBMC;
        private boolean isSelected; //是否选中的标识

        public String getFSBID() {
            return FSBID;
        }

        public void setFSBID(String FSBID) {
            this.FSBID = FSBID;
        }

        public String getSBID() {
            return SBID;
        }

        public void setSBID(String SBID) {
            this.SBID = SBID;
        }

        public String getSBMC() {
            return SBMC;
        }

        public void setSBMC(String SBMC) {
            this.SBMC = SBMC;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
