package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/11.
 */

public class XwaqgcJh extends DataSupport {

    /**
     * JHID : GCJH00000000001
     * GCRY : 210041
     * AREANAME : 油码头区域
     * AREACODE : MT03
     * WCZT :
     * ST : 2016-11-08 19:41:49
     * DQSJ : 2016-11-15 19:11:49
     */

    private String JHID;
    private String GCRY;
    private String AREANAME;
    private String AREACODE;
    private String WCZT;
    private String ST;
    private String DQSJ;
    private String JHMC;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getJHMC() {
        return JHMC;
    }

    public void setJHMC(String JHMC) {
        this.JHMC = JHMC;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getGCRY() {
        return GCRY;
    }

    public void setGCRY(String GCRY) {
        this.GCRY = GCRY;
    }

    public String getAREANAME() {
        return AREANAME;
    }

    public void setAREANAME(String AREANAME) {
        this.AREANAME = AREANAME;
    }

    public String getAREACODE() {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE) {
        this.AREACODE = AREACODE;
    }

    public String getWCZT() {
        return WCZT;
    }

    public void setWCZT(String WCZT) {
        this.WCZT = WCZT;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getDQSJ() {
        return DQSJ;
    }

    public void setDQSJ(String DQSJ) {
        this.DQSJ = DQSJ;
    }

}
