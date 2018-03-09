package com.rehome.huizhouxdj.activity.sbxdj;

import android.os.Bundle;
import android.widget.EditText;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.base.BaseFragment;

import butterknife.BindView;

/**
 * 安健环巡查管理-方法
 */
public class FfFragment extends BaseFragment {


    @BindView(R.id.et_ff)
    EditText et_ff;

    private String ff;

    private boolean isEdit;

    public FfFragment() {
    }

    public static FfFragment newInstance(boolean b, String ff) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("edit", b);
        bundle.putString("ff", ff);
        FfFragment fragment = new FfFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isEdit = bundle.getBoolean("edit");
            ff = bundle.getString("ff");
        }
    }

    @Override
    protected void initView() {
        et_ff = (EditText) view.findViewById(R.id.et_ff);
        et_ff.setEnabled(isEdit);
        update(ff);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_ff;
    }

    public void initData() {
    }

    /**
     * 更新数据
     *
     * @param aaa
     */
    public void update(String aaa) {
        et_ff.setText(aaa);
    }
}
