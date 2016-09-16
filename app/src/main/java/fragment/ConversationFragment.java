package fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tata.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Adapter.ConversationAdapter;
import Bean.Conversation;
import Bean.TuLingMessage;
import base.BaseFragment;
import butterknife.ButterKnife;
import db.TuLingMessageDB;
import ui.ChatWithTuLingActivity;
import ui.ChatWithYouActivity;
import ui.FutureMsgActivity;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ConversationFragment extends BaseFragment {
    private ListView listView;
    private TuLingMessageDB db;
    //未来信息时间
    private long now;
    private  long future_time;
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
        String lastContent="";
        if (messages!=null && messages.size()>0){
            lastContent=messages.get(messages.size()-1).getMsg();
        }
        List<Conversation> conversations=new ArrayList<Conversation>();
        Conversation tuling=new Conversation();
        tuling.setTitle("ileng");
        tuling.setLastContent(lastContent+"");
        conversations.add(tuling);
        Conversation you=new Conversation();
        you.setTitle("You");
        you.setLastContent("Good night!");
        conversations.add(you);
        //future message
        Conversation future_msg=new Conversation();
        future_msg.setTitle("For you that one year later");
        future_msg.setLastContent("");
        future_msg.setConverPicture(R.mipmap.picture_future);
        conversations.add(future_msg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        String str = "201609101528";
        now=System.currentTimeMillis();
        try {
            future_time=sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //
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
                }else if (position==2)
                {
                    if(now-future_time>=0) {
                        startActivity(FutureMsgActivity.class, null);
                    }
                }
                else {
                    startActivity(ChatWithYouActivity.class,null);
                }
            }
        });
    }
}
