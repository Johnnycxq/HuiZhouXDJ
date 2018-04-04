package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihong on 2018/3/29.
 */

public class XjjhList extends DataSupport {

    private int id;
    private String state;
    private String msg;
    private List<Xjjh> data = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Xjjh> getData() {
        return data;
    }

    public void setData(List<Xjjh> data) {
        this.data = data;
    }


}
