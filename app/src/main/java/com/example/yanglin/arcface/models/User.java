package com.example.yanglin.arcface.models;

public class User {

    /**
     * code : 1
     * msg : 成功
     * data : {"user":{"id":594,"name":"face593","gender":0,"age":20,"email":"email593","phone":"17805850233","avatar":"static/images/attachment/LNjcfeHPVUdnAlfIawEF1rvX.jpg","types":2},"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWxmSWQiOjU5NCwidHlwZSI6MiwiaWF0IjoxNTU3NDc5NDg4LCJleHAiOjE1NTgzNDM0ODh9.zY8KlSqTpoqdXDnGCZBfXACDBL2OfXUrYCSg-31o2wU","isVerify":1}
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
         * user : {"id":594,"name":"face593","gender":0,"age":20,"email":"email593","phone":"17805850233","avatar":"static/images/attachment/LNjcfeHPVUdnAlfIawEF1rvX.jpg","types":2}
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWxmSWQiOjU5NCwidHlwZSI6MiwiaWF0IjoxNTU3NDc5NDg4LCJleHAiOjE1NTgzNDM0ODh9.zY8KlSqTpoqdXDnGCZBfXACDBL2OfXUrYCSg-31o2wU
         * isVerify : 1
         */

        private UserBean user;
        private String token;
        private int isVerify;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(int isVerify) {
            this.isVerify = isVerify;
        }

        public static class UserBean {
            /**
             * id : 594
             * name : face593
             * gender : 0
             * age : 20
             * email : email593
             * phone : 17805850233
             * avatar : static/images/attachment/LNjcfeHPVUdnAlfIawEF1rvX.jpg
             * types : 2
             */

            private int id;
            private String name;
            private int gender;
            private int age;
            private String email;
            private String phone;
            private String avatar;
            private int types;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getTypes() {
                return types;
            }

            public void setTypes(int types) {
                this.types = types;
            }
        }
    }
}
