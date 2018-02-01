package com.mazaiting.facepp.funcation.humanbody.segment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.humanbody.segment.bean.SegmentBean;
import com.mazaiting.facepp.funcation.humanbody.segment.service.SegmentApi;
import com.mazaiting.facepp.net.HttpManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class SegmentActivity extends AppCompatActivity {

    private SegmentApi mSegmentApi;
    @InjectView(R.id.imageView)
    ImageView mImageView;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment);
        ButterKnife.inject(this);
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mSegmentApi = retrofit.create(SegmentApi.class);
    }

    @OnClick(R.id.btnSegment)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.HUMAN_BODY_SEGMENT_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<SegmentBean> observable = mSegmentApi.getSegment(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SegmentBean>() {
                    @Override
                    public void accept(@NonNull SegmentBean segmentBean) throws Exception {
                        File tempFile = saveFile(segmentBean);
                        drawPicture(tempFile);
                    }
                });
    }

    /**
     * 画图
     */
    private void drawPicture(File tempFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
        Bitmap upBitmap = BitmapFactory.decodeFile(Config.HUMAN_BODY_SEGMENT_FILE);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(newBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));

        mCanvas.drawBitmap(upBitmap, new Matrix(), mPaint);
        mCanvas.drawBitmap(bitmap, new Matrix(), mPaint);

        mImageView.setImageBitmap(newBitmap);
    }

    @android.support.annotation.NonNull
    private File saveFile(@NonNull SegmentBean segmentBean) throws IOException {
        byte[] bytes = Base64.decode(segmentBean.getResult(), Base64.DEFAULT);
        File tempFile = new File(Config.HUMAN_BODY_SEGMENT_TEMP_FILE);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(bytes);
        fos.close();
        return tempFile;
    }
}
