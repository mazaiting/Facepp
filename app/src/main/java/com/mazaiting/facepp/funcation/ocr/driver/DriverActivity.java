package com.mazaiting.facepp.funcation.ocr.driver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.net.HttpManager;
import com.mazaiting.facepp.funcation.ocr.driver.bean.DriverBean;
import com.mazaiting.facepp.funcation.ocr.driver.service.DriverApi;

import java.io.File;

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

public class DriverActivity extends AppCompatActivity {
    private static final String TAG = "DriverActivity";
    @InjectView(R.id.imageView)
    ImageView mImageView;
    @InjectView(R.id.textView)
    TextView mTextView;
    private DriverApi mDriverApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mDriverApi = retrofit.create(DriverApi.class);
    }

    @OnClick(R.id.btnOcrDriver)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.OCR_DRIVER_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<DriverBean> observable = mDriverApi.getDriverInfo(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DriverBean>() {
                    @Override
                    public void accept(@NonNull DriverBean driverBean) throws Exception {
                        // DriverBean{time_used=1535, request_id='1517463364,90fcbcc8-8faa-4b14-9370-c04cbca1fb2a', cards=[CardsBean{valid_from='null', gender='男', issued_by='广东省东莞市公安局交通警察支队', issue_date='2012-07-24', classX='C1', license_number='341225199309263916', valid_for='null', birthday='1993-09-26', version=2, address='安徽省阜南县田集镇杨寨村寨西队88号', nationality='中国', type=2, side='front', name='杨成'}]}
                        Log.e(TAG, "accept: " + driverBean.toString());
                        Bitmap bitmap = BitmapFactory.decodeFile(Config.OCR_DRIVER_FILE);
                        mImageView.setImageBitmap(bitmap);
                        mTextView.setText(driverBean.toString());
                    }
                });
    }
}
