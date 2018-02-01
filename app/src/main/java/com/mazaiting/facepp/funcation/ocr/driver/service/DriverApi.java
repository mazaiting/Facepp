package com.mazaiting.facepp.funcation.ocr.driver.service;

import com.mazaiting.facepp.funcation.ocr.driver.bean.DriverBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface DriverApi {
    @Multipart
    @POST("cardpp/v1/ocrdriverlicense")
    Observable<DriverBean> getDriverInfo(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
