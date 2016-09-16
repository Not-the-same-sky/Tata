package com.example.tata;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import Bean.ChatMessage;
import Bean.MessageType;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import cn.bmob.newim.notification.BmobNotificationManager;
import ui.ChatWithYouActivity;
import ui.MainActivity;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class IMHandler extends BmobIMMessageHandler {
    private Context context;
    private NotificationCompat.Builder builder;
    private NotificationManager n_manager;

    public IMHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
        BmobIMMessage msg = messageEvent.getMessage();
        BmobIMUserInfo info =messageEvent.getFromUserInfo();
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.icon)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentTitle(info.getName())
                    .setContentText(msg.getContent())
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true);
            ChatMessage chatMessage=null;
            if (msg.getMsgType().equals("text")){
                chatMessage=new ChatMessage(MessageType.RECEIVE_TEXT,msg.getContent(),System.currentTimeMillis());
            }else if (msg.getMsgType().equals("image")){
                chatMessage=new ChatMessage(MessageType.RECEIVE_IMAGE,msg.getContent(),System.currentTimeMillis());
            }
            ChatWithYouActivity.chatWithYouAdapter.updateUI(chatMessage);
            ChatWithYouActivity.dbHelper.insertChatMessage(chatMessage);
            n_manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            n_manager.notify(1, builder.build());
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }
}
