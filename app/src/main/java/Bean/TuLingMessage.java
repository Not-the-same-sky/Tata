package Bean;

import java.util.Date;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class TuLingMessage {
    private int messageType;
    private String msg;
    private long date;
    public TuLingMessage(int messageType,String msg,long date){
        this.messageType=messageType;
        this.msg=msg;
        this.date=date;
    }
    public TuLingMessage(){

    }
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMessageType() {

        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
