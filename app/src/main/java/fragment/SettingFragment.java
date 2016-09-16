package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tata.R;

import base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ui.SetAvatarActivity;
import ui.SetBackgroundActivity;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class SettingFragment extends BaseFragment {
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_set_bg)
    public void setBackgroundImage() {
        startActivity(SetBackgroundActivity.class, null);
    }

    @OnClick(R.id.tv_set_avatar)
    public void setAvatar() {
        startActivity(SetAvatarActivity.class, null);
    }
}
