package com.mazaiting.facepp.funcation.humanbody.detect;

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
import com.mazaiting.facepp.funcation.humanbody.detect.bean.HumanDetectBean;
import com.mazaiting.facepp.funcation.humanbody.detect.service.HumanDetectApi;
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

public class HumanDetectActivity extends AppCompatActivity {

    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.imageView)
    ImageView mImageView;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_human);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btnDetect)
    public void onViewClicked() {
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        HumanDetectApi detectApi = retrofit.create(HumanDetectApi.class);
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        final File file = new File(Config.HUMAN_BODY_DETECT_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        MultipartBody.Part return_attributes = MultipartBody.Part.createFormData("return_attributes", Config.HUMAN_BODY_DETECT_ATTR);
        Observable<HumanDetectBean> observable = detectApi.getDetect(api_key, api_secret, image_file, return_attributes);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HumanDetectBean>() {
                    @Override
                    public void accept(@NonNull HumanDetectBean humanDetectBean) throws Exception {
                        drawPicture(humanDetectBean, file);
                    }
                });
    }

    private void drawPicture(@NonNull HumanDetectBean humanDetectBean, File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(newBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        // 画矩形框
        // 获取人数
        List<HumanDetectBean.HumanbodiesBean> humanbodies = humanDetectBean.getHumanbodies();
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        for (HumanDetectBean.HumanbodiesBean bean : humanbodies) {
            // 获取属性
            HumanDetectBean.HumanbodiesBean.AttributesBean attributes = bean.getAttributes();
            HumanDetectBean.HumanbodiesBean.AttributesBean.GenderBean gender = attributes.getGender();
            // 性别
            String genderStr = gender.getFemale() > gender.getMale() ? "女性" : "男性";
            // 获取人体位置
            HumanDetectBean.HumanbodiesBean.HumanbodyRectangleBean rectangle = bean.getHumanbody_rectangle();
            int left = rectangle.getLeft();
            int top = rectangle.getTop();
            int right = rectangle.getLeft() + rectangle.getWidth();
            int bottom = rectangle.getTop() + rectangle.getHeight();
            // 标记男女
            mCanvas.drawText(genderStr, left + 10, top + 10, mPaint);
            // 绘制框
            mCanvas.drawRect(left, top, right, bottom, mPaint);
        }
        mImageView.setImageBitmap(newBitmap);
        mTextView.setText(humanDetectBean.toString());
    }
}
