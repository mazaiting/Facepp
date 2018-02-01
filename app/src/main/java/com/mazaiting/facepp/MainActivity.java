package com.mazaiting.facepp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mazaiting.facepp.adapter.MainAdapter;
import com.mazaiting.facepp.bean.ActivityBean;
import com.mazaiting.facepp.bean.MySectionEntity;
import com.mazaiting.facepp.funcation.face.compare.CompareActivity;
import com.mazaiting.facepp.funcation.face.detect.DetectActivity;
import com.mazaiting.facepp.funcation.face.faceset.FaceSetActivity;
import com.mazaiting.facepp.funcation.face.search.SearchActivity;
import com.mazaiting.facepp.funcation.humanbody.detect.HumanDetectActivity;
import com.mazaiting.facepp.funcation.humanbody.gesture.GestureActivity;
import com.mazaiting.facepp.funcation.humanbody.segment.SegmentActivity;
import com.mazaiting.facepp.funcation.ocr.bank.BankActivity;
import com.mazaiting.facepp.funcation.ocr.driver.DriverActivity;
import com.mazaiting.facepp.funcation.ocr.id.IdActivity;
import com.mazaiting.facepp.funcation.ocr.vehicle.VehicleActivity;
import com.mazaiting.facepp.funcation.picture.scene.SceneActivity;
import com.mazaiting.facepp.funcation.picture.text.TextActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    /**数据*/
    private static List<MySectionEntity> mDatas;
    static {
        mDatas = new ArrayList<>();
        mDatas.add(new MySectionEntity(true, "人脸识别"));
        mDatas.add(new MySectionEntity(new ActivityBean("人脸检测--Detect", DetectActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("人脸比较--Compare", CompareActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("人脸集合--FaceSet", FaceSetActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("人脸搜索--Search", SearchActivity.class)));
        mDatas.add(new MySectionEntity(true, "人体识别"));
        mDatas.add(new MySectionEntity(new ActivityBean("人体检测--Detect", HumanDetectActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("轮廓检测--Segment", SegmentActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("手势识别--Gesture", GestureActivity.class)));
        mDatas.add(new MySectionEntity(true, "证件识别"));
        mDatas.add(new MySectionEntity(new ActivityBean("身份证识别--IdCard", IdActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("驾驶证识别--Driver", DriverActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("行驶证识别--Vehicle", VehicleActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("银行卡识别--Bank", BankActivity.class)));
        mDatas.add(new MySectionEntity(true, "图像识别"));
        mDatas.add(new MySectionEntity(new ActivityBean("场景识别--Scene", SceneActivity.class)));
        mDatas.add(new MySectionEntity(new ActivityBean("文字识别--Text", TextActivity.class)));
    }

    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(R.layout.item_section_content, R.layout.def_section_head, mDatas);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                MySectionEntity entity = mDatas.get(i);
                // 头部
                if (entity.isHeader) {
                    Toast.makeText(MainActivity.this, entity.header, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, entity.t.getName(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, entity.t.getActivity()));
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
