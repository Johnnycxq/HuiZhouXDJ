package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/11.
 */

public class Qy extends DataSupport {

    private String AREACODE;
    private String AREANAME;
    private String BQBM;
    private String TXM;

    public String getAREACODE() {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE) {
        this.AREACODE = AREACODE;
    }

    public String getAREANAME() {
        return AREANAME;
    }

    public void setAREANAME(String AREANAME) {
        this.AREANAME = AREANAME;
    }

    public String getBQBM() {
        return BQBM;
    }

    public void setBQBM(String BQBM) {
        this.BQBM = BQBM;
    }

    public String getTXM() {
        return TXM;
    }

    public void setTXM(String TXM) {
        this.TXM = TXM;
    }
}
