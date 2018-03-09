package com.rehome.huizhouxdj.activity.sbxdj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.DjjhRwQy;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.weight.ListDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 安健环巡查管理-采集
 */
public class CJFragment extends BaseFragment {


    @BindView(R.id.tv_bw)
    TextView tv_bw;
    @BindView(R.id.tv_dmc)
    TextView tv_dmc;
    @BindView(R.id.et_jg)
    EditText et_jg;
    @BindView(R.id.tv_yjzj)
    TextView tv_yjzj;
    @BindView(R.id.tv_zt)
    TextView tv_zt;
    @BindView(R.id.tv_qy)
    TextView tvQy;
    @BindView(R.id.tv_sb)
    TextView tvSb;
    Unbinder unbinder;
    @BindView(R.id.et_button)
    Button et_button;

    private boolean isEdit;

    private DjjhRwQy info;

    private int zj;
    private int index;


    public static CJFragment newInstance(boolean b, DjjhRwQy info, int zj, int index) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("edit", b);
        bundle.putParcelable("testinfo", info);
        bundle.putInt("zj", zj);
        bundle.putInt("index", index);
        CJFragment fragment = new CJFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isEdit = bundle.getBoolean("edit");
            info = bundle.getParcelable("testinfo");
            zj = bundle.getInt("zj");
            index = bundle.getInt("index");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_cj;
    }

    @Override
    protected void initView() {
        et_jg.setEnabled(isEdit);
        updata(info, index, zj);
    }


    @Override
    public void initData() {

        et_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDialog dialog = new ListDialog(context, stringToList(info.getLRNR()), new ListDialog.ListDialogListener() {

                    @Override
                    public void selectText(String text, int position) {
                        et_button.setText(text);
                        et_jg.setText(text);
                    }
                });
                dialog.show();
            }
        });
    }


    /**
     * 更新数据
     */
    public void updata(DjjhRwQy info, int item, int size) {

        tvQy.setText(info.getMEAAREA());
        tvSb.setText(info.getSBMC());
        tv_bw.setText(info.getBJMC());
        tv_dmc.setText(info.getPOINTNAME());
        tv_zt.setText(info.isChecked() ? "已检" : "未检");
        tv_yjzj.setText(item + "/" + size);

        Log.e("CJFragment","lrlx = "+info.getLRLX()+", cjjg="+info.getCJJG());

        if (info.getLRLX().equals("0")) {  //当时编辑状态的是 不需要按钮

            et_button.setVisibility(View.GONE);
            et_jg.setVisibility(View.VISIBLE);
            et_jg.setText(info.getCJJG());

        } else if (info.getLRLX().equals("1")) {  //不是编辑状态的时候 需要按钮点击弹出菜单

            et_jg.setVisibility(View.GONE);
            et_button.setVisibility(View.VISIBLE);

            if (info.getCJJG() != null) {  //当按钮有内容的时候 显示在按钮上

                et_button.setText(info.getCJJG());
                et_jg.setText(info.getCJJG());
            } else {
                et_button.setText("请点击获取采集结果");

            }
        }

    }


    public String getCJJG() {
        return et_jg.getText().toString().trim();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private List<String> stringToList(String strs) {
        String str[] = strs.split(";");
        return Arrays.asList(str);
    }
}
