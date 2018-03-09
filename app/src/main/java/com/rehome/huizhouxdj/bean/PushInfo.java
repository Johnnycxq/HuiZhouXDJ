package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */

public class PushInfo {

    /**
     * Rows : [{"dbtitle":"消防巡查","dbname":"消防巡查计划10月15号开始","dbid":"20161014163244"}]
     * Total : 1
     */

    private int Total;
    /**
     * dbtitle : 消防巡查
     * dbname : 消防巡查计划10月15号开始
     * dbid : 20161014163244
     */

    private List<Push> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Push> getRows() {
        return Rows;
    }

    public void setRows(List<Push> Rows) {
        this.Rows = Rows;
    }

    public static class Push {
        private String dbtitle;
        private String dbname;
        private String dbid;

        public String getDbtitle() {
            return dbtitle;
        }

        public void setDbtitle(String dbtitle) {
            this.dbtitle = dbtitle;
        }

        public String getDbname() {
            return dbname;
        }

        public void setDbname(String dbname) {
            this.dbname = dbname;
        }

        public String getDbid() {
            return dbid;
        }

        public void setDbid(String dbid) {
            this.dbid = dbid;
        }
    }
}
