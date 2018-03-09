package com.rehome.huizhouxdj.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rehome.huizhouxdj.DBModel.Ajhjh;
import com.rehome.huizhouxdj.DBModel.AjhjhList;
import com.rehome.huizhouxdj.DBModel.Djjh;
import com.rehome.huizhouxdj.DBModel.DjjhList;
import com.rehome.huizhouxdj.DBModel.XfDjjh;
import com.rehome.huizhouxdj.DBModel.XfDjjhList;
import com.rehome.huizhouxdj.DBModel.XwaqgcJh;
import com.rehome.huizhouxdj.DBModel.XwaqgcJhList;
import com.rehome.huizhouxdj.DBModel.Zy;
import com.rehome.huizhouxdj.DBModel.ZyInfo;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.sbxdj.SxcdjActivity;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.utils.UiUtlis;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.rehome.huizhouxdj.utils.GsonUtils.GsonToBean;


public class PushService extends IntentService {

    private int[] whats = new int[]{};


    public PushService() {
        super("PushService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.AppTheme);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            whats = intent.getExtras().getIntArray("what");
            requestData(whats);
        }
    }

    private void requestData(int[] what) {

        Request<String> qy = NoHttp.createStringRequest(Contans.IP + Contans.QY);
        NohttpUtils.getInstance().add(null, 12, qy, callback, false, false, "");


        Request<String> ck_ip = NoHttp.createStringRequest(Contans.IP + Contans.GET_CK_IP);
        NohttpUtils.getInstance().add(null, 14, ck_ip, callback, false, false, "");


        for (int i = 0; i < what.length; i++) {
            switch (what[i]) {
                //消防保卫
                case 0:
                    Request<String> xfbw = NoHttp.createStringRequest(Contans.IP + Contans.XFDJJHALL);
                    String str = (String) SPUtils.get(getApplicationContext(), Contans.BZBH, "152145221");
                    xfbw.add("bzbh", UiUtlis.encoder(str));
                    NohttpUtils.getInstance().add(null, 0, xfbw, callback, false, false, "");
                    break;
                //点检
                case 1:
                    Request<String> dj = NoHttp.createStringRequest(Contans.IP + Contans.DJJHLIST);
                    dj.add("BZMC", UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.BZBH, "12108080101")));
                    NohttpUtils.getInstance().add(null, 1, dj, callback, false, false, "加载中...");

//                    Request<String> zy = NoHttp.createStringRequest(Contans.IP + Contans.QXGDZY);
//                    NohttpUtils.getInstance().add(null, 11, zy, callback, false, false, "");
                    break;
                //安建环
                case 2:
                    Request<String> request = NoHttp.createStringRequest(Contans.IP + Contans.AJHJHLIST);
                    request.add("BZMC", UiUtlis.encoder((String) SPUtils.get(getApplicationContext(), Contans.BZBH, "12108030201")));
                    NohttpUtils.getInstance().add(null, 2, request, callback, false, false, "");
                    break;
                //行为安全观察
                case 3:
                    Request<String> xwaq = NoHttp.createStringRequest(Contans.IP + Contans.XWAQGC);
                    xwaq.add("gh", (String) SPUtils.get(getApplicationContext(), Contans.USERNAME, "310194"));
                    NohttpUtils.getInstance().add(null, 3, xwaq, callback, false, false, "");
                    break;
            }
        }
    }


    private HttpListener<String> callback = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {


            switch (what) {
                //消防保卫
                case 0:

                    Log.e("xfdjjhData", response.get());
                    XfDjjhList xfdjjh = GsonToBean(response.get(), XfDjjhList.class);
                    if (xfdjjh != null) {
                        if (Integer.parseInt(xfdjjh.getTotal()) != 0) {
                            //保存计划列表到数据库
                            int dbcount = -1;//数据库中是否有数据
                            List<XfDjjh> djjhs = xfdjjh.getRows();

                            DataSupport.deleteAll(XfDjjh.class, "download = 0");
                            for (XfDjjh djjh : djjhs) {
                                List<XfDjjh> dbdjjh = DataSupport.where("jhid = ? and download = ?", djjh.getJhid(), "1").find(XfDjjh.class);
                                if (dbdjjh.size() == 0) {
                                    dbcount = 1;
                                    djjh.save();
                                }
                            }

                            if (dbcount == 1) {
                                shownotification(0, "您有新的消防保卫计划");
                            }
                        } else {
                            DataSupport.deleteAll(XfDjjh.class, "download = 0");
                        }
                    }
                    break;
                case 1:
                    Log.e("serviceData", response.get());

                    DjjhList list = GsonToBean(response.get(), DjjhList.class);
                    if (list != null) {
                        if (list.getTotal() != 0) {
                            int dbcount = -1;//数据库中是否有数据
                            List<Djjh> djjhs = list.getRows();//服务器数据
                            //先删除未下 载的计划
                            DataSupport.deleteAll(Djjh.class, "download = 0");
                            for (Djjh djjh : djjhs) {
                                List<Djjh> dbdjjh = DataSupport.where("jhid = ? and download = ?", djjh.getJHID(), "1").find(Djjh.class);
                                //如果数据库中没有这条数据，就添加到数据库
                                if (dbdjjh.size() == 0) {
                                    dbcount = 1;
                                    djjh.save();
                                }
                            }
                            if (dbcount == 1) {
                                shownotification(1, "您有新的点检计划");
                            }
                        } else {
                            DataSupport.deleteAll(Djjh.class, "download = 0");
                        }
                    }
                    break;
                case 2:
                    //安建环计划

                    Log.e("AjhjhListData", response.get());
                    AjhjhList info = GsonToBean(response.get(), AjhjhList.class);
                    if (info != null) {
                        if (info.getTotal() != 0) {
                            int dbcount = -1;//数据库中是否有数据
                            List<Ajhjh> djjhs = info.getRows();


                            DataSupport.deleteAll(Ajhjh.class, "download = 0");

                            for (Ajhjh ajhjh : djjhs) {
                                List<Ajhjh> ajhjhs = DataSupport.where("jhid = ? and download = ?", ajhjh.getJHID(), "1").find(Ajhjh.class);
                                if (ajhjhs.size() == 0) {
                                    dbcount = 1;
                                    ajhjh.save();
                                }
                            }

                            if (dbcount == 1) {
                                shownotification(2, "您有新的安健环计划");
                            }
                        } else {
                            DataSupport.deleteAll(Ajhjh.class, "download = 0");
                        }

                    }
                    break;
                case 3:
                    //行为安全观察
                    Log.e("AjhjhListData", response.get());
                    XwaqgcJhList xwaq = GsonToBean(response.get(), XwaqgcJhList.class);
                    if (xwaq != null) {
                        if (xwaq.getTotal() != 0) {
                            List<XwaqgcJh> jhs = new ArrayList<>();

                            jhs.addAll(xwaq.getRows());
                            DataSupport.deleteAll(XwaqgcJh.class);
                            int dbcount = -1;//数据库中是否有数据
                            for (XwaqgcJh jh : jhs) {
                                List<XwaqgcJh> dbdjjh = DataSupport.where("jhid=?", jh.getJHID()).find(XwaqgcJh.class);
                                //如果数据库中没有这要数据,就添加
                                if (dbdjjh.size() == 0) {
                                    dbcount = 1;
                                    jh.save();
                                }
                            }

                            if (dbcount == 1) {
                                shownotification(3, "您有新的行为安全观察计划");
                                Intent intent = new Intent();
                                intent.putExtra("isTask", true);
                                intent.setAction("com.rehome.huizhouxdj.RECEIVER");
                                sendBroadcast(intent);
                            }
                        } else {
                            DataSupport.deleteAll(XwaqgcJh.class);
                            Intent intent = new Intent();
                            intent.putExtra("isTask", false);
                            intent.setAction("com.rehome.huizhouxdj.RECEIVER");
                            sendBroadcast(intent);
                        }
                    }
                    break;
                //专业
                case 11:

                    Log.e("zyData", response.get());
                    ZyInfo zy = GsonToBean(response.get(), ZyInfo.class);

                    if (zy != null) {

                        if (zy.getRows().size() != 0) {
                            if (DataSupport.count("zy") != 0) {
                                int i = DataSupport.deleteAll(Zy.class);
                                if (i != 0) {
                                    DataSupport.saveAll(zy.getRows());
                                }
                            } else {
                                DataSupport.saveAll(zy.getRows());
                            }
                        }
                    }
                    break;
                case 12:

//                    Log.e("QyListData", response.get());
//
//                    QyList qys = GsonToBean(response.get(), QyList.class);
//
//                    if (qys != null) {
//
//                        if (qys.getRows().size() != 0) {
//                            if (Qy.count("qy") != 0) {
//                                int i = DataSupport.deleteAll(Qy.class);
//                                if (i != 0) {
//                                    DataSupport.saveAll(qys.getRows());
//                                }
//                            } else {
//                                DataSupport.saveAll(qys.getRows());
//                            }
//                        }
//                    }
                    break;

                case 13:

                    break;

                case 14:


                    break;
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };

    private void shownotification(int what, String msg) {

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent;
        Intent intent = null;
        if (what == 0) {
//            intent = new Intent(this, XxzActivity.class);
        } else if (what == 1) {
            intent = new Intent(this, SxcdjActivity.class);
        } else if (what == 2) {
//            intent = new Intent(this, AjhXzActivity.class);
        } else if (what == 3) {
//            intent = new Intent(this, AxwaqgcglActivity.class);
        }
        Notification notify;
        pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notify = new Notification.Builder(this)
                .setTicker(System.currentTimeMillis() + "")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker("您有新的计划")
                .setContentTitle(msg)
                .setContentText("点击进入下载")
                .setContentIntent(pendingIntent).build(); // 需要注意build()是在API
        notify.defaults = Notification.DEFAULT_SOUND;
        // level16及之后增加的，API11可以使用getNotificatin()来替代
        notify.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        manager.notify(what, notify);
    }


}