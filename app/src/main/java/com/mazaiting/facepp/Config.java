package com.mazaiting.facepp;

/**
 * @author mazaiting
 * @date 2018/1/30
 */

public class Config {
    /**秘钥*/
    public static final String API_KEY = "rv5Pab4EsmqWFft6tBKK5GqvsBGerHeb";
    public static final String API_SECRET = "OVNhOVgsxOhq4wsYzSj7-88ZD1BO3vHP";
    public static final String TOKEN = "token";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String BASE_URL = "https://api-cn.faceplusplus.com/";
    /**
     * ===========================================================================================
     * =========================================== 人 脸 检 测 =====================================
     * ===========================================================================================
     */
    /**人脸检测 detect*/
    public static final String FACE_DETECT_FILE = "/sdcard/face/detect.png";
    public static final String FACE_DETECT_NET_FILE = "http://pic1.hebei.com.cn/003/005/869/00300586905_449eedbb.jpg";
    public static final String FACE_DETECT_ATTRIBUTES = "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus";
    /**人脸比较*/
    public static final String FACE_COMPARE_FILE1 = "/sdcard/face/compare1.png";
    public static final String FACE_COMPARE_FILE2 = "/sdcard/face/compare2.png";

    /**
     * ===========================================================================================
     * =========================================== 人 体 检 测 =====================================
     * ===========================================================================================
     */
    /**人体检测BaseUrl*/
    public static final String HUMAN_BODY_API = "https://api-cn.faceplusplus.com/";
    /**人体检测*/
    public static final String HUMAN_BODY_DETECT_FILE = "/sdcard/face/humanbody.png";
    public static final String HUMAN_BODY_DETECT_ATTR = "gender,upper_body_cloth,lower_body_cloth";
    /**人体轮廓识别*/
    public static final String HUMAN_BODY_SEGMENT_FILE = "/sdcard/face/humanbody_segment.png";
    public static final String HUMAN_BODY_SEGMENT_TEMP_FILE = "/sdcard/face/humanbody_segment_temp.png";
    /**手势识别*/
    public static final String HUMAN_BODY_GESTURE_FILE = "/sdcard/face/gesture.png";
    /**身份证识别*/
    public static final String OCR_IC_FILE = "/sdcard/face/ocr_id.png";
    /**驾驶证识别*/
    public static final String OCR_DRIVER_FILE = "/sdcard/face/ocr_driver.png";
    /**行驶证识别*/
    public static final String OCR_VEHICLE_FILE = "/sdcard/face/ocr_vehicle.png";
    /**银行卡识别*/
    public static final String OCR_BANK_FILE = "/sdcard/face/ocr_bank.png";
    /**场景及物体检测*/
    public static final String PIC_SCENE_OBJECT_FILE = "/sdcard/face/pic_scene_object.png";
    /**文字识别*/
    public static final String PIC_TEXT_FILE = "/sdcard/face/pic_text.png";




}
