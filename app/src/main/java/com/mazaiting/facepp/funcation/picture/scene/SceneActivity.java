package com.mazaiting.facepp.funcation.picture.scene;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.net.HttpManager;
import com.mazaiting.facepp.funcation.picture.scene.bean.SceneBean;
import com.mazaiting.facepp.funcation.picture.scene.service.SceneApi;

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

public class SceneActivity extends AppCompatActivity {

    @InjectView(R.id.imageView)
    ImageView mImageView;
    @InjectView(R.id.textView)
    TextView mTextView;
    private SceneApi mSceneApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mSceneApi = retrofit.create(SceneApi.class);
    }

    @OnClick(R.id.btnObject)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.PIC_SCENE_OBJECT_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<SceneBean> observable = mSceneApi.getSceneInfo(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SceneBean>() {
                    @Override
                    public void accept(@NonNull SceneBean sceneBean) throws Exception {
                        // {"time_used": 1048, "scenes": [{"confidence": 41.268, "value": "Hotel"}], "image_id": "bmdWL6jLUOpt9YPV20bZXg==", "objects": [{"confidence": 20.831, "value": "Basket"}], "request_id": "1517475067,19ae718e-27af-48a3-937b-cf875f22ffc1"}
                        Bitmap bitmap = BitmapFactory.decodeFile(Config.PIC_SCENE_OBJECT_FILE);
                        mImageView.setImageBitmap(bitmap);
                        mTextView.setText(sceneBean.toString());
                    }
                });
    }
}
