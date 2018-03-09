package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 点检计划列表
 */

public class DjjhList extends DataSupport {

    private int id;
    private int Total;

    private List<Djjh> Rows = new ArrayList<Djjh>();

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Djjh> getRows() {
        return Rows;
    }

    public void setRows(List<Djjh> Rows) {
        this.Rows = Rows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
