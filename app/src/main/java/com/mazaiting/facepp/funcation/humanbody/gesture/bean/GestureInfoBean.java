package com.mazaiting.facepp.funcation.humanbody.gesture.bean;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class GestureInfoBean {

    /**
     * image_id : jw7Qz0EjM6wm3qSjM6n/jw==
     * request_id : 1517460370,215bc417-ea10-43e7-a128-dd5baf6c8dae
     * time_used : 219
     * hands : [{"gesture":{"thumb_up":0,"namaste":0,"ok":0,"beg":0,"unknown":0,"index_finger_up":0,"thanks":0,"phonecall":0,"palm_up":0,"big_v":0,"double_finger_up":0,"thumb_down":0,"fist":0,"rock":0,"heart_d":0,"hand_open":0,"heart_b":100,"heart_c":0,"victory":0,"heart_a":0},"hand_rectangle":{"width":964,"top":35,"height":538,"left":0}}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<HandsBean> hands;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public List<HandsBean> getHands() {
        return hands;
    }

    public void setHands(List<HandsBean> hands) {
        this.hands = hands;
    }

    public static class HandsBean {
        /**
         * gesture : {"thumb_up":0,"namaste":0,"ok":0,"beg":0,"unknown":0,"index_finger_up":0,"thanks":0,"phonecall":0,"palm_up":0,"big_v":0,"double_finger_up":0,"thumb_down":0,"fist":0,"rock":0,"heart_d":0,"hand_open":0,"heart_b":100,"heart_c":0,"victory":0,"heart_a":0}
         * hand_rectangle : {"width":964,"top":35,"height":538,"left":0}
         */

        private GestureBean gesture;
        private HandRectangleBean hand_rectangle;

        public GestureBean getGesture() {
            return gesture;
        }

        public void setGesture(GestureBean gesture) {
            this.gesture = gesture;
        }

        public HandRectangleBean getHand_rectangle() {
            return hand_rectangle;
        }

        public void setHand_rectangle(HandRectangleBean hand_rectangle) {
            this.hand_rectangle = hand_rectangle;
        }

        public static class GestureBean {
            /**
             * thumb_up : 0
             * namaste : 0
             * ok : 0
             * beg : 0
             * unknown : 0
             * index_finger_up : 0
             * thanks : 0
             * phonecall : 0
             * palm_up : 0
             * big_v : 0
             * double_finger_up : 0
             * thumb_down : 0
             * fist : 0
             * rock : 0
             * heart_d : 0
             * hand_open : 0
             * heart_b : 100
             * heart_c : 0
             * victory : 0
             * heart_a : 0
             */

            private int thumb_up;
            private int namaste;
            private int ok;
            private int beg;
            private int unknown;
            private int index_finger_up;
            private int thanks;
            private int phonecall;
            private int palm_up;
            private int big_v;
            private int double_finger_up;
            private int thumb_down;
            private int fist;
            private int rock;
            private int heart_d;
            private int hand_open;
            private int heart_b;
            private int heart_c;
            private int victory;
            private int heart_a;

            public int getThumb_up() {
                return thumb_up;
            }

            public void setThumb_up(int thumb_up) {
                this.thumb_up = thumb_up;
            }

            public int getNamaste() {
                return namaste;
            }

            public void setNamaste(int namaste) {
                this.namaste = namaste;
            }

            public int getOk() {
                return ok;
            }

            public void setOk(int ok) {
                this.ok = ok;
            }

            public int getBeg() {
                return beg;
            }

            public void setBeg(int beg) {
                this.beg = beg;
            }

            public int getUnknown() {
                return unknown;
            }

            public void setUnknown(int unknown) {
                this.unknown = unknown;
            }

            public int getIndex_finger_up() {
                return index_finger_up;
            }

            public void setIndex_finger_up(int index_finger_up) {
                this.index_finger_up = index_finger_up;
            }

            public int getThanks() {
                return thanks;
            }

            public void setThanks(int thanks) {
                this.thanks = thanks;
            }

            public int getPhonecall() {
                return phonecall;
            }

            public void setPhonecall(int phonecall) {
                this.phonecall = phonecall;
            }

            public int getPalm_up() {
                return palm_up;
            }

            public void setPalm_up(int palm_up) {
                this.palm_up = palm_up;
            }

            public int getBig_v() {
                return big_v;
            }

            public void setBig_v(int big_v) {
                this.big_v = big_v;
            }

            public int getDouble_finger_up() {
                return double_finger_up;
            }

            public void setDouble_finger_up(int double_finger_up) {
                this.double_finger_up = double_finger_up;
            }

            public int getThumb_down() {
                return thumb_down;
            }

            public void setThumb_down(int thumb_down) {
                this.thumb_down = thumb_down;
            }

            public int getFist() {
                return fist;
            }

            public void setFist(int fist) {
                this.fist = fist;
            }

            public int getRock() {
                return rock;
            }

            public void setRock(int rock) {
                this.rock = rock;
            }

            public int getHeart_d() {
                return heart_d;
            }

            public void setHeart_d(int heart_d) {
                this.heart_d = heart_d;
            }

            public int getHand_open() {
                return hand_open;
            }

            public void setHand_open(int hand_open) {
                this.hand_open = hand_open;
            }

            public int getHeart_b() {
                return heart_b;
            }

            public void setHeart_b(int heart_b) {
                this.heart_b = heart_b;
            }

            public int getHeart_c() {
                return heart_c;
            }

            public void setHeart_c(int heart_c) {
                this.heart_c = heart_c;
            }

            public int getVictory() {
                return victory;
            }

            public void setVictory(int victory) {
                this.victory = victory;
            }

            public int getHeart_a() {
                return heart_a;
            }

            public void setHeart_a(int heart_a) {
                this.heart_a = heart_a;
            }

            @Override
            public String toString() {
                return "GestureBean{" +
                        "thumb_up=" + thumb_up +
                        ", namaste=" + namaste +
                        ", ok=" + ok +
                        ", beg=" + beg +
                        ", unknown=" + unknown +
                        ", index_finger_up=" + index_finger_up +
                        ", thanks=" + thanks +
                        ", phonecall=" + phonecall +
                        ", palm_up=" + palm_up +
                        ", big_v=" + big_v +
                        ", double_finger_up=" + double_finger_up +
                        ", thumb_down=" + thumb_down +
                        ", fist=" + fist +
                        ", rock=" + rock +
                        ", heart_d=" + heart_d +
                        ", hand_open=" + hand_open +
                        ", heart_b=" + heart_b +
                        ", heart_c=" + heart_c +
                        ", victory=" + victory +
                        ", heart_a=" + heart_a +
                        '}';
            }
        }

        public static class HandRectangleBean {
            /**
             * width : 964
             * top : 35
             * height : 538
             * left : 0
             */

            private int width;
            private int top;
            private int height;
            private int left;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            @Override
            public String toString() {
                return "HandRectangleBean{" +
                        "left=" + left +
                        ", height=" + height +
                        ", top=" + top +
                        ", width=" + width +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "HandsBean{" +
                    "gesture=" + gesture +
                    ", hand_rectangle=" + hand_rectangle +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GestureInfoBean{" +
                "image_id='" + image_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", time_used=" + time_used +
                ", hands=" + hands +
                '}';
    }
}
