package com.rehome.huizhouxdj.activity.qfgd;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.activity.aqjc.AqjcrwSaveActivity;
import com.rehome.huizhouxdj.base.BaseCallBack;
import com.rehome.huizhouxdj.bean.MessageEvent;
import com.rehome.huizhouxdj.bean.QxTjgdRequestBean;
import com.rehome.huizhouxdj.bean.ResultBean2;
import com.rehome.huizhouxdj.bean.UploadPhotosBean;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity2;
import com.rehome.huizhouxdj.utils.GsonUtils;
import com.rehome.huizhouxdj.utils.HttpListener;
import com.rehome.huizhouxdj.utils.NohttpUtils;
import com.rehome.huizhouxdj.utils.RetrofitHttpUtils;
import com.rehome.huizhouxdj.utils.SPUtils;
import com.rehome.huizhouxdj.weight.DateTimePickDialog;
import com.rehome.huizhouxdj.weight.InputLayout;
import com.rehome.huizhouxdj.weight.toastviewbymyself;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.rehome.huizhouxdj.contans.Contans.USERNAME;

/**
 * Created by ruihong on 2018/4/17.
 */

public class TjqxdActivity extends BaseActivity2 implements View.OnClickListener {

    private static final int REQUEST_AUDIO = 0;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;


    private ArrayList<String> imagePaths = new ArrayList<>();
    ArrayList<String> finalList;
    private GridAdapter gridAdapter;
    private String TAG = AqjcrwSaveActivity.class.getSimpleName();


    @BindView(R.id.gridView)
    GridView gridView;
    private Uri uri;
    @BindView(R.id.il_gdbh)
    InputLayout ilGdbh;
    @BindView(R.id.il_sbbh)
    InputLayout ilSbbh;
    @BindView(R.id.il_zrbz)
    InputLayout ilZrbz;
    @BindView(R.id.il_xmfr)
    InputLayout ilXmfr;
    @BindView(R.id.il_jxbz)
    InputLayout ilJxbz;
    @BindView(R.id.il_jxfzr)
    InputLayout ilJxfzr;
    @BindView(R.id.il_yxj)
    InputLayout ilYxj;
    @BindView(R.id.il_gzxz)
    InputLayout ilGzxz;
    @BindView(R.id.il_startime)
    InputLayout ilStartime;
    @BindView(R.id.il_endtime)
    InputLayout ilEndtime;

    @BindView(R.id.il_xzfj)
    InputLayout ilXzfj;
    @BindView(R.id.il_sbmc)
    InputLayout ilSbmc;


    @BindView(R.id.et_qxms)
    InputLayout etQxms;
    @BindView(R.id.et_gzms)
    InputLayout etGzms;


    private String SBID;
    private String SBMC;

    private String zrbzBMID;
    private String zrbzBMMC;

    private String jxbzBMID;
    private String jxbzBMMC;

    private String xmYHID;
    private String xmYHMC;

    private String jxYHID;
    private String jxYHMC;

    private String DJID;
    private String DJMC;

    private String filePath, fileSize, filetype, uploadfiletype;

    private File file;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tjqxd;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                TJ();
                break;
        }
    }


    @Override
    public void initData() {

        initToolbar("提交缺陷工单", "提交", this);

        ilSbbh.setTvContentOnClickListener(new View.OnClickListener() {//设备名称
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TjqxdActivity.this, SblistActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ilZrbz.setTvContentOnClickListener(new View.OnClickListener() {//责任班组
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TjqxdActivity.this, BmListActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        ilJxbz.setTvContentOnClickListener(new View.OnClickListener() {//检修班组
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TjqxdActivity.this, BmListActivity.class);
                startActivityForResult(intent, 3);
            }
        });


        ilXmfr.setTvContentOnClickListener(new View.OnClickListener() { //项目负责人
            @Override
            public void onClick(View v) {

                if (zrbzBMMC == null) {
                    toastviewbymyself.makeText(context, "请先选择责任班组", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TjqxdActivity.this, FzrListActivity.class);
                    intent.putExtra("zrbzBMID", zrbzBMID);
                    startActivityForResult(intent, 4);
                }
            }
        });
        ilJxfzr.setTvContentOnClickListener(new View.OnClickListener() {  //检修负责人
            @Override
            public void onClick(View v) {

                if (jxbzBMMC == null) {
                    toastviewbymyself.makeText(context, "请先选择检修班组", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TjqxdActivity.this, FzrListActivity.class);
                    intent.putExtra("jxbzBMID", jxbzBMID);
                    startActivityForResult(intent, 5);
                }
            }
        });
        ilYxj.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TjqxdActivity.this, DjListActivity.class);
                startActivityForResult(intent, 6);
            }
        });
        ilStartime.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialog dialog = new DateTimePickDialog(context, new DateTimePickDialog.CommitClickListener() {
                    @Override
                    public void confirm(String outPutDate, String outPutDate1, String outPutDate2) {
                        ilStartime.setContent(outPutDate);
                    }
                });
                dialog.show();
            }
        });
        ilEndtime.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialog dialog = new DateTimePickDialog(context, new DateTimePickDialog.CommitClickListener() {
                    @Override
                    public void confirm(String outPutDate, String outPutDate1, String outPutDate2) {
                        ilEndtime.setContent(outPutDate);
                    }
                });
                dialog.show();
            }
        });
        ilXzfj.setTvContentOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("audio/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_AUDIO);
            }
        });


        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String imgs = (String) parent.getItemAtPosition(position);

                if ("paizhao".equals(imgs)) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(TjqxdActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    Toast.makeText(TjqxdActivity.this, "1" + position, Toast.LENGTH_SHORT).show();
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(TjqxdActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        ilSbmc.setContent(messageEvent.getMessage());
        ilSbbh.setContent(messageEvent.getMessageid());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    SBID = bundle.getString("SBID");
                    SBMC = bundle.getString("SBMC");
                    ilSbbh.setContent(SBID);
                    ilSbmc.setContent(SBMC);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    zrbzBMID = bundle.getString("BMID");
                    zrbzBMMC = bundle.getString("BMMC");
                    ilZrbz.setContent(zrbzBMMC);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    jxbzBMID = bundle.getString("BMID");
                    jxbzBMMC = bundle.getString("BMMC");
                    ilJxbz.setContent(jxbzBMMC);
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    xmYHID = bundle.getString("YHID");
                    xmYHMC = bundle.getString("YHMC");
                    ilXmfr.setContent(xmYHMC);
                }
                break;
            case 5:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    jxYHID = bundle.getString("YHID");
                    jxYHMC = bundle.getString("YHMC");
                    ilJxfzr.setContent(jxYHMC);
                }
                break;
            case 6:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    DJID = bundle.getString("DJID");
                    DJMC = bundle.getString("DJMC");
                    ilYxj.setContent(DJMC);
                }
                break;
            case REQUEST_AUDIO:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    filePath = cursor.getString(1); // 文件路径
                    file = new File(filePath);
                    filetype = file.getName().substring(file.getName().lastIndexOf(".") + 1);

                    if (filetype.equals("doc") || filetype.equals("docx") || filetype.equals("xls")
                            || filetype.equals("xlsx") || filetype.equals("ppt") || filetype.equals("pptx") || filetype.equals("pdf")) {
                        uploadfiletype = "10001";

                    } else if (filetype.equals("flv") || filetype.equals("mp4")) {

                        uploadfiletype = "10002";
                    }

                    try {
                        fileSize = formetFileSize(getFileSize(file));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ilXzfj.setContent(file.getName());
                }
                break;
            case REQUEST_CAMERA_CODE:
                if (resultCode == RESULT_OK) {
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    loadAdpater(list);
                }
                break;
            case REQUEST_PREVIEW_CODE:
                if (resultCode == RESULT_OK) {
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);
                }
                break;
        }
    }

    /**
     * ˙
     * 计算文件的大小
     *
     * @param fileS
     * @return
     */
    public String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static long getFileSize(File file) throws Exception {
        if (file == null) {
            return 0;
        }
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        return size;
    }

    private void TJ() {
        final Request<String> requset = NoHttp.createStringRequest(Contans.IP + Contans.QFGD, RequestMethod.POST);
        requset.setDefineRequestBodyForJson(createJson());
        NohttpUtils.getInstance().add(this, 0, requset, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                try {


                    ResultBean2 resultbean = GsonUtils.GsonToBean(response.get(), ResultBean2.class);

                    if (resultbean.getState() == 1) {

                        String gdid = resultbean.getGdid();

                        TJTP(gdid);

                    } else {

                        showToast("提交失败,请稍后再试试");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void TJTP(String gdid) {

        finalList = new ArrayList<>();
        finalList.addAll(imagePaths);
        if (finalList.contains("paizhao")) {
            finalList.remove("paizhao");
        }

        String substring = finalList.toString().substring(1, finalList.toString().length() - 1);

        MultipartBody.Builder builder;

        if (substring.contains(",")) {

            builder = new MultipartBody.Builder();

            builder.setType(MultipartBody.FORM);

            String[] split = substring.split(",");

            for (String imagePath : split) {

                File file = new File(imagePath.trim());

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                builder.addFormDataPart("file", file.getName(), imageBody);

            }

        } else {

            builder = new MultipartBody.Builder();

            builder.setType(MultipartBody.FORM);

            File file = new File(substring);

            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            builder.addFormDataPart("file", file.getName(), imageBody);

        }

        RetrofitHttpUtils.getApi().upload_QXGDTP(gdid, builder.build()).enqueue(new BaseCallBack<UploadPhotosBean>(context) {
            @Override
            public void onSuccess(Call<UploadPhotosBean> call, retrofit2.Response<UploadPhotosBean> response) {

                UploadPhotosBean uploadPhotosBean = response.body();

                if (uploadPhotosBean != null) {

                    if (uploadPhotosBean.getState().equals("1")) {

                        showToast("上传数据成功");

                        finish();


                    }
                }
            }

            @Override
            public void onError(Call<UploadPhotosBean> call, Throwable t) {

            }
        });
    }

    private String createJson() {
        QxTjgdRequestBean info = new QxTjgdRequestBean();
        info.setAction("Q4GD_ADD");
        info.setYHID((String) SPUtils.get(context, USERNAME, ""));
        info.setGDZT_SO("");
        info.setGDZT_NO("");
        info.setGDDJ(DJID);
        info.setQXMS(etQxms.getContent());
        info.setGZMS(etGzms.getContent());
        info.setSBBH(ilSbbh.getContent());
        info.setSBMC(ilSbmc.getContent());
        info.setZRBZ(zrbzBMID);
        info.setZRR(xmYHID);
        info.setJXBZ(jxbzBMID);
        info.setJXR(jxYHID);
        info.setGZXZ("");
        info.setST(ilStartime.getContent());
        info.setET(ilEndtime.getContent());
        String json = GsonUtils.GsonString(info);
        return json;
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            imagePaths.clear();
        }
        if (paths.contains("paizhao")) {
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        try {
            JSONArray obj = new JSONArray(imagePaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if (listUrls.size() == 7) {
                listUrls.remove(listUrls.size() - 1);
            }
            //这个意思就是 如果最后返回的列表有7哥 就把最后一个移除了
            inflater = LayoutInflater.from(TjqxdActivity.this);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.sblcsave_item, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);

            //这里是如果取到path的值是paizhao  就显示加好  如果不是  就是返回的那个图片


            if (path.equals("paizhao")) {
                holder.image.setImageResource(R.mipmap.find_add_img);
            } else {
                Glide.with(TjqxdActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }
}

