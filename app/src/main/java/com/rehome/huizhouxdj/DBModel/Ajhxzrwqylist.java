package com.rehome.huizhouxdj.DBModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gzw on 2016/11/7.
 */

public class Ajhxzrwqylist {

    public AjhjhxzrwList getListl() {
        return listl;
    }

    public void setListl(AjhjhxzrwList listl) {
        this.listl = listl;
    }

    private AjhjhxzrwList listl;

    @SerializedName("区域分组")
    private List<Ajhxzrwqy> Ajhxzrwqys;

    public List<Ajhxzrwqy> getAjhxzrwqys() {
        return Ajhxzrwqys;
    }

    public void setAjhxzrwqys(List<Ajhxzrwqy> Ajhxzrwqys) {
        this.Ajhxzrwqys = Ajhxzrwqys;
    }
}
