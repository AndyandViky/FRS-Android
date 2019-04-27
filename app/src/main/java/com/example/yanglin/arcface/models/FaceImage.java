package com.example.yanglin.arcface.models;

import android.graphics.Bitmap;

import com.example.yanglin.arcface.utils.OkhttpService;

import java.util.List;

/**
 * Created by yanglin on 18-4-3.
 */

public class FaceImage {


    /**
     * code : 1
     * msg : 成功
     * data : [{"id":584,"model_image":"static/images/face/2LRGswmaRmRCIZ1nKOBTndOX.jpg","is_active":1,"people_id":595},{"id":586,"model_image":"static/images/face/Daniel_Darnell_0001.jpg","is_active":1,"people_id":595},{"id":587,"model_image":"static/images/face/Daniel_Coats_0001.jpg","is_active":1,"people_id":595}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 584
         * model_image : static/images/face/2LRGswmaRmRCIZ1nKOBTndOX.jpg
         * is_active : 1
         * people_id : 595
         */

        private int id;
        private String model_image;
        private int is_active;
        private int people_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModel_image() {
            return model_image;
        }

        public void setModel_image(String model_image) {
            this.model_image = model_image;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public int getPeople_id() {
            return people_id;
        }

        public void setPeople_id(int people_id) {
            this.people_id = people_id;
        }
    }
}
