package com.mazaiting.facepp.funcation.ocr.id;

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
import com.mazaiting.facepp.funcation.ocr.id.bean.IdBean;
import com.mazaiting.facepp.funcation.ocr.id.service.IdApi;

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

public class IdActivity extends AppCompatActivity {
    private static final String TAG = "IdActivity";
    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.imageView)
    ImageView mImageView;
    private IdApi mIdApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mIdApi = retrofit.create(IdApi.class);
    }

    @OnClick(R.id.btnOcrId)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.OCR_IC_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<IdBean> observable = mIdApi.getIdInfo(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IdBean>() {
                    @Override
                    public void accept(@NonNull IdBean idBean) throws Exception {
                        Log.e(TAG, "accept: " + idBean.toString());
                        mTextView.setText(idBean.toString());
                        Bitmap bitmap = BitmapFactory.decodeFile(Config.OCR_IC_FILE);
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }
}
