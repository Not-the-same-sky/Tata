package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tata.R;

import java.util.List;

import Bean.MessageType;
import Bean.ChatMessage;

/**
 * Created by 不一样的天空 on 2016/9/7.
 */
public class ChatWithYouAdapter extends BaseAdapter {
    private Context context;
    private  List<ChatMessage> messages;

    public ChatWithYouAdapter(Context context, List<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
    }
   public  void updateUI(ChatMessage chatMessage){
       messages.add(chatMessage);
       notifyDataSetChanged();
   }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = messages.get(position);
        return chatMessage.getMessageType();
    }

    @Override
    public int getViewTypeCount() {
        return MessageType.CHATMESSAGECOUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChatMessage message = messages.get(position);
        int type = getItemViewType(position);
        if (type == MessageType.SEND_TEXT) {
            BaseViewHolder sendTextHolder = new BaseViewHolder(context, parent, position, R.layout.send_text_item);
            sendTextHolder.showTime(R.id.send_date, message.getDate(), shouldShowTime(position));
            sendTextHolder.setText(R.id.send_text, message.getMsg());
            return sendTextHolder.getmConvertView();
        } else if (type == MessageType.RECEIVE_TEXT) {
            BaseViewHolder receiveTextHolder = new BaseViewHolder(context, parent, position, R.layout.receive_text_item);
            receiveTextHolder.showTime(R.id.receive_date, message.getDate(), shouldShowTime(position));
            receiveTextHolder.setText(R.id.receive_text, message.getMsg());
            return receiveTextHolder.getmConvertView();
        }else if (type==MessageType.SEND_IMAGE){
            BaseViewHolder sendImageHolder=new BaseViewHolder(context,parent,position,R.layout.send_image_item);
            sendImageHolder.showTime(R.id.id_to_image_date, message.getDate(), shouldShowTime(position));
            sendImageHolder.setImageByPath(R.id.send_image,message.getMsg());
            return sendImageHolder.getmConvertView();
        }else {
            BaseViewHolder receiveImageHolder=new BaseViewHolder(context,parent,position,R.layout.receive_image_item);
            receiveImageHolder.showTime(R.id.id_form_image_date, message.getDate(), shouldShowTime(position));
            receiveImageHolder.setImageByPath(R.id.receive_image,message.getMsg());
            return  receiveImageHolder.getmConvertView();
        }
    }

    private boolean shouldShowTime(int position) {
        if (position == 0) {
            return true;
        }
        long lastTime = messages.get(position - 1).getDate();
        long curTime = messages.get(position).getDate();
        return curTime - lastTime > 60 * 1000;
    }
}
