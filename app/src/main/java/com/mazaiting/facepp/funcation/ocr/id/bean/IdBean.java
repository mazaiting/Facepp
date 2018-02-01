package com.mazaiting.facepp.funcation.ocr.id.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class IdBean {

    /**
     * cards : [{"gender":"女","name":"牛XX","id_card_number":"XXXXXX19841013XXXX","birthday":"1984-10-13","race":"汉","address":"广东省深圳市XXXXXXXX","legality":{"Edited":0.001,"Photocopy":0,"ID Photo":0.502,"Screen":0.496,"Temporary ID Photo":0},"type":1,"side":"front"}]
     * time_used : 2151
     * request_id : 1473759244,40dfde25-6d1a-4c90-a994-813556c81e30
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
         * gender : 女
         * name : 牛XX
         * id_card_number : XXXXXX19841013XXXX
         * birthday : 1984-10-13
         * race : 汉
         * address : 广东省深圳市XXXXXXXX
         * legality : {"Edited":0.001,"Photocopy":0,"ID Photo":0.502,"Screen":0.496,"Temporary ID Photo":0}
         * type : 1
         * side : front
         */

        private String gender;
        private String name;
        private String id_card_number;
        private String birthday;
        private String race;
        private String address;
        private LegalityBean legality;
        private int type;
        private String side;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LegalityBean getLegality() {
            return legality;
        }

        public void setLegality(LegalityBean legality) {
            this.legality = legality;
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

        public static class LegalityBean {
            /**
             * Edited : 0.001
             * Photocopy : 0.0
             * ID Photo : 0.502
             * Screen : 0.496
             * Temporary ID Photo : 0.0
             */

            private double Edited;
            private double Photocopy;
            @SerializedName("ID Photo")
            private double _$IDPhoto264; // FIXME check this code
            private double Screen;
            @SerializedName("Temporary ID Photo")
            private double _$TemporaryIDPhoto79; // FIXME check this code

            public double getEdited() {
                return Edited;
            }

            public void setEdited(double Edited) {
                this.Edited = Edited;
            }

            public double getPhotocopy() {
                return Photocopy;
            }

            public void setPhotocopy(double Photocopy) {
                this.Photocopy = Photocopy;
            }

            public double get_$IDPhoto264() {
                return _$IDPhoto264;
            }

            public void set_$IDPhoto264(double _$IDPhoto264) {
                this._$IDPhoto264 = _$IDPhoto264;
            }

            public double getScreen() {
                return Screen;
            }

            public void setScreen(double Screen) {
                this.Screen = Screen;
            }

            public double get_$TemporaryIDPhoto79() {
                return _$TemporaryIDPhoto79;
            }

            public void set_$TemporaryIDPhoto79(double _$TemporaryIDPhoto79) {
                this._$TemporaryIDPhoto79 = _$TemporaryIDPhoto79;
            }

            @Override
            public String toString() {
                return "LegalityBean{" +
                        "Edited=" + Edited +
                        ", Photocopy=" + Photocopy +
                        ", _$IDPhoto264=" + _$IDPhoto264 +
                        ", Screen=" + Screen +
                        ", _$TemporaryIDPhoto79=" + _$TemporaryIDPhoto79 +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CardsBean{" +
                    "gender='" + gender + '\'' +
                    ", name='" + name + '\'' +
                    ", id_card_number='" + id_card_number + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", race='" + race + '\'' +
                    ", address='" + address + '\'' +
                    ", legality=" + legality +
                    ", type=" + type +
                    ", side='" + side + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "IdBean{" +
                "time_used=" + time_used +
                ", request_id='" + request_id + '\'' +
                ", cards=" + cards +
                '}';
    }
}
