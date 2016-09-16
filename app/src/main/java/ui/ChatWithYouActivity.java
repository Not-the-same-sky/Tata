package ui;

import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.tata.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Adapter.ChatWithYouAdapter;
import Bean.ChatMessage;
import Bean.MessageType;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMImageMessage;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;
import db.ChatMessageHelper;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class ChatWithYouActivity extends BaseActivity {
    @Bind(R.id.chat_list)
    ListView listView;
    @Bind(R.id.buttom_image)
    RelativeLayout layout;
    @Bind(R.id.btn_chat_add)
    Button button;
    @Bind(R.id.et_input_msg)
    EditText editText;
    private BmobIMConversation c;
    public static ChatWithYouAdapter chatWithYouAdapter;
    private List<ChatMessage> mDatas;
    public static  ChatMessageHelper dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithyou_main);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//默认键盘不弹出
        Bundle bundle = getIntent().getBundleExtra(getPackageName());
        c = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) bundle.getSerializable("c"));
        dbHelper=new ChatMessageHelper(this);
        mDatas=dbHelper.getChatMessage();
        if (mDatas==null && mDatas.size()<0){
            mDatas = new ArrayList<ChatMessage>();
        }
        chatWithYouAdapter = new ChatWithYouAdapter(this, mDatas);
        listView.setAdapter(chatWithYouAdapter);
        listView.setSelection(mDatas.size()-1);
    }

    @OnClick(R.id.btn_chat_add)
    public void sendMessage() {
        String inputContent = editText.getText().toString();
        editText.setText("");
        ChatMessage chatMessage = new ChatMessage(MessageType.SEND_TEXT, inputContent, System.currentTimeMillis());
        mDatas.add(chatMessage);
        dbHelper.insertChatMessage(chatMessage);
        chatWithYouAdapter.notifyDataSetChanged();
        sendTextMessage(inputContent);
    }
    /**
     * 消息发送监听器
     */
    public MessageSendListener listener =new MessageSendListener() {
        @Override
        public void done(BmobIMMessage msg, BmobException e) {
            if (e != null) {
                toast(e.getMessage());
            }else {
              //  toast("信息发送成功");
            }
        }
    };
    public void sendTextMessage(String message) {
        BmobIMTextMessage bmobIMTextMessage = new BmobIMTextMessage();
        bmobIMTextMessage.setContent(message);
        c.sendMessage(bmobIMTextMessage, listener);
    }
    /**
     * 直接发送远程图片地址
     */
    public void sendRemoteImageMessage(){
        BmobIMImageMessage image =new BmobIMImageMessage();
        image.setRemoteUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473696422&di=3f78c2c4087a9e6907beb880d453c6f0&src=http://g.hiphotos.baidu.com/image/pic/item/241f95cad1c8a7866f726fe06309c93d71cf5087.jpg");
        c.sendMessage(image, listener);
    }
    /**
     * 发送本地图片地址
     */
    public void sendLocalImageMessage(){
        //正常情况下，需要调用系统的图库或拍照功能获取到图片的本地地址，开发者只需要将本地的文件地址传过去就可以发送文件类型的消息
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Camera/123.jpg");
        BmobIMImageMessage bmobIMFileMessage =new BmobIMImageMessage(file.getAbsolutePath());
        c.sendMessage(bmobIMFileMessage,listener);
        ChatMessage chatMessage = new ChatMessage(MessageType.SEND_IMAGE, file.getAbsolutePath(), System.currentTimeMillis());
        mDatas.add(chatMessage);
        chatWithYouAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

}
