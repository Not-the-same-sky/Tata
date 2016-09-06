package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tata.R;

import java.util.List;

import Bean.Conversation;

/**
 * Created by 不一样的天空 on 2016/9/5.
 */
public class ConversationAdapter extends BaseAdapter{
    private Context context;
    private List<Conversation> conversations;
    public ConversationAdapter(Context context,List<Conversation> conversations){
        this.context=context;
        this.conversations=conversations;
    }
    @Override
    public int getCount() {
        return conversations.size();
    }

    @Override
    public Object getItem(int position) {
        return conversations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Conversation conversation=conversations.get(position);
        final BaseViewHolder baseViewHolder=new BaseViewHolder(context,parent,position, R.layout.conversation_item);
        baseViewHolder.setText(R.id.tv_title,conversation.getTitle());
        baseViewHolder.setText(R.id.tv_lastcontent,conversation.getLastContent());
        return baseViewHolder.getmConvertView();
    }
}
