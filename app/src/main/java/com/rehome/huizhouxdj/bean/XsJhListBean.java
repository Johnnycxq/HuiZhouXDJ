package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/17.
 * 巡视计划列表
 */

public class XsJhListBean {


    /**
     * state : 1
     * msg : 查询成功
     * data : [{"jhid":"JH00000000061","zxid":"ZX00000000061","jhmc":"测试1","jhlx":"51","jhzq":"8","st":"2017/7/17 11:00:00","et":"2017/7/17 19:00:00","wczt":"0","ljds":"0","jhds":"16","zc":"12108020202","iswsc":"1"},{"jhid":"JH00000000062","zxid":"ZX00000000062","jhmc":"test2","jhlx":"51","jhzq":"8","st":"2017/7/17 11:00:00","et":"2017/7/17 19:00:00","wczt":"0","ljds":"0","jhds":"19","zc":"12108020202","iswsc":"1"}]
     */

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

    public static class DataBean implements Parcelable {
        /**
         * jhid : JH00000000061
         * zxid : ZX00000000061
         * jhmc : 测试1
         * jhlx : 51
         * jhzq : 8
         * st : 2017/7/17 11:00:00
         * et : 2017/7/17 19:00:00
         * wczt : 0
         * ljds : 0
         * jhds : 16
         * zc : 12108020202
         * iswsc : 1
         */

        private String jhid;
        private String zxid;
        private String jhmc;
        private String jhlx;
        private String jhzq;
        private String st;
        private String et;
        private String wczt;
        private String ljds;
        private String jhds;
        private String zc;
        private String iswsc;
        private String scsj;

        public String getScsj() {
            return scsj;
        }

        public void setScsj(String scsj) {
            this.scsj = scsj;
        }



        public String getJhid() {
            return jhid;
        }

        public void setJhid(String jhid) {
            this.jhid = jhid;
        }

        public String getZxid() {
            return zxid;
        }

        public void setZxid(String zxid) {
            this.zxid = zxid;
        }

        public String getJhmc() {
            return jhmc;
        }

        public void setJhmc(String jhmc) {
            this.jhmc = jhmc;
        }

        public String getJhlx() {
            return jhlx;
        }

        public void setJhlx(String jhlx) {
            this.jhlx = jhlx;
        }

        public String getJhzq() {
            return jhzq;
        }

        public void setJhzq(String jhzq) {
            this.jhzq = jhzq;
        }

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public String getEt() {
            return et;
        }

        public void setEt(String et) {
            this.et = et;
        }

        public String getWczt() {
            return wczt;
        }

        public void setWczt(String wczt) {
            this.wczt = wczt;
        }

        public String getLjds() {
            return ljds;
        }

        public void setLjds(String ljds) {
            this.ljds = ljds;
        }

        public String getJhds() {
            return jhds;
        }

        public void setJhds(String jhds) {
            this.jhds = jhds;
        }

        public String getZc() {
            return zc;
        }

        public void setZc(String zc) {
            this.zc = zc;
        }

        public String getIswsc() {
            return iswsc;
        }

        public void setIswsc(String iswsc) {
            this.iswsc = iswsc;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.jhid);
            dest.writeString(this.zxid);
            dest.writeString(this.jhmc);
            dest.writeString(this.jhlx);
            dest.writeString(this.jhzq);
            dest.writeString(this.st);
            dest.writeString(this.et);
            dest.writeString(this.wczt);
            dest.writeString(this.ljds);
            dest.writeString(this.jhds);
            dest.writeString(this.zc);
            dest.writeString(this.iswsc);
            dest.writeString(this.scsj);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.jhid = in.readString();
            this.zxid = in.readString();
            this.jhmc = in.readString();
            this.jhlx = in.readString();
            this.jhzq = in.readString();
            this.st = in.readString();
            this.et = in.readString();
            this.wczt = in.readString();
            this.ljds = in.readString();
            this.jhds = in.readString();
            this.zc = in.readString();
            this.iswsc = in.readString();
            this.scsj = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
