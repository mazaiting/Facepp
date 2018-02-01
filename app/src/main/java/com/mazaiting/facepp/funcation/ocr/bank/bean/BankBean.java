package com.mazaiting.facepp.funcation.ocr.bank.bean;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class BankBean {

    /**
     * bank_cards : [{"bound":{"right_top":{"x":770,"y":20},"left_top":{"x":17,"y":20},"right_bottom":{"x":770,"y":420},"left_bottom":{"x":17,"y":420}},"number":"6225768761224816"}]
     * time_used : 2151
     * request_id : 1473759244,40dfde25-6d1a-4c90-a994-813556c81e30
     */

    private int time_used;
    private String request_id;
    private List<BankCardsBean> bank_cards;

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

    public List<BankCardsBean> getBank_cards() {
        return bank_cards;
    }

    public void setBank_cards(List<BankCardsBean> bank_cards) {
        this.bank_cards = bank_cards;
    }

    public static class BankCardsBean {
        /**
         * bound : {"right_top":{"x":770,"y":20},"left_top":{"x":17,"y":20},"right_bottom":{"x":770,"y":420},"left_bottom":{"x":17,"y":420}}
         * number : 6225768761224816
         */

        private BoundBean bound;
        private String number;

        public BoundBean getBound() {
            return bound;
        }

        public void setBound(BoundBean bound) {
            this.bound = bound;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public static class BoundBean {
            /**
             * right_top : {"x":770,"y":20}
             * left_top : {"x":17,"y":20}
             * right_bottom : {"x":770,"y":420}
             * left_bottom : {"x":17,"y":420}
             */

            private RightTopBean right_top;
            private LeftTopBean left_top;
            private RightBottomBean right_bottom;
            private LeftBottomBean left_bottom;

            public RightTopBean getRight_top() {
                return right_top;
            }

            public void setRight_top(RightTopBean right_top) {
                this.right_top = right_top;
            }

            public LeftTopBean getLeft_top() {
                return left_top;
            }

            public void setLeft_top(LeftTopBean left_top) {
                this.left_top = left_top;
            }

            public RightBottomBean getRight_bottom() {
                return right_bottom;
            }

            public void setRight_bottom(RightBottomBean right_bottom) {
                this.right_bottom = right_bottom;
            }

            public LeftBottomBean getLeft_bottom() {
                return left_bottom;
            }

            public void setLeft_bottom(LeftBottomBean left_bottom) {
                this.left_bottom = left_bottom;
            }

            public static class RightTopBean {
                /**
                 * x : 770
                 * y : 20
                 */

                private int x;
                private int y;

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                @Override
                public String toString() {
                    return "RightTopBean{" +
                            "x=" + x +
                            ", y=" + y +
                            '}';
                }
            }

            public static class LeftTopBean {
                /**
                 * x : 17
                 * y : 20
                 */

                private int x;
                private int y;

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                @Override
                public String toString() {
                    return "LeftTopBean{" +
                            "x=" + x +
                            ", y=" + y +
                            '}';
                }
            }

            public static class RightBottomBean {
                /**
                 * x : 770
                 * y : 420
                 */

                private int x;
                private int y;

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                @Override
                public String toString() {
                    return "RightBottomBean{" +
                            "x=" + x +
                            ", y=" + y +
                            '}';
                }
            }

            public static class LeftBottomBean {
                /**
                 * x : 17
                 * y : 420
                 */

                private int x;
                private int y;

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                @Override
                public String toString() {
                    return "LeftBottomBean{" +
                            "x=" + x +
                            ", y=" + y +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "BoundBean{" +
                        "right_top=" + right_top +
                        ", left_top=" + left_top +
                        ", right_bottom=" + right_bottom +
                        ", left_bottom=" + left_bottom +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "BankCardsBean{" +
                    "bound=" + bound +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BankBean{" +
                "time_used=" + time_used +
                ", request_id='" + request_id + '\'' +
                ", bank_cards=" + bank_cards +
                '}';
    }
}

