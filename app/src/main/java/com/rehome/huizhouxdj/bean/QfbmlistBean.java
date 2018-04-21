package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/4/18.
 */

public class QfbmlistBean {


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
         * BMID : 1-LS
         * BMMC : 安徽合源电力工程有限公司
         * SJBM : 10
         */

        private String BMID;
        private String BMMC;
        private String SJBM;
        private boolean isSelected; //是否选中的标识


        public String getBMID() {
            return BMID;
        }

        public void setBMID(String BMID) {
            this.BMID = BMID;
        }

        public String getBMMC() {
            return BMMC;
        }

        public void setBMMC(String BMMC) {
            this.BMMC = BMMC;
        }

        public String getSJBM() {
            return SJBM;
        }

        public void setSJBM(String SJBM) {
            this.SJBM = SJBM;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
