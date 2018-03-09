package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 点检计划任务列表
 */

public class DjjhRwList extends DataSupport implements Parcelable {

    private String JHID;

    private boolean deleted;

    private int Total;

    private List<DjjhRwQyList> Rows;





    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<DjjhRwQyList> getRows() {
        return Rows;
    }

    public void setRows(List<DjjhRwQyList> Rows) {
        this.Rows = Rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.JHID);
        dest.writeInt(this.Total);
        dest.writeTypedList(this.Rows);
    }

    public DjjhRwList() {
    }

    protected DjjhRwList(Parcel in) {
        this.JHID = in.readString();
        this.Total = in.readInt();
        this.Rows = in.createTypedArrayList(DjjhRwQyList.CREATOR);
    }

    public static final Creator<DjjhRwList> CREATOR = new Creator<DjjhRwList>() {
        @Override
        public DjjhRwList createFromParcel(Parcel source) {
            return new DjjhRwList(source);
        }

        @Override
        public DjjhRwList[] newArray(int size) {
            return new DjjhRwList[size];
        }
    };
}
