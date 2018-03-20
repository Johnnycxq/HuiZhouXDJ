package com.rehome.huizhouxdj.service;

import android.app.IntentService;
import android.content.Intent;

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
import com.rehome.huizhouxdj.DBModel.XwaqgcJh;
import com.rehome.huizhouxdj.DBModel.XwaqgcJs;
import com.rehome.huizhouxdj.DBModel.XwaqgcSc;
import com.rehome.huizhouxdj.DBModel.YhpcInfo;
import com.rehome.huizhouxdj.utils.UiUtlis;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 删除计划到期的服务
 */
public class DeleteOverdueJHService extends IntentService {


    private List<String> djjhs;
    private List<String> xfDjjhs;
    private List<String> ajhjhs;
    private List<String> xwaqgcJhs;

    public DeleteOverdueJHService() {
        super("DeleteOverdueJHService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        djjhs = new ArrayList<>();
        xfDjjhs = new ArrayList<>();
        ajhjhs = new ArrayList<>();
        xwaqgcJhs = new ArrayList<>();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        List<Djjh> djjh = DataSupport.findAll(Djjh.class);
        for (Djjh jh : djjh) {
            if (UiUtlis.isdelete(jh.getGWID())) {
                djjhs.add(jh.getGWID());
            }
        }
        List<Ajhjh> ajhjh = DataSupport.findAll(Ajhjh.class);
        for (Ajhjh jh : ajhjh) {
            if (UiUtlis.isdelete(jh.getDQSJ())) {
                ajhjhs.add(jh.getJHID());
            }
        }
        List<XfDjjh> xfDjjh = DataSupport.findAll(XfDjjh.class);
        for (XfDjjh jh : xfDjjh) {
            if (UiUtlis.isdelete(jh.getNexttime())) {
                xfDjjhs.add(jh.getJhid());
            }
        }

        List<XwaqgcJh> xwaqgcJh = DataSupport.findAll(XwaqgcJh.class);

        for (XwaqgcJh jh : xwaqgcJh) {
            if (UiUtlis.isdelete(jh.getDQSJ())) {
                xwaqgcJhs.add(jh.getJHID());
            }
        }
    }


    //计划过期,删除数据
    private void removeData() {

        //删除点检数据
        for (String jhid : djjhs) {
            DataSupport.deleteAll(Djjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(DjjhRwQy.class, "jhid = ?", jhid);
            List<XcjsInfo> infos = DataSupport.where("jhid = ?", jhid).find(XcjsInfo.class);
            for (XcjsInfo info : infos) {
                File file = new File(info.getFilename());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(XcjsInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(QxgdInfo.class);
        }

        //删除安健环数据
        for (String jhid : ajhjhs) {
            List<Ajhxcjs> ajhxcjses = DataSupport.findAll(Ajhxcjs.class);
            for (Ajhxcjs js : ajhxcjses) {
                File file = new File(js.getFile());
                if (file.isFile()) {
                    file.delete();
                }
            }
            DataSupport.deleteAll(Ajhxcjs.class, "jhid = ?", jhid);
            DataSupport.deleteAll(Ajhjh.class, "jhid = ?", jhid);
            DataSupport.deleteAll(Ajhxzrwqy.class, "jhid = ?", jhid);
            DataSupport.deleteAll(AjhScInfo.class, "jhid = ?", jhid);
            DataSupport.deleteAll(YhpcInfo.class);
        }

        //删除消防数据
        for (String jhid : xfDjjhs) {

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

        for (String jh : xwaqgcJhs) {
            DataSupport.deleteAll(XwaqgcJh.class, "jhid = ?", jh);
            DataSupport.deleteAll(XwaqgcSc.class, "jhid = ?", jh);
            DataSupport.deleteAll(XwaqgcJs.class, "jhid = ?", jh);
        }
    }
}
