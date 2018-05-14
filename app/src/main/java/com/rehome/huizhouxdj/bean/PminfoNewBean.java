package com.rehome.huizhouxdj.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

/**
 * Created by ruihong on 2018/4/20.
 */


public class PminfoNewBean {


    private int state;
    private String msg;
    private List<DataBean> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @SmartTable(name = "PM工单查询")
    public static class DataBean {

        @SmartColumn(id = 1, name = "PM单号")
        private String ta_pm_no_p;

        @SmartColumn(id = 2, name = "设备描述")
        private String eq_ma_de;

//        @SmartColumn(id = 3, name = "工作规范代码")
        private String ta_pm_sn;

//        @SmartColumn(id = 4, name = "工作规范类型")
        private String ta_ws_ca;

        @SmartColumn(id = 3, name = "工作规范描述")
        private String ta_ws_de;

        @SmartColumn(id = 4, name = "数据日期")
        private String ta_pm_ld;

        @SmartColumn(id = 5, name = "下次到期")
        private String ta_pm_nextdue;

        @SmartColumn(id = 6, name = "PM状态")
        private String ta_pm_fl;

        @SmartColumn(id = 7, name = "责任单位")
        private String re_tm_de;

        @SmartColumn(id = 8, name = "类型")
        private String ta_md_de;

        public String getTa_pm_no_p() {
            return ta_pm_no_p;
        }

        public void setTa_pm_no_p(String ta_pm_no_p) {
            this.ta_pm_no_p = ta_pm_no_p;
        }

        public String getEq_ma_de() {
            return eq_ma_de;
        }

        public void setEq_ma_de(String eq_ma_de) {
            this.eq_ma_de = eq_ma_de;
        }

        public String getTa_pm_sn() {
            return ta_pm_sn;
        }

        public void setTa_pm_sn(String ta_pm_sn) {
            this.ta_pm_sn = ta_pm_sn;
        }

        public String getTa_ws_ca() {
            return ta_ws_ca;
        }

        public void setTa_ws_ca(String ta_ws_ca) {
            this.ta_ws_ca = ta_ws_ca;
        }

        public String getTa_ws_de() {
            return ta_ws_de;
        }

        public void setTa_ws_de(String ta_ws_de) {
            this.ta_ws_de = ta_ws_de;
        }

        public String getTa_pm_ld() {
            return ta_pm_ld;
        }

        public void setTa_pm_ld(String ta_pm_ld) {
            this.ta_pm_ld = ta_pm_ld;
        }

        public String getTa_pm_nextdue() {
            return ta_pm_nextdue;
        }

        public void setTa_pm_nextdue(String ta_pm_nextdue) {
            this.ta_pm_nextdue = ta_pm_nextdue;
        }

        public String getTa_pm_fl() {
            return ta_pm_fl;
        }

        public void setTa_pm_fl(String ta_pm_fl) {
            this.ta_pm_fl = ta_pm_fl;
        }

        public String getRe_tm_de() {
            return re_tm_de;
        }

        public void setRe_tm_de(String re_tm_de) {
            this.re_tm_de = re_tm_de;
        }

        public String getTa_md_de() {
            return ta_md_de;
        }

        public void setTa_md_de(String ta_md_de) {
            this.ta_md_de = ta_md_de;
        }
    }
}
