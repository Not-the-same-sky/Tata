package ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.example.tata.R;
import Utils.TimeUtil;
import base.BaseActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 不一样的天空 on 2016/9/1.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        userLogin();
        ImageView imageView= (ImageView) findViewById(R.id.iv_splash);
        if (TimeUtil.getToday().equals("16年09月15日")){
            imageView.setBackgroundResource(R.drawable.splash);
            toast("中秋让明月来寄托对远方的家的思念!");
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String user = null;
                if (user == null) {
                    startActivity(MainActivity.class, null, true);
                } else {
                    startActivity(ChatWithYouActivity.class, null, true);
                }
            }
        },3000);
    }

    public void userLogin() {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername("FYT");
        bmobUser.setPassword("123");
        bmobUser.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
               // toast("登录成功");
            }
            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}
