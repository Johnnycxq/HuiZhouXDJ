package com.rehome.huizhouxdj.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

/**
 * Created by ruihong on 2018/4/20.
 */


public class PminfoBean {


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

        @SmartColumn(id = 1, name = "工作单号")
        private String GZDH;

        @SmartColumn(id = 2, name = "工作状态")
        private String GZZT;

        @SmartColumn(id = 3, name = "责任部门")
        private String ZRBM;

        @SmartColumn(id = 4, name = "工作先级")
        private String GZYXJ;

        @SmartColumn(id = 5, name = "设备编号")
        private String SBBH;

        @SmartColumn(id = 6, name = "设备名称")
        private String SBMC;

        @SmartColumn(id = 7, name = "缺陷描述")
        private String QXMS;

        @SmartColumn(id = 8, name = "工作描述")
        private String GZMS;

        @SmartColumn(id = 9, name = "提交人")
        private String TJR;

        @SmartColumn(id = 10, name = "提交人名")
        private String TJR_NAME;

        @SmartColumn(id = 11, name = "提交日期")
        private String TJRQ;

        @SmartColumn(id = 12, name = "提交时间")
        private String TJSJ;

        @SmartColumn(id = 13, name = "报告人")
        private String BGR;

        @SmartColumn(id = 14, name = "报告人名")
        private String BGR_NAME;

        @SmartColumn(id = 15, name = "报告日期")
        private String BGRQ;

        @SmartColumn(id = 16, name = "报告时间")
        private String BGSJ;

        @SmartColumn(id = 17, name = "安排人")
        private String APR;

        @SmartColumn(id = 18, name = "安排人名")
        private String APR_NAME;

        @SmartColumn(id = 19, name = "安排日期")
        private String APRQ;

        @SmartColumn(id = 20, name = "安排时间")
        private String ARSJ;

        @SmartColumn(id = 21, name = "批准人")
        private String PZR;

        @SmartColumn(id = 22, name = "批准人名")
        private String PZR_NAME;

        @SmartColumn(id = 23, name = "批准日期")
        private String PZRQ;

        @SmartColumn(id = 24, name = "批准日期")
        private String PZSJ;

        @SmartColumn(id = 25, name = "批准日期")
        private String XMFZR;

        @SmartColumn(id = 26, name = "批准日期")
        private String LRFS;

        public String getGZDH() {
            return GZDH;
        }

        public void setGZDH(String GZDH) {
            this.GZDH = GZDH;
        }

        public String getGZZT() {
            return GZZT;
        }

        public void setGZZT(String GZZT) {
            this.GZZT = GZZT;
        }

        public String getZRBM() {
            return ZRBM;
        }

        public void setZRBM(String ZRBM) {
            this.ZRBM = ZRBM;
        }

        public String getGZYXJ() {
            return GZYXJ;
        }

        public void setGZYXJ(String GZYXJ) {
            this.GZYXJ = GZYXJ;
        }

        public String getSBBH() {
            return SBBH;
        }

        public void setSBBH(String SBBH) {
            this.SBBH = SBBH;
        }

        public String getSBMC() {
            return SBMC;
        }

        public void setSBMC(String SBMC) {
            this.SBMC = SBMC;
        }

        public String getQXMS() {
            return QXMS;
        }

        public void setQXMS(String QXMS) {
            this.QXMS = QXMS;
        }

        public String getGZMS() {
            return GZMS;
        }

        public void setGZMS(String GZMS) {
            this.GZMS = GZMS;
        }

        public String getTJR() {
            return TJR;
        }

        public void setTJR(String TJR) {
            this.TJR = TJR;
        }

        public String getTJR_NAME() {
            return TJR_NAME;
        }

        public void setTJR_NAME(String TJR_NAME) {
            this.TJR_NAME = TJR_NAME;
        }

        public String getTJRQ() {
            return TJRQ;
        }

        public void setTJRQ(String TJRQ) {
            this.TJRQ = TJRQ;
        }

        public String getTJSJ() {
            return TJSJ;
        }

        public void setTJSJ(String TJSJ) {
            this.TJSJ = TJSJ;
        }

        public String getBGR() {
            return BGR;
        }

        public void setBGR(String BGR) {
            this.BGR = BGR;
        }

        public String getBGR_NAME() {
            return BGR_NAME;
        }

        public void setBGR_NAME(String BGR_NAME) {
            this.BGR_NAME = BGR_NAME;
        }

        public String getBGRQ() {
            return BGRQ;
        }

        public void setBGRQ(String BGRQ) {
            this.BGRQ = BGRQ;
        }

        public String getBGSJ() {
            return BGSJ;
        }

        public void setBGSJ(String BGSJ) {
            this.BGSJ = BGSJ;
        }

        public String getAPR() {
            return APR;
        }

        public void setAPR(String APR) {
            this.APR = APR;
        }

        public String getAPR_NAME() {
            return APR_NAME;
        }

        public void setAPR_NAME(String APR_NAME) {
            this.APR_NAME = APR_NAME;
        }

        public String getAPRQ() {
            return APRQ;
        }

        public void setAPRQ(String APRQ) {
            this.APRQ = APRQ;
        }

        public String getARSJ() {
            return ARSJ;
        }

        public void setARSJ(String ARSJ) {
            this.ARSJ = ARSJ;
        }

        public String getPZR() {
            return PZR;
        }

        public void setPZR(String PZR) {
            this.PZR = PZR;
        }

        public String getPZR_NAME() {
            return PZR_NAME;
        }

        public void setPZR_NAME(String PZR_NAME) {
            this.PZR_NAME = PZR_NAME;
        }

        public String getPZRQ() {
            return PZRQ;
        }

        public void setPZRQ(String PZRQ) {
            this.PZRQ = PZRQ;
        }

        public String getPZSJ() {
            return PZSJ;
        }

        public void setPZSJ(String PZSJ) {
            this.PZSJ = PZSJ;
        }

        public String getXMFZR() {
            return XMFZR;
        }

        public void setXMFZR(String XMFZR) {
            this.XMFZR = XMFZR;
        }

        public String getLRFS() {
            return LRFS;
        }

        public void setLRFS(String LRFS) {
            this.LRFS = LRFS;
        }
    }
}
