package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/27.
 */

public class BasicDataBean {


    /**
     * state : 1
     * msg : 获取成功
     * data : [{"id":"7","name":"灰控"},{"id":"6","name":"脱硫"},{"id":"5","name":"#2汽机"},{"id":"4","name":"#1汽机"},{"id":"3","name":"#2锅炉"},{"id":"2","name":"#1锅炉"},{"id":"1","name":"电气网控"},{"id":"8","name":"化学"},{"id":"9","name":"厂用电"}]
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
         * id : 7
         * name : 灰控
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
