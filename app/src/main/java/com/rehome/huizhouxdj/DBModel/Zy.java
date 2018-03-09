package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/10/29.
 */

public class Zy extends DataSupport{

    private int id;
    private String ZYID;
    private String VALUE;
    private String XXMS;

    public String getZYID() {
        return ZYID;
    }

    public void setZYID(String ZYID) {
        this.ZYID = ZYID;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getXXMS() {
        return XXMS;
    }

    public void setXXMS(String XXMS) {
        this.XXMS = XXMS;
    }
}
