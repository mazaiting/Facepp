package com.mazaiting.facepp.funcation.ocr.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class DriverBean {

    /**
     * cards : [{"valid_from":"2008-10-23","gender":"男","issued_by":"山东省临沂市公安局交通警察支队","issue_date":"2008-10-23","class":"B2","license_number":"XXXXXX1984121XXXX","valid_for":"6年","birthday":"1984-12-18","version":1,"address":"山东省XXXXXXXXXXXXXXX","nationality":"中国","type":2,"side":"front","name":"李XX"}]
     * time_used : 3195
     * request_id : 1473762382,ba6a3595-ff51-44af-9b01-61ffbb19c596
     */

    private int time_used;
    private String request_id;
    private List<CardsBean> cards;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * valid_from : 2008-10-23
         * gender : 男
         * issued_by : 山东省临沂市公安局交通警察支队
         * issue_date : 2008-10-23
         * class : B2
         * license_number : XXXXXX1984121XXXX
         * valid_for : 6年
         * birthday : 1984-12-18
         * version : 1
         * address : 山东省XXXXXXXXXXXXXXX
         * nationality : 中国
         * type : 2
         * side : front
         * name : 李XX
         */

        private String valid_from;
        private String gender;
        private String issued_by;
        private String issue_date;
        @SerializedName("class")
        private String classX;
        private String license_number;
        private String valid_for;
        private String birthday;
        private int version;
        private String address;
        private String nationality;
        private int type;
        private String side;
        private String name;

        public String getValid_from() {
            return valid_from;
        }

        public void setValid_from(String valid_from) {
            this.valid_from = valid_from;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIssued_by() {
            return issued_by;
        }

        public void setIssued_by(String issued_by) {
            this.issued_by = issued_by;
        }

        public String getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(String issue_date) {
            this.issue_date = issue_date;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getLicense_number() {
            return license_number;
        }

        public void setLicense_number(String license_number) {
            this.license_number = license_number;
        }

        public String getValid_for() {
            return valid_for;
        }

        public void setValid_for(String valid_for) {
            this.valid_for = valid_for;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "CardsBean{" +
                    "valid_from='" + valid_from + '\'' +
                    ", gender='" + gender + '\'' +
                    ", issued_by='" + issued_by + '\'' +
                    ", issue_date='" + issue_date + '\'' +
                    ", classX='" + classX + '\'' +
                    ", license_number='" + license_number + '\'' +
                    ", valid_for='" + valid_for + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", version=" + version +
                    ", address='" + address + '\'' +
                    ", nationality='" + nationality + '\'' +
                    ", type=" + type +
                    ", side='" + side + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DriverBean{" +
                "time_used=" + time_used +
                ", request_id='" + request_id + '\'' +
                ", cards=" + cards +
                '}';
    }
}
