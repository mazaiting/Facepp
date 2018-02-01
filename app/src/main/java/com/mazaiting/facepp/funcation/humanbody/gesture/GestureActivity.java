package com.mazaiting.facepp.funcation.humanbody.gesture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.humanbody.gesture.bean.GestureInfoBean;
import com.mazaiting.facepp.funcation.humanbody.gesture.service.GestureApi;
import com.mazaiting.facepp.net.HttpManager;

import java.io.File;
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

public class GestureActivity extends AppCompatActivity {

    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.imageView)
    ImageView mImageView;
    private GestureApi mGestureApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.inject(this);
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mGestureApi = retrofit.create(GestureApi.class);
    }

    @OnClick(R.id.btnOcrId)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.HUMAN_BODY_GESTURE_FILE);
        RequestBody requestBody = MultipartBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        MultipartBody.Part return_gesture = MultipartBody.Part.createFormData("return_gesture", "1");
        Observable<GestureInfoBean> observable = mGestureApi.gesture(api_key, api_secret, image_file, return_gesture);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GestureInfoBean>() {
                    @Override
                    public void accept(@NonNull GestureInfoBean gestureBean) throws Exception {
                        drawPicture(gestureBean);
                    }
                });
    }

    /**
     * 画框
     */
    private void drawPicture(GestureInfoBean gestureBean) {
        Bitmap bitmap = BitmapFactory.decodeFile(Config.HUMAN_BODY_GESTURE_FILE);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(newBitmap);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        // 画矩形框
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        // 获取手势集合
        List<GestureInfoBean.HandsBean> hands = gestureBean.getHands();
        for (GestureInfoBean.HandsBean handbean : hands) {
            // 获取手势
            String gestureStr = computeGesture(handbean.getGesture());
            // 获取手势位置
            GestureInfoBean.HandsBean.HandRectangleBean rectangle = handbean.getHand_rectangle();
            int left = rectangle.getLeft();
            int top = rectangle.getTop();
            int right = rectangle.getLeft() + rectangle.getWidth();
            int bottom = rectangle.getTop() + rectangle.getHeight();
            // 绘制框
            mCanvas.drawRect(left, top, right, bottom, mPaint);
            // 绘制文字
            mCanvas.drawText(gestureStr, left + 20, top + 20, mPaint);
        }
        mImageView.setImageBitmap(newBitmap);
    }

    /**
     * 获取手势大小
     */
    private String computeGesture(GestureInfoBean.HandsBean.GestureBean gesture) {
        int[] gestureInt = {
                gesture.getThumb_up(),
                gesture.getNamaste(),
                gesture.getOk(),
                gesture.getBeg(),
                gesture.getUnknown(),
                gesture.getIndex_finger_up(),
                gesture.getThanks(),
                gesture.getPhonecall(),
                gesture.getPalm_up(),
                gesture.getBig_v(),
                gesture.getDouble_finger_up(),
                gesture.getThumb_down(),
                gesture.getFist(),
                gesture.getRock(),
                gesture.getHeart_d(),
                gesture.getHand_open(),
                gesture.getHeart_b(),
                gesture.getVictory(),
                gesture.getHeart_c()
        };
        String[] gestureStr = {
                "thumb_up", "namaste", "ok", "beg", "unknown", "index_finger_up", "thanks", "phonecall",
                "palm_up", "big_v", "double_finger_up", "thumb_down", "fist", "rock", "heart_d",
                "hand_open", "heart_b", "heart_c", "victory", "heart_a"
        };
        int max = sort(gestureInt);
        return gestureStr[max];
    }

    /**
     * 获取最大值
     */
    private int sort(int[] gestureInt) {
        int max = 0;
        for (int i = 0; i < gestureInt.length; i++) {
            if (gestureInt[i] > max) {
                max = i;
            }
        }
        return max;
    }
}
