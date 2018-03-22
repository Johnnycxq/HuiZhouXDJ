package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2017/12/22.
 */

public class UploadPhotosBean {


    /**
     * state : 1
     * msg :
     * data : []
     */

    private String state;
    private String msg;
    private List<?> data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
