package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 消防点检计划列表
 */

public class XfDjjhList extends DataSupport {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Rows : [{"id":"20161019184614","jhmc":"消防计划2","xdjzq":"10","khzq":"7","nexttime":"2016/10/19 0:00:00"},{"id":"20161014165348","jhmc":"消防巡查计划10月15号开始","xdjzq":"10","khzq":"7","nexttime":"2016/10/15 0:00:00"}]
     * Total : 2
     */

    private String Total;
    /**
     * id : 20161019184614
     * jhmc : 消防计划2
     * xdjzq : 10
     * khzq : 7
     * nexttime : 2016/10/19 0:00:00
     */

    private List<XfDjjh> Rows;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<XfDjjh> getRows() {
        return Rows;
    }

    public void setRows(List<XfDjjh> Rows) {
        this.Rows = Rows;
    }
}
