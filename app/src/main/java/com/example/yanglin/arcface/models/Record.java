package com.example.yanglin.arcface.models;

import java.util.List;

/**
 * Created by yanglin on 18-3-21.
 */

public class Record {

    /**
     * code : 1
     * msg : 成功
     * data : {"datas":[{"count":1,"type":0,"created_at":"2018-04-12T22:23:43.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:23:48.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:23:52.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:41.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:46.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:51.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:55.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:59.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:26:03.000Z"}],"pageNo":1,"pageSize":10,"total":9}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas : [{"count":1,"type":0,"created_at":"2018-04-12T22:23:43.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:23:48.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:23:52.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:41.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:46.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:51.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:55.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:25:59.000Z"},{"count":1,"type":0,"created_at":"2018-04-12T22:26:03.000Z"}]
         * pageNo : 1
         * pageSize : 10
         * total : 9
         */

        private int pageNo;
        private int pageSize;
        private int total;
        private List<DatasBean> datas;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * count : 1
             * type : 0
             * created_at : 2018-04-12T22:23:43.000Z
             */

            private int count;
            private int type;
            private String created_at;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
