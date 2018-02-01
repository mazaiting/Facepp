package com.mazaiting.facepp.funcation.humanbody.detect.service;

import com.mazaiting.facepp.funcation.humanbody.detect.bean.HumanDetectBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface HumanDetectApi {
    /**
     * 人体识别
     */
    @Multipart
    @POST("humanbodypp/v1/detect")
    Observable<HumanDetectBean> getDetect(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file,
            @Part MultipartBody.Part return_attributes
    );
}
