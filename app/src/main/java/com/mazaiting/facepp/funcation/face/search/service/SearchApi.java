package com.mazaiting.facepp.funcation.face.search.service;

import com.mazaiting.facepp.funcation.face.search.bean.SearchBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/1/31
 */

public interface SearchApi {
    @Multipart
    @POST("facepp/v3/search")
    Observable<SearchBean> search(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file,
            @Part MultipartBody.Part faceset_token,
            @Part MultipartBody.Part return_result_count
    );
}
