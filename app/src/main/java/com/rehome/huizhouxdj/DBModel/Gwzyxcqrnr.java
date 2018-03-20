package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2018/2/3.
 */

public class Gwzyxcqrnr extends DataSupport {

    private int mPositionID;

    private String GDID;

    private String QRID;

    private String QRNR;

    private String QRSJ;

    public String getGDID() {
        return GDID;
    }

    public void setGDID(String GDID) {
        this.GDID = GDID;
    }

    public int getmPositionID() {
        return mPositionID;
    }

    public void setmPositionID(int mPositionID) {
        this.mPositionID = mPositionID;
    }

    public String getQRID() {
        return QRID;
    }

    public void setQRID(String QRID) {
        this.QRID = QRID;
    }

    public String getQRNR() {
        return QRNR;
    }

    public void setQRNR(String QRNR) {
        this.QRNR = QRNR;
    }

    public String getQRSJ() {
        return QRSJ;
    }

    public void setQRSJ(String QRSJ) {
        this.QRSJ = QRSJ;
    }



}
