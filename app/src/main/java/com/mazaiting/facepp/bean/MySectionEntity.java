package com.mazaiting.facepp.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class MySectionEntity extends SectionEntity<ActivityBean> {

    public MySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySectionEntity(ActivityBean activityBean) {
        super(activityBean);
    }
}
