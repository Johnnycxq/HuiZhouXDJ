package com.rehome.huizhouxdj.service;

import android.app.IntentService;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.rehome.huizhouxdj.DBModel.AjhScInfo;
import com.rehome.huizhouxdj.DBModel.Ajhjh;
import com.rehome.huizhouxdj.DBModel.Ajhxcjs;
import com.rehome.huizhouxdj.DBModel.Ajhxzrwqy;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.DBModel.LyXcjsInfo;
import com.rehome.huizhouxdj.DBModel.LyYhpcInfo;
import com.rehome.huizhouxdj.DBModel.LyxcXm;
import com.rehome.huizhouxdj.DBModel.LyxcXmJg;
import com.rehome.huizhouxdj.DBModel.Lyxcrwqy;
import com.rehome.huizhouxdj.DBModel.QxgdInfo;
import com.rehome.huizhouxdj.DBModel.XcjsInfo;
import com.rehome.huizhouxdj.DBModel.XfBaxcRwqy;
import com.rehome.huizhouxdj.DBModel.XfDjjh;
import com.rehome.huizhouxdj.DBModel.XfDjjhRwqy;
import com.rehome.huizhouxdj.DBModel.XfXcjsInfo;
import com.rehome.huizhouxdj.DBModel.XfXcmhqc;
import com.rehome.huizhouxdj.DBModel.XfXcxm;
import com.rehome.huizhouxdj.DBModel.XfXcxmjg;
import com.rehome.huizhouxdj.DBModel.YhpcInfo;
import com.rehome.huizhouxdj.bean.ScDjjhInfo;
import com.rehome.huizhouxdj.bean.StatusInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.HttpResponseListener;
import com.rehome.huizhouxdj.utils.NetworkAvailableUtils;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.litepal.crud.DataSupport.where;

/**
 * 上传已完成数据
 */
public class UploadDataService extends IntentService {


    private static final int DJJH = 1;
    private static final int AJHJH = 2;
    private static final int XFJH = 3;

    private List<Djjh> djjhs;
    private List<DjjhRwQy> djjhRwQies;
    private List<String> djjhids;

    Map<String, List<XfDjjhRwqy>> rwqys = new HashMap<>();
    Map<String, List<XfXcmhqc>> mhqcs = new HashMap<>();
    Map<String, List<XfXcxmjg>> xms = new HashMap<>();
    List<XfDjjh> xfDjjhs = new ArrayList<>();
    Map<String, List<XfBaxcRwqy>> xfBaxcRwqys = new HashMap<>();
    Map<String, List<XfDjjh>> xfdjjh = new HashMap<>();
    List<XfDjjh> xfdjjhss = new ArrayList<>();

    Map<String, List<XfDjjh>> badjjh = new HashMap<>();
    Map<String, List<XfDjjh>> lydjjh = new HashMap<>();
    Map<String, List<Lyxcrwqy>> lyxcrwqies = new HashMap<>();
    Map<String, List<LyxcXmJg>> lyxcXms = new HashMap<>();
    private List<String> xfdjjhids = new ArrayList<>();
    //
    private List<Ajhjh> ajhjhs;
    private List<Ajhxzrwqy> ajhxzrwqies;
    private List<String> ajhjhids;

    private int requestCount = 0;
    private int successCount = 0;

    private RequestQueue queue;


    public UploadDataService() {
        super("UploadDataService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        queue = NoHttp.newRequestQueue(1);

        djjhs = new ArrayList<>();
        djjhRwQies = new ArrayList<>();
        djjhids = new ArrayList<>();

        ajhjhs = new ArrayList<>();
        ajhxzrwqies = new ArrayList<>();
        ajhjhids = new ArrayList<>();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (NetworkAvailableUtils.isNetworkAvailable(getApplicationContext())) {
//            djjhUploadData();
//            ajhjhUploadData();
            xfjhUploadData();
        }

    }


    //上传点检计划
    private void djjhUploadData() {
        djjhs.addAll(DataSupport.where("download = ?", "1").find(Djjh.class));
        //遍历已经全部检查的计划
        for (Djjh djjh : djjhs) {
            List<DjjhRwQy> rwqys = DataSupport.where("jhid = ? ", djjh.getJHID()).order("date").find(DjjhRwQy.class);
            int nochecked = 0;
            for (DjjhRwQy rwqy : rwqys) {
                if (!rwqy.isChecked()) {
                    nochecked = 1;
                    break;
                }
            }
            if (nochecked == 0) {
                //如果全部已经检查
                djjhRwQies.addAll(rwqys);
                djjhids.add(djjh.getJHID());
            }
        }

        if (djjhRwQies.size() != 0) {
            //如果有数据，就上传数据
            djjhSCData(DJJH);
        }
    }

    //安健环计划上传
    private void ajhjhUploadData() {

        ajhjhs.addAll(DataSupport.where("download = ?", "1").find(Ajhjh.class));
        for (Ajhjh ajhjh : ajhjhs) {
            List<Ajhxzrwqy> rwqys = DataSupport.where("jhid = ?", ajhjh.getJHID()).find(Ajhxzrwqy.class);
            int nochecked = 0;
            for (Ajhxzrwqy ajhxzrwqy : rwqys) {
                if (!ajhxzrwqy.isChecked()) {
                    nochecked = 1;
                    break;
                }
            }

            if (nochecked == 0) {
                ajhxzrwqies.addAll(rwqys);
                ajhjhids.add(ajhjh.getJHID());
            }
        }
        if (ajhxzrwqies.size() != 0) {
            djjhSCData(AJHJH);
        }
    }

    //消防，楼宇，保安数据上传
    private void xfjhUploadData() {

        xfDjjhs.addAll(DataSupport.where("download = ?", "1").find(XfDjjh.class));
        for (XfDjjh djjh : xfDjjhs) {
            if (djjh.getXctypes().equals("1")) {
                //消防需要上传的数据
                List<XfDjjhRwqy> xfrwqys = DataSupport.where("jhid = ?", djjh.getJhid()).find(XfDjjhRwqy.class);
                int nochecked = 0;
                for (XfDjjhRwqy rwqy : xfrwqys) {
                    if (!rwqy.isChecked()) {
                        nochecked = 1;
                        break;
                    }
                }
                if (nochecked == 0) {
                    xfdjjhids.add(djjh.getJhid());
                    rwqys.put(djjh.getJhid(), xfrwqys);
                    mhqcs.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(XfXcmhqc.class));
                    xfdjjh.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(XfDjjh.class));
                    xms.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(XfXcxmjg.class));
                    xfdjjhss.add(djjh);
                    //djjhSCData(XFJH);
                }
            }
            if (djjh.getXctypes().equals("2")) {
                //保安需要上传的数据
                List<XfBaxcRwqy> barwqys = DataSupport.where("jhid = ?", djjh.getJhid()).find(XfBaxcRwqy.class);
                int nochecked = 0;
                for (XfBaxcRwqy rwqy : barwqys) {
                    if (!rwqy.isChecked()) {
                        nochecked = 1;
                        break;
                    }
                }
                if (nochecked == 0) {
                    xfdjjhids.add(djjh.getJhid());
                    xfBaxcRwqys.put(djjh.getJhid(), barwqys);
                    badjjh.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(XfDjjh.class));
                    xfdjjhss.add(djjh);
                    //djjhSCData(XFJH);
                }
            }
            if (djjh.getXctypes().equals("3")) {
                //楼宇需要上传的数据
                List<Lyxcrwqy> lyrwqys = DataSupport.where("jhid = ?", djjh.getJhid()).find(Lyxcrwqy.class);
                int nochecked = 0;
                for (Lyxcrwqy qy : lyrwqys) {
                    //有未检查项目，进行提示
                    if (!qy.isChecked()) {
                        nochecked = 1;
                        break;
                    }
                }
                if (nochecked == 0) {
                    xfdjjhids.add(djjh.getJhid());
                    for (Lyxcrwqy qy : lyrwqys) {
                        List<LyxcXm> xm = DataSupport.where("xmid = ?", qy.getXMID()).find(LyxcXm.class);
                        if (qy.getISZC().equals("0")) {
                            //如果不正常，生成隐患排查单
                            LyYhpcInfo yhpcInfo = new LyYhpcInfo();
                            yhpcInfo.setAREACODE(qy.getQYID());
                            yhpcInfo.setFXSJ(qy.getCJSJ());
                            yhpcInfo.setFXR((String) SPUtils.get(getApplicationContext(), Contans.USERNAME,""));
                            yhpcInfo.setWT(qy.getXMNAME() + xm.get(0).getTypename());
                            yhpcInfo.setNR(qy.getCJJG());
                        }
                    }
                    lydjjh.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(XfDjjh.class));
                    lyxcrwqies.put(djjh.getJhid(), lyrwqys);
                    lyxcXms.put(djjh.getJhid(), where("jhid = ?", djjh.getJhid()).find(LyxcXmJg.class));
                    xfdjjhss.add(djjh);
                    //djjhSCData(XFJH);
                }
            }
        }
        if (xfdjjhss.size() != 0) {
            djjhSCData(XFJH);
        }
    }

    private void djjhSCData(int tag) {

        if (tag == DJJH) {
            //计划
            requestCount++;
            Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.DJJHSC, RequestMethod.POST);
            request.setDefineRequestBodyForJson(toJson());
            NohttpUtils.getInstance().add(null, 0, request, callback, false, false, "");

            //缺陷工单，如果缺陷工单为空，就没有上传
            List<QxgdInfo> infos = DataSupport.findAll(QxgdInfo.class);
            if (infos.size() != 0) {
                requestCount++;
                Request<String> request1 = NoHttp.createStringRequest(Contans.IP + Contans.DJJHQXGD, RequestMethod.POST);
                String qxgdjson = "{\"Rows\":" + GsonUtils.GsonString(infos) + ",\"Total\": " + infos.size() + "}";
                Logger.json(qxgdjson);
                System.out.println(qxgdjson);
                request1.setDefineRequestBodyForJson(qxgdjson);
                NohttpUtils.getInstance().add(null, 1, request1, callback, false, false, "");
            }

            for (String jhid : djjhids) {
                //现场记事
                List<XcjsInfo> xcjss = DataSupport.where("jhid = ?", jhid).find(XcjsInfo.class);
                for (XcjsInfo info : xcjss) {
                    requestCount++;
                    Request<String> xcjs = NoHttp.createStringRequest(Contans.IP + Contans.DJJHXCJSSC + "?ms=" + UiUtlis.encoder(info.getMs()) +
                            "&jhid=" + UiUtlis.encoder(info.getJhid()) + "&pointnum=" + UiUtlis.encoder(info.getPointnum()) +
                            "&djr=" + UiUtlis.encoder(info.getDjr()), RequestMethod.POST);
                    xcjs.add(Contans.FILEPS, new FileBinary(new File(info.getFilename())));
                    NohttpUtils.getInstance().add(null, 2, xcjs, callback, false, false, "");
                }
            }
        }

        //安健康环上传
        if (tag == AJHJH) {
            requestCount++;
            Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.AJHSC, RequestMethod.POST);
            request.setDefineRequestBodyForJson(AtoJson(ajhxzrwqies));
            NohttpUtils.getInstance().add(null, 0, request, callback, false, false, "");

            for (String jhid : ajhjhids) {
                List<Ajhxcjs> xcjs = DataSupport.where("jhid = ?", jhid).find(Ajhxcjs.class);
                for (Ajhxcjs ajhxcjs : xcjs) {
                    requestCount++;
                    Request<String> request1 = NoHttp.createStringRequest(Contans.IP + Contans.AJHXCJS +
                            "?bz=" + UiUtlis.encoder(ajhxcjs.getBz()) +
                            "&jhid=" + UiUtlis.encoder(ajhxcjs.getJhid()) +
                            "&areacode=" + UiUtlis.encoder(ajhxcjs.getAreacode()) +
                            "&jsr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.USERNAME, "")), RequestMethod.POST);
                    request1.add(Contans.FILEPS, new FileBinary(new File(ajhxcjs.getFile())));
                    NohttpUtils.getInstance().add(null, 1, request1, callback, false, false, "");
                }
            }

            Request<String> request2 = NoHttp.createStringRequest(Contans.IP + Contans.YHPC, RequestMethod.POST);
            List<YhpcInfo> infos = DataSupport.findAll(YhpcInfo.class);
            if (infos.size() != 0) {
                requestCount++;
                String json1 = "{\"Rows\":" + GsonUtils.GsonString(infos) + ",\"Total\": " + infos.size() + "}";
                Logger.json(json1);
                request2.setDefineRequestBodyForJson(json1);
                NohttpUtils.getInstance().add(null, 2, request2, callback, false, false, "上传中...");
            }
        }

        if (tag == XFJH) {

            for (XfDjjh djjh : xfdjjhss) {
                StringBuilder xf = new StringBuilder();
                StringBuilder ba = new StringBuilder();
                StringBuilder ly = new StringBuilder();
                if (rwqys.size() != 0) {
                    List<XfDjjhRwqy> djjhRwqies = rwqys.get(djjh.getJhid());
                    if (djjhRwqies != null) {
                        String str1 = GsonUtils.GsonString(xms.get(djjh.getJhid()));
                        String str2 = GsonUtils.GsonString(mhqcs.get(djjh.getJhid()));
                        String str3 = GsonUtils.GsonString(rwqys.get(djjh.getJhid()));
                        String str4 = GsonUtils.GsonString(xfdjjh.get(djjh.getJhid()));
                        xf.append("{\"Rows\":[");
                        xf.append("{\"表\":" + str1 + "},");
                        xf.append("{\"表\":" + str2 + "},");
                        xf.append("{\"表\":" + str3 + "},");
                        xf.append("{\"表\":" + str4);
                        xf.append("}],\"total\":4}");
                    }
                }

                //保安的JSON数据
                if (xfBaxcRwqys.size() != 0) {
                    List<XfBaxcRwqy> xfBaxcRwqies = xfBaxcRwqys.get(djjh.getJhid());
                    if (xfBaxcRwqies != null) {
                        String str1 = GsonUtils.GsonString(xfBaxcRwqys.get(djjh.getJhid()));
                        String str2 = GsonUtils.GsonString(badjjh.get(djjh.getJhid()));
                        ba.append("{\"Rows\":[");
                        ba.append("{\"表\":" + str1 + "},");
                        ba.append("{\"表\":" + str2);
                        ba.append("}],\"total\":2}");
                    }
                }
                if (lyxcrwqies.size() != 0) {
                    List<Lyxcrwqy> lyxcrwqies1 = lyxcrwqies.get(djjh.getJhid());
                    if (lyxcrwqies1 != null) {
                        String str1 = GsonUtils.GsonString(lyxcrwqies.get(djjh.getJhid()));
                        String str2 = GsonUtils.GsonString(lyxcXms.get(djjh.getJhid()));
                        String str3 = GsonUtils.GsonString(lydjjh.get(djjh.getJhid()));
                        ly.append("{\"Rows\":[");
                        ly.append("{\"表\":" + str2 + "},");
                        ly.append("{\"表\":" + str1 + "},");
                        ly.append("{\"表\":" + str3);
                        ly.append("}],\"total\":3}");
                    }
                }

                String bajson = ba.toString();
                String xfjson = xf.toString();
                String lyjson = ly.toString();
                try {
                    bajson = new String(ba.toString().getBytes(), "UTF-8");
                    xfjson = new String(xf.toString().getBytes(), "UTF-8");
                    lyjson = new String(ly.toString().getBytes(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (!xf.toString().isEmpty()) {
                    requestCount++;
                    Logger.v(xf.toString());
                    Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.XFDJJHSC + "?cjscr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.NAME, "测试")), RequestMethod.POST);
                    request.setDefineRequestBodyForJson(UiUtlis.encoder(xfjson));
                    queue.add(0, request, new HttpResponseListener<>(null, request, callback, false, false, ""));
                    //NohttpUtils.getInstance().add(null, 0, request, callback, false, false, "");
                    List<XfXcjsInfo> xcjss = DataSupport.findAll(XfXcjsInfo.class);
                    for (XfXcjsInfo info : xcjss) {
                        requestCount++;
                        Request<String> xcjs = NoHttp.createStringRequest(Contans.IP + Contans.XFXCJSSC + "?ms=" + UiUtlis.encoder(info.getMs()) +
                                "&jhid=" + UiUtlis.encoder(info.getJhid()) + "&filename=" + UiUtlis.encoder(info.getFilename()) +
                                "&djr=" + UiUtlis.encoder(info.getDjr()) + "&xfid=" + UiUtlis.encoder(info.getXfid()), RequestMethod.POST);
                        xcjs.add(Contans.FILEPS, new FileBinary(new File(info.getPath())));
                        queue.add(1, xcjs, new HttpResponseListener<>(null, xcjs, callback, false, false, ""));
                        //NohttpUtils.getInstance().add(null, 1, xcjs, callback, false, false, "上传中...");
                    }
                }
                if (!ba.toString().isEmpty()) {
                    requestCount++;
                    Logger.v(ba.toString());
                    Request<String> baxc = NoHttp.createStringRequest(Contans.IP + Contans.BAXCJHSC + "?cjscr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.NAME, "测试")), RequestMethod.POST);
                    baxc.setDefineRequestBodyForJson(UiUtlis.encoder(bajson));
                    queue.add(2, baxc, new HttpResponseListener<>(null, baxc, callback, false, false, ""));
                    //NohttpUtils.getInstance().add(null, 2, baxc, callback, false, false, "");

                }
                if (!ly.toString().isEmpty()) {
                    Logger.v(ly.toString());
                    //上传隐患排查单
                    List<LyYhpcInfo> info = DataSupport.findAll(LyYhpcInfo.class);
                    if (info.size() != 0) {
                        requestCount++;
                        Request<String> yhpc = NoHttp.createStringRequest(Contans.IP + Contans.YHPC + "?cjscr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.NAME, "测试")), RequestMethod.POST);
                        String json1 = "{\"Rows\":" + GsonUtils.GsonString(info) + ",\"Total\": " + info.size() + "}";
                        yhpc.setDefineRequestBodyForJson(json1);
                        queue.add(3, yhpc, new HttpResponseListener<>(null, yhpc, callback, false, false, ""));
                        //NohttpUtils.getInstance().add(null, 3, yhpc, callback, false, false, "");
                    }

                    requestCount++;
                    Request<String> lyxc = NoHttp.createStringRequest(Contans.IP + Contans.LYXCSC + "?cjscr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.NAME, "测试")), RequestMethod.POST);
                    lyxc.setDefineRequestBodyForJson(UiUtlis.encoder(lyjson));
                    queue.add(4, lyxc, new HttpResponseListener<>(null, lyxc, callback, false, false, ""));
                    //NohttpUtils.getInstance().add(null, 4, lyxc, callback, false, false, "");

                    //楼宇现场记事
                    List<LyXcjsInfo> xcjss = DataSupport.findAll(LyXcjsInfo.class);
                    for (LyXcjsInfo lyinfo : xcjss) {
                        requestCount++;
                        Request<String> xcjs = NoHttp.createStringRequest(Contans.IP + Contans.LYXCJSSC + "?ms=" + UiUtlis.encoder(lyinfo.getMs()) +
                                "&jhid=" + UiUtlis.encoder(lyinfo.getJhid()) + "&filename=" + UiUtlis.encoder(lyinfo.getFilename()) +
                                "&djr=" + UiUtlis.encoder(lyinfo.getDjr()) + "&xfid=" + UiUtlis.encoder(lyinfo.getXfid()), RequestMethod.POST);
                        xcjs.add(Contans.FILEPS, new FileBinary(new File(lyinfo.getPath())));
                        queue.add(5, xcjs, new HttpResponseListener<>(null, xcjs, callback, false, false, ""));
                        //NohttpUtils.getInstance().add(null, 5, xcjs, callback, false, false, "");
                    }
                }
            }
        }
    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            try {
                Logger.v(response.get());
                StatusInfo info = GsonUtils.GsonToBean(response.get(), StatusInfo.class);
                if (info != null) {
                    if (info.getTotal() != 0) {
                        if (info.getRows().get(0).getStatus().equals("1")) {
                            successCount++;
                        }
                    }
                }
                if (successCount == requestCount) {
                    removeData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };

    //上传成功，删除数据
    private void removeData() {

//        //删除点检数据
//        for (String jhid : djjhids) {
//            DataSupport.deleteAll(Djjh.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(DjjhRwQy.class, "jhid = ?", jhid);
//            List<XcjsInfo> infos = DataSupport.where("jhid = ?", jhid).find(XcjsInfo.class);
//            for (XcjsInfo info : infos) {
//                File file = new File(info.getFilename());
//                if (file.isFile()) {
//                    file.delete();
//                }
//            }
//            DataSupport.deleteAll(XcjsInfo.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(QxgdInfo.class);
//        }
//
//        //删除安健环数据
//        for (String jhid : ajhjhids) {
//            List<Ajhxcjs> ajhxcjses = DataSupport.findAll(Ajhxcjs.class);
//            for (Ajhxcjs js : ajhxcjses) {
//                File file = new File(js.getFile());
//                if (file.isFile()) {
//                    file.delete();
//                }
//            }
//            DataSupport.deleteAll(Ajhxcjs.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(Ajhjh.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(Ajhxzrwqy.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(AjhScInfo.class, "jhid = ?", jhid);
//            DataSupport.deleteAll(YhpcInfo.class);
//        }

        //删除消防数据
        for (String jhid : xfdjjhids) {

            List<XfXcjsInfo> infos = DataSupport.where("jhid = ?", jhid).find(XfXcjsInfo.class);
            for (XfXcjsInfo info : infos) {
                File file = new File(info.getPath());
                if (file.isFile()) {
                    file.delete();
                }
            }

            List<LyXcjsInfo> lyjs = DataSupport.where("jhid = ?", jhid).find(LyXcjsInfo.class);
            for (LyXcjsInfo info : lyjs) {
                File file = new File(info.getPath());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(XfDjjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfDjjhRwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcxmjg.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcmhqc.class, "jhid = ?", jhid);

            DataSupport.deleteAll(XfBaxcRwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(XfXcxm.class, "jhid = ?", jhid);

            DataSupport.deleteAll(Lyxcrwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyxcXm.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyXcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyxcXmJg.class, "jhid = ?", jhid);
            DataSupport.deleteAll(LyYhpcInfo.class);
        }
    }

    //点检
    private String toJson() {
        List<ScDjjhInfo> scs = new ArrayList<>();
        for (DjjhRwQy rwqy : djjhRwQies) {
            ScDjjhInfo info = new ScDjjhInfo();
            info.setChecked(rwqy.isChecked());
            info.setCjjg(rwqy.getCJJG() == null ? "" : rwqy.getCJJG());
            info.setJhid(rwqy.getJHID() == null ? "" : rwqy.getJHID());
            info.setPointnum(rwqy.getPOINTNUM() == null ? "" : rwqy.getPOINTNUM());
            info.setDate(rwqy.getDATE());
            info.setDjr((String) SPUtils.get(getApplicationContext(), Contans.USERNAME, ""));
            info.setBzmc((String) SPUtils.get(getApplicationContext(), Contans.BZBH, ""));
            info.setFxnr(rwqy.getFxnr() == null ? "" : rwqy.getFxnr());
            info.setSmfx(rwqy.isSMFX() ? "扫描条码" : "NFC标签");
            info.setSBZT(rwqy.isSBZT() ? (1 + "") : (0 + ""));
            info.setASSETNUM(rwqy.getASSETNUM());
            scs.add(info);
        }
        String json = "{\"Rows\":" + GsonUtils.GsonString(scs) + ",\"Total\": " + scs.size() + "}";
        Logger.json(json);
        return json;
    }

    //生成安健环json数据
    private String AtoJson(List<Ajhxzrwqy> rwqys) {
        List<AjhScInfo> infos = new ArrayList<>();
        for (Ajhxzrwqy rwqy : rwqys) {
            AjhScInfo info = new AjhScInfo();
            info.setMS(rwqy.getMS());
            info.setBZID("");//班组ID，登录成功的时候返回的
            info.setCHECKED(rwqy.isChecked());
            info.setJCR((String) SPUtils.get(getApplicationContext(), Contans.USERNAME, ""));
            info.setJCSJ(rwqy.getDATE());
            info.setJHID(rwqy.getJHID());
            info.setSCNR(rwqy.getSCRN());
            info.setYSID(rwqy.getYSID());
            info.setCHECKED(rwqy.isChecked());
            info.setSMFX(rwqy.isSMFX() ? "扫描条码" : "NFC标签");
            infos.add(info);
        }
        String json = "{\"Rows\":" + GsonUtils.GsonString(infos) + ",\"Total\": " + infos.size() + "}";
        Logger.json(json);
        return json;
    }
}
