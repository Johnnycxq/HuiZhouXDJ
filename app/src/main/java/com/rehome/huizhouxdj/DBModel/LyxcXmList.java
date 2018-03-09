package com.rehome.huizhouxdj.DBModel;

import java.util.List;

/**
 * Created by gzw on 2016/11/12.
 */

public class LyxcXmList {


    /**
     * Rows : [{"xmid":"78cb0bb2df5548ea9ab0fda3d4220e4a","ssid":"4a68a4e25eea4b23bd17c11131485e0b","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"},{"xmid":"ebd90304b3f14156adbf71fc00123e85","ssid":"28dbda78ceb94c809f014635227951b5","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"},{"xmid":"2d76e717394c456e924029b0e5ee290b","ssid":"9cc7e7c905ee4ab69e2b3da729494b84","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"},{"xmid":"af90e99c237d498d8ca34b4ecb2921e5","ssid":"b9046a62f25e422db0583b8b38369fad","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"},{"xmid":"0381dab762aa4751b165d2f385a4b907","ssid":"1d619e47b2a14e508bdfb5ce66f8a019","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"},{"xmid":"3debf515f3704af7925d2b49ef572a9c","ssid":"f0d823228a194796bfafdd685da15336","jhid":"20161103192018","typename":"是否有漏水","type1":"是","type2":"否"},{"xmid":"fc5ff06fb8c14e9ab4c31dca6e66e5f8","ssid":"d47cc191a913403aae4c3ba41e313b9f","jhid":"20161103192018","typename":"是否有掉灰","type1":"是","type2":"否"},{"xmid":"6918731a58474be186a77ba3e97168c9","ssid":"01a13ca9a4c14a3a971c61bcf75b6a28","jhid":"20161103192018","typename":"是否正常","type1":"是","type2":"否"},{"xmid":"2a0879f795be4dd69bb776b0c0da7dcd","ssid":"65fd15e7a85842dd8d2f535171fd36b3","jhid":"20161103192018","typename":"是否正常","type1":"是","type2":"否"},{"xmid":"e9692c642e2a457f9b954314c373e1b6","ssid":"d533905e89ed4ab0af7db27f39b8e2cc","jhid":"20161103192018","typename":"是否完好","type1":"是","type2":"否"}]
     * Total : 10
     */

    private String Total;
    private List<LyxcXm> Rows;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public List<LyxcXm> getRows() {
        return Rows;
    }

    public void setRows(List<LyxcXm> Rows) {
        this.Rows = Rows;
    }

}
