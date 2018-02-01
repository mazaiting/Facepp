package com.mazaiting.facepp.funcation.ocr.bank.service;

import com.mazaiting.facepp.funcation.ocr.bank.bean.BankBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public interface BankApi {
    @Multipart
    @POST("cardpp/beta/ocrbankcard")
    Observable<BankBean> getBankInfo(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
