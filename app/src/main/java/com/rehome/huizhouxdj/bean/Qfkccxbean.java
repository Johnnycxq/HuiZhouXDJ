package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihong on 2018/3/23.
 */

public class Qfkccxbean implements Parcelable {

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

        private String WZBM;
        private String CKH;
        private String WZMC;
        private String SL;
        private String DW;
        private String DJ;
        private String CC;

        public String getWZBM() {
            return WZBM;
        }

        public void setWZBM(String WZBM) {
            this.WZBM = WZBM;
        }

        public String getCKH() {
            return CKH;
        }

        public void setCKH(String CKH) {
            this.CKH = CKH;
        }

        public String getWZMC() {
            return WZMC;
        }

        public void setWZMC(String WZMC) {
            this.WZMC = WZMC;
        }

        public String getSL() {
            return SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }

        public String getDW() {
            return DW;
        }

        public void setDW(String DW) {
            this.DW = DW;
        }

        public String getDJ() {
            return DJ;
        }

        public void setDJ(String DJ) {
            this.DJ = DJ;
        }

        public String getCC() {
            return CC;
        }

        public void setCC(String CC) {
            this.CC = CC;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.WZBM);
            dest.writeString(this.CKH);
            dest.writeString(this.WZMC);
            dest.writeString(this.SL);
            dest.writeString(this.DW);
            dest.writeString(this.DJ);
            dest.writeString(this.CC);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.WZBM = in.readString();
            this.CKH = in.readString();
            this.WZMC = in.readString();
            this.SL = in.readString();
            this.DW = in.readString();
            this.DJ = in.readString();
            this.CC = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.state);
        dest.writeString(this.msg);
        dest.writeList(this.data);
    }

    public Qfkccxbean() {
    }

    protected Qfkccxbean(Parcel in) {
        this.state = in.readInt();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Qfkccxbean> CREATOR = new Parcelable.Creator<Qfkccxbean>() {
        @Override
        public Qfkccxbean createFromParcel(Parcel source) {
            return new Qfkccxbean(source);
        }

        @Override
        public Qfkccxbean[] newArray(int size) {
            return new Qfkccxbean[size];
        }
    };
}