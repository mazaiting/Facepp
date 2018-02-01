package com.mazaiting.facepp.funcation.picture.text.service;

import com.mazaiting.facepp.funcation.picture.text.bean.TextBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface TextApi {
    @Multipart
    @POST("imagepp/v1/recognizetext")
    Observable<TextBean> getText(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
