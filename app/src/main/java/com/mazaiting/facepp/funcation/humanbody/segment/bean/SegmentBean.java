package com.mazaiting.facepp.funcation.humanbody.segment.bean;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class SegmentBean {

    /**
     * image_id : 7OO7N1dYiJjszvV38oKVpw==
     * result : /9j/2wCEAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYn.............s3/AOWq/nUjeOLNesq/ufWo3YmhDVhScV/9k=
     * request_id : 1470378968,c6f50ec6-49bd-4838-9923-11db04c40f8d
     * time_used : 573
     */

    private String image_id;
    private String result;
    private String request_id;
    private int time_used;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    @Override
    public String toString() {
        return "SegmentBean{" +
                "image_id='" + image_id + '\'' +
                ", result='" + result + '\'' +
                ", request_id='" + request_id + '\'' +
                ", time_used=" + time_used +
                '}';
    }
}
