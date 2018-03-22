package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihong on 2017/12/28.
 */

public class AqjclbBean implements Parcelable {
    /**
     * state : 1
     * msg :
     * data : [{"JHID":"jh00000000061","JHMC":"TEST1","ST":"2017/12/20 0:00:00","ET":"2017/12/30 0:00:00","JCFW":"123123","TJR":"SH_Admin","TJSJ":"2017/12/21 16:38:57","XGR":"","XGSJ":""},{"JHID":"jh00000000072","JHMC":"test9","ST":"2017/12/13 0:00:00","ET":"2017/12/30 0:00:00","JCFW":"123","TJR":"SH_Admin","TJSJ":"2017/12/22 13:41:44","XGR":"SH_Admin","XGSJ":"2017/12/22 13:41:48"},{"JHID":"jh00000000075","JHMC":"TEST13","ST":"2017/12/7 0:00:00","ET":"2017/12/29 0:00:00","JCFW":"123","TJR":"SH_Admin","TJSJ":"2017/12/22 13:50:24","XGR":"","XGSJ":""},{"JHID":"jh00000000064","JHMC":"TEST4","ST":"2017/12/16 0:00:00","ET":"2017/12/30 0:00:00","JCFW":"123123","TJR":"SH_Admin","TJSJ":"2017/12/22 9:31:48","XGR":"","XGSJ":""},{"JHID":"jh00000000066","JHMC":"TEST5","ST":"2017/12/21 0:00:00","ET":"2018/1/6 0:00:00","JCFW":"123","TJR":"SH_Admin","TJSJ":"2017/12/22 9:56:35","XGR":"","XGSJ":""},{"JHID":"jh00000000068","JHMC":"TEST6","ST":"2017/12/22 0:00:00","ET":"2017/12/29 0:00:00","JCFW":"1111","TJR":"SH_Admin","TJSJ":"2017/12/22 10:16:42","XGR":"","XGSJ":""}]
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
         * JHID : jh00000000061
         * JHMC : TEST1
         * ST : 2017/12/20 0:00:00
         * ET : 2017/12/30 0:00:00
         * JCFW : 123123
         * TJR : SH_Admin
         * TJSJ : 2017/12/21 16:38:57
         * XGR :
         * XGSJ :
         */

        private String JHID;
        private String JHMC;
        private String ST;
        private String ET;
        private String JCFW;
        private String TJR;
        private String TJSJ;
        private String XGR;
        private String XGSJ;

        public String getJHID() {
            return JHID;
        }

        public void setJHID(String JHID) {
            this.JHID = JHID;
        }

        public String getJHMC() {
            return JHMC;
        }

        public void setJHMC(String JHMC) {
            this.JHMC = JHMC;
        }

        public String getST() {
            return ST;
        }

        public void setST(String ST) {
            this.ST = ST;
        }

        public String getET() {
            return ET;
        }

        public void setET(String ET) {
            this.ET = ET;
        }

        public String getJCFW() {
            return JCFW;
        }

        public void setJCFW(String JCFW) {
            this.JCFW = JCFW;
        }

        public String getTJR() {
            return TJR;
        }

        public void setTJR(String TJR) {
            this.TJR = TJR;
        }

        public String getTJSJ() {
            return TJSJ;
        }

        public void setTJSJ(String TJSJ) {
            this.TJSJ = TJSJ;
        }

        public String getXGR() {
            return XGR;
        }

        public void setXGR(String XGR) {
            this.XGR = XGR;
        }

        public String getXGSJ() {
            return XGSJ;
        }

        public void setXGSJ(String XGSJ) {
            this.XGSJ = XGSJ;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.JHID);
            dest.writeString(this.JHMC);
            dest.writeString(this.ST);
            dest.writeString(this.ET);
            dest.writeString(this.JCFW);
            dest.writeString(this.TJR);
            dest.writeString(this.TJSJ);
            dest.writeString(this.XGR);
            dest.writeString(this.XGSJ);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.JHID = in.readString();
            this.JHMC = in.readString();
            this.ST = in.readString();
            this.ET = in.readString();
            this.JCFW = in.readString();
            this.TJR = in.readString();
            this.TJSJ = in.readString();
            this.XGR = in.readString();
            this.XGSJ = in.readString();
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

    public AqjclbBean() {
    }

    protected AqjclbBean(Parcel in) {
        this.state = in.readInt();
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<AqjclbBean> CREATOR = new Creator<AqjclbBean>() {
        @Override
        public AqjclbBean createFromParcel(Parcel source) {
            return new AqjclbBean(source);
        }

        @Override
        public AqjclbBean[] newArray(int size) {
            return new AqjclbBean[size];
        }
    };
}
