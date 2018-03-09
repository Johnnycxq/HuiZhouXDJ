package com.rehome.huizhouxdj.DBModel;

import java.util.List;

/**
 * Created by gzw on 2016/11/12.
 */

public class XwaqgcJhList {


    /**
     * Rows : [{"区域分组":[{"JHID":"GCJH00000000001","GCRY":"210041","AREANAME":"油码头区域","AREACODE":"MT03","WCZT":"","ST":"2016-11-08 19:41:49","DQSJ":"2016-11-15 19:11:49"}]}]
     * Total : 1
     */

    private int Total;
    private List<XwaqgcJh> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<XwaqgcJh> getRows() {
        return Rows;
    }

    public void setRows(List<XwaqgcJh> Rows) {
        this.Rows = Rows;
    }
}
