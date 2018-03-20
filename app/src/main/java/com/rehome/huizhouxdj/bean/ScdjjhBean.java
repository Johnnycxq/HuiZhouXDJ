package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by ruihong on 2018/3/19.
 */

public class ScdjjhBean {//用于上传点检计划的bean

    private String Action;
    private String GWID;
    private String YHID;
    private List<DJ_DATA> DJ_DATA;

    public List<ScdjjhBean.DJ_DATA> getDJ_DATA() {
        return DJ_DATA;
    }

    public void setDJ_DATA(List<ScdjjhBean.DJ_DATA> DJ_DATA) {
        this.DJ_DATA = DJ_DATA;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getGWID() {
        return GWID;
    }

    public void setGWID(String GWID) {
        this.GWID = GWID;
    }

    public String getYHID() {
        return YHID;
    }

    public void setYHID(String YHID) {
        this.YHID = YHID;
    }


    public static class DJ_DATA {
        private String QYBH;
        private String QYDJ_ST;
        private List<QYDJ_DATA> QYDJ_DATA;

        public List<ScdjjhBean.DJ_DATA.QYDJ_DATA> getQYDJ_DATA() {
            return QYDJ_DATA;
        }

        public void setQYDJ_DATA(List<ScdjjhBean.DJ_DATA.QYDJ_DATA> QYDJ_DATA) {
            this.QYDJ_DATA = QYDJ_DATA;
        }

        public String getQYBH() {
            return QYBH;
        }

        public void setQYBH(String QYBH) {
            this.QYBH = QYBH;
        }

        public String getQYDJ_ST() {
            return QYDJ_ST;
        }

        public void setQYDJ_ST(String QYDJ_ST) {
            this.QYDJ_ST = QYDJ_ST;
        }


        public static class QYDJ_DATA {
            private String SCID;
            private String DJSZ;
            private String DJSJ;
            private String FXNR;
            private String SMFS;

            public String getSCID() {
                return SCID;
            }

            public void setSCID(String SCID) {
                this.SCID = SCID;
            }

            public String getDJSZ() {
                return DJSZ;
            }

            public void setDJSZ(String DJSZ) {
                this.DJSZ = DJSZ;
            }

            public String getDJSJ() {
                return DJSJ;
            }

            public void setDJSJ(String DJSJ) {
                this.DJSJ = DJSJ;
            }

            public String getFXNR() {
                return FXNR;
            }

            public void setFXNR(String FXNR) {
                this.FXNR = FXNR;
            }

            public String getSMFS() {
                return SMFS;
            }

            public void setSMFS(String SMFS) {
                this.SMFS = SMFS;
            }

        }

    }


}
