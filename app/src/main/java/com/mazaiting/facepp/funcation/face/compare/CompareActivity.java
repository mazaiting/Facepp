package com.mazaiting.facepp.funcation.face.compare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.face.compare.bean.CompareBean;
import com.mazaiting.facepp.funcation.face.compare.service.CompareApi;
import com.mazaiting.facepp.net.HttpManager;

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

public class CompareActivity extends AppCompatActivity {
    private static final String TAG = "CompareActivity";
    @InjectView(R.id.textView)
    TextView mTextView;
    private CompareApi mCompareApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mCompareApi = retrofit.create(CompareApi.class);
    }


    @OnClick(R.id.btnNative)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file1 = new File(Config.FACE_COMPARE_FILE1);
        RequestBody requestBody1 = MultipartBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file1);
        MultipartBody.Part image_file1 = MultipartBody.Part.createFormData("image_file1", file1.getName(), requestBody1);
        File file2 = new File(Config.FACE_COMPARE_FILE2);
        RequestBody requestBody2 = MultipartBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file2);
        MultipartBody.Part image_file2 = MultipartBody.Part.createFormData("image_file2", file2.getName(), requestBody2);

        Observable<CompareBean> observable = mCompareApi.getCompare(api_key, api_secret, image_file1, image_file2);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompareBean>() {
                    @Override
                    public void accept(@NonNull CompareBean compareBean) throws Exception {
                        printInfo(compareBean);
                    }
                });
    }

    /**
     * 打印
     * @param compareBean 比较
     */
    private void printInfo(CompareBean compareBean) {
        String string =
                "阈值： 千分之一： " + compareBean.getThresholds().get_$1e3()
                    + ", 万分之一： " + compareBean.getThresholds().get_$1e4()
                    + ", 十万分之一：" + compareBean.getThresholds().get_$1e5()
                    + "\n"
                + "置信度: " + compareBean.getConfidence() + "\n";
        mTextView.setText(string);
    }
}
