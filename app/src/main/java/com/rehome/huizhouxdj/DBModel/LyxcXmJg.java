package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/12.
 */

public class LyxcXmJg extends DataSupport {

    /**
     * xmid : 78cb0bb2df5548ea9ab0fda3d4220e4a
     * ssid : 4a68a4e25eea4b23bd17c11131485e0b
     * jhid : 20161103192018
     * typename : 是否完好
     * type1 : 是
     * type2 : 否
     */

    private String xmid;
    private String ssid;
    private String jhid;
    private String typename;
    private String type1;
    private String type2;
    private String qyid;
    private boolean jg;

    public String getQyid() {
        return qyid;
    }

    public void setQyid(String qyid) {
        this.qyid = qyid;
    }

    public boolean isJg() {
        return jg;
    }

    public void setJg(boolean jg) {
        this.jg = jg;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
}
