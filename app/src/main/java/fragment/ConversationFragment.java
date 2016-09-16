package fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tata.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ConversationAdapter;
import Bean.ChatMessage;
import Bean.Conversation;
import base.BaseFragment;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import db.ChatMessageHelper;
import db.TuLingMessageDB;
import ui.ChatWithTuLingActivity;
import ui.ChatWithYouActivity;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ConversationFragment extends BaseFragment {
    private ListView listView;
    private ConversationAdapter conversationAdapter;
    private List<Conversation> conversations;
    private TuLingMessageDB db;
    private ChatMessageHelper dbHelper;
    private Conversation tuling;
    private Conversation you;

    public static ConversationFragment getInsance() {
        return new ConversationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ConversationFragment", "Come to onCreate: ");
        db = new TuLingMessageDB(getActivity());
        dbHelper=new ChatMessageHelper(getActivity());
        conversations = new ArrayList<>();
        tuling = new Conversation();
        you = new Conversation();
    }

    @Override
    public void onResume() {
        super.onResume();
        ChatMessage tulingMessage = db.getLastTuLingMessage();
        ChatMessage chatMessage=dbHelper.getLastTuLingMessage();
        if (tulingMessage != null) {
            tuling.setLastContent(tulingMessage.getMsg() + "");
            conversationAdapter.notifyDataSetChanged();
        }
        if (chatMessage!=null){
            you.setLastContent(chatMessage.getMsg() + "");
            conversationAdapter.notifyDataSetChanged();
        }
        Log.d("ConversationFragment", "Come to onResume: ");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ConversationFragment", "Come to onCreateView: ");
        View view = inflater.inflate(R.layout.frgment_conversation, container, false);
        ButterKnife.bind(this, view);
        listView = (ListView) view.findViewById(R.id.id_conversation);
        initListener();
        ChatMessage chatMessage = db.getLastTuLingMessage();//获取数据库中最后一条记录
        String lastContent = "";
        if (chatMessage != null) {
            lastContent = chatMessage.getMsg();
        }
        tuling.setTitle("Chat with Tuling");
        tuling.setLastContent(lastContent + "");
        conversations.add(tuling);
        you.setTitle("Chat with you");
        you.setLastContent("Good night!");
        conversations.add(you);
        conversationAdapter = new ConversationAdapter(getActivity(), conversations);
        listView.setAdapter(conversationAdapter);
        return view;
    }

    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(ChatWithTuLingActivity.class, null);
                } else {
                    queryUser();
                }
            }
        });
    }

    public void queryUser() {
        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
        query.addWhereEqualTo("username", "大风");
        query.findObjects(getActivity(), new FindListener<BmobUser>() {
            @Override
            public void onSuccess(List<BmobUser> list) {
                toast("查询成功" + list.get(0).getUsername());
                String userName = list.get(0).getUsername();
                String objectId = list.get(0).getUsername();
                BmobIM.getInstance().startPrivateConversation(new BmobIMUserInfo("59c90b8fab", userName, ""), new ConversationListener() {
                    @Override
                    public void done(BmobIMConversation bmobIMConversation, BmobException e) {
                        if (e == null && bmobIMConversation != null) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("c", bmobIMConversation);
                            bundle.putString("key", "欢迎来到聊天");
                            startActivity(ChatWithYouActivity.class, bundle);
                        } else {
                            toast(e.getMessage()+"");
                        }
                    }
                });
            }
            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
