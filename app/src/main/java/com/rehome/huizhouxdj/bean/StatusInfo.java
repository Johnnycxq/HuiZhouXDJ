package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class StatusInfo {


    /**
     * Rows : [{"status":"1"}]
     * Total : 1
     */

    private int Total;
    /**
     * status : 1 表示上传成功，0表示上传失败
     */

    private List<Status> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Status> getRows() {
        return Rows;
    }

    public void setRows(List<Status> Rows) {
        this.Rows = Rows;
    }

    public static class Status {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}


