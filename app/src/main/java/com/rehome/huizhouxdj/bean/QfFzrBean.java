package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/4/19.
 */

public class QfFzrBean {

    private int state;
    private String msg1;
    private List<DataBean> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String YHID;
        private String YHMC;
        private String BMID;
        private String BMMC;
        private boolean isSelected; //是否选中的标识


        public String getYHID() {
            return YHID;
        }

        public void setYHID(String YHID) {
            this.YHID = YHID;
        }

        public String getYHMC() {
            return YHMC;
        }

        public void setYHMC(String YHMC) {
            this.YHMC = YHMC;
        }

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

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
