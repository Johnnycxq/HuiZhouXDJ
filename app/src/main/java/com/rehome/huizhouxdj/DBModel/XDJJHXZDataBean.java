package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Johnny_Chen
 * on 2018/2/28.
 */

public class XDJJHXZDataBean extends DataSupport implements Parcelable {


    /**
     * QYBH : 00000000021
     * QYMC : 测试区域1
     * QYEWM : asdfasd
     * QYEWMZT : 1
     * JHID : zx00000001
     * QYNFC : 12312fdwfasdf
     * QYNFCZT : 1
     * QYD_DATA : [{"SCID":"B17A736A283C47999347F8E01CB5C1A6","SBMC":"测试设备1","BJMC":"部件2","DID":"5C556F3D44DF420BB8D34593842AF8D2","DMC":"消防测试点3_SBL1","BZZ":"标准值","SJMC":"数据名称","SJDW":"数据单位","JCFS":"检查方式","XMZQ":"1","LRFS":"1","LRMRZ":""}]
     * QYAQFX_DATA : [{"AQFXID":"D3F75475F4D14A25BCBC1C798ED38368","FXLX":"安全风险测试","FXMS":"风险描述","FHCS":"防护措施"},{"AQFXID":"B0B062FDE8FF42E3B3EB59FD78E5639F","FXLX":"安全风险测试1","FXMS":"风险描述1","FHCS":"防护措施1"}]
     */

    private String QYBH;
    private String QYMC;
    private String QYEWM;
    private String QYEWMZT;
    private String JHID;
    private String QYNFC;
    private String QYNFCZT;
    private List<QYDDATABean> QYD_DATA;
    private List<QYAQFXDATABean> QYAQFX_DATA;

    protected XDJJHXZDataBean(Parcel in) {
        QYBH = in.readString();
        QYMC = in.readString();
        QYEWM = in.readString();
        QYEWMZT = in.readString();
        JHID = in.readString();
        QYNFC = in.readString();
        QYNFCZT = in.readString();
        QYD_DATA = in.createTypedArrayList(QYDDATABean.CREATOR);
        QYAQFX_DATA = in.createTypedArrayList(QYAQFXDATABean.CREATOR);
    }

    public static final Creator<XDJJHXZDataBean> CREATOR = new Creator<XDJJHXZDataBean>() {
        @Override
        public XDJJHXZDataBean createFromParcel(Parcel in) {
            return new XDJJHXZDataBean(in);
        }

        @Override
        public XDJJHXZDataBean[] newArray(int size) {
            return new XDJJHXZDataBean[size];
        }
    };

    public String getQYBH() {
        return QYBH;
    }

    public void setQYBH(String QYBH) {
        this.QYBH = QYBH;
    }

    public String getQYMC() {
        return QYMC;
    }

    public void setQYMC(String QYMC) {
        this.QYMC = QYMC;
    }

    public String getQYEWM() {
        return QYEWM;
    }

    public void setQYEWM(String QYEWM) {
        this.QYEWM = QYEWM;
    }

    public String getQYEWMZT() {
        return QYEWMZT;
    }

    public void setQYEWMZT(String QYEWMZT) {
        this.QYEWMZT = QYEWMZT;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getQYNFC() {
        return QYNFC;
    }

    public void setQYNFC(String QYNFC) {
        this.QYNFC = QYNFC;
    }

    public String getQYNFCZT() {
        return QYNFCZT;
    }

    public void setQYNFCZT(String QYNFCZT) {
        this.QYNFCZT = QYNFCZT;
    }

    public List<QYDDATABean> getQYD_DATA() {
        return QYD_DATA;
    }

    public void setQYD_DATA(List<QYDDATABean> QYD_DATA) {
        this.QYD_DATA = QYD_DATA;
    }

    public List<QYAQFXDATABean> getQYAQFX_DATA() {
        return QYAQFX_DATA;
    }

    public void setQYAQFX_DATA(List<QYAQFXDATABean> QYAQFX_DATA) {
        this.QYAQFX_DATA = QYAQFX_DATA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(QYBH);
        dest.writeString(QYMC);
        dest.writeString(QYEWM);
        dest.writeString(QYEWMZT);
        dest.writeString(JHID);
        dest.writeString(QYNFC);
        dest.writeString(QYNFCZT);
        dest.writeTypedList(QYD_DATA);
        dest.writeTypedList(QYAQFX_DATA);
    }

    public static class QYDDATABean extends DataSupport implements Parcelable {
        /**
         * SCID : B17A736A283C47999347F8E01CB5C1A6
         * SBMC : 测试设备1
         * BJMC : 部件2
         * DID : 5C556F3D44DF420BB8D34593842AF8D2
         * DMC : 消防测试点3_SBL1
         * BZZ : 标准值
         * SJMC : 数据名称
         * SJDW : 数据单位
         * JCFS : 检查方式
         * XMZQ : 1
         * LRFS : 1
         * LRMRZ :
         */

        private String SCID;
        private String SBMC;
        private String BJMC;
        private String DID;
        private String DMC;
        private String BZZ;
        private String SJMC;
        private String SJDW;
        private String JCFS;
        private String XMZQ;
        private String LRFS;
        private String LRMRZ;

        protected QYDDATABean(Parcel in) {
            SCID = in.readString();
            SBMC = in.readString();
            BJMC = in.readString();
            DID = in.readString();
            DMC = in.readString();
            BZZ = in.readString();
            SJMC = in.readString();
            SJDW = in.readString();
            JCFS = in.readString();
            XMZQ = in.readString();
            LRFS = in.readString();
            LRMRZ = in.readString();
        }

        public static final Creator<QYDDATABean> CREATOR = new Creator<QYDDATABean>() {
            @Override
            public QYDDATABean createFromParcel(Parcel in) {
                return new QYDDATABean(in);
            }

            @Override
            public QYDDATABean[] newArray(int size) {
                return new QYDDATABean[size];
            }
        };

        public String getSCID() {
            return SCID;
        }

        public void setSCID(String SCID) {
            this.SCID = SCID;
        }

        public String getSBMC() {
            return SBMC;
        }

        public void setSBMC(String SBMC) {
            this.SBMC = SBMC;
        }

        public String getBJMC() {
            return BJMC;
        }

        public void setBJMC(String BJMC) {
            this.BJMC = BJMC;
        }

        public String getDID() {
            return DID;
        }

        public void setDID(String DID) {
            this.DID = DID;
        }

        public String getDMC() {
            return DMC;
        }

        public void setDMC(String DMC) {
            this.DMC = DMC;
        }

        public String getBZZ() {
            return BZZ;
        }

        public void setBZZ(String BZZ) {
            this.BZZ = BZZ;
        }

        public String getSJMC() {
            return SJMC;
        }

        public void setSJMC(String SJMC) {
            this.SJMC = SJMC;
        }

        public String getSJDW() {
            return SJDW;
        }

        public void setSJDW(String SJDW) {
            this.SJDW = SJDW;
        }

        public String getJCFS() {
            return JCFS;
        }

        public void setJCFS(String JCFS) {
            this.JCFS = JCFS;
        }

        public String getXMZQ() {
            return XMZQ;
        }

        public void setXMZQ(String XMZQ) {
            this.XMZQ = XMZQ;
        }

        public String getLRFS() {
            return LRFS;
        }

        public void setLRFS(String LRFS) {
            this.LRFS = LRFS;
        }

        public String getLRMRZ() {
            return LRMRZ;
        }

        public void setLRMRZ(String LRMRZ) {
            this.LRMRZ = LRMRZ;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(SCID);
            dest.writeString(SBMC);
            dest.writeString(BJMC);
            dest.writeString(DID);
            dest.writeString(DMC);
            dest.writeString(BZZ);
            dest.writeString(SJMC);
            dest.writeString(SJDW);
            dest.writeString(JCFS);
            dest.writeString(XMZQ);
            dest.writeString(LRFS);
            dest.writeString(LRMRZ);
        }
    }

    public static class QYAQFXDATABean extends DataSupport implements Parcelable {
        /**
         * AQFXID : D3F75475F4D14A25BCBC1C798ED38368
         * FXLX : 安全风险测试
         * FXMS : 风险描述
         * FHCS : 防护措施
         */

        private String AQFXID;
        private String FXLX;
        private String FXMS;
        private String FHCS;

        protected QYAQFXDATABean(Parcel in) {
            AQFXID = in.readString();
            FXLX = in.readString();
            FXMS = in.readString();
            FHCS = in.readString();
        }

        public static final Creator<QYAQFXDATABean> CREATOR = new Creator<QYAQFXDATABean>() {
            @Override
            public QYAQFXDATABean createFromParcel(Parcel in) {
                return new QYAQFXDATABean(in);
            }

            @Override
            public QYAQFXDATABean[] newArray(int size) {
                return new QYAQFXDATABean[size];
            }
        };

        public String getAQFXID() {
            return AQFXID;
        }

        public void setAQFXID(String AQFXID) {
            this.AQFXID = AQFXID;
        }

        public String getFXLX() {
            return FXLX;
        }

        public void setFXLX(String FXLX) {
            this.FXLX = FXLX;
        }

        public String getFXMS() {
            return FXMS;
        }

        public void setFXMS(String FXMS) {
            this.FXMS = FXMS;
        }

        public String getFHCS() {
            return FHCS;
        }

        public void setFHCS(String FHCS) {
            this.FHCS = FHCS;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(AQFXID);
            dest.writeString(FXLX);
            dest.writeString(FXMS);
            dest.writeString(FHCS);
        }
    }
}
