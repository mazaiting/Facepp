package com.mazaiting.facepp.funcation.ocr.vehicle.bean;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class VehicleBean {

    /**
     * cards : [{"issue_date":"2010-04-02","vehicle_type":"小型轿车","issued_by":"江商省上饶市公安局交通警察支队","vin":"LFVXXXXXXXXX536","plate_no":"皖EHXXXX","side":"front","use_character":"非营运","address":"江西省上饶县XXXXXXXXXXX","owner":"邹XX","model":"路虎AUD","register_date":"2010-04-02","type":3,"engine_no":"ANXXXXX43"}]
     * time_used : 3474
     * request_id : 1473763408,e2c2a352-a6bd-4620-9f74-c96841aed500
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
         * issue_date : 2010-04-02
         * vehicle_type : 小型轿车
         * issued_by : 江商省上饶市公安局交通警察支队
         * vin : LFVXXXXXXXXX536
         * plate_no : 皖EHXXXX
         * side : front
         * use_character : 非营运
         * address : 江西省上饶县XXXXXXXXXXX
         * owner : 邹XX
         * model : 路虎AUD
         * register_date : 2010-04-02
         * type : 3
         * engine_no : ANXXXXX43
         */

        private String issue_date;
        private String vehicle_type;
        private String issued_by;
        private String vin;
        private String plate_no;
        private String side;
        private String use_character;
        private String address;
        private String owner;
        private String model;
        private String register_date;
        private int type;
        private String engine_no;

        public String getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(String issue_date) {
            this.issue_date = issue_date;
        }

        public String getVehicle_type() {
            return vehicle_type;
        }

        public void setVehicle_type(String vehicle_type) {
            this.vehicle_type = vehicle_type;
        }

        public String getIssued_by() {
            return issued_by;
        }

        public void setIssued_by(String issued_by) {
            this.issued_by = issued_by;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getPlate_no() {
            return plate_no;
        }

        public void setPlate_no(String plate_no) {
            this.plate_no = plate_no;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getUse_character() {
            return use_character;
        }

        public void setUse_character(String use_character) {
            this.use_character = use_character;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getEngine_no() {
            return engine_no;
        }

        public void setEngine_no(String engine_no) {
            this.engine_no = engine_no;
        }

        @Override
        public String toString() {
            return "CardsBean{" +
                    "issue_date='" + issue_date + '\'' +
                    ", vehicle_type='" + vehicle_type + '\'' +
                    ", issued_by='" + issued_by + '\'' +
                    ", vin='" + vin + '\'' +
                    ", plate_no='" + plate_no + '\'' +
                    ", side='" + side + '\'' +
                    ", use_character='" + use_character + '\'' +
                    ", address='" + address + '\'' +
                    ", owner='" + owner + '\'' +
                    ", model='" + model + '\'' +
                    ", register_date='" + register_date + '\'' +
                    ", type=" + type +
                    ", engine_no='" + engine_no + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VehicleBean{" +
                "time_used=" + time_used +
                ", request_id='" + request_id + '\'' +
                ", cards=" + cards +
                '}';
    }
}
