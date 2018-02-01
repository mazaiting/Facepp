# Facepp人脸识别
基于Face++的人脸，人体，证件识别测试

# 使用
1). 进入[Face++官网](https://www.faceplusplus.com.cn/)申请API_KEY及API_SECRET, 填入文件Config.java中的
```
    public static final String API_KEY = "";
    public static final String API_SECRET = "";
```
2). 必须绑定Bundle ID-->"com.mazaiting.facepp", 如果是自己新建的项目, 此处的Bundle请填写自己的应用包名。

# 包含内容
1). 人脸识别，人脸分析，人脸比较，人脸搜索，人脸集合的操作
2). 人体识别，人体轮廓识别，手势识别
3). 身份证识别，驾驶证识别，行驶证识别，银行卡识别
4). 场景识别，文字识别

# 使用到的框架
1). [RxJava2](https://github.com/ReactiveX/RxJava)
2). [Retrofit2](https://github.com/square/retrofit)
3). [OkHttp3](https://github.com/square/okhttp)
4). [Butterknife](https://github.com/JakeWharton/butterknife)
5). [Picasso](http://square.github.io/picasso/)
6). [Stetho](http://facebook.github.io/stetho/)
7). [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)