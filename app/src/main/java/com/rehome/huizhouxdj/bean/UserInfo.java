package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class UserInfo {


    private int Total;

    private List<User> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<User> getRows() {
        return Rows;
    }

    public void setRows(List<User> Rows) {
        this.Rows = Rows;
    }

    public static class User {
        private String status;
        private String usernames;
        private String bzbh;
        private String bzmc;
        private String PermissionsResult;

        public String getPermissionsResult() {
            return PermissionsResult;
        }

        public void setPermissionsResult(String permissionsResult) {
            PermissionsResult = permissionsResult;
        }

        public String getBzbh() {
            return bzbh;
        }

        public void setBzbh(String bzbh) {
            this.bzbh = bzbh;
        }

        public String getBzmc() {
            return bzmc;
        }

        public void setBzmc(String bzmc) {
            this.bzmc = bzmc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsernames() {
            return usernames;
        }

        public void setUsernames(String usernames) {
            this.usernames = usernames;
        }
    }
}
