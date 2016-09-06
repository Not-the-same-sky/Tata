package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.tata.R;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class SetBackgroundActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setbackground_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_choose_bgpic)
     public void displayBgPic(){
        Intent intent=new Intent(this,ChooseBgPicActivity.class);
        startActivity(intent);
    }
}
