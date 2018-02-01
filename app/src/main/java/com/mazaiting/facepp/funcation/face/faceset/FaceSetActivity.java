package com.mazaiting.facepp.funcation.face.faceset;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetBean;
import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetDetailBean;
import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetKeyBean;
import com.mazaiting.facepp.funcation.face.faceset.service.FaceSetApi;
import com.mazaiting.facepp.net.HttpManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FaceSetActivity extends AppCompatActivity {

    private FaceSetApi mFaceSetApi;
    @InjectView(R.id.textView)
    TextView mTextView;
    private SharedPreferences mSharedPreferences;
    /**FaceSets Token*/
    private String mToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_set);
        ButterKnife.inject(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        getToken();

        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mFaceSetApi = retrofit.create(FaceSetApi.class);
    }


    @OnClick({R.id.btnCreate, R.id.btnAdd, R.id.btnRemove, R.id.btnUpdate, R.id.btnGetDetail, R.id.btnDelete, R.id.btnGetFaceSets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:
                create();
                break;
            case R.id.btnAdd:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                add();
                break;
            case R.id.btnRemove:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                remove();
                break;
            case R.id.btnUpdate:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                update();
                break;
            case R.id.btnGetDetail:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                getDetail();
                break;
            case R.id.btnDelete:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                delete();
                break;
            case R.id.btnGetFaceSets:
                if (TextUtils.isEmpty(mToken)) {
                    Toast.makeText(this, "请先创建FaceSets", Toast.LENGTH_SHORT).show();
                    return;
                }
                getFaceSets();
                break;
            default:
                break;
        }
    }

    /**
     * 获取FaceSets
     */
    private void getFaceSets() {
        Observable<FaceSetKeyBean> observable = mFaceSetApi.getFaceSets(Config.API_KEY, Config.API_SECRET, "maleTest123", 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetKeyBean>() {
                    @Override
                    public void accept(@NonNull FaceSetKeyBean faceSetKeyBean) throws Exception {
                        /**
                         * {
                             "time_used": 90,
                             "facesets": [
                             {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "outer_id": "beautyTest",
                             "display_name": "maleTest",
                             "tags": "maleTest123"
                             }
                             ],
                             "request_id": "1517395565,3f61e6c9-917f-4c98-ba86-9632becf05b2"
                         }
                         */
                        mTextView.setText(faceSetKeyBean.toString());
                    }
                });
    }

    /**
     * 删除人脸FaceSets
     */
    private void delete() {
        Observable<FaceSetBean> observable = mFaceSetApi.deleteFaceSet(Config.API_KEY, Config.API_SECRET, mToken, 0);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetBean>() {
                    @Override
                    public void accept(@NonNull FaceSetBean faceSetBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "request_id": "1517395669,705b84f9-1fab-42a6-9f07-f0b4eaec87fd",
                             "time_used": 94,
                             "outer_id": "our_id"
                         }
                         */
                        mTextView.setText(faceSetBean.toString());
                    }
                });
    }

    /**
     * 获取细节FaceSets
     */
    private void getDetail() {
        Observable<FaceSetDetailBean> observable = mFaceSetApi.getDetail(Config.API_KEY, Config.API_SECRET, mToken, 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetDetailBean>() {
                    @Override
                    public void accept(@NonNull FaceSetDetailBean faceSetDetailBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "tags": "maleTest123",
                             "time_used": 66,
                             "user_data": "mazaiting create face sets",
                             "display_name": "maleTest",
                             "face_tokens": [
                                 "0ad6cbf71fd7ec3054f3b09fab118a52",
                                 "ddd73946106182bb26cd8a3ebbdcdcb9"
                             ],
                             "face_count": 2,
                             "request_id": "1517395472,e5575ea0-fdd4-4130-a619-ff44183ac9f7",
                             "outer_id": "beautyTest"
                         }
                         */
                        mTextView.setText(faceSetDetailBean.toString());
                    }
                });
    }

    /**
     * 更新FaceSets
     */
    private void update() {
        Observable<FaceSetBean> observable = mFaceSetApi.updateFaceSet(Config.API_KEY, Config.API_SECRET, mToken, "our_id");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetBean>() {
                    @Override
                    public void accept(@NonNull FaceSetBean faceSetBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "request_id": "1517395640,fcded40a-dd39-48c5-b63e-8478f06614d7",
                             "time_used": 33,
                             "outer_id": "our_id"
                             }
                         */
                        mTextView.setText(faceSetBean.toString());
                    }
                });
    }

    /**
     * 移除人脸FaceSets
     */
    private void remove() {
        Observable<FaceSetBean> observable = mFaceSetApi.removeFace(Config.API_KEY, Config.API_SECRET, mToken, "90bd0bdbc26b27772ff0cc347a1e1091");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetBean>() {
                    @Override
                    public void accept(@NonNull FaceSetBean faceSetBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "face_removed": 1,
                             "time_used": 150,
                             "face_count": 2,
                             "request_id": "1517395603,5f6833a2-ea06-4f8d-8f50-0a400a198cf9",
                             "outer_id": "beautyTest",
                             "failure_detail": [

                            ]
                         }
                         */
                        mTextView.setText(faceSetBean.toString());
                    }
                });
    }

    /**
     * 添加人脸到FaceSets
     */
    private void add() {
        Observable<FaceSetBean> observable = mFaceSetApi.addface(Config.API_KEY, Config.API_SECRET, mToken, "90bd0bdbc26b27772ff0cc347a1e1091");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetBean>() {
                    @Override
                    public void accept(@NonNull FaceSetBean faceSetBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "time_used": 568,
                             "face_count": 3,
                             "face_added": 1,
                             "request_id": "1517395521,8a41eae7-fae7-4079-8fa1-c5bd58d11ef2",
                             "outer_id": "beautyTest",
                             "failure_detail": [

                         ]
                         }
                         */
                        mTextView.setText(faceSetBean.toString());
                    }
                });
    }

    /**
     * 创建FaceSets
     */
    private void create() {
        Observable<FaceSetBean> observable = mFaceSetApi.createFaceSet(Config.API_KEY, Config.API_SECRET, "maleTest", "beautyTest", "maleTest123",
                "0ad6cbf71fd7ec3054f3b09fab118a52,ddd73946106182bb26cd8a3ebbdcdcb9",
                "mazaiting create face sets", 0);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FaceSetBean>() {
                    @Override
                    public void accept(@NonNull FaceSetBean faceSetBean) throws Exception {
                        /**
                         * {
                             "faceset_token": "3dc7448f8f38707d9e353902c673fcb8",
                             "time_used": 1082,
                             "face_count": 2,
                             "face_added": 2,
                             "request_id": "1517395425,57208b1f-ac6e-45ab-9f22-d5ec73a346d0",
                             "outer_id": "beautyTest",
                             "failure_detail": [

                         ]
                         }
                         */
                        mToken = faceSetBean.getFaceset_token();
                        saveToken(faceSetBean.getFaceset_token());
                        mTextView.setText(faceSetBean.toString());
                    }
                });
    }

    /**
     * 保存token
     */
    private void saveToken(String token){
        mSharedPreferences.edit().putString(Config.TOKEN, token).apply();
    }

    public void getToken() {
        mToken = mSharedPreferences.getString(Config.TOKEN, "");
    }
}


















