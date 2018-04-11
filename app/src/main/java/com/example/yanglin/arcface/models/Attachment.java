package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-4-11.
 */

public class Attachment {

    /**
     * code : 1
     * msg : 成功
     * data : {"id":82,"path":"static/images/attachment/BJCLoaCwAL-QGQc24ASD7GzV.jpg"}
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
         * id : 82
         * path : static/images/attachment/BJCLoaCwAL-QGQc24ASD7GzV.jpg
         */

        private int id;
        private String path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
