package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 点检计划列表
 */

public class DjjhList extends DataSupport {

    private int id;
    private String state;
    private String msg;
    private List<Djjh> data = new ArrayList<Djjh>();

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

    public List<Djjh> getData() {
        return data;
    }

    public void setData(List<Djjh> data) {
        this.data = data;
    }
}
