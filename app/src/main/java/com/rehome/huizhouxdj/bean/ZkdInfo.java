package com.rehome.huizhouxdj.bean;

/**
 * 质控点bean
 */
public class ZkdInfo {

    private int xh;
    private String gx;
    private String zt;
    private String fl;
    private String aj;
    private String bj;
    private String cj;
    private String jl;

    /**
     * @param xh 序号
     * @param gx 工序
     * @param zt 状态
     * @param fl 质控点分类
     * @param aj A级质检员
     * @param bj B级质检员
     * @param cj C级质检员
     * @param jl 监理工程师
     */
    public ZkdInfo(int xh, String gx, String zt, String fl, String aj, String bj, String cj, String jl) {
        this.xh = xh;
        this.gx = gx;
        this.zt = zt;
        this.fl = fl;
        this.aj = aj;
        this.bj = bj;
        this.cj = cj;
        this.jl = jl;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getAj() {
        return aj;
    }

    public void setAj(String aj) {
        this.aj = aj;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }
}
