package ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tata.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ChatWithTuLingAdapter;
import Bean.ChatMessage;
import Bean.MessageType;
import Utils.FileUtils;
import Utils.GetReponseFromTuling;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import db.TuLingMessageDB;


public class ChatWithTuLingActivity extends BaseActivity {
    @Bind(R.id.id_listview)
    public ListView listView;

    @Bind(R.id.id_input_msg)
    public EditText mInputMsg;

    @Bind(R.id.id_send_msg)
    public Button mSendMsg;
    private ChatWithTuLingAdapter mAdapter;
    private List<ChatMessage> mDatas;
    public static TuLingMessageDB db;
    private static final int RESPONSE_OK = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RESPONSE_OK:
                    // 等待接收，子线程完成返回的frommessage
                    ChatMessage fromMessge = (ChatMessage) msg.obj;
                    mDatas.add(fromMessge);
                    //更新适配器
                    mAdapter.notifyDataSetChanged();
                    listView.setSelection(mDatas.size() - 1);
                    break;
                default:
                    break;
            }

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView((R.layout.chatwithtuling_main));
        ButterKnife.bind(this);
        Drawable drawable = FileUtils.getBgDrawableFromFile(this);
        if (drawable != null) {
            getWindow().setBackgroundDrawable(drawable);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//默认键盘不弹出
        db = new TuLingMessageDB(this);
        mDatas = db.getTuLingMessage();
        if (mDatas == null) {
            mDatas = new ArrayList<ChatMessage>();
        }
        mAdapter = new ChatWithTuLingAdapter(this, mDatas);
        listView.setAdapter(mAdapter);
        listView.setSelection(mDatas.size() - 1);
    }

    @OnClick(R.id.id_send_msg)
    public void sendMessage() {
        final String sendContent = mInputMsg.getText().toString();
        if (TextUtils.isEmpty(sendContent)) {
            toast("不能发送空消息");
            return;
        }
        ChatMessage sendMessage = new ChatMessage();
        sendMessage.setDate(System.currentTimeMillis());
        sendMessage.setMessageType(MessageType.SEND_TEXT);
        sendMessage.setMsg(sendContent);
        db.saveTuLingMessage(sendMessage);
        mDatas.add(sendMessage);
        mAdapter.notifyDataSetChanged();
        listView.setSelection(mDatas.size() - 1);
        mInputMsg.setText("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatMessage receiveMessage = GetReponseFromTuling.sendMessage(sendContent);
                db.saveTuLingMessage(receiveMessage);
                Message message = Message.obtain();
                message.what = RESPONSE_OK;
                message.obj = receiveMessage;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
        db=null;
    }
}
