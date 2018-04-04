package com.rehome.huizhouxdj.utils;


import com.rehome.huizhouxdj.bean.ContactListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/5.
 * 通讯录 工具类
 */

public class ContactDatas {


    public static List<ContactListBean.RowsBean.OrderlistBean> getContanctList(ContactListBean bean) {

        List<ContactListBean.RowsBean.OrderlistBean> orderlistBeanListEnd = new ArrayList<>();

        List<ContactListBean.RowsBean> rowsBeanList = bean.getRows();
        for (int i = 0; i < rowsBeanList.size(); i++) {
            ContactListBean.RowsBean rowsBean = rowsBeanList.get(i);

            if (rowsBean.getOrderlist() != null) {
                List<ContactListBean.RowsBean.OrderlistBean> orderlistBeanList = rowsBean.getOrderlist();
                if (orderlistBeanList.size() != 0) {
                    orderlistBeanListEnd.add(new ContactListBean.RowsBean.OrderlistBean("", "", "", rowsBean.getDeptName(), ""));

                    for (ContactListBean.RowsBean.OrderlistBean orderlistBean : orderlistBeanList) {
                        orderlistBean.setGroupName(rowsBean.getDeptName());
                    }

                    orderlistBeanListEnd.addAll(orderlistBeanList);
                }
            }
        }
        return orderlistBeanListEnd;
    }

}
