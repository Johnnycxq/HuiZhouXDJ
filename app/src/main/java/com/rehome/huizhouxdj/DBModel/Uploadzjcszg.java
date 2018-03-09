package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2018/1/25.
 */

public class Uploadzjcszg extends DataSupport {

    private String RWID;//任务ID（必填）
    private String ZXID;//执行ID（必填）
    private String ZGJG;//整改结果（必填）
    private String LRSJ;//录入时间
    private String photopatglist;//图片集合
    private boolean checked;//0:未选中,1:已选中
    private boolean uploaded;

    public String getRWID() {
        return RWID;
    }

    public void setRWID(String RWID) {
        this.RWID = RWID;
    }

    public String getZXID() {
        return ZXID;
    }

    public void setZXID(String ZXID) {
        this.ZXID = ZXID;
    }

    public String getZGJG() {
        return ZGJG;
    }

    public void setZGJG(String ZGJG) {
        this.ZGJG = ZGJG;
    }

    public String getLRSJ() {
        return LRSJ;
    }

    public void setLRSJ(String LRSJ) {
        this.LRSJ = LRSJ;
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
}
