package com.rehome.huizhouxdj.DBModel;

import java.util.List;

/**
 * Created by Administrator on 2016/10/29.
 */

public class ZyInfo {


    /**
     * Rows : [{"ID":"2086","VALUE":"MIS","XXMS":"信息中心"},{"ID":"2083","VALUE":"电气","XXMS":"电气分部"},{"ID":"2079","VALUE":"辅助维护","XXMS":"设备部管理室"},{"ID":"2070","VALUE":"工程机械","XXMS":"燃料推扒班"},{"ID":"2080","VALUE":"环化","XXMS":"环化分部"},{"ID":"2084","VALUE":"机械","XXMS":"机械分部"},{"ID":"2076","VALUE":"码头","XXMS":"码头分部"},{"ID":"2075","VALUE":"其他","XXMS":"其他"},{"ID":"2085","VALUE":"燃料","XXMS":"燃料点检班"},{"ID":"2082","VALUE":"热控","XXMS":"热控分部"},{"ID":"2088","VALUE":"生技","XXMS":"生技分部"},{"ID":"2081","VALUE":"输煤","XXMS":"输煤分部"},{"ID":"2074","VALUE":"土建","XXMS":"土建专业"},{"ID":"2087","VALUE":"运行","XXMS":"运行部"},{"ID":"2071","VALUE":"运行A值","XXMS":"运行A值"},{"ID":"2072","VALUE":"运行B值","XXMS":"运行B值"},{"ID":"2073","VALUE":"运行C值","XXMS":"运行C值"},{"ID":"2078","VALUE":"运行D值","XXMS":"运行D值"},{"ID":"2077","VALUE":"运行E值","XXMS":"运行E值"}]
     * Total : 19
     */

    private int Total;
    /**
     * ID : 2086
     * VALUE : MIS
     * XXMS : 信息中心
     */

    private List<Zy> Rows;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<Zy> getRows() {
        return Rows;
    }

    public void setRows(List<Zy> Rows) {
        this.Rows = Rows;
    }
}
