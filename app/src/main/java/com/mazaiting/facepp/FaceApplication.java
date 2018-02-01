package com.mazaiting.facepp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.mazaiting.facepp.utils.FileUtil;

/**
 * @author mazaiting
 * @date 2018/1/31
 */

public class FaceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 人脸检测
                FileUtil.copyFile(getApplicationContext(), "detect.png", Config.FACE_DETECT_FILE);
                // 人脸比较
                FileUtil.copyFile(getApplicationContext(), "compare1.jpg", Config.FACE_COMPARE_FILE1);
                FileUtil.copyFile(getApplicationContext(), "compare2.jpg", Config.FACE_COMPARE_FILE2);
                // 人体检测
                FileUtil.copyFile(getApplicationContext(), "humanbody.jpg", Config.HUMAN_BODY_DETECT_FILE);
                FileUtil.copyFile(getApplicationContext(), "humanbody_segment.jpg", Config.HUMAN_BODY_SEGMENT_FILE);
                // 手势识别
                FileUtil.copyFile(getApplicationContext(), "gesture.jpg", Config.HUMAN_BODY_GESTURE_FILE);
                // 身份证识别
                FileUtil.copyFile(getApplicationContext(), "ocr_id.jpg", Config.OCR_IC_FILE);
                // 驾驶证识别
                FileUtil.copyFile(getApplicationContext(), "ocr_driver.jpg", Config.OCR_DRIVER_FILE);
                // 行驶证识别
                FileUtil.copyFile(getApplicationContext(), "ocr_vehicle.jpg", Config.OCR_VEHICLE_FILE);
                // 银行卡识别
                FileUtil.copyFile(getApplicationContext(), "ocr_bank.jpg", Config.OCR_BANK_FILE);
                // 图片及场景识别
                FileUtil.copyFile(getApplicationContext(), "pic_scene_object.jpg", Config.PIC_SCENE_OBJECT_FILE);
                // 文字识别
                FileUtil.copyFile(getApplicationContext(), "pic_text.jpg", Config.PIC_TEXT_FILE);
            }
        }).start();
    }
}
