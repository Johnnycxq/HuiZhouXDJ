package com.rehome.huizhouxdj.service;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;

import com.orhanobut.logger.Logger;
import com.rehome.huizhouxdj.DBModel.AjhScInfo;
import com.rehome.huizhouxdj.DBModel.Ajhjh;
import com.rehome.huizhouxdj.DBModel.Ajhxcjs;
import com.rehome.huizhouxdj.DBModel.Ajhxzrwqy;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.DBModel.QxgdInfo;
import com.rehome.huizhouxdj.DBModel.XcjsInfo;
import com.rehome.huizhouxdj.DBModel.YhpcInfo;
import com.rehome.huizhouxdj.bean.ScDjjhInfo;
import com.rehome.huizhouxdj.bean.StatusInfo;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NetworkAvailableUtils;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadService extends IntentService {

    private List<DjjhRwQy> alljhqy;//下载计划的所有任务区域
    private ArrayList<String> jhids;//计划id
    private ArrayList<String> qymcs;//区域名称
    private List<DjjhRwQy> rwqys;//任务区域

    //安建环
    private List<Ajhxzrwqy> allAjhxzrwqy;
    private List<Ajhxzrwqy> ajhxzrwqies;
    private ArrayList<String> ajhjhids;//计划id
    private ArrayList<String> qycode;//区域名称

    private int requestCount = 0;//请求数量
    private int successCount = 0;//请求成功数量

    public UploadService() {
        super("UploadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //点击计划
        alljhqy = new ArrayList<>();
        rwqys = new ArrayList<>();
        qymcs = new ArrayList<>();
        jhids = new ArrayList<>();

        ajhjhids = new ArrayList<>();
        qycode = new ArrayList<>();
        ajhxzrwqies = new ArrayList<>();
        allAjhxzrwqy = new ArrayList<>();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        djjhUpload();
        ajhjhUpload();

        //判断当前是否有网络，如果有就后台上传数据
        if (NetworkAvailableUtils.isNetworkAvailable(getApplicationContext())) {
            uploadData();
        }
    }

    //安建环需要上传的数据
    private void ajhjhUpload() {

        Cursor cursor = DataSupport.findBySQL("select * from ajhxzrwqy where checked = \"1\" group by areacode");
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("areacode");
            qycode.add(cursor.getString(index));
        }
        cursor.close();

        Cursor cursor1 = DataSupport.findBySQL("select * from ajhxzrwqy where checked = \"1\" group by jhid");
        while (cursor1.moveToNext()) {
            int index = cursor1.getColumnIndex("jhid");
            jhids.add(cursor1.getString(index));
        }
        cursor1.close();

        for (int i = 0; i < qymcs.size(); i++) {
            //如果已检的区域等于所有区域
            List<Ajhxzrwqy> aa = DataSupport.where(" areacode= ?", qycode.get(i)).find(Ajhxzrwqy.class);
            List<Ajhxzrwqy> bb = DataSupport.where("areacode = ? and checked = 1", qycode.get(i)).find(Ajhxzrwqy.class);

            if (aa.size() == bb.size() & bb.size() != 0) {
                ajhxzrwqies.addAll(aa);
            }
        }

    }

    //点检计划需要上传的数据
    private void djjhUpload() {
        Cursor cursor = DataSupport.findBySQL("select * from djjhrwqy where checked = \"1\" group by meaarea");
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("meaarea");
            qymcs.add(cursor.getString(index));
        }
        cursor.close();

        Cursor cursor1 = DataSupport.findBySQL("select * from djjhrwqy where checked = \"1\" group by jhid");
        while (cursor1.moveToNext()) {
            int index = cursor1.getColumnIndex("jhid");
            jhids.add(cursor1.getString(index));
        }
        cursor1.close();

        for (int i = 0; i < qymcs.size(); i++) {
            //如果已检的区域等于所有区域
            List<DjjhRwQy> aa = DataSupport.where("meaarea = ?", qymcs.get(i)).find(DjjhRwQy.class);
            List<DjjhRwQy> bb = DataSupport.where("meaarea = ? and checked = 1", qymcs.get(i)).find(DjjhRwQy.class);

            if (aa.size() == bb.size() & bb.size() != 0) {
                rwqys.addAll(aa);
            }
        }
    }

    //上传数据
    private void uploadData() {

        if (rwqys.size() != 0) {
            ++requestCount;
            List<ScDjjhInfo> scs = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            for (DjjhRwQy rwqy : rwqys) {
                ScDjjhInfo info = new ScDjjhInfo();
                info.setChecked(rwqy.isChecked());
                info.setCjjg(rwqy.getCJJG() == null ? "" : rwqy.getCJJG());
                info.setJhid(rwqy.getJHID() == null ? "" : rwqy.getJHID());
                info.setPointnum(rwqy.getPOINTNUM() == null ? "" : rwqy.getPOINTNUM());
                info.setDate(sdf.format(date));
                info.setDjr((String) SPUtils.get(getApplicationContext(), Contans.USERNAME, ""));
                info.setBzmc("测试班组");
                info.setFxnr(rwqy.getFxnr() == null ? "" : rwqy.getFxnr());
                info.setSmfx(rwqy.isSMFX() ? "扫描条码" : "NFC标签");
                scs.add(info);
            }
            String json = "{\"Rows\":" + GsonUtils.GsonString(scs) + ",\"Total\": " + scs.size() + "}";
            //计划
            Request<String> request = NoHttp.createStringRequest(Contans.IP+Contans.DJJHSC, RequestMethod.POST);
            request.setDefineRequestBodyForJson(json);
            NohttpUtils.getInstance().add(null, 0, request, callback, false, false, "");

            //缺陷工单，如果缺陷工单为空，就没有上传
            List<QxgdInfo> infos = DataSupport.findAll(QxgdInfo.class);
            if (infos.size() != 0) {
                ++requestCount;
                Request<String> request1 = NoHttp.createStringRequest(Contans.IP+Contans.DJJHQXGD, RequestMethod.POST);
                String qxgdjson = "{\"Rows\":" + GsonUtils.GsonString(infos) + ",\"Total\": " + infos.size() + "}";
                Logger.json(qxgdjson);
                request1.setDefineRequestBodyForJson(qxgdjson);
                NohttpUtils.getInstance().add(null, 1, request1, callback, false, false, "");
            }
            //现场记事
            List<XcjsInfo> xcjss = DataSupport.findAll(XcjsInfo.class);
            for (int i = 0; i < xcjss.size(); i++) {
                ++requestCount;
                Request<String> xcjs = NoHttp.createStringRequest(Contans.IP+Contans.DJJHXCJSSC + "?ms=" + xcjss.get(i).getMs() +
                        "&jhid=" + xcjss.get(i).getJhid() + "&pointnum=" + xcjss.get(i).getPointnum() +
                        "&djr=" + xcjss.get(i).getDjr(), RequestMethod.POST);
                xcjs.add(Contans.FILEPS, new FileBinary(new File(xcjss.get(i).getFilename())));
                NohttpUtils.getInstance().add(null, 2, xcjs, callback, false, false, "");
            }
        }

        if (ajhxzrwqies.size() != 0) {

            requestCount++;
            List<AjhScInfo> infos = new ArrayList<>();
            for (Ajhxzrwqy rwqy : ajhxzrwqies) {
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
            Request<String> request = NoHttp.createStringRequest(Contans.IP+Contans.AJHSC, RequestMethod.POST);
            request.setDefineRequestBodyForJson(json);
            NohttpUtils.getInstance().add(null, 0, request, callback, false, false, "");

            for (String jhid : jhids) {
                List<Ajhxcjs> xcjs = DataSupport.where("jhid = ?", jhid).find(Ajhxcjs.class);
                for (Ajhxcjs ajhxcjs : xcjs) {
                    requestCount++;
                    Request<String> request1 = NoHttp.createStringRequest(Contans.IP+Contans.AJHXCJS +
                            "?bz=" + UiUtlis.encoder(ajhxcjs.getBz()) +
                            "&jhid=" + UiUtlis.encoder(ajhxcjs.getJhid()) +
                            "&areacode=" + UiUtlis.encoder(ajhxcjs.getAreacode()) +
                            "&jsr=" + UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.USERNAME, "")), RequestMethod.POST);
                    request1.add(Contans.FILEPS, new FileBinary(new File(ajhxcjs.getFile())));
                    NohttpUtils.getInstance().add(null, 1, request1, callback, false, false, "");
                }
            }
            Request<String> request2 = NoHttp.createStringRequest(Contans.IP+Contans.YHPC, RequestMethod.POST);
            List<YhpcInfo> info = DataSupport.findAll(YhpcInfo.class);
            if (info.size() != 0) {
                requestCount++;
                String json1 = "{\"Rows\":" + GsonUtils.GsonString(info) + ",\"Total\": " + info.size() + "}";
                request.setDefineRequestBodyForJson(json1);
                NohttpUtils.getInstance().add(null, 3, request2, callback, false, false, "上传中...");
            }
        }

    }

    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            System.out.println(response.get());
            try {
                StatusInfo info = GsonUtils.GsonToBean(response.get(), StatusInfo.class);
                if (info != null) {
                    StatusInfo.Status status = info.getRows().get(0);
                    if (status.getStatus().equals("1")) {
                        ++successCount;
                        //如果请求数量等于请求成功数量，就删除本地数据
                        if (successCount == requestCount) {

                            if (rwqys.size() != 0) {
                                //上传成功，就删除数据
                                for (int i = 0; i < jhids.size(); i++) {
                                    alljhqy.addAll(DataSupport.where("jhid = ?", jhids.get(i)).find(DjjhRwQy.class));
                                }
                                //表示全部计划已经检查完毕，就删除所有数据
                                if (alljhqy.size() == rwqys.size() && rwqys.size() != 0) {
                                    DataSupport.deleteAll(Djjh.class);
                                    DataSupport.deleteAll(DjjhRwQy.class);
                                } else {
                                    for (String jhid : jhids) {
                                        for (String qymc : qymcs) {
                                            int size = DataSupport.deleteAll(DjjhRwQy.class, "jhid = ? and meaarea = ?", jhid, qymc);
                                        }
                                    }
                                }

                                List<XcjsInfo> infos = DataSupport.findAll(XcjsInfo.class);
                                for (XcjsInfo xcjs : infos) {
                                    File file = new File(xcjs.getFilename());
                                    if (file.isFile()) {
                                        file.delete();
                                    }
                                }
                                DataSupport.deleteAll(XcjsInfo.class);
                                DataSupport.deleteAll(QxgdInfo.class);
                            }

                            if (ajhxzrwqies.size() != 0) {

                                //上传成功，就删除数据
                                for (int i = 0; i < ajhjhids.size(); i++) {
                                    allAjhxzrwqy.addAll(DataSupport.where("jhid = ?", ajhjhids.get(i)).find(Ajhxzrwqy.class));
                                }
                                //表示全部计划已经检查完毕，就删除所有数据
                                if (allAjhxzrwqy.size() == ajhxzrwqies.size() && ajhxzrwqies.size() != 0) {
                                    DataSupport.deleteAll(Ajhjh.class);
                                    DataSupport.deleteAll(Ajhxzrwqy.class);
                                } else {
                                    for (String jhid : jhids) {
                                        for (String qymc : qymcs) {
                                            int size = DataSupport.deleteAll(DjjhRwQy.class, "jhid = ? and meaarea = ?", jhid, qymc);
                                        }
                                    }
                                }

                                List<Ajhxcjs> infos = DataSupport.findAll(Ajhxcjs.class);
                                for (Ajhxcjs xcjs : infos) {
                                    File file = new File(xcjs.getFile());
                                    if (file.isFile()) {
                                        file.delete();
                                    }
                                }
                                DataSupport.deleteAll(Ajhxcjs.class);
                                DataSupport.deleteAll(YhpcInfo.class);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };

}
