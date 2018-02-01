package com.mazaiting.facepp.funcation.picture.scene.service;

import com.mazaiting.facepp.funcation.picture.scene.bean.SceneBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface SceneApi {
    @Multipart
    @POST("imagepp/beta/detectsceneandobject")
    Observable<SceneBean> getSceneInfo(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
