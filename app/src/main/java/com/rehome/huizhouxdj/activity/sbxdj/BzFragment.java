package com.rehome.huizhouxdj.activity.sbxdj;

import android.os.Bundle;
import android.widget.EditText;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;

import butterknife.BindView;

/**
 * 安健环巡查管理-标准
 */
public class BzFragment extends BaseFragment {

    @BindView(R.id.et_bz)
    EditText et_bz;

    private boolean isEdit;
    private String bz;

    public BzFragment() {
    }

    public static BzFragment newInstance(boolean b, String bz) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("edit", b);
        bundle.putString("bz", bz);
        BzFragment fragment = new BzFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isEdit = bundle.getBoolean("edit");
            bz = bundle.getString("bz");
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_bz;
    }

    @Override
    protected void initView() {
        et_bz = (EditText) view.findViewById(R.id.et_bz);
        et_bz.setEnabled(isEdit);
        updata(bz);
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 更新数据
     */
    public void updata(String bz) {
        et_bz.setText(bz);
    }
}
