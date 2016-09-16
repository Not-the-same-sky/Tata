package com.example.tata;

import android.content.Context;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class IMHandler extends BmobIMMessageHandler {
    private Context context;
    public IMHandler(Context context) {
        this.context = context;
    }
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
    }
    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }
}
