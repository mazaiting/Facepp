package com.mazaiting.facepp.funcation.humanbody.detect.bean;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class HumanDetectBean {

    /**
     * image_id : k5paQaZ/1SXMGqzbVYWKGw==
     * request_id : 1517455569,2fb3d3ff-5221-4f70-92a5-6bd7ba58dfd0
     * time_used : 2546
     * humanbodies : [{"attributes":{"gender":{"male":1.858,"female":98.142},"lower_body_cloth":{"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":64,"b":29,"g":35}},"upper_body_cloth":{"upper_body_cloth_color_rgb":{"r":9,"b":12,"g":9},"upper_body_cloth_color":"black"}},"humanbody_rectangle":{"width":150,"top":160,"height":494,"left":136},"confidence":99.856},{"attributes":{"gender":{"male":81.587,"female":18.413},"lower_body_cloth":{"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":9,"b":12,"g":9}},"upper_body_cloth":{"upper_body_cloth_color_rgb":{"r":232,"b":243,"g":232},"upper_body_cloth_color":"white"}},"humanbody_rectangle":{"width":311,"top":142,"height":483,"left":549},"confidence":99.492},{"attributes":{"gender":{"male":97.555,"female":2.445},"lower_body_cloth":{"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":25,"b":38,"g":31}},"upper_body_cloth":{"upper_body_cloth_color_rgb":{"r":239,"b":243,"g":232},"upper_body_cloth_color":"white"}},"humanbody_rectangle":{"width":263,"top":170,"height":504,"left":298},"confidence":99.36},{"attributes":{"gender":{"male":0.814,"female":99.186},"lower_body_cloth":{"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":9,"b":12,"g":8}},"upper_body_cloth":{"upper_body_cloth_color_rgb":{"r":8,"b":12,"g":8},"upper_body_cloth_color":"black"}},"humanbody_rectangle":{"width":160,"top":175,"height":505,"left":866},"confidence":97.584}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<HumanbodiesBean> humanbodies;

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

    public List<HumanbodiesBean> getHumanbodies() {
        return humanbodies;
    }

    public void setHumanbodies(List<HumanbodiesBean> humanbodies) {
        this.humanbodies = humanbodies;
    }

    public static class HumanbodiesBean {
        /**
         * attributes : {"gender":{"male":1.858,"female":98.142},"lower_body_cloth":{"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":64,"b":29,"g":35}},"upper_body_cloth":{"upper_body_cloth_color_rgb":{"r":9,"b":12,"g":9},"upper_body_cloth_color":"black"}}
         * humanbody_rectangle : {"width":150,"top":160,"height":494,"left":136}
         * confidence : 99.856
         */

        private AttributesBean attributes;
        private HumanbodyRectangleBean humanbody_rectangle;
        private double confidence;

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

        public HumanbodyRectangleBean getHumanbody_rectangle() {
            return humanbody_rectangle;
        }

        public void setHumanbody_rectangle(HumanbodyRectangleBean humanbody_rectangle) {
            this.humanbody_rectangle = humanbody_rectangle;
        }

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public static class AttributesBean {
            /**
             * gender : {"male":1.858,"female":98.142}
             * lower_body_cloth : {"lower_body_cloth_color":"black","lower_body_cloth_color_rgb":{"r":64,"b":29,"g":35}}
             * upper_body_cloth : {"upper_body_cloth_color_rgb":{"r":9,"b":12,"g":9},"upper_body_cloth_color":"black"}
             */

            private GenderBean gender;
            private LowerBodyClothBean lower_body_cloth;
            private UpperBodyClothBean upper_body_cloth;

            public GenderBean getGender() {
                return gender;
            }

            public void setGender(GenderBean gender) {
                this.gender = gender;
            }

            public LowerBodyClothBean getLower_body_cloth() {
                return lower_body_cloth;
            }

            public void setLower_body_cloth(LowerBodyClothBean lower_body_cloth) {
                this.lower_body_cloth = lower_body_cloth;
            }

            public UpperBodyClothBean getUpper_body_cloth() {
                return upper_body_cloth;
            }

            public void setUpper_body_cloth(UpperBodyClothBean upper_body_cloth) {
                this.upper_body_cloth = upper_body_cloth;
            }

            public static class GenderBean {
                /**
                 * male : 1.858
                 * female : 98.142
                 */

                private double male;
                private double female;

                public double getMale() {
                    return male;
                }

                public void setMale(double male) {
                    this.male = male;
                }

                public double getFemale() {
                    return female;
                }

                public void setFemale(double female) {
                    this.female = female;
                }

                @Override
                public String toString() {
                    return "GenderBean{" +
                            "male=" + male +
                            ", female=" + female +
                            '}';
                }
            }

            public static class LowerBodyClothBean {
                /**
                 * lower_body_cloth_color : black
                 * lower_body_cloth_color_rgb : {"r":64,"b":29,"g":35}
                 */

                private String lower_body_cloth_color;
                private LowerBodyClothColorRgbBean lower_body_cloth_color_rgb;

                public String getLower_body_cloth_color() {
                    return lower_body_cloth_color;
                }

                public void setLower_body_cloth_color(String lower_body_cloth_color) {
                    this.lower_body_cloth_color = lower_body_cloth_color;
                }

                public LowerBodyClothColorRgbBean getLower_body_cloth_color_rgb() {
                    return lower_body_cloth_color_rgb;
                }

                public void setLower_body_cloth_color_rgb(LowerBodyClothColorRgbBean lower_body_cloth_color_rgb) {
                    this.lower_body_cloth_color_rgb = lower_body_cloth_color_rgb;
                }

                public static class LowerBodyClothColorRgbBean {
                    /**
                     * r : 64
                     * b : 29
                     * g : 35
                     */

                    private int r;
                    private int b;
                    private int g;

                    public int getR() {
                        return r;
                    }

                    public void setR(int r) {
                        this.r = r;
                    }

                    public int getB() {
                        return b;
                    }

                    public void setB(int b) {
                        this.b = b;
                    }

                    public int getG() {
                        return g;
                    }

                    public void setG(int g) {
                        this.g = g;
                    }

                    @Override
                    public String toString() {
                        return "LowerBodyClothColorRgbBean{" +
                                "r=" + r +
                                ", b=" + b +
                                ", g=" + g +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "LowerBodyClothBean{" +
                            "lower_body_cloth_color='" + lower_body_cloth_color + '\'' +
                            ", lower_body_cloth_color_rgb=" + lower_body_cloth_color_rgb +
                            '}';
                }
            }

            public static class UpperBodyClothBean {
                /**
                 * upper_body_cloth_color_rgb : {"r":9,"b":12,"g":9}
                 * upper_body_cloth_color : black
                 */

                private UpperBodyClothColorRgbBean upper_body_cloth_color_rgb;
                private String upper_body_cloth_color;

                public UpperBodyClothColorRgbBean getUpper_body_cloth_color_rgb() {
                    return upper_body_cloth_color_rgb;
                }

                public void setUpper_body_cloth_color_rgb(UpperBodyClothColorRgbBean upper_body_cloth_color_rgb) {
                    this.upper_body_cloth_color_rgb = upper_body_cloth_color_rgb;
                }

                public String getUpper_body_cloth_color() {
                    return upper_body_cloth_color;
                }

                public void setUpper_body_cloth_color(String upper_body_cloth_color) {
                    this.upper_body_cloth_color = upper_body_cloth_color;
                }

                public static class UpperBodyClothColorRgbBean {
                    /**
                     * r : 9
                     * b : 12
                     * g : 9
                     */

                    private int r;
                    private int b;
                    private int g;

                    public int getR() {
                        return r;
                    }

                    public void setR(int r) {
                        this.r = r;
                    }

                    public int getB() {
                        return b;
                    }

                    public void setB(int b) {
                        this.b = b;
                    }

                    public int getG() {
                        return g;
                    }

                    public void setG(int g) {
                        this.g = g;
                    }

                    @Override
                    public String toString() {
                        return "UpperBodyClothColorRgbBean{" +
                                "r=" + r +
                                ", b=" + b +
                                ", g=" + g +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "UpperBodyClothBean{" +
                            "upper_body_cloth_color_rgb=" + upper_body_cloth_color_rgb +
                            ", upper_body_cloth_color='" + upper_body_cloth_color + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "AttributesBean{" +
                        "gender=" + gender +
                        ", lower_body_cloth=" + lower_body_cloth +
                        ", upper_body_cloth=" + upper_body_cloth +
                        '}';
            }
        }

        public static class HumanbodyRectangleBean {
            /**
             * width : 150
             * top : 160
             * height : 494
             * left : 136
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
                return "HumanbodyRectangleBean{" +
                        "width=" + width +
                        ", top=" + top +
                        ", height=" + height +
                        ", left=" + left +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "HumanbodiesBean{" +
                    "attributes=" + attributes +
                    ", humanbody_rectangle=" + humanbody_rectangle +
                    ", confidence=" + confidence +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HumanDetectBean{" +
                "image_id='" + image_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", time_used=" + time_used +
                ", humanbodies=" + humanbodies +
                '}';
    }
}
