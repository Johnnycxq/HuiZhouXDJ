package com.rehome.huizhouxdj.activity.sbxj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rehome.huizhouxdj.DBModel.XSJJHDataBean;
import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;
import com.rehome.huizhouxdj.weight.ListDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rehome.huizhouxdj.R.id.et_button;
import static com.rehome.huizhouxdj.R.id.et_jg;
import static com.rehome.huizhouxdj.R.id.tv_zt;


public class XJCJFragment extends BaseFragment {


    @BindView(R.id.tv_bjmc)
    TextView tvBjmc;
    @BindView(R.id.tv_sbmc)
    TextView tvSbmc;
    @BindView(R.id.tv_zymc)
    TextView tvZymc;
    @BindView(R.id.tv_dz)
    TextView tvDz;
    @BindView(R.id.tv_gz)
    TextView tvGz;
    @BindView(R.id.tv_zczt)
    TextView tvZczt;
    @BindView(R.id.tv_bsyl)
    TextView tvBsyl;
    @BindView(R.id.tv_xcnr)
    TextView tvXcnr;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(et_jg)
    EditText etJg;
    @BindView(et_button)
    Button etButton;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.tv_yjzj)
    TextView tvYjzj;
    @BindView(tv_zt)
    TextView tvZt;
    private boolean isEdit;
    private XSJJHDataBean info;
    private int zj;
    private int index;


    public static XJCJFragment newInstance(boolean b, XSJJHDataBean info, int zj, int index) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("edit", b);
        bundle.putParcelable("testinfo", info);
        bundle.putInt("zj", zj);
        bundle.putInt("index", index);


        XJCJFragment fragment = new XJCJFragment();
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
        return R.layout.fragment_xjcj;
    }

    @Override
    protected void initView() {


        etJg.setEnabled(isEdit);

//        etJg.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        etButton.setEnabled(isEdit);


        updata(info, index, zj);
    }


    @Override
    public void initData() {

        etButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDialog dialog = new ListDialog(context, stringToList(info.getMRNR()), new ListDialog.ListDialogListener() {

                    @Override
                    public void selectText(String text, int position) {
                        etButton.setText(text);
                        etJg.setText(text);
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * 更新当前检查item的检查结果
     *
     * @param value
     */
    public void updateState(String value) {
        etJg.setText(value);
        etButton.setText(value);
    }

    public void updatecheck(Boolean value) {

        if (value) {
            tvZt.setText("已检");
        } else {
            tvZt.setText("未检");
        }

    }

    /**
     * 更新数据
     */
    public void updata(XSJJHDataBean info, int item, int size) {

        tvBjmc.setText(info.getBJMC());
        tvSbmc.setText(info.getSb());
        tvZymc.setText(info.getZymc());
        tvDz.setText(info.getDz());
        tvGz.setText(info.getGz());
        tvZczt.setText(info.getZczt());
        tvBsyl.setText(info.getBsyl());
        tvXcnr.setText(info.getXcnr());
        tvZt.setText(info.isChecked() ? "已检" : "未检");
        tvYjzj.setText(item + "/" + size);

//        Log.e("XJCJFragment", "lrlx = " + info.getLRFS() + ", cjjg=" + info.getCJJG());

        if (info.getLRFS().equals("0")) {      //当时编辑状态的是 不需要按钮

            etButton.setVisibility(View.GONE);
            etJg.setVisibility(View.VISIBLE);


            if (info.getCJJG() == null) {
                etJg.setText("");
            } else {
                if (info.getCJJG().equals("已运行")) {
                    etJg.setText("");
                } else if (info.getCJJG().equals("大小修")) {
                    etJg.setText("大小修");
                } else if (info.getCJJG().equals("已停用")) {
                    etJg.setText("已停用");
                }else if (info.getCJJG().equals("")) {
                    etJg.setText("");
                } else {
                    etJg.setText(info.getCJJG());
                }
            }


        } else if (info.getLRFS().equals("1")) {       //不是编辑状态的时候 需要按钮点击弹出菜单

            etJg.setVisibility(View.GONE);
            etButton.setVisibility(View.VISIBLE);

            if (info.getCJJG() == null) {
                etButton.setText("正常");
                etJg.setText("正常");
            } else {
                if (info.getCJJG().equals("已运行")) {
                    etButton.setText("正常");
                    etJg.setText("正常");
                } else if (info.getCJJG().equals("大小修")) {
                    etButton.setText("大小修");
                    etJg.setText("大小修");
                } else if (info.getCJJG().equals("已停用")) {
                    etButton.setText("已停用");
                    etJg.setText("已停用");
                } else if (info.getCJJG().equals("")) {
                    etButton.setText("正常");
                    etJg.setText("正常");
                } else {
                    etButton.setText(info.getCJJG());
                    etJg.setText(info.getCJJG());
                }
            }
        }
    }


    public String getCJJG() {
        return etJg.getText().toString().trim();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    private List<String> stringToList(String strs) {
        String str[] = strs.split(";");
        return Arrays.asList(str);
    }
}
