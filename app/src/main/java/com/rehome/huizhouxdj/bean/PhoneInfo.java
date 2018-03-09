package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class PhoneInfo {

    /**
     * Rows : [{"phonemodel":"MX5","sysversion":"wwwww","imeinum":"55484848","phonenum":"48524848","username":"454545","password":"sfasf"}]
     * Total : 1
     */

    private int Total;
    /**
     * phonemodel : MX5
     * sysversion : wwwww
     * imeinum : 55484848
     * phonenum : 48524848
     * username : 454545
     * password : sfasf
     */

    private List<UserInfo> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<UserInfo> getRows() {
        return Rows;
    }

    public void setRows(List<UserInfo> Rows) {
        this.Rows = Rows;
    }

    public static class UserInfo {
        private String phonemodel;//手机类型
        private String sysversion;//系统版本
        private String imeinum;//imei
        private String phonenum;//手机号码
        private String username;//用户名
        private String password;//密码

        public String getPhonemodel() {
            return phonemodel;
        }

        public void setPhonemodel(String phonemodel) {
            this.phonemodel = phonemodel;
        }

        public String getSysversion() {
            return sysversion;
        }

        public void setSysversion(String sysversion) {
            this.sysversion = sysversion;
        }

        public String getImeinum() {
            return imeinum;
        }

        public void setImeinum(String imeinum) {
            this.imeinum = imeinum;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
