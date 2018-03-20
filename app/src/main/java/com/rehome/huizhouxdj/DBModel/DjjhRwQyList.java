package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 点检计划任务区域列表
 */


public class DjjhRwQyList extends DataSupport implements Parcelable {

    private String qymc;//区域名称

    private String JHID;//计划id

    private List<DjjhRwQy> DjjhRqqys;

    private DjjhRwList djjhRwList;




    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    @SerializedName("区域分组")


    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public DjjhRwList getDjjhRwList() {
        return djjhRwList;
    }

    public void setDjjhRwList(DjjhRwList djjhRwList) {
        this.djjhRwList = djjhRwList;
    }

    public List<DjjhRwQy> getDjjhRqqys() {
        return DjjhRqqys;
    }

    public void setDjjhRqqys(List<DjjhRwQy> DjjhRqqys) {
        this.DjjhRqqys = DjjhRqqys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.qymc);
        dest.writeParcelable(this.djjhRwList, flags);
        dest.writeTypedList(this.DjjhRqqys);
    }

    public DjjhRwQyList() {
    }

    protected DjjhRwQyList(Parcel in) {
        this.qymc = in.readString();
        this.djjhRwList = in.readParcelable(DjjhRwList.class.getClassLoader());
        this.DjjhRqqys = in.createTypedArrayList(DjjhRwQy.CREATOR);
    }

    public static final Creator<DjjhRwQyList> CREATOR = new Creator<DjjhRwQyList>() {
        @Override
        public DjjhRwQyList createFromParcel(Parcel source) {
            return new DjjhRwQyList(source);
        }

        @Override
        public DjjhRwQyList[] newArray(int size) {
            return new DjjhRwQyList[size];
        }
    };
}
