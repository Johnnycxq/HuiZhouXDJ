package com.rehome.huizhouxdj.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rehome.huizhouxdj.R;
import com.rehome.huizhouxdj.adapter.ContactAdapter;
import com.rehome.huizhouxdj.base.BaseCallBack;
import com.rehome.huizhouxdj.base.BaseFragment2;
import com.rehome.huizhouxdj.bean.ContactListBean;
import com.rehome.huizhouxdj.utils.ContactDatas;
import com.rehome.huizhouxdj.utils.HttpUtils;
import com.rehome.huizhouxdj.weight.ClearEditText;
import com.rehome.huizhouxdj.weight.ListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;


/**
 * 通讯录
 */

public class ContactFragment extends BaseFragment2 {

    private static ContactFragment instance = null;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_dept_name)
    TextView tvDeptName;

    private ContactAdapter adapter;

    public List<ContactListBean.RowsBean.OrderlistBean> datas;
    private List<ContactListBean.RowsBean.OrderlistBean> initDatas;

    public static ContactFragment getInstance() {
        if (instance == null) {
            instance = new ContactFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void initData() {

        datas = new ArrayList<>();
        initDatas = new ArrayList<>();

        getDatas();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//
//        if (isVisibleToUser) {
//            initData();
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
////
////        if(getUserVisibleHint()) {
////            initData();
////        }
//
//    }

    private void filterData(String str) {
        List<ContactListBean.RowsBean.OrderlistBean> filters = new ArrayList<>();

        if (initDatas.size() != 0) {

            if (TextUtils.isEmpty(str)) {
                filters.addAll(initDatas);
            } else {
                for (ContactListBean.RowsBean.OrderlistBean bean : initDatas) {
                    if (bean.getName().contains(str) ||
                            bean.getAddress_tel().contains(str) ||
                            bean.getTelephone().contains(str) || bean.getGroupName().contains(str)) {

                        filters.add(bean);
                    }
                }
            }
            datas.clear();
            datas.addAll(filters);
            adapter.notifyDataSetChanged();
        }
    }

    public void getDatas() {

        HttpUtils.getApi().getContactList().enqueue(new BaseCallBack<ContactListBean>(getContext()) {
            @Override
            public void onSuccess(Call<ContactListBean> call, Response<ContactListBean> response) {

                datas.clear();
                initDatas.clear();

                ContactListBean bean = response.body();
                if (bean != null) {
                    datas.addAll(ContactDatas.getContanctList(response.body()));
                    initDatas.addAll(ContactDatas.getContanctList(response.body()));
                    if (datas.size() != 0) {
                        setAdapter();
                        tvDeptName.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(Call<ContactListBean> call, Throwable t) {

            }
        });
    }

    private void setAdapter() {

        if (adapter == null) {
            adapter = new ContactAdapter(getContext(), datas);

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String telephone = datas.get(position).getTelephone();
                    String address_tel = datas.get(position).getAddress_tel();
                    List<String> list = new ArrayList<String>();
                    if (!TextUtils.isEmpty(telephone)) {
                        list.add(telephone);
                    }
                    if (!TextUtils.isEmpty(address_tel)) {
                        String[] tels = address_tel.split(",");
                        for (String tel : tels) {
                            list.add(tel);
                        }
                    }

                    if (list.size() != 0) {
                        ListDialog dialog = new ListDialog(getContext(), list, new ListDialog.ListDialogListener() {
                            @Override
                            public void selectText(String text, int position) {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + text));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }

                        });
                        dialog.setTvTitle("选择要拨打的电话");
                        dialog.show();
                    } else {
                        showToast("没有该联系人电话");
                    }
                }
            });

            lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (datas.size() != 0) {
                        ContactListBean.RowsBean.OrderlistBean itme = datas.get(firstVisibleItem);
                        tvDeptName.setText(itme.getGroupName());
                    }
                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        instance = null;
    }
}
