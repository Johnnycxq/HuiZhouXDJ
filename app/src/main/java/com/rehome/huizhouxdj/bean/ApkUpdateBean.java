package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2017/8/29.
 */

public class ApkUpdateBean {
    /**
     * Rows : [{"versionname":"1.0.1","versioncode":"1.0.1","apkurl":"http://192.168.2.5:9000/apkFiles/release/zdxoaapp.apk","bz":"http://pre.im/zfPq"}]
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

        private String versionname;
        private String versioncode;
        private String apkurl;//外网
        private String apkurl2;
        private String bz;

        public String getApkurl2() {
            return apkurl2;
        }

        public void setApkurl2(String apkurl2) {
            this.apkurl2 = apkurl2;
        }


        public String getVersionname() {
            return versionname;
        }

        public void setVersionname(String versionname) {
            this.versionname = versionname;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }

        public String getApkurl() {
            return apkurl;
        }

        public void setApkurl(String apkurl) {
            this.apkurl = apkurl;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }
    }
}
