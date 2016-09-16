package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Bean.ChatMessage;

/**
 * Created by 不一样的天空 on 2016/9/14.
 */
public class ChatMessageHelper extends SQLiteOpenHelper{
    private static final String TAG = ChatMessageHelper.class.getSimpleName();
    private final String TABLENAME="ChatMessage";
    public ChatMessageHelper(Context context){
        super(context,"ChatMessage.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE IF NOT EXISTS "
                + TABLENAME
                + " (MessageType INTEGER,MessageContent TEXT,Time TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insertChatMessage(ChatMessage chatMessage){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MessageType", chatMessage.getMessageType());
        contentValues.put("MessageContent", chatMessage.getMsg());
        contentValues.put("Time", chatMessage.getDate());
        try {
            db.insert(TABLENAME,null,contentValues);
            Log.d(TAG, "insertChatMessage: 成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "insertChatMessage: 失败");
        }
    }
    public void deleteChatMessage(ChatMessage message){
        SQLiteDatabase db=getWritableDatabase();
        try {
            db.delete(TABLENAME,"WHERE Time= "+message.getDate(),null);
            Log.d(TAG, "deleteChatMessage: 成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "deleteChatMessage: 失败");
        }
    }
    public List<ChatMessage> getChatMessage() {
        SQLiteDatabase db=getWritableDatabase();
        List<ChatMessage> list = new ArrayList<ChatMessage>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        while (cursor.moveToNext()) {
            int MessageType = cursor.getInt(cursor.getColumnIndex("MessageType"));
            String MessageContent = cursor.getString(cursor.getColumnIndex("MessageContent"));
            long Time = cursor.getLong(cursor.getColumnIndex("Time"));
            ChatMessage chatMessage = new ChatMessage(MessageType, MessageContent, Time);
            list.add(chatMessage);
        }
        cursor.close();
        return list;
    }
    public ChatMessage getLastTuLingMessage() {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        while (cursor.moveToPosition(cursor.getCount() - 1)) {
            int MessageType = cursor.getInt(cursor.getColumnIndex("MessageType"));
            String MessageContent = cursor.getString(cursor.getColumnIndex("MessageContent"));
            long Time = cursor.getLong(cursor.getColumnIndex("Time"));
            ChatMessage chatMessage = new ChatMessage(MessageType, MessageContent, Time);
            return chatMessage;
        }
        return null;
    }
}
