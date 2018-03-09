package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by gzw on 2016/11/7.
 */

public class Ajhjh extends DataSupport {

    private boolean checked;
    private boolean download;
    private String JHID;
    private String JHMC;
    private String DQSJ;
    private String DJJHID;

    public String getDJJHID() {
        return DJJHID;
    }

    public void setDJJHID(String DJJHID) {
        this.DJJHID = DJJHID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
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

    public String getDQSJ() {
        return DQSJ;
    }

    public void setDQSJ(String DQSJ) {
        this.DQSJ = DQSJ;
    }
}
