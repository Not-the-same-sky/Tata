package ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tata.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
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
//    @Bind(R.id.btn_timeline)
//    Button btn_timeline;
    private ConversationFragment conversationFragment;
    private SettingFragment setFragment;
    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        mTabs = new Button[2];
        mTabs[0] = btn_conversation;
        mTabs[1] = btn_set;
        //mTabs[2]=btn_timeline;
        mTabs[0].setSelected(true);
        initTab();
    }

    private void initTab() {
        conversationFragment = new ConversationFragment();
        setFragment = new SettingFragment();
        fragments = new Fragment[]{conversationFragment, setFragment};
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationFragment)
                .add(R.id.fragment_container, setFragment)
                .hide(setFragment).show(conversationFragment).commit();
    }

    public void onTabSelect(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_set:
                index = 1;
//            case R.id.btn_timeline:
//                index=2;
                break;
        }
        onTabIndex(index);
    }

    private void onTabIndex(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getFragmentManager().beginTransaction();
            trx.remove(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

}
