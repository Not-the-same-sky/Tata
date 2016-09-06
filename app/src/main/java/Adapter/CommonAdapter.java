package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import Bean.MessageType;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> messages;
    public CommonAdapter(Context context,List<T> messages){
        this.context=context;
        this.messages=messages;
    }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public T getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          BaseViewHolder baseViewHolder=new BaseViewHolder(context,parent,position,0);
          convert(baseViewHolder,getItem(position));
        return baseViewHolder.getmConvertView();
    }
    public abstract void convert(BaseViewHolder baseViewHolder,T t);
}
