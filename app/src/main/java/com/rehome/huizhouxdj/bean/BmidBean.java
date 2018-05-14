package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/5/10.
 */

public class BmidBean {


    /**
     * Rows : [{"re_tm_no_p":"91","re_tm_de":"策划分部"}]
     * Total : 1
     */

    private String Total;
    private List<RowsBean> Rows;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<RowsBean> getRows() {
        return Rows;
    }

    public void setRows(List<RowsBean> Rows) {
        this.Rows = Rows;
    }

    public static class RowsBean {
        /**
         * re_tm_no_p : 91
         * re_tm_de : 策划分部
         */

        private String re_tm_no_p;
        private String re_tm_de;

        public String getRe_tm_no_p() {
            return re_tm_no_p;
        }

        public void setRe_tm_no_p(String re_tm_no_p) {
            this.re_tm_no_p = re_tm_no_p;
        }

        public String getRe_tm_de() {
            return re_tm_de;
        }

        public void setRe_tm_de(String re_tm_de) {
            this.re_tm_de = re_tm_de;
        }
    }
}
