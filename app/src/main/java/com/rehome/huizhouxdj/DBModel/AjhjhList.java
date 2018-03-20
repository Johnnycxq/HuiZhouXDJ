package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 安健环计划
 */

public class AjhjhList extends DataSupport {

    /**
     * Rows : [{"JHID":"AJH00000000001","JHMC":"XCJ228","DQSJ":"2016-11-09"}]
     * Total : 1
     */

    private int id;

    private int Total;
    /**
     * JHID : AJH00000000001
     * JHMC : XCJ228
     * DQSJ : 2016-11-09
     */

    private List<Ajhjh> Rows;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Ajhjh> getRows() {
        return Rows;
    }

    public void setRows(List<Ajhjh> Rows) {
        this.Rows = Rows;
    }
}
