package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * 点检计划
 */

public class Djjh extends DataSupport {

    private int id;
    private String JHID;
    private String JHMC;
    private String JHLX;
    private String DQSJ;
    private String XZR;
    private String XZSJ;
    private boolean checked;//0:未选中,1:已选中
    private int download;//0:未下载,1:已经下载
    private DjjhList djjhList;

    public String getXZR() {
        return XZR;
    }

    public void setXZR(String XZR) {
        this.XZR = XZR;
    }

    public String getXZSJ() {
        return XZSJ;
    }

    public void setXZSJ(String XZSJ) {
        this.XZSJ = XZSJ;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public DjjhList getDjjhList() {
        return djjhList;
    }

    public void setDjjhList(DjjhList djjhList) {
        this.djjhList = djjhList;
    }

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getJHMC() {
        return JHMC;
    }

    public void setJHMC(String JHMC) {
        this.JHMC = JHMC;
    }

    public String getJHLX() {
        return JHLX;
    }

    public void setJHLX(String JHLX) {
        this.JHLX = JHLX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDQSJ() {
        return DQSJ;
    }

    public void setDQSJ(String DQSJ) {
        this.DQSJ = DQSJ;
    }

    public DjjhList getList() {
        return djjhList;
    }

    public void setList(DjjhList list) {
        this.djjhList = list;
    }
}
