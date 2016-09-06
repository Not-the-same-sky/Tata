package com.example.tata;

import android.app.Application;

import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class BmobIMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BmobIM.init(this);   //NewIM初始化
        BmobIM.registerDefaultMessageHandler(new IMHandler(this)); //注册消息接收器
        BmobConfig config=new BmobConfig.Builder(this)
                .setApplicationId("bb074d4b83f7bae9c3ab4a406a8c3708")
                .build();
        Bmob.initialize(config);
    }
}
