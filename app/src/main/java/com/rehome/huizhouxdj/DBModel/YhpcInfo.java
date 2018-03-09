package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/1.
 */

public class YhpcInfo extends DataSupport {

    private String NR;//内容
    private String WT;//问题
    private String AREACODE;//区域编号
    private String FXR;//发现人
    private String FXSJ;//发现时间
    private String GUID;

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public String getWT() {
        return WT;
    }

    public void setWT(String WT) {
        this.WT = WT;
    }

    public String getAREACODE() {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE) {
        this.AREACODE = AREACODE;
    }

    public String getFXR() {
        return FXR;
    }

    public void setFXR(String FXR) {
        this.FXR = FXR;
    }

    public String getFXSJ() {
        return FXSJ;
    }

    public void setFXSJ(String FXSJ) {
        this.FXSJ = FXSJ;
    }
}
