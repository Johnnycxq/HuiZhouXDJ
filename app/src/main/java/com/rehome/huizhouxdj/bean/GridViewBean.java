package com.rehome.huizhouxdj.bean;

/**
 * Created by Rehome-rjb1 on 2017/3/29.
 */

public class GridViewBean {

    private String title;
    private int imageid;
    private int backgroup;
    private boolean isShow;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getBackgroup() {
        return backgroup;
    }

    public void setBackgroup(int backgroup) {
        this.backgroup = backgroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
