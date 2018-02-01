package com.mazaiting.facepp.funcation.humanbody.gesture.service;

import com.mazaiting.facepp.funcation.humanbody.gesture.bean.GestureInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface GestureApi {
    @Multipart
    @POST("humanbodypp/beta/gesture")
    Observable<GestureInfoBean> gesture(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file,
            @Part MultipartBody.Part return_gesture
    );
}
