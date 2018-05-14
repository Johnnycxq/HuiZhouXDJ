package com.rehome.huizhouxdj.contans;

/**
 * Created by gzw on 2016/8/12.
 */

public class Contans {


    public static String YXCB_ZY_ID = "";//运行抄表专业ID

    public static String YXCB_ZY_NAME = "";//运行抄表专业名字

    public static String IP = "http://192.168.2.189:8092/";


    public static String CK_BASE_IP = "http://10.19.0.13:80/";

    public static String GET_CK_IP = "WLJS/NETWORK/GetData.aspx";

    //接收与转移
    public static String MATRSER_IP = "eamwebservice/services/MatrSer";

    //发送与退库
    public static String MATUSER_IP = "eamwebservice/services/MatuSer";

    public static String LOGIN = "AppLogin/GetData.aspx";

    public static String PRINT = "AppTXM/GetData.aspx";

    public static final String HEAD_PHOTO = "head_photo";

//    public static String UPLOADAPP = "ApkUpdate/GetData.aspx";

    //-----------------------移动仓库管理--------------------------//

    /**
     * 物料发放
     */
    public static String WLFF = "WLJS/WLFF/GetData.aspx";

    public static String WLFFJL = "WLJS/WLFF/GetDetailDataByItem.aspx";

    /**
     * 库存查询
     */
    public static String KCCX = "WLJS/KCCX/GetData.aspx";

    /**
     * 物料发放上传
     */
    public static String WLFFSC = "WLJS/WLFF/SetData.aspx";

    /**
     * 物料标签打印
     */
    public static String WLBQDY = "WLJS/WLBQDY/GetData.aspx";
    /**
     * 验收批准接收
     */
    public static String YSPZJS = "WLJS/YSPZJS/GetData.aspx";

    public static String YSPZJSJL = "WLJS/YSPZJS/GetDetailDataByItem.aspx";

    /**
     * 验收批准接收
     */
    public static String YSPZJSSC = "WLJS/YSPZJS/SetData.aspx";

    /**
     * 验收批准接收
     */
    public static String YSPZJSFILE = "WLJS/YSPZJS/SetFiles.aspx?";

    /**
     * 接收退回
     */
    public static String JSTH = "WLJS/WLTK/GetData.aspx";

    /**
     * 领料人
     */
    public static String LLR = "UserInfos/GetData.aspx";

    /**
     * 退料入库
     */
    public static String TLRK = "WLJS/TLRK/GetData.aspx";

    /**
     * 退料入库
     */
    public static String TLRKSC = "WLJS/TLRK/SetData.aspx";

    /**
     * 货架移位
     */
    public static String HJYW = "WLJS/HJYW/GetData.aspx";

    /**
     * 货架移位上传
     */
    public static String HJYWSC = "WLJS/HJYW/SetData.aspx";

    /**
     * 盘点计划列表
     */
    public static String PDJHLIST = "WLJS/KCPD/GetData.aspx";

    /**
     * 盘点计划
     */
    public static String PDJH = "WLJS/KCPD/GetData.aspx";

    public static String PDCX = "WLJS/KCPDHZ/GetData.aspx";//userid  pdid

    /**
     * 盘点计划上传
     */
    public static String PDJHSC = "WLJS/KCPD/SetData.aspx";

    /**
     * 条码打印
     */
    public static String TMDY = "WLJS/TMDY/GetData.aspx";

    public static String TMDYSC = "WLJS/TMDY/SetFiles.aspx?";


    /**
     * 物资图片上传
     */

    public static String WZTP = "WLJS/WLUP/GetData.aspx";
    public static String WZTPSC = "WLJS/WLUP/SetFiles.aspx";


    //-----------------------消防巡查--------------------------//
    public static String XFDJJHALL = "JHCommFile/JHSJ/GetData.aspx";//所有计划
    //public static String XFDJJHLIST = "XFXC/JHSJ/GetData.aspx";//计划列表
    public static String XFDJJHSC = "XFXC/JCJG/SetData.aspx";//上传计划数据
    public static String XFDJJHXZ = "XFXC/QYSJ/GetData.aspx";//下载计划 jhid=20161014163244
    public static String XFXCMHQC = "XFXC/QYDSJ/GetData.aspx";//灭火器材 jhid=20161014163244&xftype=1
    public static String XFXCXM = "XFXC/GXMSJ/GetData.aspx";//消防巡查项目
    public static String XFXCJSSC = "XFXC/JCJG/SetFiles.aspx";//上传现场记事图片

    //----------------------保安巡查-------------------------//
    //public static String BAXCJH = "BAXG/JHSJ/GetData.aspx";//计划列表
    public static String BAXCJHXZ = "BAXG/QYDSJ/GetData.aspx";  //计划下载jhid=2016101390900
    public static String BAXCJHSC = "BAXG/JCJG/SetData.aspx";//上传计划数据


    //----------------------楼宇计划下载------------------//
    public static String LYXCXM = "LYXC/GXMSJ/GetData.aspx"; //计划jhid=20161103192018
    public static String LYXCQY = "LYXC/QYSJ/GetData.aspx";
    public static String LYXCSC = "LYXC/JCJG/SetData.aspx";//楼宇上传
    public static String LYXCJSSC = "LYXC/JCJG/SetFiles.aspx";//楼宇现场记事上传

    //-----------------------推送-待办事物---------------------//
    public static String PUSH = "TZDB/GetData.aspx"; //userid=test2

    //-----------------------点检--------------------------//
    public static String DJJHLIST = "DJGL/DJGL_GWDJ.ashx";//prame BZMC = 测试班组
    public static String DJJHDLB = "DJGL/DJGL_GWDJ.ashx";// prame jhid=20161013141937;
    public static String DJJHXCJSSC = "DJGL/DJGL_XCJS.ashx";//ms:描述  fileps:文件  poinnum jhid
    public static String DJJHSC = "DJGL/DJGL_GWDJ.ashx";//json字符串
    public static String DJJHQXGD = "DJGL/DJGL_QXGD.ashx";//json字符串
    public static String QXGDZY = "QXGD/QXGD_ZYXX.ASHX";
    public static String YHPC = "AJH/AJH_YHPCSC.ashx";//隐患排查
    public static String YHPCTP = "AJH/AJH_YHWJSC.ashx";//guid

    //-----------------------安健环------------------------//
    public static String AJHJHLIST = "AJH/AJH_XZJH.ashx";
    public static String AJHXZRW = "AJH/AJH_XZRW.ashx";
    public static String AJHSC = "AJH/AJH_JLSC.ashx";
    public static String AJHXCJS = "AJH/AJH_JSXC.ashx";
    public static String XWAQGC = "ajh/gc_xzrw.ashx"; //gh =
    public static String QY = "djgl/DJGL_QYLIST.ashx";
    public static String XWAQGCSC = "ajh/gc_jlsc.ashx";//观察结果上传
    public static String XWAQGCJS = "ajh/GC_JSSC.ashx";//记事上传
    public static String GZQKSBLIST = "AJH/GZQKSB/GetDataByList.ashx";//工作上报列表查看
    public static String GZQKSBSAVE = "AJH/GZQKSB/SaveData.ashx";//工作情况上报保存
    public static String GZQKSBGB = "AJH/GZQKSB/SetRWState.ashx";//工作情况上报关闭
    public static String GZQKID = "AJH/GZQKSB/GetDataByID.ashx";

    public final static int IMAGE_RESULT = 10;
    public final static int SBXJZT_WWC = 0;
    public final static int SBXJZT_ZC = 1;
    public final static int SBXJZT_BZC = 2;
    public final static int RESULT_OK = 1;
    public final static int DLB = 1;
    public final static int ZKDLB = 2;

    //==============================巡视抄表=========================================//

    public final static String XSCB = "XSCB/XSCB_JHGL.ashx";//巡视抄表

    public final static String XSDJ = "DQGZ/DQGZ_CHGL.ashx";//定期工作

    public final static String SBLC = "SBLC/SBLC_JHGL.ashx";

    public final static String QFGD = "Q4GD/Q4GD_CKGL.ashx";

    public final static String AQJC = "AQJC/AQJC_JHGL.ashx";


    public final static String ZDCS = "ZDCS/ZDCS_JHGL.ashx";

    public final static String XS_JCSJ = "GGJK/GGJK.ashx";

    public final static String GWZY = "GWZY/GWZY_GDGL.ashx";


    public final static String GET_QY = "ZDCS/ZDCS_JHGL.ashx";


    public final static String SCTP = "SBLC/SBLC_RWIMG.ashx";


    public final static String UPDATEURL = "ApkUpdate/GetDataversion.aspx";


    // ========================= 用于判断是哪个界面传过的数据 =========================//

    public final static String ACT_FRG_FLAG = "act_frg_flag";
    //灭火器材
    public final static int MHQCFRG = 1;
    //消防栓
    public final static int XFSFRG = 2;
    //防火门
    public final static int FHMFRG = 3;
    //点列表
    public final static int SDLBACT = 4;

    public final static int AjhACT = 5;

    // =========================  intent KEY  ========================//
    public final static String KEY_ITEMID = "itemid";
    public final static String KEY_SBINFO = "sbinfo";
    public final static String KEY_SBDJLIST = "sbdjlist";
    public final static String KEY_JCZT = "zczt";
    public final static String KEY_MS = "ms";
    public final static String KEY_FLAG = "flag";
    public final static String KEY_IS_EDIT = "edit";
    public final static String KEY_NOTIF = "notification";
    public final static String KEY_PDID = "pdid";
    public final static String KEY_DJJHLIST = "djjhlist";
    public final static String KEY_DJJHRWQY = "djjhrwqy";
    public final static String KEY_ITEM = "item";
    public final static String FILEPS = "fileps";
    public final static String KEY_XSCBJH = "xscbjh";
    public final static String KEY_QY = "qydlist";
    public final static String KEY_EWM_OR_NFC = "ewm_or_nfc";//二维码或NFC   0  NFC  1 一维码二维码
    public final static String KEY_IS_HIS = "history";//历史
    public final static String KEY_JHLX = "jhlx";
    public final static String KEY_BG = "DQGZBG";//定期工作变更
    public final static String KEY_ZXID = "zxid";
    public final static String KEY_XS_HISTORY = "xs_history";
    public final static String KEY_NAME = "keyName";
    public final static String KEY_POSITION = "keyPosition";

    public final static String KEY_DQGZ = "dqgz";//定期工作


    public static String JHID = "JHID";


    public final static String FIRST = "first";//是否第一次进入程序
    public final static String KEY_IP = "ip";//ip地址
    public final static String KEY_BQBM = "bqbm";//二维码或条形码
    public final static String KEY_NFCBH = "nfcbh";//NFC编号
    public final static String KEY_WIFI_IP = "WIFI";
    public final static String KEY_4G_IP = "4G";
    public final static String NFCOREWM = "nfcorewm";
    public final static String WIFI_4G = "wifi4g";
    public final static String PERMISSIONSRESULT = "PermissionsResult";
    public final static String USERNAME = "enterby";
    public final static String BZBH = "bzbh";
    public final static String BZMC = "bzmc";
    public final static String NAME = "username";
    public static boolean NEWWORK_STATE;//网络状态


    public static boolean TEST = false;


    public static String PRINTIP = "10.19.110.19";//打印机的IP


    /********************广播字段（唯一性）********************************/
    public static final String ACTION_YULONE = "actionYulOne";


}