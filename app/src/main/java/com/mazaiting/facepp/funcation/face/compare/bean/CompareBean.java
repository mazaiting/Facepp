package com.mazaiting.facepp.funcation.face.compare.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/1/31
 */

public class CompareBean {

    /**
     * faces1 : [{"face_rectangle":{"width":203,"top":159,"left":105,"height":203},"face_token":"0ad6cbf71fd7ec3054f3b09fab118a52"}]
     * faces2 : [{"face_rectangle":{"width":209,"top":213,"left":98,"height":209},"face_token":"ddd73946106182bb26cd8a3ebbdcdcb9"}]
     * time_used : 795
     * thresholds : {"1e-3":62.327,"1e-5":73.975,"1e-4":69.101}
     * confidence : 84.725
     * image_id2 : Ty4wcGFEhpo7AdsHZwJqKw==
     * image_id1 : vpnxSJgaiOlF5xPhN9KrPA==
     * request_id : 1517390113,08795493-24a9-47c7-acc3-c283091aa5d5
     */

    private int time_used;
    private ThresholdsBean thresholds;
    private double confidence;
    private String image_id2;
    private String image_id1;
    private String request_id;
    private List<Faces1Bean> faces1;
    private List<Faces2Bean> faces2;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public ThresholdsBean getThresholds() {
        return thresholds;
    }

    public void setThresholds(ThresholdsBean thresholds) {
        this.thresholds = thresholds;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getImage_id2() {
        return image_id2;
    }

    public void setImage_id2(String image_id2) {
        this.image_id2 = image_id2;
    }

    public String getImage_id1() {
        return image_id1;
    }

    public void setImage_id1(String image_id1) {
        this.image_id1 = image_id1;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<Faces1Bean> getFaces1() {
        return faces1;
    }

    public void setFaces1(List<Faces1Bean> faces1) {
        this.faces1 = faces1;
    }

    public List<Faces2Bean> getFaces2() {
        return faces2;
    }

    public void setFaces2(List<Faces2Bean> faces2) {
        this.faces2 = faces2;
    }

    public static class ThresholdsBean {
        /**
         * 1e-3 : 62.327
         * 1e-5 : 73.975
         * 1e-4 : 69.101
         */

        @SerializedName("1e-3")
        private double _$1e3;
        @SerializedName("1e-5")
        private double _$1e5;
        @SerializedName("1e-4")
        private double _$1e4;

        public double get_$1e3() {
            return _$1e3;
        }

        public void set_$1e3(double _$1e3) {
            this._$1e3 = _$1e3;
        }

        public double get_$1e5() {
            return _$1e5;
        }

        public void set_$1e5(double _$1e5) {
            this._$1e5 = _$1e5;
        }

        public double get_$1e4() {
            return _$1e4;
        }

        public void set_$1e4(double _$1e4) {
            this._$1e4 = _$1e4;
        }
    }

    public static class Faces1Bean {
        /**
         * face_rectangle : {"width":203,"top":159,"left":105,"height":203}
         * face_token : 0ad6cbf71fd7ec3054f3b09fab118a52
         */

        private FaceRectangleBean face_rectangle;
        private String face_token;

        public FaceRectangleBean getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceRectangleBean face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public static class FaceRectangleBean {
            /**
             * width : 203
             * top : 159
             * left : 105
             * height : 203
             */

            private int width;
            private int top;
            private int left;
            private int height;

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

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }

    public static class Faces2Bean {
        /**
         * face_rectangle : {"width":209,"top":213,"left":98,"height":209}
         * face_token : ddd73946106182bb26cd8a3ebbdcdcb9
         */

        private FaceRectangleBeanX face_rectangle;
        private String face_token;

        public FaceRectangleBeanX getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceRectangleBeanX face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public static class FaceRectangleBeanX {
            /**
             * width : 209
             * top : 213
             * left : 98
             * height : 209
             */

            private int width;
            private int top;
            private int left;
            private int height;

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

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
