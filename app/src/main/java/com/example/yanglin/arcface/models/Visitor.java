package com.example.yanglin.arcface.models;

import java.util.List;

/**
 * Created by yanglin on 18-3-23.
 */

public class Visitor {
    /**
     * code : 1
     * msg : 成功
     * data : {"datas":[{"id":2,"visitor_id":500,"belong":595,"deadline":1,"pass_time":"1524670658841","reason":"2","status":0,"created_at":"2018-04-25T07:09:53.000Z","updated_at":"2019-04-26T18:32:22.000Z","deletedAt":null,"people":{"id":500,"name":"face499","age":20,"phone":null,"email":"email499","gender":1,"avatar":null,"house_number":null,"password":"password499","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:24.000Z","updated_at":"2018-04-12T05:19:24.000Z","deletedAt":null}},{"id":3,"visitor_id":501,"belong":595,"deadline":1,"pass_time":"1","reason":"1","status":0,"created_at":"2018-04-25T07:10:05.000Z","updated_at":"2019-04-26T00:17:35.000Z","deletedAt":null,"people":{"id":501,"name":"face500","age":20,"phone":null,"email":"email500","gender":0,"avatar":null,"house_number":null,"password":"password500","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:25.000Z","updated_at":"2018-04-12T05:19:25.000Z","deletedAt":null}},{"id":4,"visitor_id":502,"belong":595,"deadline":1,"pass_time":"1","reason":"1","status":0,"created_at":"2018-04-25T07:11:15.000Z","updated_at":"2019-04-26T18:32:22.000Z","deletedAt":null,"people":{"id":502,"name":"face501","age":20,"phone":null,"email":"email501","gender":1,"avatar":null,"house_number":null,"password":"password501","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:25.000Z","updated_at":"2018-04-12T05:19:25.000Z","deletedAt":null}}],"pageNo":1,"pageSize":10,"total":3}
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
         * datas : [{"id":2,"visitor_id":500,"belong":595,"deadline":1,"pass_time":"1524670658841","reason":"2","status":0,"created_at":"2018-04-25T07:09:53.000Z","updated_at":"2019-04-26T18:32:22.000Z","deletedAt":null,"people":{"id":500,"name":"face499","age":20,"phone":null,"email":"email499","gender":1,"avatar":null,"house_number":null,"password":"password499","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:24.000Z","updated_at":"2018-04-12T05:19:24.000Z","deletedAt":null}},{"id":3,"visitor_id":501,"belong":595,"deadline":1,"pass_time":"1","reason":"1","status":0,"created_at":"2018-04-25T07:10:05.000Z","updated_at":"2019-04-26T00:17:35.000Z","deletedAt":null,"people":{"id":501,"name":"face500","age":20,"phone":null,"email":"email500","gender":0,"avatar":null,"house_number":null,"password":"password500","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:25.000Z","updated_at":"2018-04-12T05:19:25.000Z","deletedAt":null}},{"id":4,"visitor_id":502,"belong":595,"deadline":1,"pass_time":"1","reason":"1","status":0,"created_at":"2018-04-25T07:11:15.000Z","updated_at":"2019-04-26T18:32:22.000Z","deletedAt":null,"people":{"id":502,"name":"face501","age":20,"phone":null,"email":"email501","gender":1,"avatar":null,"house_number":null,"password":"password501","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:25.000Z","updated_at":"2018-04-12T05:19:25.000Z","deletedAt":null}}]
         * pageNo : 1
         * pageSize : 10
         * total : 3
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
             * id : 2
             * visitor_id : 500
             * belong : 595
             * deadline : 1
             * pass_time : 1524670658841
             * reason : 2
             * status : 0
             * created_at : 2018-04-25T07:09:53.000Z
             * updated_at : 2019-04-26T18:32:22.000Z
             * deletedAt : null
             * people : {"id":500,"name":"face499","age":20,"phone":null,"email":"email499","gender":1,"avatar":null,"house_number":null,"password":"password499","adress_id":1,"types":2,"is_active":1,"created_at":"2018-04-12T05:19:24.000Z","updated_at":"2018-04-12T05:19:24.000Z","deletedAt":null}
             */

            private int id;
            private int visitor_id;
            private int belong;
            private int deadline;
            private String pass_time;
            private String reason;
            private int status;
            private String created_at;
            private String updated_at;
            private Object deletedAt;
            private PeopleBean people;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVisitor_id() {
                return visitor_id;
            }

            public void setVisitor_id(int visitor_id) {
                this.visitor_id = visitor_id;
            }

            public int getBelong() {
                return belong;
            }

            public void setBelong(int belong) {
                this.belong = belong;
            }

            public int getDeadline() {
                return deadline;
            }

            public void setDeadline(int deadline) {
                this.deadline = deadline;
            }

            public String getPass_time() {
                return pass_time;
            }

            public void setPass_time(String pass_time) {
                this.pass_time = pass_time;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public PeopleBean getPeople() {
                return people;
            }

            public void setPeople(PeopleBean people) {
                this.people = people;
            }

            public static class PeopleBean {
                /**
                 * id : 500
                 * name : face499
                 * age : 20
                 * phone : null
                 * email : email499
                 * gender : 1
                 * avatar : null
                 * house_number : null
                 * password : password499
                 * adress_id : 1
                 * types : 2
                 * is_active : 1
                 * created_at : 2018-04-12T05:19:24.000Z
                 * updated_at : 2018-04-12T05:19:24.000Z
                 * deletedAt : null
                 */

                private int id;
                private String name;
                private int age;
                private Object phone;
                private String email;
                private int gender;
                private Object avatar;
                private Object house_number;
                private String password;
                private int adress_id;
                private int types;
                private int is_active;
                private String created_at;
                private String updated_at;
                private Object deletedAt;

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

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public Object getPhone() {
                    return phone;
                }

                public void setPhone(Object phone) {
                    this.phone = phone;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public Object getAvatar() {
                    return avatar;
                }

                public void setAvatar(Object avatar) {
                    this.avatar = avatar;
                }

                public Object getHouse_number() {
                    return house_number;
                }

                public void setHouse_number(Object house_number) {
                    this.house_number = house_number;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public int getAdress_id() {
                    return adress_id;
                }

                public void setAdress_id(int adress_id) {
                    this.adress_id = adress_id;
                }

                public int getTypes() {
                    return types;
                }

                public void setTypes(int types) {
                    this.types = types;
                }

                public int getIs_active() {
                    return is_active;
                }

                public void setIs_active(int is_active) {
                    this.is_active = is_active;
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
}
