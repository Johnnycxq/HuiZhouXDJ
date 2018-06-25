package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * 点检计划
 */

public class Djjh extends DataSupport {

    private int id;
    private String GWID;
    private String GWMC;
    private String GWDS;
    private String GWLX;
    private boolean checked;//0:未选中,1:已选中
    private int download;//0:未下载,1:已经下载
    private DjjhList djjhList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }

    public String getGWMC() {
        return GWMC;
    }

    public void setGWMC(String GWMC) {
        this.GWMC = GWMC;
    }

    public String getGWDS() {
        return GWDS;
    }

    public void setGWDS(String GWDS) {
        this.GWDS = GWDS;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public DjjhList getDjjhList() {
        return djjhList;
    }

    public void setDjjhList(DjjhList djjhList) {
        this.djjhList = djjhList;
    }

    public String getGWLX() {
        return GWLX;
    }

    public void setGWLX(String GWLX) {
        this.GWLX = GWLX;
    }
}
