package com.mazaiting.facepp.funcation.humanbody.segment.service;

import com.mazaiting.facepp.funcation.humanbody.segment.bean.SegmentBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface SegmentApi {
    /**
     * 人体轮廓识别
     */
    @Multipart
    @POST("humanbodypp/v1/segment")
    Observable<SegmentBean> getSegment(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
