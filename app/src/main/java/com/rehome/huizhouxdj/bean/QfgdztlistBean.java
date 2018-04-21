package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/4/18.
 */

public class QfgdztlistBean {


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

        private String GDZT_NO;
        private String GDZT_SO;
        private String GDZTMC;
        private boolean isSelected; //是否选中的标识

        public String getGDZT_NO() {
            return GDZT_NO;
        }

        public void setGDZT_NO(String GDZT_NO) {
            this.GDZT_NO = GDZT_NO;
        }

        public String getGDZT_SO() {
            return GDZT_SO;
        }

        public void setGDZT_SO(String GDZT_SO) {
            this.GDZT_SO = GDZT_SO;
        }

        public String getGDZTMC() {
            return GDZTMC;
        }

        public void setGDZTMC(String GDZTMC) {
            this.GDZTMC = GDZTMC;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
