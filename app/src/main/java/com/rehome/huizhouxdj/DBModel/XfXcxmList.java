package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfXcxmList extends DataSupport {


    /**
     * Rows : [{"xmid":"e202262940b147a3997abd76473a7646","id":"dbfcee97c583464cb28d9f1d1e19b393","xftype":"1","typename":"结果","type1":"正常","type2":"不正常"},{"xmid":"a0349df3eca44c7d8eafe39469bdddae","id":"f111c59a845342bdbce9f9dc0909d696","xftype":"2","typename":"结果","type1":"正常","type2":"不正常"},{"xmid":"1b179cac1cff41b4a4e9aa26f934660c","id":"ae4e5bb311c2498293593482e2aaf0eb","xftype":"3","typename":"门","type1":"正常","type2":"不正常"},{"xmid":"a0349df3eca44c7d8eafe39469bdddae","id":"8f8073765dfd466187a0ddc625e0172f","xftype":"2","typename":"结果2","type1":"正常2","type2":"不正常2"},{"xmid":"1b179cac1cff41b4a4e9aa26f934660c","id":"70fc834605994822bde12b34d7c97d58","xftype":"3","typename":"灯","type1":"正常","type2":"不正常"}]
     * Total : 5
     */

    private String Total;
    /**
     * xmid : e202262940b147a3997abd76473a7646
     * id : dbfcee97c583464cb28d9f1d1e19b393
     * xftype : 1
     * typename : 结果
     * type1 : 正常
     * type2 : 不正常
     */

    private List<XfXcxm> Rows;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<XfXcxm> getRows() {
        return Rows;
    }

    public void setRows(List<XfXcxm> Rows) {
        this.Rows = Rows;
    }

}
