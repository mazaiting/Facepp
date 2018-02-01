package com.mazaiting.facepp.funcation.face.faceset.service;

import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetBean;
import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetDetailBean;
import com.mazaiting.facepp.funcation.face.faceset.bean.FaceSetKeyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author mazaiting
 * @date 2018/1/31
 */

public interface FaceSetApi {
    /**
     * 创建人脸集合
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/create")
    Observable<FaceSetBean> createFaceSet(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("display_name") String display_name,
            @Field("outer_id") String outer_id,
            @Field("tags") String tags,
            // 多个用逗号隔开。0ad6cbf71fd7ec3054f3b09fab118a52,ddd73946106182bb26cd8a3ebbdcdcb9
            @Field("face_tokens") String face_tokens,
            @Field("user_data") String user_data,
            @Field("force_merge") int force_merge
    );

    /**
     * 添加人脸
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/addface")
    Observable<FaceSetBean> addface(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("faceset_token") String faceset_token,
            // 人脸标识 face_token 组成的字符串，可以是一个或者多个，用逗号分隔。最多不超过5个face_token
            @Field("face_tokens") String face_tokens
    );

    /**
     * 移除人脸
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/removeface")
    Observable<FaceSetBean> removeFace(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("faceset_token") String faceset_token,
            // 需要移除的人脸标识字符串，可以是一个或者多个face_token组成，用逗号分隔。最多不能超过1,000个face_token
            // 注：face_tokens字符串传入“RemoveAllFaceTokens”则会移除FaceSet内所有的face_token
            @Field("face_tokens") String face_tokens
    );

    /**
     * 更新人脸集合
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/update")
    Observable<FaceSetBean> updateFaceSet(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("faceset_token") String faceset_token,
            @Field("new_outer_id") String  new_outer_id
    );

    /**
     * 获取细节
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/getdetail")
    Observable<FaceSetDetailBean> getDetail(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("faceset_token") String faceset_token,
            @Field("start") int  start
    );

    /**
     * 删除人脸集合
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/delete")
    Observable<FaceSetBean> deleteFaceSet(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("faceset_token") String faceset_token,
            @Field("check_empty") int check_empty
    );

    /**
     * 获取当前API_KEY下的FaceSets集合
     */
    @FormUrlEncoded
    @POST("facepp/v3/faceset/getfacesets")
    Observable<FaceSetKeyBean> getFaceSets(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("tags") String tags,
            @Field("start") int start
    );

}
