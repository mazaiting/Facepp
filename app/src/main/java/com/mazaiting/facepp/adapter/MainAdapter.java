package com.mazaiting.facepp.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mazaiting.facepp.R;
import com.mazaiting.facepp.bean.MySectionEntity;

import java.util.List;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class MainAdapter extends BaseSectionQuickAdapter<MySectionEntity> {
    public MainAdapter(int layoutResId, int sectionHeadResId, List<MySectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, MySectionEntity entity) {
        holder.setText(R.id.header, entity.header);
    }

    @Override
    protected void convert(BaseViewHolder holder, MySectionEntity entity) {
        holder.setText(R.id.textView, entity.t.getName());
    }
}
