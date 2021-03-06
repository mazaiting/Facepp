package com.mazaiting.facepp.funcation.face.compare.service;

import com.mazaiting.facepp.funcation.face.compare.bean.CompareBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/1/31
 */

public interface CompareApi {
    @Multipart
    @POST("facepp/v3/compare")
    Observable<CompareBean> getCompare(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file1,
            @Part MultipartBody.Part image_file2
            );
}
