package com.mazaiting.facepp.bean;

/**
 * @author mazaiting
 * @date 2018/2/1
 */

public class ActivityBean {
    /**
     * Activity的类实例
     */
    private Class<?> mActivity;
    /**
     * Activity类名
     */
    private String mName;

    public ActivityBean(String name, Class<?> activity) {
        this.mName = name;
        this.mActivity = activity;
    }

    public Class<?> getActivity() {
        return mActivity;
    }

    public void setActivity(Class<?> activity) {
        mActivity = activity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
