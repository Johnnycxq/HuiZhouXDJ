package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ruihong on 2018/1/20.
 */

public class AqjcrwListBean {
    /**
     * state : 1
     * msg :
     * data : [{"RWID":"RW00000000071","RWZT":"2","RWZT_NAME":"整改中","JHID":"jh00000000101","JHMC":"TEST1","WTQY":"13123123123123123131","WTMS":"234213546432134","FXLB":"","YHDJ":"","ZRBM":"BM00003","LRR":"管理员","LRSJ":"2018/1/20 10:21:38","SCSJ":"2018/1/20 10:20:41","TJSJ":"2018/1/20 10:21:35","APR":"管理员","APSJ":"2018/1/20 10:21:51","ZGJG":"","ZGR":"管理员","ZGSJ":"","QRR":"","QRSJ":"","QRBZ":"","IMG":[{"IMGLX":"0","IMGURL":"AQJC_IMG/jh00000000101/B18B074D8B8E4CB4B6C4515258A25E22.jpg"}]},{"RWID":"RW00000000072","RWZT":"2","RWZT_NAME":"整改中","JHID":"jh00000000101","JHMC":"TEST1","WTQY":"如意","WTMS":"测试","FXLB":"","YHDJ":"","ZRBM":"BM00003","LRR":"管理员","LRSJ":"2018/1/20 10:21:56","SCSJ":"2018/1/20 10:20:59","TJSJ":"2018/1/20 10:21:12","APR":"管理员","APSJ":"2018/1/20 10:21:30","ZGJG":"","ZGR":"管理员","ZGSJ":"","QRR":"","QRSJ":"","QRBZ":"","IMG":[{"IMGLX":"0","IMGURL":"AQJC_IMG/jh00000000101/7BF8F27227F94BC5A8B28381F0065555.jpg"}]}]
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


        private String RWID;
        private String RWZT;
        private String RWZT_NAME;
        private String JHID;
        private String JHMC;
        private String WTQY;
        private String WTMS;
        private String FXLB;
        private String YHDJ;
        private String ZRBM;
        private String LRR;
        private String LRSJ;
        private String SCSJ;
        private String TJSJ;
        private String APR;
        private String APSJ;
        private String ZGJG;
        private String ZGR;
        private String ZGSJ;
        private String QRR;
        private String QRSJ;
        private String QRBZ;
        private List<IMGBean> IMG;

        public String getRWID() {
            return RWID;
        }

        public void setRWID(String RWID) {
            this.RWID = RWID;
        }

        public String getRWZT() {
            return RWZT;
        }

        public void setRWZT(String RWZT) {
            this.RWZT = RWZT;
        }

        public String getRWZT_NAME() {
            return RWZT_NAME;
        }

        public void setRWZT_NAME(String RWZT_NAME) {
            this.RWZT_NAME = RWZT_NAME;
        }

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

        public String getWTQY() {
            return WTQY;
        }

        public void setWTQY(String WTQY) {
            this.WTQY = WTQY;
        }

        public String getWTMS() {
            return WTMS;
        }

        public void setWTMS(String WTMS) {
            this.WTMS = WTMS;
        }

        public String getFXLB() {
            return FXLB;
        }

        public void setFXLB(String FXLB) {
            this.FXLB = FXLB;
        }

        public String getYHDJ() {
            return YHDJ;
        }

        public void setYHDJ(String YHDJ) {
            this.YHDJ = YHDJ;
        }

        public String getZRBM() {
            return ZRBM;
        }

        public void setZRBM(String ZRBM) {
            this.ZRBM = ZRBM;
        }

        public String getLRR() {
            return LRR;
        }

        public void setLRR(String LRR) {
            this.LRR = LRR;
        }

        public String getLRSJ() {
            return LRSJ;
        }

        public void setLRSJ(String LRSJ) {
            this.LRSJ = LRSJ;
        }

        public String getSCSJ() {
            return SCSJ;
        }

        public void setSCSJ(String SCSJ) {
            this.SCSJ = SCSJ;
        }

        public String getTJSJ() {
            return TJSJ;
        }

        public void setTJSJ(String TJSJ) {
            this.TJSJ = TJSJ;
        }

        public String getAPR() {
            return APR;
        }

        public void setAPR(String APR) {
            this.APR = APR;
        }

        public String getAPSJ() {
            return APSJ;
        }

        public void setAPSJ(String APSJ) {
            this.APSJ = APSJ;
        }

        public String getZGJG() {
            return ZGJG;
        }

        public void setZGJG(String ZGJG) {
            this.ZGJG = ZGJG;
        }

        public String getZGR() {
            return ZGR;
        }

        public void setZGR(String ZGR) {
            this.ZGR = ZGR;
        }

        public String getZGSJ() {
            return ZGSJ;
        }

        public void setZGSJ(String ZGSJ) {
            this.ZGSJ = ZGSJ;
        }

        public String getQRR() {
            return QRR;
        }

        public void setQRR(String QRR) {
            this.QRR = QRR;
        }

        public String getQRSJ() {
            return QRSJ;
        }

        public void setQRSJ(String QRSJ) {
            this.QRSJ = QRSJ;
        }

        public String getQRBZ() {
            return QRBZ;
        }

        public void setQRBZ(String QRBZ) {
            this.QRBZ = QRBZ;
        }

        public List<IMGBean> getIMG() {
            return IMG;
        }

        public void setIMG(List<IMGBean> IMG) {
            this.IMG = IMG;
        }

        public static class IMGBean {
            /**
             * IMGLX : 0
             * IMGURL : AQJC_IMG/jh00000000101/B18B074D8B8E4CB4B6C4515258A25E22.jpg
             */

            private String IMGLX;
            private String IMGURL;

            public String getIMGLX() {
                return IMGLX;
            }

            public void setIMGLX(String IMGLX) {
                this.IMGLX = IMGLX;
            }

            public String getIMGURL() {
                return IMGURL;
            }

            public void setIMGURL(String IMGURL) {
                this.IMGURL = IMGURL;
            }
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.RWID);
            dest.writeString(this.RWZT);
            dest.writeString(this.RWZT_NAME);
            dest.writeString(this.JHID);
            dest.writeString(this.JHMC);
            dest.writeString(this.WTQY);
            dest.writeString(this.WTMS);
            dest.writeString(this.FXLB);
            dest.writeString(this.YHDJ);
            dest.writeString(this.ZRBM);
            dest.writeString(this.LRR);
            dest.writeString(this.LRSJ);
            dest.writeString(this.SCSJ);
            dest.writeString(this.TJSJ);
            dest.writeString(this.APR);
            dest.writeString(this.APSJ);
            dest.writeString(this.ZGJG);
            dest.writeString(this.ZGR);
            dest.writeString(this.ZGSJ);
            dest.writeString(this.QRR);
            dest.writeString(this.QRSJ);
            dest.writeString(this.QRBZ);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.RWID = in.readString();
            this.RWZT = in.readString();
            this.RWZT_NAME = in.readString();
            this.JHID = in.readString();
            this.JHMC = in.readString();
            this.WTQY = in.readString();
            this.WTMS = in.readString();
            this.FXLB = in.readString();
            this.YHDJ = in.readString();
            this.ZRBM = in.readString();
            this.LRR = in.readString();
            this.LRSJ = in.readString();
            this.SCSJ = in.readString();
            this.TJSJ = in.readString();
            this.APR = in.readString();
            this.APSJ = in.readString();
            this.ZGJG = in.readString();
            this.ZGR = in.readString();
            this.ZGSJ = in.readString();
            this.QRR = in.readString();
            this.QRSJ = in.readString();
            this.QRBZ = in.readString();
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
