package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfDjjhRwqyList extends DataSupport implements Parcelable {

    private String qyname;

    private XfDjjhRwList xfDjjhRwList;

    public XfDjjhRwList getXfDjjhRwList() {
        return xfDjjhRwList;
    }

    public void setXfDjjhRwList(XfDjjhRwList xfDjjhRwList) {
        this.xfDjjhRwList = xfDjjhRwList;
    }

    public String getQyname() {
        return qyname;
    }

    public void setQyname(String qyname) {
        this.qyname = qyname;
    }

    @SerializedName("区域分组")
    private List<XfDjjhRwqy> xfdjjhrwqy;

    public List<XfDjjhRwqy> getXfdjjhrwqy() {
        return xfdjjhrwqy;
    }

    public void setXfdjjhrwqy(List<XfDjjhRwqy> xfdjjhrwqy) {
        this.xfdjjhrwqy = xfdjjhrwqy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.qyname);
        dest.writeParcelable(this.xfDjjhRwList, flags);
        dest.writeTypedList(this.xfdjjhrwqy);
    }

    public XfDjjhRwqyList() {
    }

    protected XfDjjhRwqyList(Parcel in) {
        this.qyname = in.readString();
        this.xfDjjhRwList = in.readParcelable(XfDjjhRwList.class.getClassLoader());
        this.xfdjjhrwqy = in.createTypedArrayList(XfDjjhRwqy.CREATOR);
    }

    public static final Creator<XfDjjhRwqyList> CREATOR = new Creator<XfDjjhRwqyList>() {
        @Override
        public XfDjjhRwqyList createFromParcel(Parcel source) {
            return new XfDjjhRwqyList(source);
        }

        @Override
        public XfDjjhRwqyList[] newArray(int size) {
            return new XfDjjhRwqyList[size];
        }
    };
}
