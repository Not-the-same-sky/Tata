package Adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.tata.R;

import java.util.List;

import Bean.MessageType;
import Bean.TuLingMessage;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class TuLingMessageAdapter extends BaseAdapter{
    private Context context;
    private List<TuLingMessage> messages;
    public TuLingMessageAdapter(Context context,List<TuLingMessage> messages){
        this.context=context;
        this.messages=messages;
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
        TuLingMessage tuLingMessage=messages.get(position);
        return tuLingMessage.getMessageType();
    }

    @Override
    public int getViewTypeCount() {
        return MessageType.TULINGMESSAGECOUNT;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final TuLingMessage message=messages.get(position);
        int type=getItemViewType(position);
            if(type==MessageType.SEND_TEXT){
                BaseViewHolder sendTextHolder=new BaseViewHolder(context,parent,position, R.layout.send_text_item);
                sendTextHolder.setText(R.id.send_text,message.getMsg());
                sendTextHolder.getView(R.id.send_text).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setTitle("");
                        final String[] items = {"复制", "删除"};
                        build.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clipData = ClipData.newPlainText("copyContent", message.getMsg());
                                    clipboardManager.setPrimaryClip(clipData);
                                    Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    messages.remove(position);
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.show();
                        return false;
                    }
                });
                sendTextHolder.getView(R.id.send_text).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }
                });
                sendTextHolder.getView(R.id.send_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"您点击了"+message.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                });
                return sendTextHolder.getmConvertView();
            }else if (type==MessageType.RECEIVE_TEXT){
                BaseViewHolder receiveTextHolder=new BaseViewHolder(context,parent,position,R.layout.receive_text_item);
                receiveTextHolder.setText(R.id.receive_text,message.getMsg());
                receiveTextHolder.getView(R.id.receive_text).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setTitle("");
                        final String[] items = {"复制", "删除"};
                        build.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    ClipboardManager clipboardManager= (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clipData=ClipData.newPlainText("copyContent", message.getMsg());
                                    clipboardManager.setPrimaryClip(clipData);
                                    Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                                } else {
                                    messages.remove(position);
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.show();
                        return false;
                    }
                });
                receiveTextHolder.getView(R.id.receive_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "您点击了" + message.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                return receiveTextHolder.getmConvertView();
            }

     return null;
    }
}
