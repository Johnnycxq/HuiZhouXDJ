package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

/**
 * Created by ruihong on 2018/1/24.
 */

public class Uploadzdcsrw extends DataSupport {


    private String ZXID;//执行ID（必填）
    private String QYBH;//区域编号
    private String WTMS;//问题描述
    private String LUSJ;//录入时间
    private String FXLB;//风险类别
    private String ZRBM;//责任部门
    private String YHDJ;//隐患等级

    private String photopatglist;//图片集合
    private boolean checked;//0:未选中,1:已选中
    private boolean uploaded;


    public String getZXID() {
        return ZXID;
    }

    public void setZXID(String ZXID) {
        this.ZXID = ZXID;
    }

    public String getQYBH() {
        return QYBH;
    }

    public void setQYBH(String QYBH) {
        this.QYBH = QYBH;
    }

    public String getWTMS() {
        return WTMS;
    }

    public void setWTMS(String WTMS) {
        this.WTMS = WTMS;
    }

    public String getLUSJ() {
        return LUSJ;
    }

    public void setLUSJ(String LUSJ) {
        this.LUSJ = LUSJ;
    }

    public String getFXLB() {
        return FXLB;
    }

    public void setFXLB(String FXLB) {
        this.FXLB = FXLB;
    }

    public String getZRBM() {
        return ZRBM;
    }

    public void setZRBM(String ZRBM) {
        this.ZRBM = ZRBM;
    }

    public String getYHDJ() {
        return YHDJ;
    }

    public void setYHDJ(String YHDJ) {
        this.YHDJ = YHDJ;
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
