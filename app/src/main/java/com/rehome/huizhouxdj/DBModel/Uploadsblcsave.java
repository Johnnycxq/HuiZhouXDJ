package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2017/12/23.
 */

public class Uploadsblcsave extends DataSupport {

    private String JHID;//计划ID（必填）
    private String LCSB;//联查设备（必填）
    private String WTMS;//问题描述（必填）
    private String LRSJ;//录入时间
    private String ZGYJ;//整改意见（选填）
    private String ZGBM;//整改部门（选填）
    private String ZGZRR;//整改责任人（选填）

    private String photopatglist;//图片集合
    private boolean checked;//0:未选中,1:已选中
    private boolean uploaded;


    public String getJHID() {
        return JHID;
    }

    public void setJHID(String JHID) {
        this.JHID = JHID;
    }

    public String getLCSB() {
        return LCSB;
    }

    public void setLCSB(String LCSB) {
        this.LCSB = LCSB;
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

    public String getZGYJ() {
        return ZGYJ;
    }

    public void setZGYJ(String ZGYJ) {
        this.ZGYJ = ZGYJ;
    }

    public String getZGBM() {
        return ZGBM;
    }

    public void setZGBM(String ZGBM) {
        this.ZGBM = ZGBM;
    }

    public String getZGZRR() {
        return ZGZRR;
    }

    public void setZGZRR(String ZGZRR) {
        this.ZGZRR = ZGZRR;
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
