package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/17.
 * 巡视区域列表
 */

public class XsjhQyBean {

    /**
     * state : 1
     * msg : 暂无数据
     * data : [{"zxid":"JH00000000062","qybh":"YX002","qymc":"2号机厂高变区域","nfcbm":"","txm":"YX002","data":[{"dbh":"10205","dlxbh":"1","dlxmc":"巡视","sb":" 2号机厂高变区域","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10013","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变高压绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10014","dlxbh":"2","dlxmc":"抄表","sb":"高压绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10015","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变低压X侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10016","dlxbh":"2","dlxmc":"抄表","sb":"低压X侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10017","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变低压Y侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10018","dlxbh":"2","dlxmc":"抄表","sb":"低压Y侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10019","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变上层油温","zymc":"1","dw":"℃","dz":"","gz":"90","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10020","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变冷却系统运行方式","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10021","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变可燃气体浓度","zymc":"1","dw":"ppm","dz":"","gz":"150","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10022","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变高压绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10023","dlxbh":"2","dlxmc":"抄表","sb":"高压绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10024","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变低压X侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10025","dlxbh":"2","dlxmc":"抄表","sb":"低压X侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10026","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变低压Y侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10027","dlxbh":"2","dlxmc":"抄表","sb":"低压Y侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10028","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变上层油温","zymc":"1","dw":"℃","dz":"","gz":"90","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10029","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变冷却系统运行方式","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10030","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变可燃气体浓度","zymc":"1","dw":"ppm","dz":"","gz":"150","zczt":"","bsyl":"","bqyl":"","xcnr":""}]}]
     */

    private int state;
    private String msg;
    private List<DataBeanX> data;

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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX implements Parcelable {
        /**
         * zxid : JH00000000062
         * qybh : YX002
         * qymc : 2号机厂高变区域
         * nfcbm :
         * txm : YX002
         * data : [{"dbh":"10205","dlxbh":"1","dlxmc":"巡视","sb":" 2号机厂高变区域","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10013","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变高压绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10014","dlxbh":"2","dlxmc":"抄表","sb":"高压绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10015","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变低压X侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10016","dlxbh":"2","dlxmc":"抄表","sb":"低压X侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10017","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变低压Y侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10018","dlxbh":"2","dlxmc":"抄表","sb":"低压Y侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10019","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变上层油温","zymc":"1","dw":"℃","dz":"","gz":"90","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10020","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变冷却系统运行方式","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10021","dlxbh":"2","dlxmc":"抄表","sb":"A厂高变可燃气体浓度","zymc":"1","dw":"ppm","dz":"","gz":"150","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10022","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变高压绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10023","dlxbh":"2","dlxmc":"抄表","sb":"高压绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10024","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变低压X侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10025","dlxbh":"2","dlxmc":"抄表","sb":"低压X侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10026","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变低压Y侧绕组温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10027","dlxbh":"2","dlxmc":"抄表","sb":"低压Y侧绕组历史最高温度","zymc":"1","dw":"℃","dz":"","gz":"106","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10028","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变上层油温","zymc":"1","dw":"℃","dz":"","gz":"90","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10029","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变冷却系统运行方式","zymc":"1","dw":"","dz":"","gz":"","zczt":"","bsyl":"","bqyl":"","xcnr":""},{"dbh":"10030","dlxbh":"2","dlxmc":"抄表","sb":"B厂高变可燃气体浓度","zymc":"1","dw":"ppm","dz":"","gz":"150","zczt":"","bsyl":"","bqyl":"","xcnr":""}]
         */

        private String zxid;
        private String qybh;
        private String qymc;
        private String nfcbm;
        private String txm;
        private String sczt;
        private String scsj;
        private List<DataBean> data;

        public String getZxid() {
            return zxid;
        }

        public void setZxid(String zxid) {
            this.zxid = zxid;
        }

        public String getQybh() {
            return qybh;
        }

        public void setQybh(String qybh) {
            this.qybh = qybh;
        }

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public String getNfcbm() {
            return nfcbm;
        }

        public void setNfcbm(String nfcbm) {
            this.nfcbm = nfcbm;
        }

        public String getTxm() {
            return txm;
        }

        public void setTxm(String txm) {
            this.txm = txm;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public String getSczt() {
            return sczt;
        }

        public void setSczt(String sczt) {
            this.sczt = sczt;
        }

        public String getScsj() {
            return scsj;
        }

        public void setScsj(String scsj) {
            this.scsj = scsj;
        }

        public static class DataBean implements Parcelable {
            /**
             * dbh : 10205
             * dlxbh : 1
             * dlxmc : 巡视
             * sb :  2号机厂高变区域
             * zymc : 1
             * dw :
             * dz :
             * gz :
             * zczt :
             * bsyl :
             * bqyl :
             * xcnr :
             */

            private String dbh;
            private String dlxbh;
            private String dlxmc;
            private String sb;
            private String zymc;
            private String dw;
            private String dz;
            private String gz;
            private String zczt;
            private String bsyl;
            private String bqyl;
            private String xcnr;
            private String cbsz = "";
            private String djsj = "";
            private String smfx;
            private String sisData;
            private String dpx;
            private String scid;


            public String getDbh() {
                return dbh;
            }

            public void setDbh(String dbh) {
                this.dbh = dbh;
            }

            public String getDlxbh() {
                return dlxbh;
            }

            public void setDlxbh(String dlxbh) {
                this.dlxbh = dlxbh;
            }

            public String getDlxmc() {
                return dlxmc;
            }

            public void setDlxmc(String dlxmc) {
                this.dlxmc = dlxmc;
            }

            public String getSb() {
                return sb;
            }

            public void setSb(String sb) {
                this.sb = sb;
            }

            public String getZymc() {
                return zymc;
            }

            public void setZymc(String zymc) {
                this.zymc = zymc;
            }

            public String getDw() {
                return dw;
            }

            public void setDw(String dw) {
                this.dw = dw;
            }

            public String getDz() {
                return dz;
            }

            public void setDz(String dz) {
                this.dz = dz;
            }

            public String getGz() {
                return gz;
            }

            public void setGz(String gz) {
                this.gz = gz;
            }

            public String getZczt() {
                return zczt;
            }

            public void setZczt(String zczt) {
                this.zczt = zczt;
            }

            public String getBsyl() {
                return bsyl;
            }

            public void setBsyl(String bsyl) {
                this.bsyl = bsyl;
            }

            public String getBqyl() {
                return bqyl;
            }

            public void setBqyl(String bqyl) {
                this.bqyl = bqyl;
            }

            public String getXcnr() {
                return xcnr;
            }

            public void setXcnr(String xcnr) {
                this.xcnr = xcnr;
            }

            public String getCbsz() {
                return cbsz;
            }

            public void setCbsz(String cbsz) {
                this.cbsz = cbsz;
            }

            public String getDjsj() {
                return djsj;
            }

            public void setDjsj(String djsj) {
                this.djsj = djsj;
            }

            public String getSmfx() {
                return smfx;
            }

            public void setSmfx(String smfx) {
                this.smfx = smfx;
            }

            public String getSisData() {
                return sisData;
            }

            public void setSisData(String sisData) {
                this.sisData = sisData;
            }

            public String getDpx() {
                return dpx;
            }

            public void setDpx(String dpx) {
                this.dpx = dpx;
            }

            public String getScid() {
                return scid;
            }

            public void setScid(String scid) {
                this.scid = scid;
            }

            public DataBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.dbh);
                dest.writeString(this.dlxbh);
                dest.writeString(this.dlxmc);
                dest.writeString(this.sb);
                dest.writeString(this.zymc);
                dest.writeString(this.dw);
                dest.writeString(this.dz);
                dest.writeString(this.gz);
                dest.writeString(this.zczt);
                dest.writeString(this.bsyl);
                dest.writeString(this.bqyl);
                dest.writeString(this.xcnr);
                dest.writeString(this.cbsz);
                dest.writeString(this.djsj);
                dest.writeString(this.smfx);
                dest.writeString(this.sisData);
                dest.writeString(this.dpx);
                dest.writeString(this.scid);
            }

            protected DataBean(Parcel in) {
                this.dbh = in.readString();
                this.dlxbh = in.readString();
                this.dlxmc = in.readString();
                this.sb = in.readString();
                this.zymc = in.readString();
                this.dw = in.readString();
                this.dz = in.readString();
                this.gz = in.readString();
                this.zczt = in.readString();
                this.bsyl = in.readString();
                this.bqyl = in.readString();
                this.xcnr = in.readString();
                this.cbsz = in.readString();
                this.djsj = in.readString();
                this.smfx = in.readString();
                this.sisData = in.readString();
                this.dpx = in.readString();
                this.scid = in.readString();
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

        public DataBeanX() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.zxid);
            dest.writeString(this.qybh);
            dest.writeString(this.qymc);
            dest.writeString(this.nfcbm);
            dest.writeString(this.txm);
            dest.writeString(this.sczt);
            dest.writeString(this.scsj);
            dest.writeTypedList(this.data);
        }

        protected DataBeanX(Parcel in) {
            this.zxid = in.readString();
            this.qybh = in.readString();
            this.qymc = in.readString();
            this.nfcbm = in.readString();
            this.txm = in.readString();
            this.sczt = in.readString();
            this.scsj = in.readString();
            this.data = in.createTypedArrayList(DataBean.CREATOR);
        }

        public static final Creator<DataBeanX> CREATOR = new Creator<DataBeanX>() {
            @Override
            public DataBeanX createFromParcel(Parcel source) {
                return new DataBeanX(source);
            }

            @Override
            public DataBeanX[] newArray(int size) {
                return new DataBeanX[size];
            }
        };
    }
}
