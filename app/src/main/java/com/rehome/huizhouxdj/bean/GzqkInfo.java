package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/4/12.
 */

public class GzqkInfo {


    /**
     * Rows : [{"id":"201704110942207293","ggtime_s":"2017/4/5 18:00:00","ggtime_e":"","ggplace":"煤场D","ggcontent":"隐患排查发现问题310194","lrr":"张三杰","addtime":"2017/4/1 18:00:00","bm":"燃料部","rwstate":"0","is_del":"0","tbtime":""},{"id":"201704110942074272","ggtime_s":"2017/4/5 18:00:00","ggtime_e":"2017/4/11 10:07:20","ggplace":"煤场Dccccccc","ggcontent":"隐患排查发现问题310194","lrr":"张三杰","addtime":"2017/4/11 9:46:33","bm":"燃料部","rwstate":"0","is_del":"0","tbtime":"2017/4/11 13:46:03"}]
     * Total : 2
     */

    private String Total;
    private List<Gzqk> Rows;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<Gzqk> getRows() {
        return Rows;
    }

    public void setRows(List<Gzqk> Rows) {
        this.Rows = Rows;
    }

}
