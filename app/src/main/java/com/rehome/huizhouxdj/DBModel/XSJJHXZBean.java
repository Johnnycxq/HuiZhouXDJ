package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ruihong on 2018/2/26.
 */

public class XSJJHXZBean extends DataSupport implements Parcelable {

    private int state;
    private String msg;
    private List<XSJJHXZDataBean> data;

    protected XSJJHXZBean(Parcel in) {
        state = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(XSJJHXZDataBean.CREATOR);
    }

    public static final Creator<XSJJHXZBean> CREATOR = new Creator<XSJJHXZBean>() {
        @Override
        public XSJJHXZBean createFromParcel(Parcel in) {
            return new XSJJHXZBean(in);
        }

        @Override
        public XSJJHXZBean[] newArray(int size) {
            return new XSJJHXZBean[size];
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

    public List<XSJJHXZDataBean> getData() {
        return data;
    }

    public void setData(List<XSJJHXZDataBean> data) {
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
