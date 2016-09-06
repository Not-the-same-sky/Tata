package db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Bean.TuLingMessage;

/**
 * Created by 不一样的天空 on 2016/9/6.
 */
public class TuLingMessageDB {
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
    public void saveTuLingMessage(TuLingMessage tuLingMessage){
        db.execSQL(
                "insert into "
                        + TABLENAME
                        + " (MessageType,MessageContent,Time) values(?,?,?)",
                new Object[] {tuLingMessage.getMessageType(),tuLingMessage.getMsg(),tuLingMessage.getDate()});
    }
    public List<TuLingMessage> getTuLingMessage(){
        List<TuLingMessage> list = new ArrayList<TuLingMessage>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLENAME,null);
        while (cursor.moveToNext()){
            int MessageType=cursor.getInt(cursor.getColumnIndex("MessageType"));
            String MessageContent=cursor.getString(cursor.getColumnIndex("MessageContent"));
            long Time=cursor.getLong(cursor.getColumnIndex("Time"));
            TuLingMessage tuLingMessage=new TuLingMessage(MessageType,MessageContent,Time);
            list.add(tuLingMessage);
        }
        cursor.close();
        return list;
    }
    public TuLingMessage getLastTuLingMessage(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLENAME+" where Time=(SELECT max(Time) FROM "+TABLENAME+")",null);
            int MessageType=cursor.getInt(cursor.getColumnIndex("MessageType"));
            String MessageContent=cursor.getString(cursor.getColumnIndex("MessageContent"));
            long Time=cursor.getLong(cursor.getColumnIndex("Time"));
            TuLingMessage tuLingMessage=new TuLingMessage(MessageType,MessageContent,Time);
            return tuLingMessage;
    }
}
