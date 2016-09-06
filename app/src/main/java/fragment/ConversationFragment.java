package fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tata.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ConversationAdapter;
import Bean.Conversation;
import Bean.TuLingMessage;
import Utils.ImageUtils;
import Utils.FileUtils;
import base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import db.TuLingMessageDB;
import ui.ChatWithTuLingActivity;
import ui.ChatWithYouActivity;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ConversationFragment extends BaseFragment {
    private ListView listView;
    private TuLingMessageDB db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ConversationFragment", "Come to onCreate: ");
        db=new TuLingMessageDB(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ConversationFragment", "Come to onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ConversationFragment", "Come to onDestroy: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ConversationFragment", "Come to onStart: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ConversationFragment", "Come to onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ConversationFragment", "Come to onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ConversationFragment", "Come to onDestroyView: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ConversationFragment", "Come to onDetach: ");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ConversationFragment", "Come to onCreateView: ");
        View view=inflater.inflate(R.layout.frgment_conversation, container, false);
        ButterKnife.bind(this,view);
        listView= (ListView) view.findViewById(R.id.id_conversation);
        initListener();
        List<TuLingMessage> messages=db.getTuLingMessage();
        String lastContent=null;
        if (messages!=null){
            lastContent=messages.get(messages.size()-1).getMsg();
        }
        List<Conversation> conversations=new ArrayList<Conversation>();
        Conversation tuling=new Conversation();
        tuling.setTitle("Chat with Tuling");
        tuling.setLastContent(lastContent+"");
        conversations.add(tuling);
        Conversation you=new Conversation();
        you.setTitle("Chat with you");
        you.setLastContent("Good night!");
        conversations.add(you);
        ConversationAdapter conversationAdapter=new ConversationAdapter(getActivity(),conversations);
        listView.setAdapter(conversationAdapter);
        return view;
    }
    public void initListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(ChatWithTuLingActivity.class, null);
                }else {
                    startActivity(ChatWithYouActivity.class,null);
                }
            }
        });
    }
}
