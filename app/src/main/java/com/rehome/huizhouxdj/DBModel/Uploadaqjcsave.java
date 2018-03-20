package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2018/1/8.
 */

public class Uploadaqjcsave extends DataSupport {

    private String JHID;//计划ID（必填）
    private String WTQY;//问题区域（必填）
    private String WTMS;//问题描述（必填）
    private String LRSJ;//录入时间
    private String FXLB;//风险类别（选填）
    private String YHDJ;//隐患等级（选填）
    private String ZRBM;//责任部门（选填）

    private String photopatglist;//图片集合
    private boolean checked;//0:未选中,1:已选中
    private boolean uploaded;

    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getWTQY() {
        return WTQY;
    }

    public void setWTQY(String WTQY) {
        this.WTQY = WTQY;
    }

    public String getWTMS() {
        return WTMS;
    }

    public void setWTMS(String WTMS) {
        this.WTMS = WTMS;
    }

    public String getLRSJ() {
        return LRSJ;
    }

    public void setLRSJ(String LRSJ) {
        this.LRSJ = LRSJ;
    }

    public String getFXLB() {
        return FXLB;
    }

    public void setFXLB(String FXLB) {
        this.FXLB = FXLB;
    }

    public String getYHDJ() {
        return YHDJ;
    }

    public void setYHDJ(String YHDJ) {
        this.YHDJ = YHDJ;
    }

    public String getZRBM() {
        return ZRBM;
    }

    public void setZRBM(String ZRBM) {
        this.ZRBM = ZRBM;
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
