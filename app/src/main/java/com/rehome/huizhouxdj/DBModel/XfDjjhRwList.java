package com.rehome.huizhouxdj.DBModel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class XfDjjhRwList extends DataSupport implements Parcelable {


    /**
     * Rows : [{"区域分组":[{"BH":"1","XFTYPE":"2","XFWZ":"TT1一层","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"8544fa6528c04c2c889fd03ad3776689","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"2","XFTYPE":"2","XFWZ":"TT1二层","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"1ee542a998c248db8cfa76e90dbfc8db","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"3","XFTYPE":"2","XFWZ":"TT1三层","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"fa3e06f4a7fe4678918b25fd0cd89885","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"38","XFTYPE":"3","XFWZ":"TT1一层北面","XFNAME":"防火门安全出口灯","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"TT1","XMID":"1b179cac1cff41b4a4e9aa26f934660c","XFID":"07bc3be9b11c40c88130216690d3b97a","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"1","SL2":"1","BZXQ":"单"},{"BH":"38","XFTYPE":"3","XFWZ":"TT1三层北面","XFNAME":"防火门安全出口灯","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"TT1","XMID":"1b179cac1cff41b4a4e9aa26f934660c","XFID":"eb0e804742c845d8837b7219a040aa43","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"2","SL2":"","BZXQ":"单"},{"BH":"38","XFTYPE":"3","XFWZ":"TT1三层南面楼梯口","XFNAME":"防火门安全出口灯","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"TT1","XMID":"1b179cac1cff41b4a4e9aa26f934660c","XFID":"a6c604bbb8b94e5bb081662e4de86fd9","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"1","BZXQ":""},{"BH":"38","XFTYPE":"3","XFWZ":"TT1二层北面","XFNAME":"防火门安全出口灯","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"TT1","XMID":"1b179cac1cff41b4a4e9aa26f934660c","XFID":"b8132310000446219acb96d0848bea69","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"1","SL2":"1","BZXQ":"单"},{"BH":"4","XFTYPE":"2","XFWZ":"输煤系统C1A皮带","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"416f01f0841b46bfbfdad57be647d955","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"5","XFTYPE":"2","XFWZ":"输煤系统C1A皮带","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"0c033535710645c4ba6c22315705e4a5","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"6","XFTYPE":"2","XFWZ":"输煤系统C1B皮带","XFNAME":"消防栓","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"配水枪1支、水带1盘","XMID":"a0349df3eca44c7d8eafe39469bdddae","XFID":"31020a569ca0447f82807137ffd9ea3c","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG058","XFTYPE":"1","XFWZ":"四号卸煤机驾驶室","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"2KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"03644aa0219e4afaaedc7bccd1da12e5","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG061","XFTYPE":"1","XFWZ":"一号卸煤机驾驶室","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"2KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"63527182f85f4c6b929b5591410ed8b0","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG063","XFTYPE":"1","XFWZ":"三号卸煤机变压器房门口","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4瓶 4KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"a23465a7737245a0963b12163eebf393","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG064","XFTYPE":"1","XFWZ":"三号卸煤机驾驶室","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"2KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"3bc50b00acd14332b80c4377ce3df146","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG106","XFTYPE":"1","XFWZ":"三号卸煤机变压器房门口","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4瓶 4KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"4c4c70ed2c114048b22112c294062e9a","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG112","XFTYPE":"1","XFWZ":"二号卸煤机变压器房门口","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4瓶 4KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"7bda9f5abb7f483e82773d351771e53f","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG120","XFTYPE":"1","XFWZ":"四号卸煤机变压器房门口","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4瓶 4KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"9f4f685060504b989838a9bbe1e1bc49","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG125","XFTYPE":"1","XFWZ":"一号卸煤机变压器房门口","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4瓶 4KG干粉","XMID":"e202262940b147a3997abd76473a7646","XFID":"8651eb5cdbc045e4a6dd9db0828fb5e6","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""},{"BH":"AG173","XFTYPE":"1","XFWZ":"TT2一层","XFNAME":"灭火器材","QYNAME":"TT1转运塔、卸船机、C1A(B)输煤皮带","XHNUM":"4KG干粉3KGCO2","XMID":"e202262940b147a3997abd76473a7646","XFID":"933011da673e49c5b63ae7498d5a36e3","QYID":"d660b42aa743451194a04c5f69862622","JHID":"20161014163244","NEXTTIME":"2016/10/18 0:00:00","SL1":"","SL2":"","BZXQ":""}]},{"区域分组":[]}]
     * Total : 19
     */

    private int id;
    private int Total;
    private String jhid;
    private List<XfDjjhRwqyList> Rows;

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<XfDjjhRwqyList> getRows() {
        return Rows;
    }

    public void setRows(List<XfDjjhRwqyList> Rows) {
        this.Rows = Rows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.Total);
        dest.writeString(this.jhid);
        dest.writeTypedList(this.Rows);
    }

    public XfDjjhRwList() {
    }

    protected XfDjjhRwList(Parcel in) {
        this.id = in.readInt();
        this.Total = in.readInt();
        this.jhid = in.readString();
        this.Rows = in.createTypedArrayList(XfDjjhRwqyList.CREATOR);
    }

    public static final Creator<XfDjjhRwList> CREATOR = new Creator<XfDjjhRwList>() {
        @Override
        public XfDjjhRwList createFromParcel(Parcel source) {
            return new XfDjjhRwList(source);
        }

        @Override
        public XfDjjhRwList[] newArray(int size) {
            return new XfDjjhRwList[size];
        }
    };
}
