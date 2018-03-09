package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 保安巡查任务区域
 */

public class XfBaxcRwqyList extends DataSupport implements Parcelable {


    /**
     * Rows : [{"jhid":"20161013090900","xgid":"f90f5fd3dedc4b4aa7a60c5b00295157","xgtype":"2","bh":"BH1","qyname":"1号点","qynfc":"3344NFC","qywz":"1号门岗"},{"jhid":"20161013090900","xgid":"7dcca569747d42e3883df3787cc4fb24","xgtype":"1","bh":"BHA001","qyname":"1号点","qynfc":"BHA001NFC","qywz":"行政楼1楼"},{"jhid":"20161013090900","xgid":"703b5509fca54beb9be2343d80b91918","xgtype":"1","bh":"A002","qyname":"2号点","qynfc":"A002DF","qywz":"TT4东侧治安监控点"}]
     * Total : 3
     */

    private int id;

    private String jhid;

    private String Total;
    /**
     * jhid : 20161013090900
     * xgid : f90f5fd3dedc4b4aa7a60c5b00295157
     * xgtype : 2
     * bh : BH1
     * qyname : 1号点
     * qynfc : 3344NFC
     * qywz : 1号门岗
     */

    private List<XfBaxcRwqy> Rows;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<XfBaxcRwqy> getRows() {
        return Rows;
    }

    public void setRows(List<XfBaxcRwqy> Rows) {
        this.Rows = Rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.jhid);
        dest.writeString(this.Total);
        dest.writeTypedList(this.Rows);
    }

    public XfBaxcRwqyList() {
    }

    protected XfBaxcRwqyList(Parcel in) {
        this.id = in.readInt();
        this.jhid = in.readString();
        this.Total = in.readString();
        this.Rows = in.createTypedArrayList(XfBaxcRwqy.CREATOR);
    }

    public static final Creator<XfBaxcRwqyList> CREATOR = new Creator<XfBaxcRwqyList>() {
        @Override
        public XfBaxcRwqyList createFromParcel(Parcel source) {
            return new XfBaxcRwqyList(source);
        }

        @Override
        public XfBaxcRwqyList[] newArray(int size) {
            return new XfBaxcRwqyList[size];
        }
    };
}
