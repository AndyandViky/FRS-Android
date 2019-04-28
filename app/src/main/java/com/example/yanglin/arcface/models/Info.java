package com.example.yanglin.arcface.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanglin on 18-4-2.
 */

public class Info {

    /**
     * code : 1
     * msg : 成功
     * data : {"datas":[{"id":1,"people_id":2,"title":"系统识别警告","content":"尊敬的管理员您好, 系动检测到当前进出门识别率过低, 请关注...","status":0,"send_id":0,"created_at":"2018-04-10T03:52:36.000Z","updated_at":"2018-04-10T03:52:36.000Z","deletedAt":null}],"pageNo":1,"pageSize":10,"total":1}
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
         * datas : [{"id":1,"people_id":2,"title":"系统识别警告","content":"尊敬的管理员您好, 系动检测到当前进出门识别率过低, 请关注...","status":0,"send_id":0,"created_at":"2018-04-10T03:52:36.000Z","updated_at":"2018-04-10T03:52:36.000Z","deletedAt":null}]
         * pageNo : 1
         * pageSize : 10
         * total : 1
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

        public static class DatasBean implements Serializable {
            /**
             * id : 1
             * people_id : 2
             * title : 系统识别警告
             * content : 尊敬的管理员您好, 系动检测到当前进出门识别率过低, 请关注...
             * status : 0
             * send_id : 0
             * created_at : 2018-04-10T03:52:36.000Z
             * updated_at : 2018-04-10T03:52:36.000Z
             * deletedAt : null
             */

            private int id;
            private int people_id;
            private String title;
            private String content;
            private int status;
            private int send_id;
            private String created_at;
            private String updated_at;
            private Object deletedAt;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPeople_id() {
                return people_id;
            }

            public void setPeople_id(int people_id) {
                this.people_id = people_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSend_id() {
                return send_id;
            }

            public void setSend_id(int send_id) {
                this.send_id = send_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }
        }
    }
}
