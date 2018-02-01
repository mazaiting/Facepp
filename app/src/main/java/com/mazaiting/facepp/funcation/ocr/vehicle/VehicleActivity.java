package com.mazaiting.facepp.funcation.ocr.vehicle;

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
import com.mazaiting.facepp.funcation.ocr.vehicle.bean.VehicleBean;
import com.mazaiting.facepp.funcation.ocr.vehicle.service.VehicleApi;

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

public class VehicleActivity extends AppCompatActivity {
    private static final String TAG = "VehicleActivity";
    @InjectView(R.id.imageView)
    ImageView mImageView;
    @InjectView(R.id.textView)
    TextView mTextView;
    private VehicleApi mVehicleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        ButterKnife.inject(this);
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mVehicleApi = retrofit.create(VehicleApi.class);
    }

    @OnClick(R.id.btnOcrVehicle)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.OCR_VEHICLE_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<VehicleBean> observable = mVehicleApi.getVehicleInfo(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VehicleBean>() {
                    @Override
                    public void accept(@NonNull VehicleBean vehicleBean) throws Exception {
                        Log.e(TAG, "accept: " + vehicleBean.toString());
                        mTextView.setText(vehicleBean.toString());
                        Bitmap bitmap = BitmapFactory.decodeFile(Config.OCR_VEHICLE_FILE);
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }
}
