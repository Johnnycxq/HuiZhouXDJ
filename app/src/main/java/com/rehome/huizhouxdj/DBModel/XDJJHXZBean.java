package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ruihong on 2018/2/26.
 */

public class XDJJHXZBean extends DataSupport implements Parcelable {


    /**
     * state : 0
     * msg : 暂无数据
     * data : [{"QYBH":"00000000021","QYMC":"测试区域1","QYEWM":"asdfasd","QYEWMZT":"1","JHID":"zx00000001","QYNFC":"12312fdwfasdf","QYNFCZT":"1","QYD_DATA":[{"SCID":"B17A736A283C47999347F8E01CB5C1A6","SBMC":"测试设备1","BJMC":"部件2","DID":"5C556F3D44DF420BB8D34593842AF8D2","DMC":"消防测试点3_SBL1","BZZ":"标准值","SJMC":"数据名称","SJDW":"数据单位","JCFS":"检查方式","XMZQ":"1","LRFS":"1","LRMRZ":""}],"QYAQFX_DATA":[{"AQFXID":"D3F75475F4D14A25BCBC1C798ED38368","FXLX":"安全风险测试","FXMS":"风险描述","FHCS":"防护措施"},{"AQFXID":"B0B062FDE8FF42E3B3EB59FD78E5639F","FXLX":"安全风险测试1","FXMS":"风险描述1","FHCS":"防护措施1"}]}]
     */

    private int state;
    private String msg;
    private List<XDJJHXZDataBean> data;

    protected XDJJHXZBean(Parcel in) {
        state = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(XDJJHXZDataBean.CREATOR);
    }

    public static final Creator<XDJJHXZBean> CREATOR = new Creator<XDJJHXZBean>() {
        @Override
        public XDJJHXZBean createFromParcel(Parcel in) {
            return new XDJJHXZBean(in);
        }

        @Override
        public XDJJHXZBean[] newArray(int size) {
            return new XDJJHXZBean[size];
        }
    };

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

    public List<XDJJHXZDataBean> getData() {
        return data;
    }

    public void setData(List<XDJJHXZDataBean> data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(state);
        dest.writeString(msg);
        dest.writeTypedList(data);
    }
}
