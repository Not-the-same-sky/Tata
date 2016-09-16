package db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Bean.ChatMessage;

/**
 * Created by 不一样的天空 on 2016/9/6.
 */
public class TuLingMessageDB {
    private static final String TAG = TuLingMessageDB.class.getSimpleName();
    private static final String DBNAME = "TuLingMessage.db";
    private static final String TABLENAME = "TuLingMessage";
    private SQLiteDatabase db;

    public TuLingMessageDB(Context context) {
        db = context.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,
                null);
        db.execSQL("CREATE table IF NOT EXISTS "
                + TABLENAME
                + " (MessageType INTEGER,MessageContent TEXT,Time TEXT)");
    }

    public void saveTuLingMessage(ChatMessage chatMessage) {
        try {
            db.execSQL(
                    "insert into "
                            + TABLENAME
                            + " (MessageType,MessageContent,Time) values(?,?,?)",
                    new Object[]{chatMessage.getMessageType(), chatMessage.getMsg(), chatMessage.getDate()});
            Log.d(TAG, "saveTuLingMessage: 成功");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "saveTuLingMessage: 失败");
        }
    }
    public void deleteTuLingMessage(ChatMessage message){
        try {
            db.delete(TABLENAME,"Time = "+message.getDate(),null);
            Log.d(TAG, "deleteChatMessage: 成功");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "deleteChatMessage: 失败");
        }
    }
    public List<ChatMessage> getTuLingMessage() {
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

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }
}
