package com.rehome.huizhouxdj.activity.aqjc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.rehome.huizhouxdj.DBModel.Uploadaqjcsave;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.contans.Contans;
import com.rehome.huizhouxdj.utils.BaseActivity;
import com.rehome.huizhouxdj.utils.SPUtils;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruihong on 2018/1/8.
 */

public class AqjcrwSaveActivity extends BaseActivity {


    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;

    private ArrayList<String> imagePaths = new ArrayList<>();
    ArrayList<String> finalList;

    private GridAdapter gridAdapter;
    private String TAG = AqjcrwSaveActivity.class.getSimpleName();

    @BindView(R.id.et_wtqy)
    EditText etwtqy;
    @BindView(R.id.et_wtms)
    EditText etwtms;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.find_comment_submit)
    TextView findCommentSubmit;

    private Intent intent;
    private String JHID;

    @Override
    public int getContentViewID() {
        return R.layout.activity_aqjcrwsave;
    }

    @Override
    protected void initView() {

        setTitle("安全检查计划任务上传");
        setBack();

        intent = getIntent();
        JHID = intent.getStringExtra("JHID");

        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String imgs = (String) parent.getItemAtPosition(position);

                if ("paizhao".equals(imgs)) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(AqjcrwSaveActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    Toast.makeText(AqjcrwSaveActivity.this, "1" + position, Toast.LENGTH_SHORT).show();
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(AqjcrwSaveActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);


        findCommentSubmit.setOnClickListener(new View.OnClickListener() {  //上传任务的点击事件
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(etwtqy.getText())) {

                    showToast("请输入问题区域");

                } else if (TextUtils.isEmpty(etwtms.getText())) {

                    showToast("请输入问题描述");

                } else {

                    finalList = new ArrayList<>();
                    finalList.addAll(imagePaths);
                    if (finalList.contains("paizhao")) {
                        finalList.remove("paizhao");
                    }


                    String USERNAME = (String) SPUtils.get(context, Contans.USERNAME, "");
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


                    Uploadaqjcsave uploadaqjcsave = new Uploadaqjcsave();
                    uploadaqjcsave.setJHID(JHID);
                    uploadaqjcsave.setWTQY(etwtqy.getText().toString().trim());
                    uploadaqjcsave.setWTMS(etwtms.getText().toString().trim());
                    uploadaqjcsave.setLRSJ(sdf.format(date));
                    uploadaqjcsave.setFXLB("");
                    uploadaqjcsave.setYHDJ("");
                    uploadaqjcsave.setZRBM("");
                    uploadaqjcsave.setPhotopatglist(finalList.toString().toLowerCase());
                    uploadaqjcsave.setChecked(false);
                    uploadaqjcsave.setUploaded(false);


                    if (uploadaqjcsave.save()) {
                        showToast("保存成功");
                        finish();
                    } else {
                        showToast("保存失败");
                    }

                }


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {


                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
//                    Log.d(TAG, "数量：" + list.size());
//                    Log.e(TAG, "onActivityResult: " + Arrays.toString(list.toArray()));
                    loadAdpater(list);
                    break;


                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);

                    break;
            }
        }
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
            inflater = LayoutInflater.from(AqjcrwSaveActivity.this);
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
                Glide.with(AqjcrwSaveActivity.this)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
