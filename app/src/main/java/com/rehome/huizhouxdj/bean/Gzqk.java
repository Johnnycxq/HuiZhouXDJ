package com.rehome.huizhouxdj.bean;


import java.io.Serializable;

/**
 * Created by Rehome-rjb1 on 2017/4/11.
 */

public class Gzqk implements Serializable {

    private String id;
    private String ggtime_s; //工作开始时间
    private String ggplace; //工作地点
    private String ggcontent; //工作内容描述
    private String lrr; //录入人
    private String lrrname;//录入人名字
    private String addtime; //录入时间
    private String bm; //部门
    private String ggtime_e;//工作结束时间
    private String rwstate;//任务状态
    private String is_del;
    private String tbtime;
    private String cbs;//承包商

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGgtime_s() {
        return ggtime_s;
    }

    public void setGgtime_s(String ggtime_s) {
        this.ggtime_s = ggtime_s;
    }

    public String getGgplace() {
        return ggplace;
    }

    public void setGgplace(String ggplace) {
        this.ggplace = ggplace;
    }

    public String getGgcontent() {
        return ggcontent;
    }

    public void setGgcontent(String ggcontent) {
        this.ggcontent = ggcontent;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getGgtime_e() {
        return ggtime_e;
    }

    public void setGgtime_e(String ggtime_e) {
        this.ggtime_e = ggtime_e;
    }

    public String getRwstate() {
        return rwstate;
    }

    public void setRwstate(String rwstate) {
        this.rwstate = rwstate;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getTbtime() {
        return tbtime;
    }

    public void setTbtime(String tbtime) {
        this.tbtime = tbtime;
    }

    public String getLrrname() {
        return lrrname;
    }

    public void setLrrname(String llrname) {
        this.lrrname = llrname;
    }

    public String getCbs() {
        return cbs;
    }

    public void setCbs(String cbs) {
        this.cbs = cbs;
    }

}
