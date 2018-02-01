package com.mazaiting.facepp.funcation.picture.text;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.net.HttpManager;
import com.mazaiting.facepp.funcation.picture.text.bean.TextBean;
import com.mazaiting.facepp.funcation.picture.text.service.TextApi;

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

public class TextActivity extends AppCompatActivity {

    @InjectView(R.id.imageView)
    ImageView mImageView;
    @InjectView(R.id.textView)
    TextView mTextView;
    private TextApi mTextApi;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.inject(this);
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mTextApi = retrofit.create(TextApi.class);
    }

    @OnClick(R.id.btnObject)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.PIC_TEXT_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<TextBean> observable = mTextApi.getText(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextBean>() {
                    @Override
                    public void accept(@NonNull TextBean textBean) throws Exception {
                        drawPic(textBean);
                    }
                });
    }

    /**
     * 绘制框
     */
    private void drawPic(TextBean textBean) {
        Bitmap bitmap = BitmapFactory.decodeFile(Config.PIC_TEXT_FILE);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(newBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        // 获取结果
        List<TextBean.ResultBean> resultList = textBean.getResult();
        if (null == resultList || resultList.size() <= 0) {
            Toast.makeText(this, "未检测到数据", Toast.LENGTH_SHORT).show();
            return;
        }
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历
        for (TextBean.ResultBean resultBean : resultList) {
            // 获取类型
            String type = resultBean.getType();
            // 获取值
            String value = resultBean.getValue();
            List<TextBean.ResultBean.ChildobjectsBean> childobjects = resultBean.getChildobjects();
            for (TextBean.ResultBean.ChildobjectsBean bean : childobjects) {
                // 获取类型
                String typeChild = bean.getType();
                // 获取值
                String valueChild = bean.getValue();
                List<TextBean.ResultBean.ChildobjectsBean.PositionBean> positionBeanList = bean.getPosition();
                int left = positionBeanList.get(0).getX();
                int top = positionBeanList.get(0).getY();
                int right = positionBeanList.get(2).getX();
                int bottom = positionBeanList.get(2).getY();
                // 画矩形
                mCanvas.drawRect(left, top, right, bottom, mPaint);
            }
            stringBuilder.append("type: " + type + ", value: " + value);
        }
        // 绘制新图
        mImageView.setImageBitmap(newBitmap);
        mTextView.setText(stringBuilder.toString());
    }
}
