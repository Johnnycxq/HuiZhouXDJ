package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ruihong on 2018/5/10.
 */

public class XjSbModel implements Parcelable {

    private String sbmc;
    private String value;
    private Boolean statu;

    public String getSbId() {
        return sbmc;
    }

    public void setSbId(String sbId) {
        this.sbmc = sbId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStatu() {
        return statu;
    }

    public void setStatu(Boolean statu) {
        this.statu = statu;
    }

    public XjSbModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sbmc);
        dest.writeString(this.value);
        dest.writeValue(this.statu);
    }

    protected XjSbModel(Parcel in) {
        this.sbmc = in.readString();
        this.value = in.readString();
        this.statu = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<XjSbModel> CREATOR = new Creator<XjSbModel>() {
        @Override
        public XjSbModel createFromParcel(Parcel source) {
            return new XjSbModel(source);
        }

        @Override
        public XjSbModel[] newArray(int size) {
            return new XjSbModel[size];
        }
    };
}
