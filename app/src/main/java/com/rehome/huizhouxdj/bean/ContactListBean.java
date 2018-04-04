package com.rehome.huizhouxdj.bean;

import java.util.List;

/**
 * Created by Rehome-rjb1 on 2017/7/4.
 * <p>
 * 通讯录
 */

public class ContactListBean {


    private List<RowsBean> Rows;

    public List<RowsBean> getRows() {
        return Rows;
    }

    public void setRows(List<RowsBean> Rows) {
        this.Rows = Rows;
    }

    public static class RowsBean {
        /**
         * DeptName : 厂领导
         * orderlist : [{"name":"高苑辉","telephone":"","address_tel":""},{"name":"张洪刚","telephone":"","address_tel":""},{"name":"石喜光","telephone":"","address_tel":""},{"name":"齐晓波","telephone":"","address_tel":""},{"name":"陈运强","telephone":"","address_tel":""}]
         */

        private String DeptName;
        private List<OrderlistBean> orderlist;

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public List<OrderlistBean> getOrderlist() {
            return orderlist;
        }

        public void setOrderlist(List<OrderlistBean> orderlist) {
            this.orderlist = orderlist;
        }

        public static class OrderlistBean {
            /**
             * name : 高苑辉
             * telephone :
             * address_tel :
             */

            private String name;
            private String telephone;
            private String address_tel;
            private String groupName;
            private String man_id;
            private String account_head;

            public OrderlistBean(String name, String telephone, String address_tel, String groupName, String account_head) {
                this.name = name;
                this.telephone = telephone;
                this.address_tel = address_tel;
                this.groupName = groupName;
                this.account_head = account_head;
            }

            public OrderlistBean(String groupName) {
                this.groupName = groupName;
            }

            public OrderlistBean() {

            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress_tel() {
                return address_tel;
            }

            public void setAddress_tel(String address_tel) {
                this.address_tel = address_tel;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getMan_id() {
                return man_id;
            }

            public void setMan_id(String man_id) {
                this.man_id = man_id;
            }

            public String getAccount_head() {
                return account_head;
            }

            public void setAccount_head(String account_head) {
                this.account_head = account_head;
            }
        }
    }
}
