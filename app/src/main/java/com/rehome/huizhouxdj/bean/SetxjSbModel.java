package com.rehome.huizhouxdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ruihong on 2018/5/10.
 */

public class SetxjSbModel implements Parcelable {

    private String sbId;
    private String value;
    private Boolean statu;

    public String getSbId() {
        return sbId;
    }

    public void setSbId(String sbId) {
        this.sbId = sbId;
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

    public SetxjSbModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sbId);
        dest.writeString(this.value);
        dest.writeValue(this.statu);
    }

    protected SetxjSbModel(Parcel in) {
        this.sbId = in.readString();
        this.value = in.readString();
        this.statu = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<SetxjSbModel> CREATOR = new Creator<SetxjSbModel>() {
        @Override
        public SetxjSbModel createFromParcel(Parcel source) {
            return new SetxjSbModel(source);
        }

        @Override
        public SetxjSbModel[] newArray(int size) {
            return new SetxjSbModel[size];
        }
    };
}
