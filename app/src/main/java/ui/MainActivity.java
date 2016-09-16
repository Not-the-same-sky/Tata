package ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tata.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import fragment.ConversationFragment;
import fragment.SettingFragment;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_conversation)
    Button btn_conversation;
    @Bind(R.id.btn_set)
    Button btn_set;
    private ConversationFragment conversationFragment;
    private SettingFragment setFragment;
    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    private static FragmentTransaction transaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState!=null){
            toast("复用Fragment");
            conversationFragment=(ConversationFragment) getFragmentManager().findFragmentByTag("conversation");
            setFragment=(SettingFragment) getFragmentManager().findFragmentByTag("setting");
        }else {
            conversationFragment = new ConversationFragment();
            setFragment = new SettingFragment();
        }
        transaction = getFragmentManager().beginTransaction();
        initView();
        initTab();
        connectToServer();
    }

    protected void initView() {
        mTabs = new Button[2];
        fragments = new Fragment[]{conversationFragment, setFragment};
        mTabs[0] = btn_conversation;
        mTabs[1] = btn_set;
        mTabs[0].setSelected(true);
    }

    private void initTab() {
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationFragment,"conversation")
                .add(R.id.fragment_container, setFragment,"setting")
                .hide(setFragment).show(conversationFragment).commit();
    }

    public void onTabSelect(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_set:
                index = 1;
                break;
        }
        onTabIndex(index);
    }

    private void onTabIndex(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getFragmentManager().beginTransaction();
            if (transaction == null) {
                Log.d("onTabIndex", "onTabIndex: transaction is null");
            }
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
    public void connectToServer() {
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        BmobIM.connect(bmobUser.getObjectId(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                  // toast("连接服务器成功" + s);
                } else {
                    toast("连接服务器失败" + s);
                }
            }
        });
    }
}
