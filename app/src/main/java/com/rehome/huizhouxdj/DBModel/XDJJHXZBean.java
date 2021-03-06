package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ruihong on 2018/2/26.
 */

public class XDJJHXZBean extends DataSupport implements Parcelable {

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
