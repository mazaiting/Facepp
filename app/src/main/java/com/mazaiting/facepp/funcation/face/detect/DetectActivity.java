package com.mazaiting.facepp.funcation.face.detect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.face.detect.bean.DetectBean;
import com.mazaiting.facepp.net.HttpManager;
import com.mazaiting.facepp.funcation.face.detect.service.DetectApi;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * 人脸检测界面
 *
 * @author mazaiting
 */
public class DetectActivity extends AppCompatActivity {
    private static final String TAG = "HumanDetectActivity";
    @InjectView(R.id.imageView)
    ImageView mImageView;
    private DetectApi mDetectApi;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mDetectApi = retrofit.create(DetectApi.class);
    }

    @OnClick({R.id.btnNative, R.id.btnNativeBase, R.id.btnNet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNative:
                loadNativePic();
                break;
            case R.id.btnNativeBase:
                loadNativeBasePic();
                break;
            case R.id.btnNet:
                loadNetPic();
                break;
            default:
                break;
        }
    }

    /**
     * 检测Base64编码的图片
     */
    private void loadNativeBasePic() {
        Picasso.with(this).load(Config.FACE_DETECT_FILE).into(mImageView);

        Bitmap bitmap = BitmapFactory.decodeFile(Config.FACE_DETECT_FILE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //读取图片到ByteArrayOutputStream
        //参数如果为100那么就不压缩
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, baos);
        byte[] bytes = baos.toByteArray();
        String strbm = Base64.encodeToString(bytes, Base64.DEFAULT);

        Observable<DetectBean> observable = mDetectApi.getDetectBase64(Config.API_KEY, Config.API_SECRET, strbm);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetectBean>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull DetectBean detectBean) throws Exception {
                        Log.e(TAG, "accept: " + detectBean.toString());
                    }
                });
    }

    /**
     * 检测本地图片
     */
    private void loadNativePic() {
        Picasso.with(this).load(Config.FACE_DETECT_FILE).into(mImageView);
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        MultipartBody.Part return_landmark = MultipartBody.Part.createFormData("return_landmark", "2");
        MultipartBody.Part return_attributes = MultipartBody.Part.createFormData("return_attributes", Config.FACE_DETECT_ATTRIBUTES);
        File file = new File(Config.FACE_DETECT_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<DetectBean> observable = mDetectApi.getDetectFile(api_key, api_secret, image_file, return_landmark, return_attributes);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetectBean>() {
                    @Override
                    public void accept(@NonNull DetectBean detectBean) throws Exception {
                        Bitmap bitmap = BitmapFactory.decodeFile(Config.FACE_DETECT_FILE);
                        drawFace(bitmap, detectBean);
                    }
                });
    }

    /**
     * 检测网络图片
     */
    private void loadNetPic() {
        Observable<DetectBean> observable = mDetectApi.getDetectUrl(Config.API_KEY, Config.API_SECRET, Config.FACE_DETECT_NET_FILE, 1, Config.FACE_DETECT_ATTRIBUTES);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetectBean>() {
                               @Override
                               public void accept(@NonNull final DetectBean detectBean) throws Exception {
                                   /**
                                    * {
                                    "image_id": "Cz8grfhcqJUW/UPs2JTZbg==",
                                    "request_id": "1517308512,f274b3dc-7cf7-44f3-bb7a-4bf1a55ece27",
                                    "time_used": 230,
                                    "faces": [{
                                    "face_rectangle": {
                                    "width": 103,
                                    "top": 89,
                                    "left": 157,
                                    "height": 103
                                    },
                                    "face_token": "7e884db94f1c47fcab4052cffc786bc9"
                                    }, {
                                    "face_rectangle": {
                                    "width": 51,
                                    "top": 6,
                                    "left": 100,
                                    "height": 51
                                    },
                                    "face_token": "c3c8f788d94d47bac56515ab4c16dba4"
                                    }]
                                    }
                                    */
//                                   final Bitmap bitmap = Picasso.with(HumanDetectActivity.this).load(Config.FACE_DETECT_NET_FILE).get();

                                   new Thread(new Runnable() {
                                       @Override
                                       public void run() {
                                           final Bitmap bitmap = returnBitMap(Config.FACE_DETECT_NET_FILE);

                                           runOnUiThread(new Runnable() {
                                               @Override
                                               public void run() {
                                                   drawFace(bitmap, detectBean);
                                               }
                                           });
                                       }
                                   }).start();
                               }
                           }
                );
    }

    /**
     * 画脸
     * @param bitmap 原图bitmap
     * @param detectBean bean
     */
    private void drawFace(Bitmap bitmap, @NonNull DetectBean detectBean) {
        final Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(newBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        // 画矩形框
        List<DetectBean.FacesBean> faces = detectBean.getFaces();
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        for (DetectBean.FacesBean faceBean : faces) {
            DetectBean.FacesBean.FaceRectangleBean faceRectangle = faceBean.getFace_rectangle();
            float left = faceRectangle.getLeft();
            float top = faceRectangle.getTop();
            float right = (faceRectangle.getWidth() + left);
            float bottom = (faceRectangle.getHeight() + top);

            mCanvas.drawRect(left, top, right, bottom, mPaint);

            // 绘制特征点
            drawCircle(faceBean.getLandmark());
            // 打印相关信息
            printInfo(faceBean.getAttributes());
        }

        mImageView.setImageBitmap(newBitmap);
    }

    /**
     * 打印相关属性
     * @param attributes 属性
     */
    private void printInfo(DetectBean.FacesBean.AttributesBean attributes) {
        if (null != attributes) {
            Log.e(TAG, "printInfo: 年龄：" + attributes.getAge().getValue() + "\n"
                    + "性别：" + attributes.getGender().getValue() + "\n"
                    + "笑容：" + attributes.getSmile().getValue() + "，阈值：" + attributes.getSmile().getThreshold() + "\n"
                    + "人脸姿势：抬头-" + attributes.getHeadpose().getPitch_angle() + ", 旋转（平面旋转）-" + attributes.getHeadpose().getRoll_angle() + ",摇头-" + attributes.getHeadpose().getYaw_angle() + "\n"
                    + "模糊度：人脸移动模糊度: " + attributes.getBlur().getMotionblur().getValue() + "，阈值：" + attributes.getBlur().getMotionblur().getThreshold()
                    + "，人脸高斯模糊度: " + attributes.getBlur().getGaussianblur().getValue() + "，阈值：" + attributes.getBlur().getGaussianblur().getThreshold()
                    + "，新的人脸模糊: " + attributes.getBlur().getBlurness().getValue() + "，阈值：" + attributes.getBlur().getBlurness().getThreshold()
                    + "\n"
                    + "眼睛状态：左眼: 眼睛被遮挡的置信度-" + attributes.getEyestatus().getLeft_eye_status().getOcclusion()
                    + "，不戴眼镜且睁眼的置信度-" + attributes.getEyestatus().getLeft_eye_status().getNo_glass_eye_open()
                    + "，佩戴普通眼镜且闭眼的置信度-" + attributes.getEyestatus().getLeft_eye_status().getNormal_glass_eye_close()
                    + "，佩戴普通眼镜且睁眼的置信度-" + attributes.getEyestatus().getLeft_eye_status().getNormal_glass_eye_open()
                    + "，佩戴墨镜的置信度-" + attributes.getEyestatus().getLeft_eye_status().getDark_glasses()
                    + "，不戴眼镜且闭眼的置信度-" + attributes.getEyestatus().getLeft_eye_status().getNo_glass_eye_close()
                    + "右眼: 眼睛被遮挡的置信度-" + attributes.getEyestatus().getRight_eye_status().getOcclusion()
                    + "，不戴眼镜且睁眼的置信度-" + attributes.getEyestatus().getRight_eye_status().getNo_glass_eye_open()
                    + "，佩戴普通眼镜且闭眼的置信度-" + attributes.getEyestatus().getRight_eye_status().getNormal_glass_eye_close()
                    + "，佩戴普通眼镜且睁眼的置信度-" + attributes.getEyestatus().getRight_eye_status().getNormal_glass_eye_open()
                    + "，佩戴墨镜的置信度-" + attributes.getEyestatus().getRight_eye_status().getDark_glasses()
                    + "，不戴眼镜且闭眼的置信度-" + attributes.getEyestatus().getRight_eye_status().getNo_glass_eye_close()
                    + "\n"
                    + "表情: 愤怒-" + attributes.getEmotion().getAnger()
                    + "，厌恶-" + attributes.getEmotion().getDisgust()
                    + "，恐惧-" + attributes.getEmotion().getFear()
                    + "，高兴-" + attributes.getEmotion().getHappiness()
                    + "，平静-" + attributes.getEmotion().getNeutral()
                    + "，伤心-" + attributes.getEmotion().getSadness()
                    + "，惊讶-" + attributes.getEmotion().getSurprise()
                    + "\n"
                    + "人脸质量：" + attributes.getFacequality().getValue() + "，阈值：" + attributes.getFacequality().getThreshold() + "\n"
                    // 人种Asian	亚洲人；White	白人；Black	黑人
                    + "人种：" + attributes.getEthnicity().getValue() + "\n"
                    + "颜值识别: 男性分数： " + attributes.getBeauty().getMale_score()
                    + "，女性分数： " + attributes.getBeauty().getFemale_score()
                    + "\n"
                    + "嘴部状态：嘴部被医用口罩或呼吸面罩遮挡的置信度：" + attributes.getMouthstatus().getSurgical_mask_or_respirator()
                    + "，嘴部被其他物体遮挡的置信度： " + attributes.getMouthstatus().getOther_occlusion()
                    + "，嘴部没有遮挡且闭上的置信度： " + attributes.getMouthstatus().getClose()
                    + "，嘴部没有遮挡且张开的置信度： " + attributes.getMouthstatus().getOpen()
                    + "\n"
                    + "眼球位置与方向：左眼位置与状态：：眼球中心位置的 X 轴坐标: " + attributes.getEyegaze().getLeft_eye_gaze().getPosition_x_coordinate()
                    + "，眼球中心位置的 Y 轴坐标： " + attributes.getEyegaze().getLeft_eye_gaze().getPosition_y_coordinate()
                    + "，眼球视线方向向量的 X 轴分量： " + attributes.getEyegaze().getLeft_eye_gaze().getVector_x_component()
                    + "，眼球视线方向向量的 Y 轴分量： " + attributes.getEyegaze().getLeft_eye_gaze().getVector_y_component()
                    + "，眼球视线方向向量的 Z 轴分量： " + attributes.getEyegaze().getLeft_eye_gaze().getVector_z_component()
                    + ", 右眼位置与状态：：眼球中心位置的 X 轴坐标: " + attributes.getEyegaze().getRight_eye_gaze().getPosition_x_coordinate()
                    + "，眼球中心位置的 Y 轴坐标： " + attributes.getEyegaze().getRight_eye_gaze().getPosition_y_coordinate()
                    + "，眼球视线方向向量的 X 轴分量： " + attributes.getEyegaze().getRight_eye_gaze().getVector_x_component()
                    + "，眼球视线方向向量的 Y 轴分量： " + attributes.getEyegaze().getRight_eye_gaze().getVector_y_component()
                    + "，眼球视线方向向量的 Z 轴分量： " + attributes.getEyegaze().getRight_eye_gaze().getVector_z_component()
                    + "\n"
                    + "面部特征识别：健康： " + attributes.getSkinstatus().getHealth()
                    + "，色斑： " + attributes.getSkinstatus().getStain()
                    + "，青春痘： " + attributes.getSkinstatus().getAcne()
                    + "，黑眼圈： " + attributes.getSkinstatus().getDark_circle()
                    + "\n"
            );
        }
    }

    /**
     * 绘制特征点
     * @param landmark
     */
    private void drawCircle(DetectBean.FacesBean.LandmarkBean landmark) {
        if (null != landmark) {
            // 下巴-1
            if (null != landmark.getContour_chin()) {
                mCanvas.drawCircle(landmark.getContour_chin().getX(), landmark.getContour_chin().getY(), 1, mPaint);
            }
            // 左侧-16
            if (null != landmark.getContour_left1()) {
                mCanvas.drawCircle(landmark.getContour_left1().getX(), landmark.getContour_left1().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left2()) {
                mCanvas.drawCircle(landmark.getContour_left2().getX(), landmark.getContour_left2().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left3()) {
                mCanvas.drawCircle(landmark.getContour_left3().getX(), landmark.getContour_left3().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left4()) {
                mCanvas.drawCircle(landmark.getContour_left4().getX(), landmark.getContour_left4().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left5()) {
                mCanvas.drawCircle(landmark.getContour_left5().getX(), landmark.getContour_left5().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left6()) {
                mCanvas.drawCircle(landmark.getContour_left6().getX(), landmark.getContour_left6().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left7()) {
                mCanvas.drawCircle(landmark.getContour_left7().getX(), landmark.getContour_left7().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left8()) {
                mCanvas.drawCircle(landmark.getContour_left8().getX(), landmark.getContour_left8().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left9()) {
                mCanvas.drawCircle(landmark.getContour_left9().getX(), landmark.getContour_left9().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left10()) {
                mCanvas.drawCircle(landmark.getContour_left10().getX(), landmark.getContour_left10().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left1()) {
                mCanvas.drawCircle(landmark.getContour_left1().getX(), landmark.getContour_left1().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left11()) {
                mCanvas.drawCircle(landmark.getContour_left11().getX(), landmark.getContour_left11().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left12()) {
                mCanvas.drawCircle(landmark.getContour_left12().getX(), landmark.getContour_left12().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left13()) {
                mCanvas.drawCircle(landmark.getContour_left13().getX(), landmark.getContour_left13().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left14()) {
                mCanvas.drawCircle(landmark.getContour_left14().getX(), landmark.getContour_left14().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left15()) {
                mCanvas.drawCircle(landmark.getContour_left15().getX(), landmark.getContour_left15().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_left16()) {
                mCanvas.drawCircle(landmark.getContour_left16().getX(), landmark.getContour_left16().getY(), 1, mPaint);
            }
            // 右侧-16
            if (null != landmark.getContour_right1()) {
                mCanvas.drawCircle(landmark.getContour_right1().getX(), landmark.getContour_right1().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right1()) {
                mCanvas.drawCircle(landmark.getContour_right1().getX(), landmark.getContour_right1().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right2()) {
                mCanvas.drawCircle(landmark.getContour_right2().getX(), landmark.getContour_right2().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right3()) {
                mCanvas.drawCircle(landmark.getContour_right3().getX(), landmark.getContour_right3().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right4()) {
                mCanvas.drawCircle(landmark.getContour_right4().getX(), landmark.getContour_right4().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right5()) {
                mCanvas.drawCircle(landmark.getContour_right5().getX(), landmark.getContour_right5().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right6()) {
                mCanvas.drawCircle(landmark.getContour_right6().getX(), landmark.getContour_right6().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right7()) {
                mCanvas.drawCircle(landmark.getContour_right7().getX(), landmark.getContour_right7().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right8()) {
                mCanvas.drawCircle(landmark.getContour_right8().getX(), landmark.getContour_right8().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right9()) {
                mCanvas.drawCircle(landmark.getContour_right9().getX(), landmark.getContour_right9().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right10()) {
                mCanvas.drawCircle(landmark.getContour_right10().getX(), landmark.getContour_right10().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right11()) {
                mCanvas.drawCircle(landmark.getContour_right11().getX(), landmark.getContour_right11().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right12()) {
                mCanvas.drawCircle(landmark.getContour_right12().getX(), landmark.getContour_right12().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right13()) {
                mCanvas.drawCircle(landmark.getContour_right13().getX(), landmark.getContour_right13().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right14()) {
                mCanvas.drawCircle(landmark.getContour_right14().getX(), landmark.getContour_right14().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right15()) {
                mCanvas.drawCircle(landmark.getContour_right15().getX(), landmark.getContour_right15().getY(), 1, mPaint);
            }
            if (null != landmark.getContour_right16()) {
                mCanvas.drawCircle(landmark.getContour_right16().getX(), landmark.getContour_right16().getY(), 1, mPaint);
            }
            // 左眼-10
            if (null != landmark.getLeft_eye_bottom()) {
                mCanvas.drawCircle(landmark.getLeft_eye_bottom().getX(), landmark.getLeft_eye_bottom().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_center()) {
                mCanvas.drawCircle(landmark.getLeft_eye_center().getX(), landmark.getLeft_eye_center().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_left_corner()) {
                mCanvas.drawCircle(landmark.getLeft_eye_left_corner().getX(), landmark.getLeft_eye_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_lower_left_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eye_lower_left_quarter().getX(), landmark.getLeft_eye_lower_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_lower_right_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eye_lower_right_quarter().getX(), landmark.getLeft_eye_lower_right_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_pupil()) {
                mCanvas.drawCircle(landmark.getLeft_eye_pupil().getX(), landmark.getLeft_eye_pupil().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_right_corner()) {
                mCanvas.drawCircle(landmark.getLeft_eye_right_corner().getX(), landmark.getLeft_eye_right_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_top()) {
                mCanvas.drawCircle(landmark.getLeft_eye_top().getX(), landmark.getLeft_eye_top().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_upper_left_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eye_upper_left_quarter().getX(), landmark.getLeft_eye_upper_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eye_upper_right_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eye_upper_right_quarter().getX(), landmark.getLeft_eye_upper_right_quarter().getY(), 1, mPaint);
            }
            // 左眼睫毛-9
            if (null != landmark.getLeft_eyebrow_left_corner()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_left_corner().getX(), landmark.getLeft_eyebrow_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_lower_left_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_lower_left_quarter().getX(), landmark.getLeft_eyebrow_lower_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_lower_middle()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_lower_middle().getX(), landmark.getLeft_eyebrow_lower_middle().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_lower_right_corner()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_lower_right_corner().getX(), landmark.getLeft_eyebrow_lower_right_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_lower_right_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_lower_right_quarter().getX(), landmark.getLeft_eyebrow_lower_right_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_upper_left_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_upper_left_quarter().getX(), landmark.getLeft_eyebrow_upper_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_upper_middle()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_upper_middle().getX(), landmark.getLeft_eyebrow_upper_middle().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_upper_right_corner()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_upper_right_corner().getX(), landmark.getLeft_eyebrow_upper_right_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getLeft_eyebrow_upper_right_quarter()) {
                mCanvas.drawCircle(landmark.getLeft_eyebrow_upper_right_quarter().getX(), landmark.getLeft_eyebrow_upper_right_quarter().getY(), 1, mPaint);
            }
            // 右眼-10
            if (null != landmark.getRight_eye_bottom()) {
                mCanvas.drawCircle(landmark.getRight_eye_bottom().getX(), landmark.getRight_eye_bottom().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_center()) {
                mCanvas.drawCircle(landmark.getRight_eye_center().getX(), landmark.getRight_eye_center().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_left_corner()) {
                mCanvas.drawCircle(landmark.getRight_eye_left_corner().getX(), landmark.getRight_eye_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_lower_left_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eye_lower_left_quarter().getX(), landmark.getRight_eye_lower_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_lower_right_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eye_lower_right_quarter().getX(), landmark.getRight_eye_lower_right_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_pupil()) {
                mCanvas.drawCircle(landmark.getRight_eye_pupil().getX(), landmark.getRight_eye_pupil().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_upper_left_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eye_upper_left_quarter().getX(), landmark.getRight_eye_upper_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_upper_right_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eye_upper_right_quarter().getX(), landmark.getRight_eye_upper_right_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_top()) {
                mCanvas.drawCircle(landmark.getRight_eye_top().getX(), landmark.getRight_eye_top().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eye_right_corner()) {
                mCanvas.drawCircle(landmark.getRight_eye_right_corner().getX(), landmark.getRight_eye_right_corner().getY(), 1, mPaint);
            }
            // 右眼睫毛-9
            if (null != landmark.getRight_eyebrow_right_corner()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_right_corner().getX(), landmark.getRight_eyebrow_right_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_lower_left_corner()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_lower_left_corner().getX(), landmark.getRight_eyebrow_lower_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_lower_left_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_lower_left_quarter().getX(), landmark.getRight_eyebrow_lower_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_lower_middle()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_lower_middle().getX(), landmark.getRight_eyebrow_lower_middle().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_lower_right_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_lower_right_quarter().getX(), landmark.getRight_eyebrow_lower_right_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_upper_left_corner()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_upper_left_corner().getX(), landmark.getRight_eyebrow_upper_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_upper_left_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_upper_left_quarter().getX(), landmark.getRight_eyebrow_upper_left_quarter().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_upper_middle()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_upper_middle().getX(), landmark.getRight_eyebrow_upper_middle().getY(), 1, mPaint);
            }
            if (null != landmark.getRight_eyebrow_upper_right_quarter()) {
                mCanvas.drawCircle(landmark.getRight_eyebrow_upper_right_quarter().getX(), landmark.getRight_eyebrow_upper_right_quarter().getY(), 1, mPaint);
            }
            // 嘴-20
            if (null != landmark.getMouth_left_corner()) {
                mCanvas.drawCircle(landmark.getMouth_left_corner().getX(), landmark.getMouth_left_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_bottom()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_bottom().getX(), landmark.getMouth_lower_lip_bottom().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_left_contour1()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_left_contour1().getX(), landmark.getMouth_lower_lip_left_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_left_contour2()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_left_contour2().getX(), landmark.getMouth_lower_lip_left_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_left_contour3()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_left_contour3().getX(), landmark.getMouth_lower_lip_left_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_right_contour1()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_right_contour1().getX(), landmark.getMouth_lower_lip_right_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_right_contour2()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_right_contour2().getX(), landmark.getMouth_lower_lip_right_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_right_contour3()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_right_contour3().getX(), landmark.getMouth_lower_lip_right_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_lower_lip_top()) {
                mCanvas.drawCircle(landmark.getMouth_lower_lip_top().getX(), landmark.getMouth_lower_lip_top().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_right_corner()) {
                mCanvas.drawCircle(landmark.getMouth_right_corner().getX(), landmark.getMouth_right_corner().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_bottom()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_bottom().getX(), landmark.getMouth_upper_lip_bottom().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_left_contour1()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_left_contour1().getX(), landmark.getMouth_upper_lip_left_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_left_contour2()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_left_contour2().getX(), landmark.getMouth_upper_lip_left_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_left_contour3()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_left_contour3().getX(), landmark.getMouth_upper_lip_left_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_left_contour4()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_left_contour4().getX(), landmark.getMouth_upper_lip_left_contour4().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_right_contour1()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_right_contour1().getX(), landmark.getMouth_upper_lip_right_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_right_contour2()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_right_contour2().getX(), landmark.getMouth_upper_lip_right_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_right_contour3()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_right_contour3().getX(), landmark.getMouth_upper_lip_right_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_right_contour4()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_right_contour4().getX(), landmark.getMouth_upper_lip_right_contour4().getY(), 1, mPaint);
            }
            if (null != landmark.getMouth_upper_lip_top()) {
                mCanvas.drawCircle(landmark.getMouth_upper_lip_top().getX(), landmark.getMouth_upper_lip_top().getY(), 1, mPaint);
            }
            /**90*/
            if (null != landmark.getNose_bridge1()) {
                mCanvas.drawCircle(landmark.getNose_bridge1().getX(), landmark.getNose_bridge1().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_bridge2()) {
                mCanvas.drawCircle(landmark.getNose_bridge2().getX(), landmark.getNose_bridge2().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_bridge3()) {
                mCanvas.drawCircle(landmark.getNose_bridge3().getX(), landmark.getNose_bridge3().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_left_contour1()) {
                mCanvas.drawCircle(landmark.getNose_left_contour1().getX(), landmark.getNose_left_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_left_contour2()) {
                mCanvas.drawCircle(landmark.getNose_left_contour2().getX(), landmark.getNose_left_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_left_contour3()) {
                mCanvas.drawCircle(landmark.getNose_left_contour3().getX(), landmark.getNose_left_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_left_contour4()) {
                mCanvas.drawCircle(landmark.getNose_left_contour4().getX(), landmark.getNose_left_contour4().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_left_contour5()) {
                mCanvas.drawCircle(landmark.getNose_left_contour5().getX(), landmark.getNose_left_contour5().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_middle_contour()) {
                mCanvas.drawCircle(landmark.getNose_middle_contour().getX(), landmark.getNose_middle_contour().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_right_contour1()) {
                mCanvas.drawCircle(landmark.getNose_right_contour1().getX(), landmark.getNose_right_contour1().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_right_contour2()) {
                mCanvas.drawCircle(landmark.getNose_right_contour2().getX(), landmark.getNose_right_contour2().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_right_contour3()) {
                mCanvas.drawCircle(landmark.getNose_right_contour3().getX(), landmark.getNose_right_contour3().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_right_contour4()) {
                mCanvas.drawCircle(landmark.getNose_right_contour4().getX(), landmark.getNose_right_contour4().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_right_contour5()) {
                mCanvas.drawCircle(landmark.getNose_right_contour5().getX(), landmark.getNose_right_contour5().getY(), 1, mPaint);
            }
            if (null != landmark.getNose_tip()) {
                mCanvas.drawCircle(landmark.getNose_tip().getX(), landmark.getNose_tip().getY(), 1, mPaint);
            }
        }
    }

    /**
     * 获取Bitmap图片
     * @param url 网络链接
     * @return
     */
    public Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
