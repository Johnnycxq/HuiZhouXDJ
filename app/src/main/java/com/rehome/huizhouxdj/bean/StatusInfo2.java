package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class StatusInfo2 {

    private int state;
    private String msg;
    private String GWID;
    private List<?> data;

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

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}


