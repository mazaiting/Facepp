package com.mazaiting.facepp.funcation.face.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.face.search.bean.SearchBean;
import com.mazaiting.facepp.funcation.face.search.service.SearchApi;
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

public class SearchActivity extends AppCompatActivity {

    private SearchApi mSearchApi;
    @InjectView(R.id.textView)
    TextView mTextView;
    /**
     * {
     * "faceset_token": "3f79d6e4d383332e800f198bb865e6cf",
     * "time_used": 712,
     * "face_count": 2,
     * "face_added": 2,
     * "request_id": "1517395824,d087f279-5648-48ac-9a72-39cd21768714",
     * "outer_id": "beautyTest",
     * "failure_detail": [
     * <p>
     * ]
     * }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mSearchApi = retrofit.create(SearchApi.class);
    }

    @OnClick(R.id.btnSearch)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.FACE_COMPARE_FILE1);
        RequestBody requestBody = MultipartBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        MultipartBody.Part faceset_token = MultipartBody.Part.createFormData("faceset_token", "3f79d6e4d383332e800f198bb865e6cf");
        MultipartBody.Part return_result_count = MultipartBody.Part.createFormData("return_result_count", "5");
        Observable<SearchBean> observable = mSearchApi.search(api_key, api_secret, image_file, faceset_token, return_result_count);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(@NonNull SearchBean searchBean) throws Exception {
                        /**
                         * {
                             "image_id": "vpnxSJgaiOlF5xPhN9KrPA==",
                             "faces": [
                                 {
                                 "face_rectangle": {
                                     "width": 203,
                                     "top": 159,
                                     "left": 105,
                                     "height": 203
                                 },
                                 "face_token": "17e8196c22d0b35a329564487abf66d2"
                                 }
                                 ],
                                 "time_used": 594,
                                 "thresholds": {
                                     "1e-3": 62.327,
                                     "1e-5": 73.975,
                                     "1e-4": 69.101
                                 },
                                 "request_id": "1517396620,dfc46d25-8625-4928-b2bd-2acc9092d92a",
                                 "results": [
                                 {
                                     "confidence": 97.389,
                                     "user_id": "",
                                     "face_token": "0ad6cbf71fd7ec3054f3b09fab118a52"
                                 },
                                 {
                                     "confidence": 84.725,
                                     "user_id": "",
                                     "face_token": "ddd73946106182bb26cd8a3ebbdcdcb9"
                                 }
                            ]
                         }
                         */
                        mTextView.setText(searchBean.toString());
                    }
                });
    }
}
