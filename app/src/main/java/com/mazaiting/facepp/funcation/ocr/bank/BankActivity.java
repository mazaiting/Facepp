package com.mazaiting.facepp.funcation.ocr.bank;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazaiting.facepp.Config;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.net.HttpManager;
import com.mazaiting.facepp.funcation.ocr.bank.bean.BankBean;
import com.mazaiting.facepp.funcation.ocr.bank.service.BankApi;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class BankActivity extends AppCompatActivity {

    @InjectView(R.id.imageView)
    ImageView mImageView;
    @InjectView(R.id.textView)
    TextView mTextView;
    private BankApi mBankApi;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        ButterKnife.inject(this);
        Retrofit retrofit = HttpManager.getInstance().getRetrofit();
        mBankApi = retrofit.create(BankApi.class);
    }

    @OnClick(R.id.btnOcrBank)
    public void onViewClicked() {
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", Config.API_KEY);
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", Config.API_SECRET);
        File file = new File(Config.OCR_BANK_FILE);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.MULTIPART_FORM_DATA), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), requestBody);
        Observable<BankBean> observable = mBankApi.getBankInfo(api_key, api_secret, image_file);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BankBean>() {
                    @Override
                    public void accept(@NonNull BankBean bankBean) throws Exception {
                        drawPic(bankBean);
                    }
                });
    }

    /**
     * 绘制
     */
    private void drawPic(BankBean bankBean) {
        Bitmap bitmap = BitmapFactory.decodeFile(Config.OCR_BANK_FILE);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(newBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        
        // 获取银行卡列表
        List<BankBean.BankCardsBean> bankCards = bankBean.getBank_cards();
        if (null == bankCards || bankCards.size() <= 0) {
            Toast.makeText(this, "未检测到银行卡", Toast.LENGTH_SHORT).show();
            return;
        }
        mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        for (BankBean.BankCardsBean bean : bankCards) {
            // 银行卡号
            String number = bean.getNumber();
            BankBean.BankCardsBean.BoundBean bound = bean.getBound();
            int left = bound.getLeft_top().getX();
            int top = bound.getLeft_top().getY();
            int right = bound.getRight_bottom().getX();
            int bottom = bound.getRight_bottom().getY();
            // 绘制卡片框
            mCanvas.drawRect(left, top, right, bottom, mPaint);
            // 绘制文字
            mCanvas.drawText(number, left + 20, top + 20, mPaint);
        }
        mImageView.setImageBitmap(newBitmap);
        mTextView.setText(bankBean.toString());
    }
}
