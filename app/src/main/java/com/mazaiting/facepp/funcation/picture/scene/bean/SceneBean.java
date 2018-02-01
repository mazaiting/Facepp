package com.mazaiting.facepp.funcation.picture.scene.bean;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class SceneBean {

    /**
     * time_used : 1455
     * scenes : [{"confidence":20.888,"value":"Alcazar"},{"confidence":20.87,"value":"Temple"}]
     * image_id : +zFOHDK2c1tp948KxVxOsA==
     * objects : [{"confidence":20.888,"value":"Alcazar"},{"confidence":20.87,"value":"Temple"}]
     * request_id : 1473763608,d04a67e3-0bf8-4fdf-93d2-33100ea8654b
     */

    private int time_used;
    private String image_id;
    private String request_id;
    private List<ScenesBean> scenes;
    private List<ObjectsBean> objects;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

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

    public List<ScenesBean> getScenes() {
        return scenes;
    }

    public void setScenes(List<ScenesBean> scenes) {
        this.scenes = scenes;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ScenesBean {
        /**
         * confidence : 20.888
         * value : Alcazar
         */

        private double confidence;
        private String value;

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ScenesBean{" +
                    "confidence=" + confidence +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class ObjectsBean {
        /**
         * confidence : 20.888
         * value : Alcazar
         */

        private double confidence;
        private String value;

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ObjectsBean{" +
                    "confidence=" + confidence +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SceneBean{" +
                "time_used=" + time_used +
                ", image_id='" + image_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", scenes=" + scenes +
                ", objects=" + objects +
                '}';
    }
}
