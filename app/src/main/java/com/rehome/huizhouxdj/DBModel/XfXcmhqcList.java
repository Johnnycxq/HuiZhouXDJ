package com.rehome.huizhouxdj.DBModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 消防巡查灭火器材列表
 */

public class XfXcmhqcList extends DataSupport {

    private int id;

    /**
     * Rows : [{"bh":"AG120","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"9f4f685060504b989838a9bbe1e1bc49","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG120","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"9f4f685060504b989838a9bbe1e1bc49","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG120","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"9f4f685060504b989838a9bbe1e1bc49","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG058","xftype":"1","xhnum":"2KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"03644aa0219e4afaaedc7bccd1da12e5","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG058","xftype":"1","xhnum":"2KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"03644aa0219e4afaaedc7bccd1da12e5","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG058","xftype":"1","xhnum":"2KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"03644aa0219e4afaaedc7bccd1da12e5","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG173","xftype":"1","xhnum":"4KG干粉3KGCO2","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"933011da673e49c5b63ae7498d5a36e3","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG173","xftype":"1","xhnum":"4KG干粉3KGCO2","xmid":"e202262940b147a3997abd76473a7646","xfid":"933011da673e49c5b63ae7498d5a36e3","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG173","xftype":"1","xhnum":"4KG干粉3KGCO2","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"933011da673e49c5b63ae7498d5a36e3","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG061","xftype":"1","xhnum":"2KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"63527182f85f4c6b929b5591410ed8b0","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG061","xftype":"1","xhnum":"2KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"63527182f85f4c6b929b5591410ed8b0","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG061","xftype":"1","xhnum":"2KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"63527182f85f4c6b929b5591410ed8b0","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG112","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"7bda9f5abb7f483e82773d351771e53f","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG112","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"7bda9f5abb7f483e82773d351771e53f","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG112","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"7bda9f5abb7f483e82773d351771e53f","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG063","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"a23465a7737245a0963b12163eebf393","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG063","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"a23465a7737245a0963b12163eebf393","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG063","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"a23465a7737245a0963b12163eebf393","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG106","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"4c4c70ed2c114048b22112c294062e9a","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG106","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"4c4c70ed2c114048b22112c294062e9a","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG106","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"4c4c70ed2c114048b22112c294062e9a","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG064","xftype":"1","xhnum":"2KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"3bc50b00acd14332b80c4377ce3df146","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG064","xftype":"1","xhnum":"2KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"3bc50b00acd14332b80c4377ce3df146","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG064","xftype":"1","xhnum":"2KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"3bc50b00acd14332b80c4377ce3df146","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG125","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"1b179cac1cff41b4a4e9aa26f934660c","xfid":"8651eb5cdbc045e4a6dd9db0828fb5e6","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG125","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"e202262940b147a3997abd76473a7646","xfid":"8651eb5cdbc045e4a6dd9db0828fb5e6","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"},{"bh":"AG125","xftype":"1","xhnum":"4瓶 4KG干粉","xmid":"a0349df3eca44c7d8eafe39469bdddae","xfid":"8651eb5cdbc045e4a6dd9db0828fb5e6","qyid":"d660b42aa743451194a04c5f69862622","jhid":"20161014163244","nexttime":"2016/10/18 0:00:00"}]
     * Total : 27
     */

    private String Total;
    /**
     * bh : AG120
     * xftype : 1
     * xhnum : 4瓶 4KG干粉
     * xmid : 1b179cac1cff41b4a4e9aa26f934660c
     * xfid : 9f4f685060504b989838a9bbe1e1bc49
     * qyid : d660b42aa743451194a04c5f69862622
     * jhid : 20161014163244
     * nexttime : 2016/10/18 0:00:00
     */

    private List<XfXcmhqc> Rows;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<XfXcmhqc> getRows() {
        return Rows;
    }

    public void setRows(List<XfXcmhqc> Rows) {
        this.Rows = Rows;
    }

}
