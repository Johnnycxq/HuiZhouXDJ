package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/4/19.
 */

public class QfdjBean {


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
         * DJID : 5
         * DJMC : A(等机会或停机)
         */

        private String DJID;
        private String DJMC;
        private boolean isSelected; //是否选中的标识


        public String getDJID() {
            return DJID;
        }

        public void setDJID(String DJID) {
            this.DJID = DJID;
        }

        public String getDJMC() {
            return DJMC;
        }

        public void setDJMC(String DJMC) {
            this.DJMC = DJMC;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
