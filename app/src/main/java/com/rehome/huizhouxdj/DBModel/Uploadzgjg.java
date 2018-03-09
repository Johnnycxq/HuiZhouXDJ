package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2017/12/27.
 */

public class Uploadzgjg extends DataSupport {

    private String JHID;//计划ID（必填）
    private String RWID;//任务ID（必填）
    private String ZGJG;//整改结果（必填）
    private String photopatglist;//图片集合
    private boolean checked;//0:未选中,1:已选中
    private boolean uploaded;
    private String LRSJ;//录入时间


    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getRWID() {
        return RWID;
    }

    public void setRWID(String RWID) {
        this.RWID = RWID;
    }

    public String getZGJG() {
        return ZGJG;
    }

    public void setZGJG(String ZGJG) {
        this.ZGJG = ZGJG;
    }

    public String getPhotopatglist() {
        return photopatglist;
    }

    public void setPhotopatglist(String photopatglist) {
        this.photopatglist = photopatglist;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getLRSJ() {
        return LRSJ;
    }

    public void setLRSJ(String LRSJ) {
        this.LRSJ = LRSJ;
    }
}
