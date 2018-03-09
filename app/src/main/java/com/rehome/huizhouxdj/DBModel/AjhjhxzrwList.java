package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzw on 2016/11/7.
 */

public class AjhjhxzrwList implements Parcelable {

    private int Total;
    private List<Ajhxzrwqylist> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Ajhxzrwqylist> getRows() {
        return Rows;
    }

    public void setRows(List<Ajhxzrwqylist> Rows) {
        this.Rows = Rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Total);
        dest.writeList(this.Rows);
    }

    public AjhjhxzrwList() {
    }

    protected AjhjhxzrwList(Parcel in) {
        this.Total = in.readInt();
        this.Rows = new ArrayList<Ajhxzrwqylist>();
        in.readList(this.Rows, Ajhxzrwqylist.class.getClassLoader());
    }

    public static final Creator<AjhjhxzrwList> CREATOR = new Creator<AjhjhxzrwList>() {
        @Override
        public AjhjhxzrwList createFromParcel(Parcel source) {
            return new AjhjhxzrwList(source);
        }

        @Override
        public AjhjhxzrwList[] newArray(int size) {
            return new AjhjhxzrwList[size];
        }
    };
}
